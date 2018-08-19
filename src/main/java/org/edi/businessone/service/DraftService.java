package org.edi.businessone.service;

import com.sap.smb.sbo.api.ICompany;
import com.sap.smb.sbo.api.IDocuments;
import com.sap.smb.sbo.api.SBOCOMUtil;
import com.xxl.job.core.log.XxlJobLogger;
import org.edi.businessone.data.B1OpResultCode;
import org.edi.businessone.data.B1OpResultDescription;
import org.edi.businessone.data.DocumentType;
import org.edi.businessone.data.SBOClassData;
import org.edi.businessone.db.B1Exception;
import org.edi.businessone.db.CompanyManager;
import org.edi.businessone.db.IB1Connection;
import org.edi.businessone.repository.BORepositoryBusinessOne;
import org.edi.freamwork.data.operation.IOpResult;
import org.edi.freamwork.data.operation.OpResult;
import org.edi.freamwork.exception.BusinessException;
import org.edi.stocktask.bo.stockreport.IStockReport;

/**
 * 草稿单据服务
 */
public class DraftService implements IStockDocumentService {

    private CompanyManager companyManager = new CompanyManager();

    @Override
    public IOpResult createDocuments(IStockReport order) {
        IOpResult opRst = new OpResult();
        BORepositoryBusinessOne boRepositoryBusinessOne = null;
        ICompany company = null;
        try
        {
            if(null == order) {
                throw new B1Exception(B1OpResultDescription.SBO_ORDER_IS_EMPTY);
            }
            XxlJobLogger.log(String.format(B1OpResultDescription.SBO_DRAFT_CREATE_ORDER,order.getDocEntry()));
            //获取B1连接
            IB1Connection dbConnection  = companyManager.getB1ConnInstance(order.getCompanyName());
            boRepositoryBusinessOne = BORepositoryBusinessOne.getInstance(dbConnection);
            company = boRepositoryBusinessOne.getCompany();
            IDocuments document = SBOCOMUtil.newDocuments(company, DocumentType.DRAFT);
            document.setDocObjectCode(getBusinessObject(order.getBaseDocumentType()));
            if(document.getByKey(order.getBaseDocumentEntry())){
                document.getUserFields().getFields().item(SBOClassData.SBO_WM_DOCENTRY).setValue(order.getDocEntry());
                if(document.update()== 0){
                    int rt = document.saveDraftToDocument();
                    opRst.setCode(String.valueOf(rt));
                    opRst.setMessage(company.getLastErrorCode() + ":"
                            + company.getLastErrorDescription());
                    if(rt == 0) {
                        opRst.setThirdId(company.getNewObjectKey());
                    }
                }else {
                    opRst.setCode("-1");
                    opRst.setMessage(String.format(B1OpResultDescription.SBO_DRAFT_UPDATE_FAILED,order.getDocEntry()));
                }

            }else {
                opRst.setCode("-1");
                opRst.setMessage(String.format(B1OpResultDescription.SBO_CAN_NOT_FIND_DRAFT,order.getBaseDocumentEntry()));
            }
        }catch (Exception e){
            XxlJobLogger.log(e);
            opRst.setCode(B1OpResultCode.EXCEPTION_CODE);
            opRst.setMessage(e.getMessage());
        }
        finally {
            if(company != null){
                company.disconnect();
            }
        }
        return opRst;
    }

    private Integer getBusinessObject(String objectType){
        if(objectType == null || objectType.isEmpty()){
            throw new BusinessException(B1OpResultDescription.SBO_ORDER_BASE_TYPE_FORMAT_ERROR);
        }
        String[] types = objectType.split("-");
        if(types.length<=1 || types[1].isEmpty()) {
            throw new BusinessException(B1OpResultDescription.SBO_ORDER_BASE_TYPE_FORMAT_ERROR);
        }
        switch (types[1]){
            case "15":return DocumentType.SALES_DELIVERY;
            case "16":return DocumentType.SALES_RETURN;
            case "20":return DocumentType.PURCHASE_DELIVERY;
            case "21":return DocumentType.PURCHASE_RETURN;
            case "59":return DocumentType.GOODS_RECEIPTS;
            case "60":return DocumentType.GOODS_ISSUES;
            case "67":return DocumentType.STOCK_TRANSFER;
            default:throw new BusinessException(B1OpResultDescription.SBO_ORDER_BASE_TYPE_IS_EMPTY_OR_INVALID);
        }
    }


}

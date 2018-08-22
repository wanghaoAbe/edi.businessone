package org.edi.businessone.service;

import com.sap.smb.sbo.api.Company;
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
import org.edi.freamwork.data.DateConvert;
import org.edi.freamwork.data.operation.IOpResult;
import org.edi.freamwork.data.operation.OpResult;
import org.edi.stocktask.bo.stockreport.IStockReport;
import org.edi.stocktask.bo.stockreport.IStockReportItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Date;

/**
 * @author Fancy
 * @date 2018/7/30
 */
public class ProduceOrderService implements IStockDocumentService{
    Logger logger = LoggerFactory.getLogger(ProduceOrderService.class);
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
            //获取B1连接
            IB1Connection dbConnection  = companyManager.getB1ConnInstance(order.getCompanyName());
            company = BORepositoryBusinessOne.getInstance(dbConnection).getCompany();
            XxlJobLogger.log(String.valueOf(company.hashCode()));
            boRepositoryBusinessOne = BORepositoryBusinessOne.getInstance(dbConnection);
            company = boRepositoryBusinessOne.getCompany();
            IDocuments document = SBOCOMUtil.newDocuments(company,DocumentType.PRODUCE_ORDER);

            document.setCardCode(order.getBusinessPartnerCode());
            document.setDocDate(DateConvert.toDate(order.getDocumentDate()) );
            document.setTaxDate(DateConvert.toDate(order.getDeliveryDate()));
            document.setVatDate(DateConvert.toDate(order.getPostingDate()));
            document.setComments(order.getRemarks());
            document.getUserFields().getFields().item(SBOClassData.SBO_WM_DOCENTRY).setValue(order.getDocEntry());
            for (IStockReportItem item:order.getStockReportItems()) {
                document.getLines().setItemCode(item.getItemCode());
                document.getLines().setItemDescription(item.getItemDescription());
                document.getLines().setQuantity(item.getQuantity());
                document.getLines().setPrice(item.getPrice());
                document.getLines().setWarehouseCode(item.getToWarehouse());
                if(String.valueOf(DocumentType.PRODUCE_ORDER).equals(order.getBaseDocumentType())){
                    document.getLines().setBaseType(DocumentType.PRODUCE_ORDER);
                    document.getLines().setBaseEntry(item.getBaseDocumentEntry());
                    document.getLines().setBaseLine(item.getBaseDocumentLineId());
                }
                document.getLines().add();
            }
            int rt = document.add();
            opRst.setCode(String.valueOf(rt));
            opRst.setMessage(company.getLastErrorCode() + ":"
                    + company.getLastErrorDescription());
            if(rt == 0) {
                opRst.setThirdId(company.getNewObjectKey());
            }
        }catch (Exception e){
            logger.info(B1OpResultDescription.SBO_DOCUMENT_CREATE_RETURN_EXCEPTION,e);
            opRst.setCode(B1OpResultCode.EXCEPTION_CODE);
            opRst.setMessage(e.getMessage());
        }
        finally {
            if(company != null){
                company.disconnect();
                company.release();
            }
        }
        return opRst;
    }
}

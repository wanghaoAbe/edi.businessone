package org.edi.businessone.service;

import com.sap.smb.sbo.api.ICompany;
import com.sap.smb.sbo.api.IStockTransfer;
import com.sap.smb.sbo.api.SBOCOMConstants;
import com.sap.smb.sbo.api.SBOCOMUtil;
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

public class StockTransferRequestService implements IStockDocumentService {

    Logger logger = LoggerFactory.getLogger(StockTransferRequestService.class);
    private CompanyManager companyManager = new CompanyManager();

    /**
     * 生成库存转储单
     * @param order 库存转储
     * @return 操作结果
     */
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
            boRepositoryBusinessOne = BORepositoryBusinessOne.getInstance(dbConnection);
            company = boRepositoryBusinessOne.getCompany();
            IStockTransfer document = SBOCOMUtil.newStockTransfer(company,DocumentType.getBusinessObject(order.getTargetDocumentType()));

            document.setDocDate(DateConvert.toDate(order.getDocumentDate()) );
            document.setTaxDate(DateConvert.toDate(order.getDeliveryDate()));
            document.setDueDate(DateConvert.toDate(order.getPostingDate()));
            document.setComments(order.getRemarks());
            document.getUserFields().getFields().item(SBOClassData.SBO_WM_DOCENTRY).setValue(order.getDocEntry());
            for (IStockReportItem item:order.getStockReportItems()) {
                document.getLines().setItemCode(item.getItemCode());
                document.getLines().setItemDescription(item.getItemDescription());
                document.getLines().setQuantity(item.getQuantity());
                document.getLines().setPrice(item.getPrice());
                document.getLines().setWarehouseCode(item.getToWarehouse());
                document.getLines().setFromWarehouseCode(item.getFromWarehose());
                if(String.valueOf(DocumentType.STOCK_TRANSFER_REQUEST).equals(order.getBaseDocumentType())){
                    //document.getLines().setBaseType(DocumentType.STOCK_TRANSFER_REQUEST);
                    document.getLines().setBaseType(SBOCOMConstants.BoObjectTypes_StockTransfer_oInventoryTransferRequest);
                    document.getLines().setBaseEntry(item.getBaseDocumentEntry());
                    document.getLines().setBaseLine(item.getBaseDocumentLineId());
                }
                document.getLines().add();
            }
            int rt = document.add();
            opRst.setCode(String.valueOf(rt));
            if(rt == 0) {
                opRst.setMessage(B1OpResultDescription.SBO_ORDER_CREATE_SUCCESS);
                opRst.setThirdId(company.getNewObjectKey());
            }else {
                opRst.setMessage(company.getLastErrorCode() + ":"
                        + company.getLastErrorDescription());
            }
        }catch (Exception e){
            logger.info(B1OpResultDescription.SBO_DOCUMENT_CREATE_RETURN_EXCEPTION,e);
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
}

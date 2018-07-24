package org.edi.businessone.service;

import com.sap.smb.sbo.api.ICompany;
import com.sap.smb.sbo.api.IStockTransfer;
import com.sap.smb.sbo.api.SBOCOMUtil;
import org.edi.businessone.data.B1ErrorCode;
import org.edi.businessone.data.B1ErrorDescription;
import org.edi.businessone.data.DocumentType;
import org.edi.businessone.db.B1Exception;
import org.edi.businessone.db.CompanyManager;
import org.edi.businessone.db.IB1Connection;
import org.edi.businessone.repository.BORepositoryBusinessOne;
import org.edi.freamwork.data.operation.IOpResult;
import org.edi.freamwork.data.operation.OpResult;
import org.edi.stocktask.bo.stockreport.IStockReport;
import org.edi.stocktask.bo.stockreport.IStockReportItem;
import org.springframework.beans.factory.annotation.Autowired;

public class StockTransferService implements IStockDocumentService {

    @Autowired
    private CompanyManager companyManager;

    /**
     * 生成库存转储单
     * @param order 库存转储
     * @return 操作结果
     */
    @Override
    public IOpResult createDocuments(IStockReport order) {
        IOpResult opRst = new OpResult();
        try
        {
            if(null == order) {
                throw new B1Exception(B1ErrorDescription.SBO_ORDER_IS_EMPTY);
            }
            //获取B1连接
            IB1Connection dbConnection  = companyManager.getB1ConnInstance(order.getCompanyName());
            ICompany company = BORepositoryBusinessOne.getInstance(dbConnection).getCompany();
            IStockTransfer document = SBOCOMUtil.newStockTransfer(company,DocumentType.STOCK_TRANSFER);

            document.setDocDate(order.getDocumentDate());
            document.setTaxDate(order.getDeliveryDate());
            document.setDueDate(order.getPostingDate());
            document.setComments(order.getRemarks());

            for (IStockReportItem item:order.getStockReportItems()) {
                document.getLines().add();
                document.getLines().setItemCode(item.getItemCode());
                document.getLines().setItemDescription(item.getItemDescription());
                document.getLines().setQuantity(item.getQuantity());
                document.getLines().setPrice(item.getPrice());
                document.getLines().setWarehouseCode(item.getToWarehouse());
                document.getLines().setFromWarehouseCode(item.getFromWarehose());
                if(String.valueOf(DocumentType.PRODUCE_ORDER).equals(order.getBaseDocumentType())){
                    document.getLines().setBaseType(DocumentType.PRODUCE_ORDER);
                    document.getLines().setBaseEntry(item.getBaseDocumentEntry());
                    document.getLines().setBaseLine(item.getBaseDocumentLineId());
                }
            }
            int rt = document.add();
            opRst.setCode(rt);
            opRst.setMessage(company.getLastErrorCode() + ":"
                    + company.getLastErrorDescription());
            if(rt == 0) {
                opRst.setThirdId(company.getNewObjectKey());
            }
            company.disconnect();
        }catch (Exception e){
            opRst.setCode(B1ErrorCode.EXCEPTION_CODE);
            opRst.setMessage(e.getMessage());
        }
        return opRst;
    }
}

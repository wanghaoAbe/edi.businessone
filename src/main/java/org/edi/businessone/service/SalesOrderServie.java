package org.edi.businessone.service;

import com.sap.smb.sbo.api.ICompany;
import com.sap.smb.sbo.api.IDocuments;
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
import org.edi.freamwork.data.EnumConstData;
import org.edi.freamwork.data.operation.IOpResult;
import org.edi.freamwork.data.operation.OpResult;
import org.edi.stocktask.bo.stockreport.IStockReport;
import org.edi.stocktask.bo.stockreport.IStockReportItem;
import org.edi.stocktask.bo.stockreport.StockReportMaterialItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SalesOrderServie implements IStockDocumentService {

    Logger logger = LoggerFactory.getLogger(SalesOrderServie.class);
    private CompanyManager companyManager = new CompanyManager();


    /**
     * 生成销售交货单
     * @param order 销售交货
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
            IDocuments document = SBOCOMUtil.newDocuments(company,DocumentType.getBusinessObject(order.getTargetDocumentType()));

            document.setCardCode(order.getBusinessPartnerCode());
            document.setDocDate(DateConvert.toDate(order.getDocumentDate()) );
            document.setTaxDate(DateConvert.toDate(order.getDeliveryDate()));
            document.setVatDate(DateConvert.toDate(order.getPostingDate()));
            document.setComments(order.getRemarks());
            document.getUserFields().getFields().item(SBOClassData.SBO_WM_DOCENTRY).setValue(order.getDocEntry());
            for (IStockReportItem item:order.getStockReportItems()) {
                document.getLines().setItemCode(item.getItemCode());
                document.getLines().setQuantity(item.getQuantity());
                document.getLines().setPrice(item.getPrice());
                document.getLines().setWarehouseCode(item.getToWarehouse());
                if(String.valueOf(DocumentType.SALES_ORDER).equals(order.getBaseDocumentType())){
                    document.getLines().setBaseType(DocumentType.SALES_ORDER);
                    document.getLines().setBaseEntry(item.getBaseDocumentEntry());
                    document.getLines().setBaseLine(item.getBaseDocumentLineId());
                }
                //批次管理
                if(EnumConstData.YES.equals(item.getBatchNumberManagement())){
                    for (StockReportMaterialItem materialItem:item.getStockReportMaterialItems()) {
                        document.getLines().getBatchNumbers().add();
                        document.getLines().getBatchNumbers().setBatchNumber(materialItem.getBatchNumber());
                        document.getLines().getBatchNumbers().setQuantity(materialItem.getQuantity());
                    }
                }
                //序列管理
                if(EnumConstData.YES.equals(item.getSerialNumberManagement())){
                    for (StockReportMaterialItem materialItem:item.getStockReportMaterialItems()) {
                        document.getLines().getSerialNumbers().add();
                        document.getLines().getSerialNumbers().setInternalSerialNumber(materialItem.getSerialNumber());
                        document.getLines().getSerialNumbers().setQuantity(materialItem.getQuantity());
                    }
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
            logger.error(B1OpResultDescription.SBO_DOCUMENT_CREATE_RETURN_EXCEPTION,e);
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

package org.edi.businessone.service;

import com.sap.smb.sbo.api.ICompany;
import com.sap.smb.sbo.api.IDocuments;
import com.sap.smb.sbo.api.SBOCOMUtil;
import com.xxl.job.core.log.XxlJobLogger;
import org.edi.businessone.data.B1OpResultCode;
import org.edi.businessone.data.B1OpResultDescription;
import org.edi.businessone.data.DocumentType;
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
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;

public class PurchaseDeliveryService implements IStockDocumentService {

    private CompanyManager companyManager = new CompanyManager();


    /**
     * 生成采购收货单
     * @param order 采购收货
     * @return 操作结果
     */
    @Override
    public IOpResult createDocuments(IStockReport order) {
        IOpResult opRst = new OpResult();
        try
        {
            if(null == order) {
                throw new B1Exception(B1OpResultDescription.SBO_ORDER_IS_EMPTY);
            }
            XxlJobLogger.log(order.toString());
            //获取B1连接
            XxlJobLogger.log("获取B1连接信息");
            IB1Connection dbConnection  = companyManager.getB1ConnInstance(order.getCompanyName());
            XxlJobLogger.log("获取B1连接对象");
            ICompany company = BORepositoryBusinessOne.getInstance(dbConnection).getCompany();

            IDocuments document = SBOCOMUtil.newDocuments(company,DocumentType.PURCHASE_DELIVERY);
            XxlJobLogger.log("获取单据对象");
            document.setCardCode(order.getBusinessPartnerCode());
            XxlJobLogger.log(order.getDocumentDate());
            XxlJobLogger.log(DateConvert.toDate(order.getDocumentDate()).toString());
            document.setDocDate(DateConvert.toDate(order.getDocumentDate()) );
            document.setTaxDate(DateConvert.toDate(order.getDeliveryDate()));
            document.setVatDate(DateConvert.toDate(order.getPostingDate()));
            XxlJobLogger.log(order.getPostingDate());
            document.setComments(order.getRemarks());
            XxlJobLogger.log(order.getRemarks());
            XxlJobLogger.log("表头赋值完成");
            for (IStockReportItem item:order.getStockReportItems()) {
                document.getLines().add();
                document.getLines().setItemCode(item.getItemCode());
                document.getLines().setItemDescription(item.getItemDescription());
                document.getLines().setQuantity(item.getQuantity());
                document.getLines().setPrice(item.getPrice());
                document.getLines().setWarehouseCode(item.getToWarehouse());
                if(String.valueOf(DocumentType.PURCHASE_ORDER).equals(order.getBaseDocumentType())){
                    document.getLines().setBaseType(DocumentType.PURCHASE_ORDER);
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
                XxlJobLogger.log("表明细赋值完成");
            }
            int rt = document.add();
            opRst.setCode(rt);
            opRst.setMessage(company.getLastErrorCode() + ":"
                    + company.getLastErrorDescription());
            if(rt == 0) {
                opRst.setThirdId(company.getNewObjectKey());
            }else {
                XxlJobLogger.log("生成失败，"+company.getLastErrorDescription());
            }
            //company.disconnect();
        }catch (Exception e){
            e.printStackTrace();
            opRst.setCode(B1OpResultCode.EXCEPTION_CODE);
            opRst.setMessage(e.getMessage() +"|"+ e.getCause());
        }
        return opRst;
    }
}

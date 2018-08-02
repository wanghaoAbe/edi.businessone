package org.edi.businessone.service;

import com.sap.smb.sbo.api.ICompany;
import com.sap.smb.sbo.api.IDocuments;
import com.sap.smb.sbo.api.IStockTransfer;
import com.sap.smb.sbo.api.SBOCOMUtil;
import org.edi.businessone.data.B1OpResultCode;
import org.edi.businessone.data.B1OpResultDescription;
import org.edi.businessone.data.DocumentType;
import org.edi.businessone.db.B1Exception;
import org.edi.businessone.db.CompanyManager;
import org.edi.businessone.db.IB1Connection;
import org.edi.businessone.repository.BORepositoryBusinessOne;
import org.edi.freamwork.data.operation.IOpResult;
import org.edi.freamwork.data.operation.OpResult;
import org.edi.freamwork.exception.BusinessException;
import org.edi.stocktask.bo.stockreport.IStockReport;
import org.edi.stocktask.bo.stockreport.IStockReportItem;

import java.sql.Date;

/**
 * 草稿单据服务
 */
public class DraftService implements IStockDocumentService {

    private CompanyManager companyManager = new CompanyManager();

    @Override
    public IOpResult createDocuments(IStockReport document) {
        String[] types = document.getBaseDocumentType().split("-");
        if(types.length<=1 || types[1].isEmpty()) {
            throw new BusinessException(B1OpResultDescription.SBO_ORDER_BASE_TYPE_FORMAT_ERROR);
        }
        switch (types[2]){
            case "15":return createSalesDelivery(document);
            case "16":return createSalesReturn(document);
            case "20":return createPurchaseDelivery(document);
            case "59":return createGoodsReceipt(document);
            case "60":return createGoodsIssue(document);
            case "67":return createStockTransfer(document);
            default:throw new BusinessException(B1OpResultDescription.SBO_ORDER_BASE_TYPE_IS_EMPTY_OR_INVALID);
        }
    }

    /**
     * 销售交货草稿生成正式单据
     * @param order 销售交货草稿
     * @return
     */
    private IOpResult createSalesDelivery(IStockReport order){
        IOpResult opRst = new OpResult();
        try
        {
            if(null == order) {
                throw new B1Exception(B1OpResultDescription.SBO_ORDER_IS_EMPTY);
            }
            //获取B1连接
            IB1Connection dbConnection  = companyManager.getB1ConnInstance(order.getCompanyName());
            ICompany company = BORepositoryBusinessOne.getInstance(dbConnection).getCompany();
            IDocuments document = SBOCOMUtil.newDocuments(company,DocumentType.SALES_DELIVERY);

            document.setDocDate(Date.valueOf(order.getDocumentDate()) );
            document.setTaxDate(Date.valueOf(order.getDeliveryDate()));
            document.setVatDate(Date.valueOf(order.getPostingDate()));
            document.setComments(order.getRemarks());

            for (IStockReportItem item:order.getStockReportItems()) {
                document.getLines().add();
                document.getLines().setItemCode(item.getItemCode());
                document.getLines().setItemDescription(item.getItemDescription());
                document.getLines().setQuantity(item.getQuantity());
                document.getLines().setPrice(item.getPrice());
                document.getLines().setWarehouseCode(item.getToWarehouse());
                if(DocumentType.SALES_DELIVERY_DRAFT.equals(order.getBaseDocumentType())){
                    document.getLines().setBaseType(DocumentType.DRAFT);
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
            opRst.setCode(B1OpResultCode.EXCEPTION_CODE);
            opRst.setMessage(e.getMessage());
        }
        return opRst;
    }

    /**
     * 采购收货草稿生成正式单据
     * @param order
     * @return
     */
    private IOpResult createPurchaseDelivery(IStockReport order) {
        IOpResult opRst = new OpResult();
        try
        {
            if(null == order) {
                throw new B1Exception(B1OpResultDescription.SBO_ORDER_IS_EMPTY);
            }
            //获取B1连接
            IB1Connection dbConnection  = companyManager.getB1ConnInstance(order.getCompanyName());
            ICompany company = BORepositoryBusinessOne.getInstance(dbConnection).getCompany();
            IDocuments document = SBOCOMUtil.newDocuments(company,DocumentType.PURCHASE_DELIVERY);

            document.setCardCode(order.getBusinessPartnerCode());
            document.setDocDate(Date.valueOf(order.getDocumentDate()) );
            document.setTaxDate(Date.valueOf(order.getDeliveryDate()));
            document.setVatDate(Date.valueOf(order.getPostingDate()));
            document.setComments(order.getRemarks());
            for (IStockReportItem item:order.getStockReportItems()) {
                document.getLines().add();
                document.getLines().setItemCode(item.getItemCode());
                document.getLines().setItemDescription(item.getItemDescription());
                document.getLines().setQuantity(item.getQuantity());
                document.getLines().setPrice(item.getPrice());
                document.getLines().setWarehouseCode(item.getToWarehouse());
                if(DocumentType.PURCHASE_DELIVERY_DRAFT.equals(order.getBaseDocumentType())){
                    document.getLines().setBaseType(DocumentType.DRAFT);
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
            opRst.setCode(B1OpResultCode.EXCEPTION_CODE);
            opRst.setMessage(e.getMessage());
        }
        return opRst;
    }

    /**
     * 销售退货草稿生成正式单据
     * @param order 销售退货草稿
     * @return
     */
    private IOpResult createSalesReturn(IStockReport order){
        IOpResult opRst = new OpResult();
        try
        {
            if(null == order) {
                throw new B1Exception(B1OpResultDescription.SBO_ORDER_IS_EMPTY);
            }
            //获取B1连接
            IB1Connection dbConnection  = companyManager.getB1ConnInstance(order.getCompanyName());
            ICompany company = BORepositoryBusinessOne.getInstance(dbConnection).getCompany();
            IDocuments document = SBOCOMUtil.newDocuments(company, DocumentType.SALES_RETURN);

            //document.setCardCode(order.get);
            document.setDocDate(Date.valueOf(order.getDocumentDate()) );
            document.setTaxDate(Date.valueOf(order.getDeliveryDate()));
            document.setVatDate(Date.valueOf(order.getPostingDate()));
            document.setComments(order.getRemarks());
            for (IStockReportItem item:order.getStockReportItems()) {
                document.getLines().add();
                document.getLines().setItemCode(item.getItemCode());
                document.getLines().setItemDescription(item.getItemDescription());
                document.getLines().setQuantity(item.getQuantity());
                document.getLines().setPrice(item.getPrice());
                document.getLines().setWarehouseCode(item.getToWarehouse());
                if(DocumentType.SALES_RETURN_DRAFT.equals(order.getBaseDocumentType())){
                    document.getLines().setBaseType(DocumentType.DRAFT);
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
            opRst.setCode(B1OpResultCode.EXCEPTION_CODE);
            opRst.setMessage(e.getMessage());
        }
        return opRst;
    }

    /**
     * 采购退货草稿生成正式单据
     * @param order 采购退货操作
     * @return
     */
    private IOpResult createPurchaseReturn(IStockReport order) {
        IOpResult opRst = new OpResult();
        try
        {
            if(null == order) {
                throw new B1Exception(B1OpResultDescription.SBO_ORDER_IS_EMPTY);
            }
            //获取B1连接
            IB1Connection dbConnection  = companyManager.getB1ConnInstance(order.getCompanyName());
            ICompany company = BORepositoryBusinessOne.getInstance(dbConnection).getCompany();
            IDocuments document = SBOCOMUtil.newDocuments(company,DocumentType.PURCHASE_RETURN);

            //document.setCardCode(order.get);
            document.setDocDate(Date.valueOf(order.getDocumentDate()) );
            document.setTaxDate(Date.valueOf(order.getDeliveryDate()));
            document.setVatDate(Date.valueOf(order.getPostingDate()));
            document.setComments(order.getRemarks());
            for (IStockReportItem item:order.getStockReportItems()) {
                document.getLines().add();
                document.getLines().setItemCode(item.getItemCode());
                document.getLines().setItemDescription(item.getItemDescription());
                document.getLines().setQuantity(item.getQuantity());
                document.getLines().setPrice(item.getPrice());
                document.getLines().setWarehouseCode(item.getToWarehouse());
                if(String.valueOf(DocumentType.PURCHASE_RETURN_DRAFT).equals(order.getBaseDocumentType())){
                    document.getLines().setBaseType(DocumentType.DRAFT);
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
            opRst.setCode(B1OpResultCode.EXCEPTION_CODE);
            opRst.setMessage(e.getMessage());
        }
        return opRst;
    }

    /**
     * 库存发货草稿生成正式单据
     * @param order 库存发货草稿
     * @return
     */
    private IOpResult createGoodsIssue(IStockReport order){
        IOpResult opRst = new OpResult();
        try
        {
            if(null == order) {
                throw new B1Exception(B1OpResultDescription.SBO_ORDER_IS_EMPTY);
            }
            //获取B1连接
            IB1Connection dbConnection  = companyManager.getB1ConnInstance(order.getCompanyName());
            ICompany company = BORepositoryBusinessOne.getInstance(dbConnection).getCompany();
            IDocuments document = SBOCOMUtil.newDocuments(company, DocumentType.GOODS_ISSUES);

            document.setCardCode(order.getBusinessPartnerCode());
            document.setDocDate(Date.valueOf(order.getDocumentDate()) );
            document.setTaxDate(Date.valueOf(order.getDeliveryDate()));
            document.setVatDate(Date.valueOf(order.getPostingDate()));
            document.setComments(order.getRemarks());
            for (IStockReportItem item:order.getStockReportItems()) {
                document.getLines().add();
                document.getLines().setItemCode(item.getItemCode());
                document.getLines().setItemDescription(item.getItemDescription());
                document.getLines().setQuantity(item.getQuantity());
                document.getLines().setPrice(item.getPrice());
                document.getLines().setWarehouseCode(item.getToWarehouse());
                if(DocumentType.GOODS_ISSUES_DRAFT.equals(order.getBaseDocumentType())){
                    document.getLines().setBaseType(DocumentType.DRAFT);
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
            opRst.setCode(B1OpResultCode.EXCEPTION_CODE);
            opRst.setMessage(e.getMessage());
        }
        return opRst;
    }

    /**
     * 库存收货草稿生成正式单据
     * @param order 库存收货草稿
     * @return
     */
    private IOpResult createGoodsReceipt(IStockReport order){
        IOpResult opRst = new OpResult();
        try
        {
            if(null == order) {
                throw new B1Exception(B1OpResultDescription.SBO_ORDER_IS_EMPTY);
            }
            //获取B1连接
            IB1Connection dbConnection  = companyManager.getB1ConnInstance(order.getCompanyName());
            ICompany company = BORepositoryBusinessOne.getInstance(dbConnection).getCompany();
            IDocuments document = SBOCOMUtil.newDocuments(company,DocumentType.GOODS_RECEIPTS);

            document.setDocDate(Date.valueOf(order.getDocumentDate()) );
            document.setTaxDate(Date.valueOf(order.getDeliveryDate()));
            document.setVatDate(Date.valueOf(order.getPostingDate()));
            document.setComments(order.getRemarks());
            for (IStockReportItem item:order.getStockReportItems()) {
                document.getLines().add();
                document.getLines().setItemCode(item.getItemCode());
                document.getLines().setItemDescription(item.getItemDescription());
                document.getLines().setQuantity(item.getQuantity());
                document.getLines().setPrice(item.getPrice());
                document.getLines().setWarehouseCode(item.getToWarehouse());
                if(DocumentType.GOODS_RECEIPTS_DRAFT.equals(order.getBaseDocumentType())){
                    document.getLines().setBaseType(DocumentType.DRAFT);
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
            opRst.setCode(B1OpResultCode.EXCEPTION_CODE);
            opRst.setMessage(e.getMessage());
        }
        return opRst;
    }

    /**
     * 库存转储草稿生成正式单据
     * @param order
     * @return
     */
    private IOpResult createStockTransfer(IStockReport order){
        IOpResult opRst = new OpResult();
        try
        {
            if(null == order) {
                throw new B1Exception(B1OpResultDescription.SBO_ORDER_IS_EMPTY);
            }
            //获取B1连接
            IB1Connection dbConnection  = companyManager.getB1ConnInstance(order.getCompanyName());
            ICompany company = BORepositoryBusinessOne.getInstance(dbConnection).getCompany();
            IStockTransfer document = SBOCOMUtil.newStockTransfer(company,DocumentType.STOCK_TRANSFER);

            document.setDocDate(Date.valueOf(order.getDocumentDate()) );
            document.setTaxDate(Date.valueOf(order.getDeliveryDate()));
            document.setDueDate(Date.valueOf(order.getPostingDate()));
            document.setComments(order.getRemarks());

            for (IStockReportItem item:order.getStockReportItems()) {
                document.getLines().add();
                document.getLines().setItemCode(item.getItemCode());
                document.getLines().setItemDescription(item.getItemDescription());
                document.getLines().setQuantity(item.getQuantity());
                document.getLines().setPrice(item.getPrice());
                document.getLines().setWarehouseCode(item.getToWarehouse());
                document.getLines().setFromWarehouseCode(item.getFromWarehose());
                if(DocumentType.STOCK_TRANSFER_DRAFT.equals(order.getBaseDocumentType())){
                    document.getLines().setBaseType(DocumentType.DRAFT);
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
            opRst.setCode(B1OpResultCode.EXCEPTION_CODE);
            opRst.setMessage(e.getMessage());
        }
        return opRst;
    }
}

package org.edi.businessone.service;

import com.sap.smb.sbo.api.ICompany;
import com.sap.smb.sbo.api.IDocuments;
import com.sap.smb.sbo.api.SBOCOMUtil;
import org.edi.businessone.bo.goodsissue.IGoodsIssue;
import org.edi.businessone.bo.goodsissue.IGoodsIssueLine;
import org.edi.businessone.bo.goodsreceipt.IGoodsReceipt;
import org.edi.businessone.bo.puchasedelivery.IPurchaseDelivery;
import org.edi.businessone.bo.salesdelivery.ISalesDelivery;
import org.edi.businessone.bo.stocktransfer.IStockTransfer;
import org.edi.businessone.data.B1ErrorCode;
import org.edi.businessone.data.DocumentType;
import org.edi.businessone.data.OpDescription;
import org.edi.businessone.db.B1Connection;
import org.edi.businessone.db.B1Exception;
import org.edi.businessone.db.CompanyManager;
import org.edi.businessone.db.IB1Connection;
import org.edi.businessone.repository.BORepositoryBusinessOne;
import org.edi.freamwork.data.operation.IOpResult;
import org.edi.freamwork.data.operation.OpResult;

public class DocumentService implements IDocumentService {

    private final CompanyManager companyManager = new CompanyManager();
    /**
     * 生成库存发货/生产收货
     * @param order 库存发货
     * @return 操作结果
     */
    @Override
    public IOpResult createGoodsIssue(IGoodsIssue order) {
        IOpResult opRst = new OpResult();
        //获取B1连接
        try
        {
            if(null == order) {
                throw new B1Exception(OpDescription.SBO_ORDER_IS_EMPTY);
            }
            IB1Connection dbConnection  = companyManager.getB1ConnInstance(order.getComanyName());
            ICompany company = BORepositoryBusinessOne.getInstance(dbConnection).getCompany();
            IDocuments document = SBOCOMUtil.newDocuments(company,DocumentType.GOODS_ISSUES);

            document.setDocDate(order.getDocumentDate());
            document.setTaxDate(order.getDeliveryDate());
            document.setVatDate(order.getPostingDate());
            for (IGoodsIssueLine item:order.getStockDocumentLines()) {
                document.getLines().add();
                document.getLines().setItemCode(item.getItemCode());
                document.getLines().setItemDescription(item.getItemDescription());
                document.getLines().setQuantity(item.getQuantity());
                document.getLines().setPrice(item.getPrice());
                document.getLines().setWarehouseCode(item.getToWhsCode());
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
        }catch (Exception e){
            opRst.setCode(B1ErrorCode.EXCEPTION_CODE);
            opRst.setMessage(e.getMessage());
        }
        return opRst;
    }

    /**
     * 生成库存收货/生产发货
     * @param order 库存收货信息
     * @return 操作结果
     */
    @Override
    public IOpResult createGoodsReceipt(IGoodsReceipt order) {
        return null;
    }

    /**
     * 生成采购收货
     * @param order 采购收货信息
     * @return 操作结果
     */
    @Override
    public IOpResult createPurchaseDelivery(IPurchaseDelivery order) {
        return null;
    }

    /**
     * 生成销售交货
     * @param order 销售交货
     * @return 操作结果
     */
    @Override
    public IOpResult createSalesDelivery(ISalesDelivery order) {
        return null;
    }

    /**
     * 生成库存转储
     * @param order 库存转储信息
     * @return 操作结果
     */
    @Override
    public IOpResult createStockTranfer(IStockTransfer order) {
        return null;
    }
}

package org.edi.businessone.service;

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
import org.edi.businessone.db.B1Exception;
import org.edi.businessone.repository.BORepositoryBusinessOne;
import org.edi.freamwork.operation.IOpResult;
import org.edi.freamwork.operation.OpResult;

import javax.swing.text.Document;

public class DocumentService implements IDocumentService {



    /**
     * 生成库存发货/生产收货
     * @param order
     * @return
     */
    @Override
    public IOpResult createGoodsIssue(IGoodsIssue order) {
        IOpResult opRst = new OpResult();
        try
        {
            if(null == order)
                throw new B1Exception("订单信息为空");

            BORepositoryBusinessOne repositoryBusinessOne = new BORepositoryBusinessOne();
            IDocuments document = SBOCOMUtil.newDocuments(repositoryBusinessOne.getCompany(),DocumentType.GOODS_ISSUES);

            document.setDocDate(order.getDocumentDate());
            document.setTaxDate(order.getDeliveryDate());
            document.setVatDate(order.getPostingDate());

            for (IGoodsIssueLine item:order.getStockDocumentLines()) {
                document.getLines().add();
                document.getLines().setItemCode(item.getItemCode());
                document.getLines().setItemDescription(item.getItemDescription());
                //document.getLines().setQuantity(item.getQuantity());
                //document.getLines().setPrice(item.getPrice());
                document.getLines().setWarehouseCode(item.getToWhsCode());
                if(String.valueOf(DocumentType.PRODUCE_ORDER).equals(order.getBaseDocumentType()))
                {
                    document.getLines().setBaseType(DocumentType.PRODUCE_ORDER);
                    document.getLines().setBaseEntry(item.getBaseDocumentEntry());
                    document.getLines().setBaseLine(item.getBaseDocumentLineId());
                }
            }
            int rt = document.add();
            opRst.setCode(rt);
            opRst.setMessage(repositoryBusinessOne.getCompany().getLastErrorCode() + ":"
                             + repositoryBusinessOne.getCompany().getLastErrorDescription());
            if(rt == 0)
                opRst.setThirdId(repositoryBusinessOne.getCompany().getNewObjectKey());


        }catch (Exception e){
            opRst.setCode(B1ErrorCode.EXCEPTION_CODE);
            opRst.setMessage(e.getMessage());
        }
        finally {
            
        }
        return opRst;
    }

    /**
     * 生成库存收货/生产发货
     * @param order
     * @return
     */
    @Override
    public IOpResult createGoodsReceipt(IGoodsReceipt order) {
        return null;
    }

    /**
     * 生成采购收货
     * @param order
     * @return
     */
    @Override
    public IOpResult createPurchaseDelivery(IPurchaseDelivery order) {
        return null;
    }

    /**
     * 生成销售交货
     * @param order
     * @return
     */
    @Override
    public IOpResult createSalesDelivery(ISalesDelivery order) {
        return null;
    }

    /**
     * 生成库存转储
     * @param order
     * @return
     */
    @Override
    public IOpResult createStockTranfer(IStockTransfer order) {
        return null;
    }
}

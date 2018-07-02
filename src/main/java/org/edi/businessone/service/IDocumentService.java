package org.edi.businessone.service;

import org.edi.businessone.bo.goodsissue.IGoodsIssue;
import org.edi.businessone.bo.goodsreceipt.IGoodsReceipt;
import org.edi.businessone.bo.puchasedelivery.IPurchaseDelivery;
import org.edi.businessone.bo.salesdelivery.ISalesDelivery;
import org.edi.businessone.bo.stocktransfer.IStockTransfer;
import org.edi.freamwork.operation.IOpResult;

public interface IDocumentService {

    /**
     * 生成库存发货/生产收货
     * @param order
     * @return
     */
    IOpResult createGoodsIssue(IGoodsIssue order);


    /**
     * 生成库存收货/生产收货
     * @param order
     * @return
     */
    IOpResult createGoodsReceipt(IGoodsReceipt order);


    /**
     * 生成采购收货单
     * @param order
     * @return
     */
    IOpResult createPurchaseDelivery(IPurchaseDelivery order);

    /**
     * 生成销售交货单
     * @param order
     * @return
     */
    IOpResult createSalesDelivery(ISalesDelivery order);

    /**
     * 生成库存转储单
     * @param order
     * @return
     */
    IOpResult createStockTranfer(IStockTransfer order);

}

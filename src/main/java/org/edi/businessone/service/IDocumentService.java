package org.edi.businessone.service;

import org.edi.businessone.bo.goodsissue.IGoodsIssue;
import org.edi.businessone.bo.goodsreceipt.IGoodsReceipt;
import org.edi.businessone.bo.puchasedelivery.IPurchaseDelivery;
import org.edi.businessone.bo.salesdelivery.ISalesDelivery;
import org.edi.businessone.bo.stocktransfer.IStockTransfer;
import org.edi.freamwork.data.operation.IOpResult;

public interface IDocumentService {

    /**
     * 生成库存发货/生产收货
     * @param order 库存发货
     * @return 库存收货
     */
    IOpResult createGoodsIssue(IGoodsIssue order);


    /**
     * 生成库存收货/生产收货
     * @param order 库存收货
     * @return 操作结果
     */
    IOpResult createGoodsReceipt(IGoodsReceipt order);


    /**
     * 生成采购收货单
     * @param order 采购收货
     * @return 操作结果
     */
    IOpResult createPurchaseDelivery(IPurchaseDelivery order);

    /**
     * 生成销售交货单
     * @param order 销售交货
     * @return 操作结果
     */
    IOpResult createSalesDelivery(ISalesDelivery order);

    /**
     * 生成库存转储单
     * @param order 库存转储
     * @return 操作结果
     */
    IOpResult createStockTranfer(IStockTransfer order);

}

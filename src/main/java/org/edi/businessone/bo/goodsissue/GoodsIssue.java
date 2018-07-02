package org.edi.businessone.bo.goodsissue;

import org.edi.businessone.bo.stockdocument.IStockDocument;
import org.edi.businessone.bo.stockdocument.StockDocument;

import java.util.Date;
import java.util.List;

/**
 * 库存发货
 * create by Fancy
 */

public class GoodsIssue  extends StockDocument<IGoodsIssueLine> implements IGoodsIssue{

    public static IGoodsIssue createGoodsIssue(IStockDocument order){
        IGoodsIssue goodsIssue = new GoodsIssue();

        return goodsIssue;
    }

}

package org.edi.businessone.bo.goodsreceipt;

import org.edi.businessone.bo.stockdocument.IStockDocumentLine;
import org.edi.businessone.bo.stockdocument.StockDocumentLine;

import java.math.BigDecimal;

/**
 * 库存收获明细
 */
public class GoodsReceiptLine extends StockDocumentLine implements IGoodsReceiptLine {

    private String fromWhsCode;

    @Override
    public String getFromWhsCode(){
        return fromWhsCode;
    }

    @Override
    public void setFromWhsCode(String value){
        this.fromWhsCode = value;
    }


}

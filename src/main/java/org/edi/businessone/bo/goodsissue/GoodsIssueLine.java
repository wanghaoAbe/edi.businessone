package org.edi.businessone.bo.goodsissue;

import org.edi.businessone.bo.stockdocument.IStockDocumentLine;
import org.edi.businessone.bo.stockdocument.StockDocumentLine;

import java.math.BigDecimal;

/**
 * 库存发货明细
 */
public class GoodsIssueLine extends StockDocumentLine implements IGoodsIssueLine{

    private String toWhsCode;

    @Override
    public String getToWhsCode(){
        return toWhsCode;
    }

    @Override
    public void setToWhsCode(String value){
        this.toWhsCode = value;
    }


}

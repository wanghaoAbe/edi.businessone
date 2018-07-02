package org.edi.businessone.bo.stocktransfer;

import org.edi.businessone.bo.stockdocument.IStockDocumentLine;
import org.edi.businessone.bo.stockdocument.StockDocumentLine;

import java.math.BigDecimal;

/**
 * 库存转储明细
 */
public class StockTransferLine extends StockDocumentLine implements IStockTransferLine {

    private String fromWhsCode;

    @Override
    public String getFromWhsCode(){
        return fromWhsCode;
    }

    @Override
    public void setFromWhsCode(String value){
        this.fromWhsCode = value;
    }

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

package org.edi.businessone.bo.goodsreceipt;

import org.edi.businessone.bo.stockdocument.IStockDocumentLine;

public interface IGoodsReceiptLine extends IStockDocumentLine {

    public String getFromWhsCode();

    public void setFromWhsCode(String value);
}

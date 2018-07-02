package org.edi.businessone.bo.goodsissue;

import org.edi.businessone.bo.stockdocument.IStockDocumentLine;

public interface IGoodsIssueLine extends IStockDocumentLine {

    String getToWhsCode();
    void setToWhsCode(String value);
}

package org.edi.businessone.bo.stocktransfer;

import org.edi.businessone.bo.stockdocument.IStockDocumentLine;

public interface IStockTransferLine extends IStockDocumentLine {

    public String getFromWhsCode();

    public void setFromWhsCode(String value);


    public String getToWhsCode();

    public void setToWhsCode(String value);
}

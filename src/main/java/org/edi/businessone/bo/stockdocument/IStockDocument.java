package org.edi.businessone.bo.stockdocument;

import java.util.Date;
import java.util.List;

public interface IStockDocument<T extends IStockDocumentLine> {

    String getComanyName();

    void setComanyName(String value);

    Integer getPlateformDocEntry();

    void setPlateformDocEntry(Integer value);

    Integer getDocEntry();

    void setDocEntry(Integer value);

    String getBusinessObjectCode();

    void setBusinessObjectCode(String value);

    Date getPostingDate();

    void setPostingDate(Date value);

    Date getDeliveryDate();

    void setDeliveryDate(Date value);

    Date getDocumentDate();

    void setDocumentDate(Date value);

    String getReference1();

    void setReference1(String value);

    String getReference2();

    void setReference2(String value);

    String getRemarks();

    void setRemarks(String value);

    String getBaseDocumentType();

    void setBaseDocumentType(String value);

    Integer getBaseDocumentEntry();

    void setBaseDocumentEntry(Integer value);

    List<T> getStockDocumentLines();

    void setStockDocumentLines(List<T> stockDocumentLines);

}

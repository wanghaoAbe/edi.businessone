package org.edi.businessone.bo;

import java.util.List;

public interface IStockDocument {

    String getComanyName();

    void setComanyName(String value);

    Integer getPlateformDocEntry();

    void setPlateformDocEntry(Integer value);

    Integer getDocEntry();

    void setDocEntry(Integer value);

    void setPostingDate(String value);

    String getDeliveryDate();

    void setDeliveryDate(String value);

    String getDocumentDate();

    void setDocumentDate(String value);

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

    List<IStockDocumentLine> getStockDocumentLines();

    void setStockDocumentLines(List<IStockDocumentLine> stockDocumentLines);

}

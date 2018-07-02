package org.edi.businessone.bo;

import java.math.BigDecimal;

public interface IStockDocumentLine {

    Integer getPlateformDocEntry();

    void setPlateformDocEntry(Integer value);

    Integer getLineId();

    void setLineId(Integer value);

    String getReference1();

    void setReference1(String value);

    String getReference2();

    void setReference2(String value);

    String getBaseDocumentType();

    void setBaseDocumentType(String value);

    Integer getBaseDocumentEntry();

    void setBaseDocumentEntry(Integer value);

    Integer getBaseDocumentLineId();

    void setBaseDocumentLineId(Integer value);

    String getDistributionRule1();

    void setDistributionRule1(String value);

    String getDistributionRule2();

    void setDistributionRule2(String value);

    String getDistributionRule3();

    void setDistributionRule3(String value);

    String getDistributionRule4();

    void setDistributionRule4(String value);

    String getDistributionRule5();

    void setDistributionRule5(String value);

    String getItemCode();

    void setItemCode(String value);

    String getItemDescription();

    void setItemDescription(String value);

    BigDecimal getQuantity();

    void setQuantity(BigDecimal value);

    BigDecimal getPrice();

    void setPrice(BigDecimal value);


    String getBarCode1();

    void setBarCode1(String value);

    String getBarCode2();

    void setBarCode2(String value);

    String getBarCode3();

    void setBarCode3(String value);

    String getBarCode4();

    void setBarCode4(String value);

    String getBarCode5();

    void setBarCode5(String value);
}

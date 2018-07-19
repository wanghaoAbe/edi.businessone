package org.edi.businessone.bo.stockdocument;

import java.math.BigDecimal;

public class StockDocumentLine implements IStockDocumentLine {
    private String toWhsCode;

    public String getToWhsCode(){
        return toWhsCode;
    }

    public void setToWhsCode(String value){
        this.toWhsCode = value;
    }

    private Integer plateformDocEntry;
    @Override
    public Integer getPlateformDocEntry() {
        return plateformDocEntry;
    }

    @Override
    public void setPlateformDocEntry(Integer value) {
        this.plateformDocEntry = value;
    }

    private Integer lineId;
    @Override
    public Integer getLineId() {
        return lineId;
    }

    @Override
    public void setLineId(Integer value) {
        this.lineId = value;
    }

    private String reference1;
    @Override
    public String getReference1() {
        return reference1;
    }

    @Override
    public void setReference1(String value) {
        this.reference1 = value;
    }

    private String reference2;
    @Override
    public String getReference2() {
        return reference2;
    }

    @Override
    public void setReference2(String value) {
        this.reference2 = value;
    }

    private String baseDocumentType;
    @Override
    public String getBaseDocumentType() {
        return baseDocumentType;
    }

    @Override
    public void setBaseDocumentType(String value) {
        this.baseDocumentType = value;
    }

    private Integer baseDocumentEntry;
    @Override
    public Integer getBaseDocumentEntry() {
        return baseDocumentEntry;
    }

    @Override
    public void setBaseDocumentEntry(Integer value) {
        this.baseDocumentEntry = value;
    }

    private Integer baseDocumentLineId;
    @Override
    public Integer getBaseDocumentLineId() {
        return baseDocumentLineId;
    }

    @Override
    public void setBaseDocumentLineId(Integer value) {
        this.baseDocumentLineId = value;
    }

    private String distributionRule1;
    @Override
    public String getDistributionRule1() {
        return distributionRule1;
    }

    @Override
    public void setDistributionRule1(String value) {
        this.distributionRule1 = value;
    }

    private String distributionRule2;
    @Override
    public String getDistributionRule2() {
        return distributionRule2;
    }

    @Override
    public void setDistributionRule2(String value) {
        this.distributionRule2 = value;
    }

    private String distributionRule3;
    @Override
    public String getDistributionRule3() {
        return distributionRule3;
    }

    @Override
    public void setDistributionRule3(String value) {
        this.distributionRule3 = value;
    }

    private String distributionRule4;
    @Override
    public String getDistributionRule4() {
        return distributionRule4;
    }

    @Override
    public void setDistributionRule4(String value) {
        this.distributionRule4 = value;
    }

    private String distributionRule5;
    @Override
    public String getDistributionRule5() {
        return distributionRule5;
    }

    @Override
    public void setDistributionRule5(String value) {
        this.distributionRule5 = value;
    }


    private String itemCode;
    @Override
    public String getItemCode() {
        return itemCode;
    }

    @Override
    public void setItemCode(String value) {
        this.itemCode = value;
    }

    private String itemDescription;
    @Override
    public String getItemDescription() {
        return itemDescription;
    }

    @Override
    public void setItemDescription(String value) {
        this.itemDescription = value;
    }

    private Double quantity;
    @Override
    public Double getQuantity() {
        return quantity;
    }

    @Override
    public void setQuantity(Double value) {
        this.quantity = value;
    }

    private Double price;
    @Override
    public Double getPrice() {
        return price;
    }

    @Override
    public void setPrice(Double value) {
        this.price = value;
    }

    private String barCode1;
    @Override
    public String getBarCode1() {
        return barCode1;
    }

    @Override
    public void setBarCode1(String value) {
        this.barCode1 = value;
    }

    private String barCode2;
    @Override
    public String getBarCode2() {
        return barCode2;
    }

    @Override
    public void setBarCode2(String value) {
        this.barCode2 = value;
    }

    private String barCode3;
    @Override
    public String getBarCode3() {
        return barCode3;
    }

    @Override
    public void setBarCode3(String value) {
        this.barCode3 = value;
    }

    private String barCode4;
    @Override
    public String getBarCode4() {
        return barCode4;
    }

    @Override
    public void setBarCode4(String value) {
        this.barCode4 = value;
    }

    private String barCode5;
    @Override
    public String getBarCode5() {
        return barCode5;
    }

    @Override
    public void setBarCode5(String value) {
        this.barCode5 = value;
    }
}

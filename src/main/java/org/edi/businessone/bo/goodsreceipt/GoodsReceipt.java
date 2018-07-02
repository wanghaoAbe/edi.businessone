package org.edi.businessone.bo;

import org.edi.businessone.bo.stockdocument.IStockDocument;

import java.util.Date;
import java.util.List;

/**
 * 库存收获
 */
public class GoodsReceipt implements IStockDocument<GoodsReceiptLine> {

    private String companyName;
    @Override
    public String getComanyName() {
        return companyName;
    }

    @Override
    public void setComanyName(String value) {
        this.companyName = value;
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

    private Integer docEntry;
    @Override
    public Integer getDocEntry() {
        return docEntry;
    }

    @Override
    public void setDocEntry(Integer value) {
        this.docEntry = value;
    }

    private Date postingDate;

    @Override
    public Date getPostingDate(){
        return  postingDate;
    }

    @Override
    public void setPostingDate(Date value) {
        this.postingDate = value;
    }

    private Date deliveryDate;
    @Override
    public Date getDeliveryDate() {
        return deliveryDate;
    }

    @Override
    public void setDeliveryDate(Date value) {
        this.deliveryDate = value;
    }

    private Date documentDate;
    @Override
    public Date getDocumentDate() {
        return documentDate;
    }

    @Override
    public void setDocumentDate(Date value) {
        this.documentDate = value;
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

    private String remarks;
    @Override
    public String getRemarks() {
        return remarks;
    }

    @Override
    public void setRemarks(String value) {
        this.remarks = value;
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

    private List<GoodsReceiptLine> stockDocumentLines;
    @Override
    public List<GoodsReceiptLine> getStockDocumentLines() {
        return stockDocumentLines;
    }

    @Override
    public void setStockDocumentLines(List<GoodsReceiptLine> stockDocumentLines) {
        this.stockDocumentLines = stockDocumentLines;
    }
}

package org.edi.businessone.service;


import org.edi.businessone.data.B1OpResultDescription;
import org.edi.businessone.data.DocumentType;
import org.edi.freamwork.exception.BusinessException;
import org.edi.stocktask.bo.stockreport.IStockReport;
import org.springframework.stereotype.Component;

public class DocumentServiceFactory {

    private IStockDocumentService service;

    public IStockDocumentService getServiceInstance(IStockReport document){
        switch (document.getBaseDocumentType()){
            case "17":service = new SalesDeliveryServie();break;
            case "22":service = new PurchaseDeliveryService();break;
            case "1250000001":service = new StockTransferService();break;
            case DocumentType.GOODS_ISSUES_DRAFT:
            case DocumentType.GOODS_RECEIPTS_DRAFT:
            case DocumentType.PURCHASE_DELIVERY_DRAFT:
            case DocumentType.PURCHASE_RETURN_DRAFT:
            case DocumentType.SALES_DELIVERY_DRAFT:
            case DocumentType.STOCK_TRANSFER_DRAFT:
            case DocumentType.SALES_RETURN_DRAFT: service = new DraftService();break;
            default:throw new BusinessException(String.format(B1OpResultDescription.SBO_ORDER_BASE_TYPE_IS_EMPTY_OR_INVALID,document.getDocEntry()));
        }
        return service;
    }



}

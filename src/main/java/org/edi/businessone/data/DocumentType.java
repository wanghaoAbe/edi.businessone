package org.edi.businessone.data;

import org.edi.freamwork.exception.BusinessException;

import javax.swing.text.Document;

public class DocumentType {

    /**
     * 物料主数据
     */
    public static final Integer MATERIALS = 4;

    /**
     * 应收发票
     */
    public static final Integer SALES_INVOICE = 13;

    /**
     * 销售交货
     */
    public static final Integer SALES_DELIVERY = 15;

    /**
     * 销售退货
     */
    public static final Integer SALES_RETURN = 16;

    /**
     * 销售订单
     */
    public static final Integer SALES_ORDER = 17;

    /**
     * 应付发票
     */
    public static final Integer PURCHASE_INVOICE = 18;

    /**
     * 采购收货
     */
    public static final Integer PURCHASE_DELIVERY = 20;

    /**
     * 采购退货
     */
    public static final Integer PURCHASE_RETURN = 21;

    /**
     * 采购订单
     */
    public static final Integer PURCHASE_ORDER = 22;


    /**
     * 收货
     */
    public static final Integer GOODS_RECEIPTS = 59;

    /**
     * 发货
     */
    public static final Integer GOODS_ISSUES = 60;

    /**
     * 草稿
     */
    public static final Integer DRAFT = 112;
    /**
     * 库存转储
     */
    public static final Integer STOCK_TRANSFER = 67;



    /**
     * 生产订单
     */
    public static final Integer PRODUCE_ORDER = 202;


    public static final Integer STOCK_TRANSFER_REQUEST = 1250000001;

    /**
     * 应收发票草稿
     */
    public static final String SALES_INVOICE_DRAFT = "112-13";

    /**
     * 销售交货草稿
     */
    public static final String SALES_DELIVERY_DRAFT = "112-15";

    /**
     * 销售退货草稿
     */
    public static final String SALES_RETURN_DRAFT = "112-16";

    /**
     * 应付发票草稿
     */
    public static final String PURCHASE_INVOICE_DRAFT = "112-18";

    /**
     * 采购收货草稿
     */
    public static final String PURCHASE_DELIVERY_DRAFT = "112-20";

    /**
     * 采购退货草稿
     */
    public static final String PURCHASE_RETURN_DRAFT = "112-21";

    /**
     * 库存收货草稿
     */
    public static final String GOODS_RECEIPTS_DRAFT = "112-59";

    /**
     * 库存发货草稿
     */
    public static final String GOODS_ISSUES_DRAFT = "112-60";

    /**
     * 库存转储草稿
     */
    public static final String STOCK_TRANSFER_DRAFT = "112-67";



    public static Integer getBusinessObject(String objectType){
        if(objectType == null || objectType.isEmpty()){
            throw new BusinessException(B1OpResultDescription.SBO_ORDER_BASE_TYPE_FORMAT_ERROR);
        }
        if(objectType.contains("112")){
            String[] types = objectType.split("-");
            if(types.length<=1 || types[1].isEmpty()) {
                throw new BusinessException(B1OpResultDescription.SBO_ORDER_BASE_TYPE_FORMAT_ERROR);
            }
            return getObject(types[1]);
        }else {
            return getObject(objectType);
        }
    }

    private static Integer getObject(String objType){
        switch (objType){
            case "13":return DocumentType.SALES_INVOICE;
            case "15":return DocumentType.SALES_DELIVERY;
            case "16":return DocumentType.SALES_RETURN;
            case "17":return DocumentType.SALES_ORDER;
            case "18":return DocumentType.PURCHASE_INVOICE;
            case "20":return DocumentType.PURCHASE_DELIVERY;
            case "21":return DocumentType.PURCHASE_RETURN;
            case "22":return DocumentType.PURCHASE_ORDER;
            case "59":return DocumentType.GOODS_RECEIPTS;
            case "60":return DocumentType.GOODS_ISSUES;
            case "67":return DocumentType.STOCK_TRANSFER;
            case "1250000001":return DocumentType.STOCK_TRANSFER_REQUEST;
            default:throw new BusinessException(String.format(B1OpResultDescription.SBO_ORDER_BASE_TYPE_IS_EMPTY_OR_INVALID,objType));
        }
    }

}

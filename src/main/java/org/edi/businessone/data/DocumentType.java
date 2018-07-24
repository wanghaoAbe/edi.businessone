package org.edi.businessone.data;

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
     * 库存转储
     */
    public static final Integer STOCK_TRANSFER = 67;

    /**
     * 生产订单
     */
    public static final Integer PRODUCE_ORDER = 202;


    /**
     * 销售交货草稿
     */
    public static final String SALES_DELIVERY_DRAFT = "112-15";

    /**
     * 销售退货草稿
     */
    public static final String SALES_RETURN_DRAFT = "112-16";

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


}

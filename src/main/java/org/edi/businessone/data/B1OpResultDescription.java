package org.edi.businessone.data;

import org.edi.freamwork.data.operation.OpResultDescription;

import java.net.PortUnreachableException;

public class B1OpResultDescription extends OpResultDescription {

    public final static String OK = "OK";

    public final static String SBO_INNER_ERROR = "内部异常";

    public final static String SBO_DATA_CONVERT_ERROR = "SBO数据类型获取失败";

    public final static String SBO_ORDER_IS_EMPTY = "订单信息为空";

    public final static String SBO_ORDER_BASE_TYPE_IS_EMPTY_OR_INVALID = "[%d]订单的基于类型无效或为空";

    public final static String SBO_ORDER_BASE_TYPE_FORMAT_ERROR = "订单的基于类型格式错误";

    public final static String SBO_CREATE_ORDER_SUCCESS_INFO= "[%1$d]号任务汇报生成成功，B1单据号[%2$s]";

    public final static String SBO_CREATE_ORDER_FAILED_INFO = "[%1$d]号任务汇报生成失败，失败原因：%2$s";

    public final static String SBO_CREATE_ORDER_EXCEPTION = "单据生成发生异常：{0}";

    public final static String SBO_CAN_NOT_FIND_DRAFT = "未找到草稿单据%d";

    public final static String SBO_PUCHASEORDER_CREATE_PURCHASEDELIVERY_DRAFT = "[%d]号采购订单生成采购收货";

    public final static String SBO_SALESORDER_CREATE_SALESDELIVERY = "[%d]号销售订单生成销售交货";

    public final static String SBO_TRANSREQUEST_CREATE_TRANSORDER = "[%d]号库存转储请求生成库存转储单";

    public final static String SBO_DRAFT_CREATE_ORDER = "[%d]号草稿生成单据";
}

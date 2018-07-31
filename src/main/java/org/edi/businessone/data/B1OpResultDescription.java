package org.edi.businessone.data;

import org.edi.freamwork.data.operation.OpResultDescription;

import java.net.PortUnreachableException;

public class B1OpResultDescription extends OpResultDescription {

    public final static String SBO_DATA_CONVERT_ERROR = "SBO数据类型获取失败";

    public final static String SBO_ORDER_IS_EMPTY = "订单信息为空";

    public final static String SBO_ORDER_BASE_TYPE_IS_EMPTY_OR_INVALID = "订单的基于类型无效或为空";

    public final static String SBO_ORDER_BASE_TYPE_FORMAT_ERROR = "订单的基于类型格式错误";

    public final static String SBO_CREATE_ORDER_SUCCESS_INFO= "[%d]号任务汇报生成成功，B1单据号[%d]";

    public final static String SBO_CREATE_ORDER_FAILED_INFO = "[%d]号任务汇报生成失败，失败原因：%s";

    public final static String SBO_CREATE_ORDER_EXCEPTION = "单据生成发生异常：{0}";
}

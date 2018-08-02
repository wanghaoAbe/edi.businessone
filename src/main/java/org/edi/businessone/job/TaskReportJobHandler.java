package org.edi.businessone.job;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import com.xxl.job.core.log.XxlJobLogger;
import org.edi.businessone.data.B1OpResultCode;
import org.edi.businessone.data.B1OpResultDescription;
import org.edi.businessone.service.DocumentServiceFactory;
import org.edi.businessone.service.IStockDocumentService;
import org.edi.freamwork.data.operation.IOpResult;
import org.edi.stocktask.bo.stockreport.IStockReport;
import org.edi.stocktask.bo.stockreport.StockReport;
import org.edi.stocktask.repository.IBORepositoryStockReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Fancy
 * @date 2018/7/29
 * * 开发步骤：
 * 1、继承"IJobHandler"：“com.xxl.job.core.handler.IJobHandler”；
 * 2、注册到Spring容器：添加“@Component”注解，被Spring容器扫描为Bean实例；
 * 3、注册到执行器工厂：添加“@JobHandler(value="自定义jobhandler名称")”注解，注解value值对应的是调度中心新建任务的JobHandler属性的值。
 * 4、执行日志：需要通过 "XxlJobLogger.log" 打印执行日志；
 *
 */

@JobHandler(value = "taskReportJobHandler")
@Component
public class TaskReportJobHandler extends IJobHandler {

    @Autowired
    private IBORepositoryStockReport boRepositoryStockReport;

    private DocumentServiceFactory documentServiceFactory = new DocumentServiceFactory();


    @Override
    public ReturnT<String> execute(String s) throws Exception {
        //获取未清任务汇报
        try
        {
            List<StockReport> stockReports = boRepositoryStockReport.fetchUnSyncStockReport();
            if(stockReports != null && stockReports.size() > 0){
                XxlJobLogger.log(String.format(B1OpResultDescription.SBO_GET_ORDERS,stockReports.size()));
                IStockDocumentService service;
                for (StockReport stockReport:stockReports) {
                    service = documentServiceFactory.getServiceInstance(stockReport);
                    IOpResult result =service.createDocuments(stockReport);
                    if(B1OpResultCode.OK==result.getCode()){
                        XxlJobLogger.log(String.format(B1OpResultDescription.SBO_CREATE_ORDER_SUCCESS_INFO,stockReport.getDocEntry()),result.getThirdId());
                        stockReport.setB1DocEntry(result.getThirdId());
                        boRepositoryStockReport.updateStockReportDocStatus(stockReport);
                    }else{
                        XxlJobLogger.log(String.format(B1OpResultDescription.SBO_CREATE_ORDER_FAILED_INFO,stockReport.getDocEntry(),result.getMessage()));
                    }
                }
            }
            return SUCCESS;
        }catch (Exception e){
            XxlJobLogger.log(B1OpResultDescription.SBO_CREATE_ORDER_EXCEPTION,e);
            return ReturnT.FAIL;
        }
    }
}

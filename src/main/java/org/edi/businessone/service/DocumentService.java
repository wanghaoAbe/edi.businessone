package org.edi.businessone.service;

import com.sap.smb.sbo.api.SBOCOMUtil;
import org.edi.businessone.data.B1OpResultCode;
import org.edi.businessone.data.B1OpResultDescription;
import org.edi.businessone.data.BusinessOneData;
import org.edi.businessone.data.SBOResult;
import org.edi.freamwork.data.Result;
import org.edi.freamwork.data.operation.IOpResult;
import org.edi.freamwork.data.operation.OpResult;
import org.edi.freamwork.data.operation.OpResultCode;
import org.edi.freamwork.data.operation.OpResultDescription;
import org.edi.freamwork.log.LogFileName;
import org.edi.freamwork.log.LoggerUtils;
import org.edi.stocktask.bo.stockreport.StockReport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Fancy
 * @date 2018/8/14
 */
@Path("/v1")
public class DocumentService {
    Logger logger = LoggerUtils.Logger(BusinessOneData.APPENDER_NAME);
    private DocumentServiceFactory documentServiceFactory = new DocumentServiceFactory();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/documents")
    public Result<SBOResult> createDocument(List<StockReport> stockReports) {
        Result<SBOResult> result;
        logger.info(B1OpResultDescription.SBO_DOCUMENT_INFO + stockReports.toString());
        if (stockReports == null) {
            return new Result(OpResultCode.OBJECT_IS_EMPTY, OpResultDescription.VALUE_IS_EMPTY);
        }
        result = new Result<>();
        IStockDocumentService service;
        SBOResult sboResult;
        try{
            for (StockReport stockReport : stockReports) {
                sboResult = new SBOResult();
                try {
                    sboResult.setUniquekey(stockReport.getDocEntry().toString());
                    service = documentServiceFactory.getServiceInstance(stockReport);
                    IOpResult rst = service.createDocuments(stockReport);
                    if(rst.getCode() == OpResultCode.SUCCESS){
                        sboResult.setReturnEntry(rst.getThirdId());
                    }
                    sboResult.setCode(rst.getCode());
                    sboResult.setMessage(rst.getMessage());

                } catch (Exception e) {
                    sboResult.setCode(B1OpResultCode.EXCEPTION_CODE);
                    sboResult.setMessage(e.getMessage());
                }
                result.getData().add(sboResult);
            }
            result.setCode(B1OpResultCode.OK);
            result.setMessage(B1OpResultDescription.OK);
            logger.info(B1OpResultDescription.SBO_DOCUMENT_CREATE_RETURN_INFO + result.toString());
            return result;
        }catch(Exception e){
            logger.info(B1OpResultDescription.SBO_DOCUMENT_CREATE_RETURN_EXCEPTION,e);
            return new Result(B1OpResultCode.EXCEPTION_CODE, B1OpResultDescription.SBO_INNER_ERROR + e.getMessage());
        }finally {
            System.gc();
        }
    }

    @GET
    @Path("/hello")
    @Produces("text/plain")
    public String hello(){
        return  "hello word";
    }
}

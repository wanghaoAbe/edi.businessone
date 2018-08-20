package org.edi.businessone.service;

import com.sap.smb.sbo.api.SBOCOMUtil;
import org.edi.businessone.data.B1OpResultCode;
import org.edi.businessone.data.B1OpResultDescription;
import org.edi.businessone.data.SBOResult;
import org.edi.freamwork.data.Result;
import org.edi.freamwork.data.operation.IOpResult;
import org.edi.freamwork.data.operation.OpResult;
import org.edi.freamwork.data.operation.OpResultCode;
import org.edi.freamwork.data.operation.OpResultDescription;
import org.edi.stocktask.bo.stockreport.StockReport;

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

    private DocumentServiceFactory documentServiceFactory = new DocumentServiceFactory();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/documents")
    public Result<SBOResult> createDocument(List<StockReport> stockReports) {
        Result<SBOResult> result;
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
                    sboResult.setCode(rst.getCode());
                    sboResult.setMessage(rst.getMessage());
                    sboResult.setReturnEntry(rst.getThirdId());
                } catch (Exception e) {
                    sboResult.setCode(B1OpResultCode.EXCEPTION_CODE);
                    sboResult.setMessage(e.getMessage());
                }
                result.getData().add(sboResult);
            }
            result.setCode(B1OpResultCode.OK);
            result.setMessage(B1OpResultDescription.OK);
            return result;
        }catch(Exception e){
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

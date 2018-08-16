package org.edi.businessone.service;

import org.edi.businessone.data.B1OpResultCode;
import org.edi.businessone.data.SBOResult;
import org.edi.freamwork.data.operation.IOpResult;
import org.edi.freamwork.data.operation.OpResult;
import org.edi.freamwork.data.operation.OpResultCode;
import org.edi.freamwork.data.operation.OpResultDescription;
import org.edi.stocktask.bo.stockreport.StockReport;
import org.edi.stocktask.repository.BORepositoryStockReport;
import org.springframework.beans.factory.annotation.Autowired;

import javax.management.openmbean.OpenDataException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * @author Fancy
 * @date 2018/8/14
 */
@Path("/v1")
public class DocumentService {

    @Autowired
    private BORepositoryStockReport boRepositoryStockReport;

    private DocumentServiceFactory documentServiceFactory = new DocumentServiceFactory();


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/documents")
    public OpResult<SBOResult> createDocument(List<StockReport> stockReports){
        OpResult<SBOResult> opResult ;
        if(stockReports == null){
            return new OpResult(OpResultCode.OBJECT_IS_EMPTY, OpResultDescription.VALUE_IS_EMPTY);
        }
        IStockDocumentService service;
        SBOResult sboResult;
        opResult = new OpResult<>();
        for (StockReport stockReport:stockReports) {
            sboResult = new SBOResult();
            sboResult.setUniquekey(stockReport.getDocEntry().toString());
            try{
                service = documentServiceFactory.getServiceInstance(stockReport);
                IOpResult result =service.createDocuments(stockReport);
                sboResult.setCode(result.getCode());
                sboResult.setMessage(result.getMessage());
                sboResult.setReturnEntry(result.getThirdId());
            }catch (Exception e){
                sboResult.setCode(B1OpResultCode.EXCEPTION_CODE);
                sboResult.setMessage(e.getMessage());
            }
            opResult.getResultObject().add(sboResult);
        }
        return opResult;
    }
}

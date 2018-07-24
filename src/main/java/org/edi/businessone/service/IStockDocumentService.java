package org.edi.businessone.service;

import org.edi.freamwork.data.operation.IOpResult;
import org.edi.stocktask.bo.stockreport.IStockReport;

public interface IStockDocumentService {

    IOpResult createDocuments(IStockReport document);

}

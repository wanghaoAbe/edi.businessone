package org.edi.businessone.service;

import org.edi.businessone.data.B1OpResultDescription;
import org.edi.freamwork.data.operation.IOpResult;
import org.edi.freamwork.exception.BusinessException;
import org.edi.stocktask.bo.stockreport.IStockReport;

/**
 * 草稿单据服务
 */
public class DraftService implements IStockDocumentService {

    @Override
    public IOpResult createDocuments(IStockReport document) {
        String[] types = document.getBaseDocumentType().split("-");
        if(types.length<=1 || types[1].isEmpty()) throw new BusinessException(B1OpResultDescription.SBO_ORDER_BASE_TYPE_FORMAT_ERROR);

        switch (types[2]){
            case "15":return createSalesDelivery(document);
            case "16":return createSalesReturn(document);
            case "20":return createPurchaseDelivery(document);
            //case ""

        }
        return null;
    }

    /**
     * 销售交货草稿生成正式单据
     * @param document 销售交货草稿
     * @return
     */
    private IOpResult createSalesDelivery(IStockReport document){
        return null;
    }

    /**
     * 采购收货草稿生成正式单据
     * @param document
     * @return
     */
    private IOpResult createPurchaseDelivery(IStockReport document) {
        return null;
    }

    /**
     * 销售退货草稿生成正式单据
     * @param document 销售退货草稿
     * @return
     */
    private IOpResult createSalesReturn(IStockReport document){
        return null;
    }

    /**
     * 采购退货草稿生成正式单据
     * @param document 采购退货操作
     * @return
     */
    private IOpResult createPurchaseReturn(IStockReport document) {
        return null;
    }

    /**
     * 库存发货草稿生成正式单据
     * @param document 库存发货草稿
     * @return
     */
    private IOpResult createGoodsIssue(IStockReport document){
        return null;
    }

    /**
     * 库存收货草稿生成正式单据
     * @param document 库存收货草稿
     * @return
     */
    private IOpResult createGoodsReceipt(IStockReport document){
        return null;
    }

    /**
     * 库存转储草稿生成正式单据
     * @param document
     * @return
     */
    private IOpResult createStockTransfer(IStockReport document){
        return null;
    }
}

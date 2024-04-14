package com.ims.service;

import com.ims.dto.DNewStock;
import com.ims.dto.DStock;

import java.util.List;

public interface StockService {
    List<DStock> getAllStocks();
    DStock getStockById(Long id);
    DStock updateStockById(long id, DNewStock stockDto);
    void deleteStockById(long id);
    DStock createStock(DNewStock stock);
    List<DStock> getStocksByTag(String tag);
    List<DStock> getStocksByStatus(String status);
    boolean stockExistsWithId(long id);
}

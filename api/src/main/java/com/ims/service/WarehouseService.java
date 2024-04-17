package com.ims.service;

import com.ims.dto.DNewWarehouse;
import com.ims.dto.DStock;
import com.ims.dto.DWarehouse;

import java.util.List;

public interface WarehouseService {
    List<DWarehouse> getAllWarehouses();
    DWarehouse getWarehouseById(Long id);
    DWarehouse updateWarehouseById(long id, DNewWarehouse warehouseDto);
    void deleteWarehouseById(long id);
    DWarehouse createWarehouse(DNewWarehouse warehouse);
    boolean warehouseExistsWithId(long id);

    List<DStock> getWarehouseStocks(long warehouseId);

    void linkStock(long warehouse_id, long stock_id);
}

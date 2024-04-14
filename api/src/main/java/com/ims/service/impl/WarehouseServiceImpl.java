package com.ims.service.impl;

import com.ims.dto.DNewWarehouse;
import com.ims.dto.DStock;
import com.ims.dto.DWarehouse;
import com.ims.entity.EManager;
import com.ims.entity.EProvider;
import com.ims.entity.EStock;
import com.ims.entity.EWarehouse;
import com.ims.exception.ResourceNotFoundException;
import com.ims.repository.ManagerRepository;
import com.ims.repository.StockRepository;
import com.ims.repository.WarehouseRepository;
import com.ims.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarehouseServiceImpl implements WarehouseService {
    @Autowired
    private WarehouseRepository warehouseRepository;
    @Autowired
    private ManagerRepository managerRepository;
    @Autowired
    private StockRepository stockRepository;

    @Override
    public List<DWarehouse> getAllWarehouses() {
        return warehouseRepository.findAll().stream().map(this::mapToDTO).toList();
    }

    @Override
    public DWarehouse getWarehouseById(Long id) {
        EWarehouse warehouse = warehouseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Warehouse", "id", id));
        return mapToDTO(warehouse);
    }

    @Override
    public DWarehouse updateWarehouseById(long id, DNewWarehouse warehouseDto) {
        EWarehouse warehouse = warehouseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Warehouse", "id", id));

        warehouse.setName(warehouseDto.getName());
        warehouse.setLocation(warehouseDto.getLocation());

        if (warehouseDto.getManager_id() != null) {
            EManager manager = managerRepository.findById(warehouseDto.getManager_id()).orElseThrow(() -> new ResourceNotFoundException("Manager", "id", warehouseDto.getManager_id()));
            warehouse.setManager(manager);
        }

        return mapToDTO(warehouseRepository.save(warehouse));
    }

    @Override
    public void deleteWarehouseById(long id) {
        warehouseRepository.deleteById(id);
    }

    @Override
    public DWarehouse createWarehouse(DNewWarehouse warehouse) {
        EWarehouse newWarehouse = new EWarehouse();
        newWarehouse.setName(warehouse.getName());
        newWarehouse.setLocation(warehouse.getLocation());
        if (warehouse.getManager_id() != null) {
            EManager manager = managerRepository.findById(warehouse.getManager_id()).orElseThrow(() -> new ResourceNotFoundException("Manager", "id", warehouse.getManager_id()));
            newWarehouse.setManager(manager);
        }
        return mapToDTO(warehouseRepository.save(newWarehouse));
    }

    @Override
    public boolean warehouseExistsWithId(long id) {
        return warehouseRepository.existsById(id);
    }

    @Override
    public List<DStock> getWarehouseStocks(long warehouseId) {
        EWarehouse warehouse = warehouseRepository.findById(warehouseId).orElseThrow(() -> new ResourceNotFoundException("Warehouse", "id", warehouseId));
        return warehouse.getStocks().stream().map(this::mapToDTO).toList();
    }

    @Override
    public void linkStock(long warehouse_id, long stock_id) {
        EWarehouse warehouse = warehouseRepository.findById(warehouse_id).orElseThrow(() -> new ResourceNotFoundException("Warehouse", "id", warehouse_id));
        EStock stock = stockRepository.findById(stock_id).orElseThrow(() -> new ResourceNotFoundException("Stock", "id", stock_id));

        stock.setWarehouse(warehouse);

        stockRepository.save(stock);
    }

    private DWarehouse mapToDTO(EWarehouse warehouse) {
        return new DWarehouse(warehouse.getId(), warehouse.getName(), warehouse.getLocation(), warehouse.getManager() == null ? null : warehouse.getManager().getId());
    }

    private DStock mapToDTO(EStock stock) {
        return new DStock(stock.getId(), stock.getName(), stock.getStatus().name(), stock.getTags(), stock.getAvailableQuantity(), stock.getPrice());
    }
}

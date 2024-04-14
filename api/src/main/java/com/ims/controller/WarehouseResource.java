package com.ims.controller;

import com.ims.dto.DNewStock;
import com.ims.dto.DNewWarehouse;
import com.ims.dto.DStock;
import com.ims.dto.DWarehouse;
import com.ims.exception.BadRequestException;
import com.ims.exception.ResourceNotFoundException;
import com.ims.service.WarehouseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ims-api/v1/warehouses")
public class WarehouseResource {
    @Autowired
    private WarehouseService warehouseService;

    @GetMapping("/")
    public ResponseEntity<List<DWarehouse>> getAllWarehouses() {
        List<DWarehouse> warehouses = warehouseService.getAllWarehouses();
        if (warehouses.size() == 0) {
            throw new ResourceNotFoundException("Warehouses not found(there is currently no warehouses)");
        } else {
            return ResponseEntity.ok().body(warehouses);
        }
    }

    @PostMapping("/")
    public ResponseEntity<DWarehouse> createWarehouse(@Valid @RequestBody DWarehouse warehouseDto) {
        if (warehouseDto.getId() != null) {
            throw new BadRequestException("Invalid warehouse data provided. the body can't have an id");
        }
        return new ResponseEntity<>(warehouseService.createWarehouse(new DNewWarehouse(warehouseDto.getName(), warehouseDto.getLocation(), null)), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DWarehouse> getWarehouseById(@PathVariable(name = "id") long id) {
        if (!warehouseService.warehouseExistsWithId(id)) {
            throw new ResourceNotFoundException("Warehouse with specified id(" + id + ") not found");
        } else {
            DWarehouse warehouse = warehouseService.getWarehouseById(id);
            return ResponseEntity.ok().body(warehouse);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<DWarehouse> updateWarehouseById(@RequestBody DNewWarehouse warehouseDto, @PathVariable(name = "id") long id) {
        if (!warehouseService.warehouseExistsWithId(id)) {
            throw new ResourceNotFoundException("Warehouse with specified id(" + id + ") not found");
        }

        DWarehouse warehouse = warehouseService.getWarehouseById(id);
        DNewWarehouse toUpdate = new DNewWarehouse(
                warehouseDto.getName() == null || warehouseDto.getName().isEmpty() ? warehouse.getName() : warehouseDto.getName(),
                warehouseDto.getLocation() == null || warehouseDto.getLocation().isEmpty() ? warehouse.getLocation() : warehouseDto.getLocation(),
                warehouseDto.getManager_id() == null ? warehouse.getManager_id() : warehouseDto.getManager_id()
        );

        DWarehouse updated = warehouseService.updateWarehouseById(id, toUpdate);
        return ResponseEntity.ok().body(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteWarehouseById(@PathVariable(name = "id") long id) {
        if (!warehouseService.warehouseExistsWithId(id)) {
            throw new ResourceNotFoundException("Warehouse with specified id(" + id + ") not found");
        } else {
            warehouseService.deleteWarehouseById(id);
            return ResponseEntity.ok().body("The warehouse was successfully deleted");
        }
    }

    @GetMapping("/{id}/stocks")
    public ResponseEntity<List<DStock>> getAllStocks(@PathVariable(name = "id") long id) {
        List<DStock> stocks = warehouseService.getWarehouseStocks(id);
        if (stocks.size() == 0) {
            throw new ResourceNotFoundException("Stocks not found(there is currently no stocks in this warehouse)");
        } else {
            return ResponseEntity.ok().body(stocks);
        }
    }

    @PutMapping("/{id}/linkStock/{stock_id}")
    public ResponseEntity<String> linkStock(@PathVariable(name = "id") long id, @PathVariable(name = "stock_id") long stock_id) {
        warehouseService.linkStock(id, stock_id);
        return ResponseEntity.ok().body("The stock was successfully linked with the warehouse");
    }
}

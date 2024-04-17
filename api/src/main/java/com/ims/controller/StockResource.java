package com.ims.controller;

import com.ims.dto.DNewStock;
import com.ims.dto.DStock;
import com.ims.exception.BadRequestException;
import com.ims.exception.ResourceNotFoundException;
import com.ims.service.StockService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ims-api/v1")
public class StockResource {
    @Autowired
    private StockService stockService;

    @GetMapping("/stocks")
    public ResponseEntity<List<DStock>> getAllStocks() {
        List<DStock> stocks = stockService.getAllStocks();
        if (stocks.size() == 0) {
            throw new ResourceNotFoundException("Stocks not found(there is currently no stocks)");
        } else {
            return ResponseEntity.ok().body(stocks);
        }
    }

    @PostMapping("/stocks")
    public ResponseEntity<DStock> createStock(@Valid @RequestBody DStock stockDto) {
        if (stockDto.getId() != null) {
            throw new BadRequestException("Invalid stock data provided. the body can't have an id");
        }
        return new ResponseEntity<>(stockService.createStock(new DNewStock(stockDto.getName(), stockDto.getStatus(), stockDto.getTags(), stockDto.getAvailableQuantity(), stockDto.getPrice())), HttpStatus.CREATED);
    }

    @GetMapping("/stocks/{id}")
    public ResponseEntity<DStock> getStockById(@PathVariable(name = "id") long id) {
        if (!stockService.stockExistsWithId(id)) {
            throw new ResourceNotFoundException("Stock with specified id(" + id + ") not found");
        } else {
            DStock stock = stockService.getStockById(id);
            return ResponseEntity.ok().body(stock);
        }
    }

    @PutMapping("/stocks/{id}")
    public ResponseEntity<DStock> updateStockById(@RequestBody DNewStock stockDto, @PathVariable(name = "id") long id) {
        if (!stockService.stockExistsWithId(id)) {
            throw new ResourceNotFoundException("Stock with specified id(" + id + ") not found");
        }

        DStock stock = stockService.getStockById(id);
        DNewStock toUpdate = new DNewStock(
                stockDto.getName() == null || stockDto.getName().isEmpty() ? stock.getName() : stockDto.getName(),
                stockDto.getStatus() == null || stockDto.getStatus().isEmpty() ? stock.getStatus() : stockDto.getStatus(),
                stockDto.getTags() == null || stockDto.getTags().isEmpty() ? stock.getTags() : stockDto.getTags(),
                stockDto.getAvailableQuantity() == null ? stock.getAvailableQuantity() : stockDto.getAvailableQuantity(),
                stockDto.getPrice() == null ? stock.getPrice() : stockDto.getPrice()
        );

        DStock updated = stockService.updateStockById(id, toUpdate);
        return ResponseEntity.ok().body(updated);
    }

    @DeleteMapping("/stocks/{id}")
    public ResponseEntity<Object> deleteStockById(@PathVariable(name = "id") long id) {
        if (!stockService.stockExistsWithId(id)) {
            throw new ResourceNotFoundException("Stock with specified id(" + id + ") not found");
        } else {
            stockService.deleteStockById(id);
            return ResponseEntity.ok().body("The stock was successfully deleted");
        }
    }

    @GetMapping("/stocksByTag/{tag}")
    public ResponseEntity<List<DStock>> getStocksByTag(@PathVariable(name = "tag") String tag) {
        List<DStock> stocks = stockService.getStocksByTag(tag);
        if (stocks.size() == 0) {
            throw new ResourceNotFoundException("Stocks not found(there is currently no stocks)");
        } else {
            return ResponseEntity.ok().body(stocks);
        }
    }

    @GetMapping("/stocksByStatus/{status}")
    public ResponseEntity<List<DStock>> getStocksByStatus(@PathVariable(name = "status") String status) {
        List<DStock> stocks = stockService.getStocksByStatus(status);
        if (stocks.size() == 0) {
            throw new ResourceNotFoundException("Stocks not found(there is currently no stocks)");
        } else {
            return ResponseEntity.ok().body(stocks);
        }
    }
}


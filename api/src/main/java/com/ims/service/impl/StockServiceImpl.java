package com.ims.service.impl;

import com.ims.dto.DNewStock;
import com.ims.dto.DStock;
import com.ims.entity.EStock;
import com.ims.exception.BadRequestException;
import com.ims.exception.ResourceNotFoundException;
import com.ims.repository.StockRepository;
import com.ims.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private StockRepository stockRepository;

    @Override
    public List<DStock> getAllStocks() {
        return stockRepository.findAll().stream().map(this::mapToDTO).toList();
    }

    @Override
    public DStock getStockById(Long id) throws ResourceNotFoundException {
        EStock stock = stockRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Stock", "id", id));
        return mapToDTO(stock);
    }

    @Override
    public DStock updateStockById(long id, DNewStock stockDto) {
        EStock stock = stockRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Stock", "id", id));

        try {
            stock.setName(stockDto.getName());
            stock.setTags(stockDto.getTags());
            stock.setStatus(EStock.Status.valueOf(stockDto.getStatus()));
            stock.setAvailableQuantity(stockDto.getAvailableQuantity());
            stock.setPrice(stockDto.getPrice());
        } catch (IllegalArgumentException ex)  {
            throw new BadRequestException("Invalid value for status provided.");
        }

        return mapToDTO(stockRepository.save(stock));
    }

    @Override
    public void deleteStockById(long id) {
        stockRepository.deleteById(id);
    }

    @Override
    public DStock createStock(DNewStock stockDto) {
        EStock newStock = new EStock();
        newStock.setName(stockDto.getName());
        newStock.setTags(stockDto.getTags());
        newStock.setStatus(EStock.Status.valueOf(stockDto.getStatus()));
        newStock.setAvailableQuantity(stockDto.getAvailableQuantity());
        newStock.setPrice(stockDto.getPrice());
        return mapToDTO(stockRepository.save(newStock));
    }

    @Override
    public List<DStock> getStocksByTag(String tag) {
        return stockRepository.findByTags(tag).stream().map(this::mapToDTO).toList();
    }

    @Override
    public List<DStock> getStocksByStatus(String status) {
        return stockRepository.findByStatus(EStock.Status.valueOf(status)).stream().map(this::mapToDTO).toList();
    }

    private DStock mapToDTO(EStock stock) {
        return new DStock(stock.getId(), stock.getName(), stock.getStatus().name(), stock.getTags(), stock.getAvailableQuantity(), stock.getPrice());
    }

    @Override
    public boolean stockExistsWithId(long id) {
        return stockRepository.existsById(id);
    }
}

package com.ims.controller;

import com.ims.dto.*;
import com.ims.exception.BadRequestException;
import com.ims.exception.ResourceNotFoundException;
import com.ims.service.ProviderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ims-api/v1/providers")
public class ProviderController {
    @Autowired
    private ProviderService providerService;

    @GetMapping("/")
    public ResponseEntity<List<DProvider>> getAllProviders() {
        List<DProvider> providers = providerService.getAllProviders();
        if (providers.size() == 0) {
            throw new ResourceNotFoundException("Providers not found(there is currently no providers)");
        } else {
            return ResponseEntity.ok().body(providers);
        }
    }

    @PostMapping("/")
    public ResponseEntity<DProvider> createProvider(@Valid @RequestBody DProvider providerDto) {
        if (providerDto.getId() != null) {
            throw new BadRequestException("Invalid provider data provided. the body can't have an id");
        }
        return new ResponseEntity<>(providerService.createProvider(new DNewProvider(providerDto.getName(), providerDto.getType())), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DProvider> getProviderById(@PathVariable(name = "id") long id) {
        if (!providerService.providerExistsWithId(id)) {
            throw new ResourceNotFoundException("Provider with specified id(" + id + ") not found");
        } else {
            DProvider provider = providerService.getProviderById(id);
            return ResponseEntity.ok().body(provider);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<DProvider> updateProviderById(@RequestBody DNewProvider providerDto, @PathVariable(name = "id") long id) {
        if (!providerService.providerExistsWithId(id)) {
            throw new ResourceNotFoundException("Provider with specified id(" + id + ") not found");
        }

        DProvider provider = providerService.getProviderById(id);
        DNewProvider toUpdate = new DNewProvider(
                providerDto.getName() == null || providerDto.getName().isEmpty() ? provider.getName() : providerDto.getName(),
                providerDto.getType() == null || providerDto.getType().isEmpty() ? provider.getType() : providerDto.getType()
        );

        DProvider updated = providerService.updateProviderById(id, toUpdate);
        return ResponseEntity.ok().body(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProviderById(@PathVariable(name = "id") long id) {
        if (!providerService.providerExistsWithId(id)) {
            throw new ResourceNotFoundException("Provider with specified id(" + id + ") not found");
        } else {
            providerService.deleteProviderById(id);
            return ResponseEntity.ok().body("The provider was successfully deleted");
        }
    }

    @GetMapping("/{id}/stocks")
    public ResponseEntity<List<DStock>> getAllStocks(@PathVariable(name = "id") long id) {
        List<DStock> stocks = providerService.getProviderStocks(id);
        if (stocks.size() == 0) {
            throw new ResourceNotFoundException("Stocks not found(there is currently no stocks in this provider)");
        } else {
            return ResponseEntity.ok().body(stocks);
        }
    }

    @PutMapping("/{id}/linkStock/{stock_id}")
    public ResponseEntity<String> linkStock(@PathVariable(name = "id") long id, @PathVariable(name = "stock_id") long stock_id) {
        providerService.linkStock(id, stock_id);
        return ResponseEntity.ok().body("The stock was successfully linked with the provider");
    }
}

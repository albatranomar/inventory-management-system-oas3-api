package com.ims.service.impl;

import com.ims.dto.DNewProvider;
import com.ims.dto.DProvider;
import com.ims.dto.DStock;
import com.ims.entity.EProvider;
import com.ims.entity.EStock;
import com.ims.entity.EWarehouse;
import com.ims.exception.ResourceNotFoundException;
import com.ims.repository.ProviderRepository;
import com.ims.repository.StockRepository;
import com.ims.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProviderServiceImpl implements ProviderService {
    @Autowired
    private ProviderRepository providerRepository;
    @Autowired
    private StockRepository stockRepository;

    @Override
    public List<DProvider> getAllProviders() {
        return providerRepository.findAll().stream().map(this::mapToDTO).toList();
    }

    @Override
    public DProvider getProviderById(Long id) {
        EProvider provider = providerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Provider", "id", id));
        return mapToDTO(provider);
    }

    @Override
    public DProvider updateProviderById(long id, DNewProvider providerDto) {
        EProvider provider = providerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Provider", "id", id));

        provider.setName(providerDto.getName());
        provider.setType(providerDto.getType());

        return mapToDTO(providerRepository.save(provider));
    }

    @Override
    public void deleteProviderById(long id) {
        providerRepository.deleteById(id);
    }

    @Override
    public DProvider createProvider(DNewProvider provider) {
        EProvider newProvider = new EProvider();
        newProvider.setName(provider.getName());
        newProvider.setType(provider.getType());
        return mapToDTO(providerRepository.save(newProvider));
    }

    @Override
    public boolean providerExistsWithId(long id) {
        return providerRepository.existsById(id);
    }

    @Override
    public List<DStock> getProviderStocks(long providerId) {
        EProvider provider = providerRepository.findById(providerId).orElseThrow(() -> new ResourceNotFoundException("Provider", "id", providerId));
        return provider.getStocks().stream().map(this::mapToDTO).toList();
    }

    @Override
    public void linkStock(long provider_id, long stock_id) {
        EProvider provider = providerRepository.findById(provider_id).orElseThrow(() -> new ResourceNotFoundException("Provider", "id", provider_id));
        EStock stock = stockRepository.findById(stock_id).orElseThrow(() -> new ResourceNotFoundException("Stock", "id", stock_id));

        provider.getStocks().add(stock);

        providerRepository.save(provider);
    }

    private DProvider mapToDTO(EProvider provider) {
        return new DProvider(provider.getId(), provider.getName(), provider.getType());
    }

    private DStock mapToDTO(EStock stock) {
        return new DStock(stock.getId(), stock.getName(), stock.getStatus().name(), stock.getTags(), stock.getAvailableQuantity(), stock.getPrice());
    }
}

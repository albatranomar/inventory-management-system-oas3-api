package com.ims.service;

import com.ims.dto.*;

import java.util.List;

public interface ProviderService {
    List<DProvider> getAllProviders();
    DProvider getProviderById(Long id);
    DProvider updateProviderById(long id, DNewProvider providerDto);
    void deleteProviderById(long id);
    DProvider createProvider(DNewProvider provider);
    boolean providerExistsWithId(long id);

    List<DStock> getProviderStocks(long providerId);

    void linkStock(long provider_id, long stock_id);
}

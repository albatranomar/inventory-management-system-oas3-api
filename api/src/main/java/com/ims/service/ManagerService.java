package com.ims.service;

import com.ims.dto.DManager;
import com.ims.dto.DNewManager;

import java.util.List;

public interface ManagerService {
    List<DManager> getAllManagers();
    DManager getManagerById(Long id);
    DManager updateManagerById(long id, DNewManager managerDto);
    void deleteManagerById(long id);
    DManager createManager(DNewManager manager);
    boolean managerExistsWithId(long id);
}

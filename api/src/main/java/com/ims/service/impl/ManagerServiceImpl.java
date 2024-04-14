package com.ims.service.impl;

import com.ims.dto.DManager;
import com.ims.dto.DNewManager;
import com.ims.entity.EManager;
import com.ims.exception.ResourceNotFoundException;
import com.ims.repository.ManagerRepository;
import com.ims.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerServiceImpl implements ManagerService {
    @Autowired
    private ManagerRepository managerRepository;

    @Override
    public List<DManager> getAllManagers() {
        return managerRepository.findAll().stream().map(this::mapToDTO).toList();
    }

    @Override
    public DManager getManagerById(Long id) {
        EManager manager = managerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Manager", "id", id));
        return mapToDTO(manager);
    }

    @Override
    public DManager updateManagerById(long id, DNewManager managerDto) {
        EManager manager = managerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Manager", "id", id));

        manager.setName(managerDto.getName());
        manager.setPhoneNumber(managerDto.getPhone_number());

        return mapToDTO(managerRepository.save(manager));
    }

    @Override
    public void deleteManagerById(long id) {
        managerRepository.deleteById(id);
    }

    @Override
    public DManager createManager(DNewManager manager) {
        EManager newManager = new EManager();

        newManager.setName(manager.getName());
        newManager.setPhoneNumber(manager.getPhone_number());

        return mapToDTO(managerRepository.save(newManager));
    }

    @Override
    public boolean managerExistsWithId(long id) {
        return managerRepository.existsById(id);
    }

    private DManager mapToDTO(EManager manager) {
        return new DManager(manager.getId(), manager.getName(), manager.getPhoneNumber());
    }
}

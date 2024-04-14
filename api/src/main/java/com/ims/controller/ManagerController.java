package com.ims.controller;

import com.ims.dto.DManager;
import com.ims.dto.DNewManager;
import com.ims.dto.DNewProvider;
import com.ims.dto.DProvider;
import com.ims.exception.BadRequestException;
import com.ims.exception.ResourceNotFoundException;
import com.ims.service.ManagerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ims-api/v1/managers")
public class ManagerController {
    @Autowired
    private ManagerService managerService;

    @GetMapping("/")
    public ResponseEntity<List<DManager>> getAllProviders() {
        List<DManager> managers = managerService.getAllManagers();
        if (managers.size() == 0) {
            throw new ResourceNotFoundException("Managers not found(there is currently no managers)");
        } else {
            return ResponseEntity.ok().body(managers);
        }
    }

    @PostMapping("/")
    public ResponseEntity<DManager> createManager(@Valid @RequestBody DManager managerDto) {
        if (managerDto.getId() != null) {
            throw new BadRequestException("Invalid manager data provided. the body can't have an id");
        }
        return new ResponseEntity<>(managerService.createManager(new DNewManager(managerDto.getName(), managerDto.getPhone_number())), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DManager> getManagerById(@PathVariable(name = "id") long id) {
        if (!managerService.managerExistsWithId(id)) {
            throw new ResourceNotFoundException("Manager with specified id(" + id + ") not found");
        } else {
            DManager manager = managerService.getManagerById(id);
            return ResponseEntity.ok().body(manager);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<DManager> updateManagerById(@RequestBody DNewManager managerDto, @PathVariable(name = "id") long id) {
        if (!managerService.managerExistsWithId(id)) {
            throw new ResourceNotFoundException("Manager with specified id(" + id + ") not found");
        }

        DManager manager = managerService.getManagerById(id);
        DNewManager toUpdate = new DNewManager(
                managerDto.getName() == null || managerDto.getName().isEmpty() ? manager.getName() : managerDto.getName(),
                managerDto.getPhone_number() == null || managerDto.getPhone_number().isEmpty() ? manager.getPhone_number() : managerDto.getPhone_number()
        );

        DManager updated = managerService.updateManagerById(id, toUpdate);
        return ResponseEntity.ok().body(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteManagerById(@PathVariable(name = "id") long id) {
        if (!managerService.managerExistsWithId(id)) {
            throw new ResourceNotFoundException("Manager with specified id(" + id + ") not found");
        } else {
            managerService.deleteManagerById(id);
            return ResponseEntity.ok().body("The manager was successfully deleted");
        }
    }
}

package com.ims.repository;

import com.ims.entity.EWarehouse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarehouseRepository extends JpaRepository<EWarehouse, Long> {

}

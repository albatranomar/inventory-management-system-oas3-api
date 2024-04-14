package com.ims.repository;

import com.ims.entity.EStock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockRepository extends JpaRepository<EStock, Long> {
    List<EStock> findByTags(String tag);
    List<EStock> findByStatus(EStock.Status status);
}

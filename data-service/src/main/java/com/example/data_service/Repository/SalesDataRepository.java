package com.example.data_service.Repository;

import com.example.data_service.entity.SalesData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesDataRepository extends JpaRepository<SalesData, Long> {
}

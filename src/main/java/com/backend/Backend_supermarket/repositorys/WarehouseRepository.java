package com.backend.Backend_supermarket.repositorys;

import com.backend.Backend_supermarket.models.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
}
package com.backend.Backend_supermarket.repository;

import com.backend.Backend_supermarket.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}

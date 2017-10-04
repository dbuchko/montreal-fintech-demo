package io.pivotal.om.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.pivotal.om.domain.Order;

public interface OrderRepository extends JpaRepository<Order, String> {

}

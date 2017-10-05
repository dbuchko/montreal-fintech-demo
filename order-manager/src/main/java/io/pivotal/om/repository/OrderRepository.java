package io.pivotal.om.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.pivotal.om.domain.ClientOrder;

public interface OrderRepository extends JpaRepository<ClientOrder, Long> {

}

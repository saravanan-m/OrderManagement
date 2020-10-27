package com.order.management.repositories;

import com.order.management.entities.PODockEntity;
import com.order.management.entities.POEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PODockRepository extends JpaRepository<PODockEntity, Long> {

}

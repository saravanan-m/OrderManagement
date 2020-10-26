package com.order.management.repositories;

import com.order.management.entities.DockEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DockRepository extends JpaRepository<DockEntity,Long> {
}

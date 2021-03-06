package com.order.management.repositories;

import com.order.management.entities.POEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PORepository extends JpaRepository<POEntity, Long> {

}

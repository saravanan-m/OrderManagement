package com.order.management.repositories;

import com.order.management.entities.DockEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DockRepository extends JpaRepository<DockEntity, Long> {

    @Query(value = "select d.* from dock d join active_dock ad on d.id=ad.dock_id where ad.date = :date group by ad.dock_id", nativeQuery = true)
    List<DockEntity> findByDateDistinct(@Param("date") String date);
}

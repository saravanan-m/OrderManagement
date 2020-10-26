package com.order.management.repositories;

import com.order.management.entities.ActiveDockEntity;
import com.order.management.entities.DockEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActiveDockRepository extends JpaRepository<ActiveDockEntity, Long> {

    @Query(value = "select * from active_dock where dock_id = :dock_id and date = :date", nativeQuery = true)
    List<ActiveDockEntity> findByDockIdAndDate(@Param("dock_id") long dockId, @Param("date") String date);
}

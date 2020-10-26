package com.order.management.entities;

import com.order.management.dtos.ActiveDockDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "dock")
@Getter
@Setter
public class DockEntity extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "capacity")
    private int capacity;

    @OneToMany(mappedBy="dockEntity")
    private Set<ActiveDockEntity> activeDocks;
}

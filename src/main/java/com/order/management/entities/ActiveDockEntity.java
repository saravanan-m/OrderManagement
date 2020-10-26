package com.order.management.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "active_dock")
@Getter
@Setter
public class ActiveDockEntity extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date")
    private String date;

    @Column(name = "start_time")
    private int startTime;

    @Column(name = "end_time")
    private int endTime;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dock_id")
    private DockEntity dockEntity;
}

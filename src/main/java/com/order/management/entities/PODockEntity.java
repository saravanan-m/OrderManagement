package com.order.management.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "dock_po")
@Getter
@Setter
public class PODockEntity extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "active_dock_id")
    private ActiveDockEntity activeDock;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "po_id")
    private POEntity po;
}

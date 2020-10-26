package com.order.management.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "purchase_order")
@Getter
@Setter
public class POEntity extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "item_id")
    private int itemId;

    @Column(name = "quantity")
    private int quantity;
}

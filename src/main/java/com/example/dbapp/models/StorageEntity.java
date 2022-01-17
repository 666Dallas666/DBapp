package com.example.dbapp.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "storage", schema = "gun-shop", catalog = "")
public class StorageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @ManyToOne
    @JoinColumn(name = "catalogue_id", referencedColumnName = "id", nullable = false)
    private CatalogueEntity catalogueByCatalogueId;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id", nullable = false)
    private OrderEntity orderByOrderId;

}

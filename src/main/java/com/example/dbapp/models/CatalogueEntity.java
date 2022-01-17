package com.example.dbapp.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@Table(name = "catalogue", schema = "gun-shop", catalog = "")
public class CatalogueEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;


    @Column(name = "name", nullable = false, length = 30)
    private String name;

    @Column(name = "price", nullable = false)
    private Integer price;

    @Column(name = "category", nullable = false, length = 20)
    private String category;

    @Column(name = "caliber", nullable = false, length = 20)
    private String caliber;

    @Column(name = "ranges", nullable = false)
    private Integer range;

    @Column(name = "speed", nullable = false)
    private Integer speed;

    @ManyToOne
    @JoinColumn(name = "factory_id", referencedColumnName = "id", nullable = false)
    private FactoryEntity factoryByFactoryId;


    @OneToMany(mappedBy = "catalogueByCatalogueId", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Collection<StorageEntity> storagesById;

}

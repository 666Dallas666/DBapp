package com.example.dbapp.models;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
@Data
@Table(name = "factory", schema = "gun-shop", catalog = "")
public class FactoryEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name", nullable = false, length = 20)
    private String name;

    @Column(name = "product", nullable = false, length = 30)
    private String product;

    @Column(name = "workers", nullable = false)
    private Integer workers;

    @Column(name = "address", nullable = false, length = 50)
    private String address;

    @Column(name = "director", nullable = false, length = 50)
    private String director;

    @Column(name = "founded", nullable = false)
    private Timestamp founded;

    @Column(name = "income", nullable = false)
    private Integer income;


    @OneToMany(mappedBy = "factoryByFactoryId", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Collection<CatalogueEntity> cataloguesById;

    @ManyToOne
    @JoinColumn(name = "company_id", referencedColumnName = "id", nullable = false)
    private CompanyEntity companyByCompanyId;

    @OneToMany(mappedBy = "factoryByFactoryId", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Collection<FactoryProviderEntity> factoryProvidersById;
}

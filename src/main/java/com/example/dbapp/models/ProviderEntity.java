package com.example.dbapp.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@Table(name = "provider", schema = "gun-shop", catalog = "")
public class ProviderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name", nullable = false, length = 30)
    private String name;

    @Column(name = "material", nullable = false, length = 20)
    private String material;

    @Column(name = "address", nullable = false, length = 50)
    private String address;

    @Column(name = "director", nullable = false, length = 50)
    private String director;

    @Column(name = "workers", nullable = false)
    private Integer workers;

    @OneToMany(mappedBy = "providerByProviderId", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Collection<FactoryProviderEntity> factoryProvidersById;
}

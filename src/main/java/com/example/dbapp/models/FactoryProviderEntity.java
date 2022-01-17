package com.example.dbapp.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "factory_provider", schema = "gun-shop", catalog = "")
public class FactoryProviderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @ManyToOne
    @JoinColumn(name = "factory_id", referencedColumnName = "id", nullable = false)
    private FactoryEntity factoryByFactoryId;

    @ManyToOne
    @JoinColumn(name = "provider_id", referencedColumnName = "id", nullable = false)
    private ProviderEntity providerByProviderId;
}

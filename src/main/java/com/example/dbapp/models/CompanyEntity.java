package com.example.dbapp.models;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
@Data
@Table(name = "company", schema = "gun-shop", catalog = "")
public class CompanyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name", nullable = false, length = 20)
    private String name;

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

    @OneToMany(mappedBy = "companyByCompanyId", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Collection<FactoryEntity> factoriesById;
}

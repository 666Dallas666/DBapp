package com.example.dbapp.models;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
@Data
@Table(name = "orders", schema = "gun-shop", catalog = "")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "summ", nullable = false)
    private Integer summ;

    @Column(name = "date", nullable = false)
    private Timestamp date;

    @Column(name = "comment", nullable = true, length = 50)
    private String comment;

    @Column(name = "status", nullable = false, length = 20)
    private String status;

    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id", nullable = false)
    private ClientEntity clientByClientId;

    @OneToMany(mappedBy = "orderByOrderId", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Collection<StorageEntity> storagesById;
}

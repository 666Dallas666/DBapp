package com.example.dbapp.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "account", schema = "gun-shop", catalog = "")
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "login", nullable = false, length = 20)
    private String login;

    @Column(name = "password", nullable = false, length = 20)
    private String password;

}
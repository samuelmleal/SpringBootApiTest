package com.attornatus.Model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity (name = "adress")
@Table(name = "adress")
public class Adress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name= "name")
    private String street;
    @Column(name = "birth")
    private String cep;
    @Column(name = "number", nullable = false)
    private Integer number;
    @Column(name = "city", nullable = false)
    private String city;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id")
    private Person person;
}

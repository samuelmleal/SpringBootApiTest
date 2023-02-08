package com.attornatus.Model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "person")
public class Person {

    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name= "name")
    private String name;

    @Column(name = "birth")
    private String birth;
    @Column(name = "street", nullable = false)
    private String street;
    @Column(name = "cep", nullable = false)
    private String cep;
    @Column(name = "number", nullable = false)
    private Integer number;

    @Column(name = "city", nullable = false)
    private String city;
}

package com.attornatus.Model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "adress")
public class Adress {

    @Id
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
    @Column(name = "person_id")
    private String personId;
}

package com.attornatus.Model;

import com.attornatus.DTO.PersonDTO;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "person")
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name= "name", nullable = false)
    private String name;

    @Column(name = "birth", nullable = false)
    private String birth;
    @Column(name = "street", nullable = false)
    private String street;
    @Column(name = "cep", nullable = false)
    private String cep;
    @Column(name = "number", nullable = false)
    private Integer number;
    @Column(name = "city", nullable = false)
    private String city;

    public Person(PersonDTO personDTO) {
        this.name = personDTO.getName();
        this.birth = personDTO.getBirth();
        this.street =  personDTO.getAdress().getStreet();
        this.cep = personDTO.getAdress().getCep();
        this.number = personDTO.getAdress().getNumber();
        this.city = personDTO.getAdress().getCity();
    }

    public Person() {
    }
}

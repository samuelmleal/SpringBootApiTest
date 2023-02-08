package com.attornatus.Repository;

import com.attornatus.Model.Adress;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdressRepository<T extends Adress> extends JpaRepository<T, Integer> {

    Optional<T> findById(@Param("id") Integer id);

    Page<T> findAll(Pageable pageable);

    List<T> findByPerson(@Param("person_id") Integer personId);

    @Query(value = "INSERT INTO adress SET street = :street, cep = :cep, number = :number, \n" +
    "city = :city, person_id = :id")
    void create(
            @Param("street") String street,
            @Param("cep") String cep,
            @Param("city") String city,
            @Param("number") Integer number,
            @Param("id") Integer id
    );

}
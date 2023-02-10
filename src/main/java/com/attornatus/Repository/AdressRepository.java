package com.attornatus.Repository;

import com.attornatus.Model.Adress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@EnableJpaRepositories
public interface AdressRepository<T extends Adress> extends JpaRepository<T, Integer> {


    List<T> findByPerson(@Param("person_id") Integer personId);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO adress (street, cep, number, city, person_id) VALUES (:street, :cep, :number, :city, :id )",
    nativeQuery = true)
    void create(
            @Param("street") String street,
            @Param("cep") String cep,
            @Param("city") String city,
            @Param("number") Integer number,
            @Param("id") Integer id
    );

    @Transactional
    @Modifying
    @Query(value = "UPDATE person SET street=:street, cep=:cep, number=:number, city=:city WHERE id =:id "
            ,nativeQuery = true)
    void updateAdressPrincipal(@Param("street") String street,
                      @Param("cep") String cep,
                      @Param("city") String city,
                      @Param("number") Integer number,
                      @Param("id") Integer id);


    @Query(value = "SELECT * FROM person WHERE id=:id", nativeQuery = true)
    Integer validatePerson(@Param("id") Integer id);

}

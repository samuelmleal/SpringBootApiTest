package com.attornatus.Repository;
import com.attornatus.Model.Person;
import io.swagger.models.auth.In;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface PersonRepository<T extends Person> extends JpaRepository<T, Integer> {

    @Query(value = "SELECT * FROM person WHERE id =:id", nativeQuery = true)
    Optional<T> findById(@Param("id") Integer id);
    @Query(value = "SELECT * FROM person", nativeQuery = true)
    List<T> findAll();

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO person (name, birth, street, cep, number, city) VALUES (:name, :birth, :street, :cep, :number, :city)"
    ,nativeQuery = true)
    Optional<T> createPerson(@Param("name") String name,
                      @Param("birth") String birth,
                      @Param("street") String street,
                      @Param("cep") String cep,
                      @Param("city") String city,
                      @Param("number") Integer number);

    @Transactional
    @Modifying
    @Query(value = "UPDATE person SET name=:name, birth=:birth, street=:street, cep=:cep, number=:number, city=:city WHERE id =:id"
            ,nativeQuery = true)
    void updatePerson(@Param("name") String name,
                      @Param("birth") String birth,
                      @Param("street") String street,
                      @Param("cep") String cep,
                      @Param("city") String city,
                      @Param("number") Integer number,
                      @Param("id") Integer id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE person SET name = :name, birth = :birth WHERE id = :id", nativeQuery = true)
    void savePersonNotPrincipal(@Param("name") String name, @Param("birth") String birth, @Param("id") Integer id);

}

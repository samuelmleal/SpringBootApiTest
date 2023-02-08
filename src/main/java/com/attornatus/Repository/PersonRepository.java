package com.attornatus.Repository;

import com.attornatus.DTO.PersonDTO;
import com.attornatus.Model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository<T extends Person> extends JpaRepository<T, Integer> {

    Optional<T> findById(@Param("id") Integer id);

    List<T> findAll();

    @Transactional
    @Modifying
    @Query(value = "UPDATE person SET name = :name, birth = :birth WHERE id = :id")
    void savePerson(@Param("name") String name, @Param("birth") String birth, @Param("id") Integer id);

}

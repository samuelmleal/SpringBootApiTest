package com.attornatus.Service;

import com.attornatus.DTO.AdressDTO;
import com.attornatus.DTO.PersonDTO;
import com.attornatus.Model.Adress;
import com.attornatus.Model.Person;
import com.attornatus.Repository.AdressRepository;
import com.attornatus.Repository.PersonRepository;
import com.attornatus.Utils.PersonBuilder;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @Mock
    private AdressRepository adressRepository;

    @InjectMocks
    private PersonService personService;



    @Test
    void findAllSucesso(){
        PersonDTO personDTO = PersonBuilder.createFakeRequest();
        Person person = new Person(personDTO);
        personRepository.save(person);
        List<Person> response = personRepository.findAll();

        assertThat(response.isEmpty());
    }

    @Test
    void findByIdSucesso(){
        PersonDTO personDTO = PersonBuilder.createFakeRequest();
        Person person = new Person(personDTO);
        personRepository.save(person);
        Optional<Person> response = personRepository.findById(1);

        assertThat(response.isPresent());
    }

    @Test
    void updatePerson(){
        PersonDTO personDTO = PersonBuilder.createFakeRequest();
        Person person = new Person(personDTO);
        personRepository.save(person);


        AdressDTO updateAdressDTO = new AdressDTO("Rua bbbbb", "12333-000", 1, "São Paulo", true);
        PersonDTO updatePersonDTO = new PersonDTO("Lucas", "10-10-2010", updateAdressDTO);

        personService.updatePerson(updatePersonDTO,1);

        Optional<Person> response = personRepository.findById(1);

        assertThat(response.isPresent());
    }

}

package com.attornatus.Controller;

import com.attornatus.DTO.AdressDTO;
import com.attornatus.DTO.PersonDTO;
import com.attornatus.Model.Adress;
import com.attornatus.Model.Person;
import com.attornatus.Repository.PersonRepository;
import com.attornatus.Service.PersonService;
import com.attornatus.Utils.PersonBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(PersonController.class)
public class PersonControllerTest {


    @MockBean
    PersonService personService;

    @Autowired
    private MockMvc mockMvc;
    private final String URL = "/api/v1";

    private String objectToString(Person obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            return objectMapper.writeValueAsString(obj);
        }catch(JsonProcessingException ex){
            ex.printStackTrace();
        }
        return null;
    }
    @Test
    void  findByIdSucesso() throws Exception{
        PersonDTO personDTO = PersonBuilder.createFakeRequest();
        Optional<Person> personOptional = Optional.of(new Person(personDTO));
        Person person = new Person(personDTO);
        personService.createPerson(personDTO);
        Mockito.when(personService.getPersonById(Mockito.anyInt()))
                .thenReturn(personOptional);

        mockMvc.perform(get(URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectToString(person)));
    }

    @Test
    void findAllSucesso() throws Exception {
        PersonDTO personDTO = PersonBuilder.createFakeRequest();
        Optional<Person> personOptional = Optional.of(new Person(personDTO));
        personService.createPerson(personDTO);
        List<Person> response = personService.getAllPerson();
        Mockito.when(personService.getAllPerson())
                .thenReturn(response);

        mockMvc.perform(get(URL + "/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(response.toString()));
    }

    @Test
    void createPersonError() throws Exception{
        PersonDTO personDTO = PersonBuilder.createFakeRequest();
        Mockito.when(personService.createPerson(Mockito.any(PersonDTO.class)))
                .thenReturn(personDTO);

        mockMvc.perform(post(URL + "/createPerson")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(String.valueOf(personDTO)))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    void getAllAdressSucesso() throws Exception{
        PersonDTO personDTO = PersonBuilder.createFakeRequest();
        personService.createPerson(personDTO);
        List<Adress> response = personService.getAdressByPerson(1);
        Mockito.when(personService.getAdressByPerson(1))
                .thenReturn(response);

        mockMvc.perform(get(URL + "/getAdress/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(response.toString()));
    }

    @Test
    void createAdressWithNoPersonFailed() throws Exception{
        AdressDTO adressDTO = PersonBuilder.createFakeAdress();
        Mockito.when(personService.createAdress(adressDTO,1))
                .thenReturn(adressDTO);

        mockMvc.perform(post(URL + "/createAdress/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.valueOf(adressDTO)))
        .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }


}

package com.attornatus.Service;

import com.attornatus.Config.LoggerConfig;
import com.attornatus.DTO.AdressDTO;
import com.attornatus.DTO.PersonDTO;
import com.attornatus.Exceptions.BadRequestException;
import com.attornatus.Model.Adress;
import com.attornatus.Model.Person;
import com.attornatus.Repository.AdressRepository;
import com.attornatus.Repository.PersonRepository;
import org.hibernate.secure.spi.IntegrationException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    private final AdressRepository adressRepository;

    public PersonService(PersonRepository personRepository, AdressRepository adressRepository) {
        this.personRepository = personRepository;
        this.adressRepository = adressRepository;
    }

    public List<Person> getAllPerson() throws IntegrationException{
        try {
             List<Person> response = personRepository.findAll();
             if(response.isEmpty()){
                 LoggerConfig.warn(HttpStatus.NOT_FOUND, "Não há pessoas cadastradas no banco de dados");
                 throw new BadRequestException("Não há pessoas cadastradas no banco de dados");
             }else{
                 return response;
             }
        }catch (DataAccessException ex) {
            throw new IntegrationException("Houve uma falha ao enviar a request");
        }
    }

    public Optional<Person> getPersonById(Integer id) throws IntegrationException{
        try {
            Optional<Person> response = personRepository.findById(id);
            if(response.isEmpty()){
                LoggerConfig.warn(HttpStatus.NOT_FOUND, "Não foi encontrada pessoa com o id: " + id );
                throw new BadRequestException("Não foi encontrada pessoa com o id: " + id);
            }else{
                return response;
            }
        }catch (DataAccessException ex) {
            throw new IntegrationException("Houve uma falha ao enviar a request");
        }
    }

    public PersonDTO createPerson(PersonDTO personDTO) throws IntegrationException{
        try{
            Person person = new Person(personDTO);
            personRepository.save(person);
            if(personDTO.getAdress().getIsPrincipal()){
                adressRepository.create(personDTO.getAdress().getStreet(), personDTO.getAdress().getCep(), personDTO.getAdress().getCity(), personDTO.getAdress().getNumber(), person.getId());
            }
                return personDTO;
        }catch (DataAccessException ex) {
            throw new IntegrationException("Houve uma falha ao enviar a request");
        }
    }

    public PersonDTO updatePerson(PersonDTO personDTO, Integer id) throws IntegrationException{
        try{
            boolean exists = validatePerson(id);
            if(!exists){
                LoggerConfig.warn(HttpStatus.NOT_FOUND, "O id informado não existe no banco de dados");
                throw new BadRequestException("O id informado não existe no banco de dados");
            }
            else if(personDTO.getAdress().getIsPrincipal()) {
                Person person = new Person(personDTO, id);
                personRepository.save(person);
            }else{
                personRepository.savePersonNotPrincipal(personDTO.getName(), personDTO.getBirth(), id);
            }
            return personDTO;
        }catch (DataAccessException ex) {
            throw new IntegrationException("Houve uma falha ao enviar a request");
        }
    }

    public AdressDTO createAdress(AdressDTO adressDTO, Integer id) throws IntegrationException{
        try{
            boolean exists = validatePerson(id);
            if(!exists){
                LoggerConfig.warn(HttpStatus.NOT_FOUND, "O id informado não existe no banco de dados");
                throw new BadRequestException("O id informado não existe no banco de dados");
            }
            if(exists && adressDTO.getIsPrincipal()){
                adressRepository.updateAdressPrincipal(adressDTO.getStreet(),
                        adressDTO.getCep(),
                        adressDTO.getCity(),
                        adressDTO.getNumber(),
                        id);
            }
            else {
                adressRepository.create(
                        adressDTO.getStreet(),
                        adressDTO.getCep(),
                        adressDTO.getCity(),
                        adressDTO.getNumber(),
                        id);
            }
        }catch (DataAccessException ex) {
            throw new IntegrationException("Falha ao se comunicar com o banco de dados");
        }
        return adressDTO;
    }

    public List<Adress> getAdressByPerson(Integer id) throws BadRequestException{
        try{
            List<Adress> response = adressRepository.findByPerson(id);
            if(response.isEmpty()){
                LoggerConfig.warn(HttpStatus.NOT_FOUND, "Não foi encontrada pessoa com o id: " + id);
                throw new BadRequestException("Não foi encontrada pessoa com o id: " + id);
            }else{
                return response;
            }
        }catch (Exception ex) {
            throw new BadRequestException("Não foi encontrada pessoa com o id: " + id);
        }
    }

    private boolean validatePerson(Integer id) {
        try {
            Integer response = adressRepository.validatePerson(id);
            if(response == null) {
                return false;
            } else {
                return true;
            }
        }catch (DataAccessException ex) {
            throw new IntegrationException("Falha ao se comunicar com o banco de dados");
        }
    }

}

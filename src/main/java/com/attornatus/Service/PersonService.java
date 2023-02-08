package com.attornatus.Service;

import com.attornatus.DTO.AdressDTO;
import com.attornatus.DTO.PersonDTO;
import com.attornatus.Repository.AdressRepository;
import com.attornatus.Repository.PersonRepository;
import org.hibernate.secure.spi.IntegrationException;
import org.springframework.dao.DataAccessException;
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

    public List<PersonDTO> getAllPerson() throws IntegrationException{
        try {
            return personRepository.findAll();
        }catch (DataAccessException ex) {
            throw new IntegrationException("Falha na integração com banco de dados");
        }
    }

    public Optional<PersonDTO> getPersonById(Integer id) throws IntegrationException{
        try {
            return personRepository.findById(id);
        }catch (DataAccessException ex) {
            throw new IntegrationException("Falha na integração com banco de dados");
        }
    }

    public PersonDTO createPerson(PersonDTO personDTO) throws IntegrationException{
        try{
            this.personRepository.save(personDTO);
            return personDTO;
        }catch (DataAccessException ex) {
            throw new IntegrationException("Falha ao persistir objeto no banco de dados");
        }
    }

    public PersonDTO updatePerson(PersonDTO personDTO, Integer id) throws IntegrationException{
        try{
            if(personDTO.getAdress().getIsPrincipal() == true) {
                this.personRepository.save(personDTO);
            }else{
                this.personRepository.savePerson(personDTO.getName(), personDTO.getBirth(), id);
            }
        }catch (DataAccessException ex) {
            throw new IntegrationException("Falha ao persistir objeto no banco de dados");
        }
        return personDTO;
    }

    public AdressDTO createAdress(AdressDTO adressDTO, Integer id) throws IntegrationException{
        try{
            this.adressRepository.create(
                    adressDTO.getStreet(),
                    adressDTO.getCep(),
                    adressDTO.getCity(),
                    adressDTO.getNumber(),
                    id);
        }catch (DataAccessException ex) {
            throw new IntegrationException("Falha ao persistir objeto no banco de dados");
        }
        return adressDTO;
    }

    public List<AdressDTO> getAdressByPerson(Integer id) throws IntegrationException{
        try{
            return adressRepository.findByPerson(id);
        }catch (DataAccessException ex) {
            throw new IntegrationException("Falha ao persistir objeto no banco de dados");
        }
    }

}

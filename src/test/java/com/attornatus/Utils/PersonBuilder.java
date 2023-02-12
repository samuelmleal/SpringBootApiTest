package com.attornatus.Utils;

import com.attornatus.DTO.AdressDTO;
import com.attornatus.DTO.PersonDTO;

public class PersonBuilder {

    private static final String name = "Samuel";

    private static final String birth = "18-10-2002";

    private static final String street = "Rua aaaaa";

    private static final Integer number = 1;

    private static final String cep =  "11123-000";

    private static final String city = "Itatiba";

    public static PersonDTO createFakeRequest() {
        AdressDTO adressDTO = new AdressDTO(street,city,number,cep, true);
        PersonDTO personDTO = new PersonDTO(name, birth, adressDTO);
        return personDTO;
    }
    public static AdressDTO createFakeAdress(){
        AdressDTO adressDTO = new AdressDTO(street,city,number,cep, true);
        return adressDTO;
    }

}

package com.attornatus.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonDTO {

    @NotBlank(message = "O nome é obrigatório")
    @JsonProperty(value = "name")
    private String name;
    @NotBlank(message= "A data de nascimento é obrigatória")
    @JsonProperty(value = "birth")
    private String birth;

    private AdressDTO adress;

}



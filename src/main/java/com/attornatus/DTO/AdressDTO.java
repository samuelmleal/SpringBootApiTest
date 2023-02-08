package com.attornatus.DTO;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdressDTO {


    @JsonProperty(value = "street")
    @NotBlank(message = "O logradouro é obrigatório")
    private String street;
    @Pattern(regexp = "(^\\d{5}-\\d{3}$)", message = "CEP inválido. Padrão: 00000-000")
    @NotBlank(message = "O CEP é obrigatório")
    @JsonProperty(value = "cep")
    private String cep;
    @NotBlank(message = "Número é obrigatório")
    @JsonProperty(value = "number")
    private Integer number;

    @NotBlank(message = "Cidade é obrigatório")
    @JsonProperty(value = "city")
    private String city;

    @NotBlank(message = "Deve informar se é o endereço principal")
    @JsonProperty(value = "isPrincipal")
    private boolean isPrincipal;

    public boolean getIsPrincipal() {
        return isPrincipal;
    }
}

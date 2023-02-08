package com.attornatus.Controller;

import com.attornatus.DTO.AdressDTO;
import com.attornatus.DTO.ErrorDetail;
import com.attornatus.DTO.PersonDTO;
import com.attornatus.Service.PersonService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("api/v1")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @Operation(summary = "Busca pessoa por id")
    @ApiResponse(responseCode = "200", description = "Requisição feita no serviço", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = PersonDTO.class)) })
    @ApiResponse(responseCode = "400", description = "Dados de entrada invalidos", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetail.class)))
    @ApiResponse(responseCode = "500", description = "Falha no processamento", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetail.class)))
    @ApiResponse(responseCode = "503", description = "Falha ao se comunicar com o banco de dados", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetail.class)))
    @GetMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<PersonDTO>> findById (@Valid @PathVariable("id") @NotNull Integer id) {
        Optional<PersonDTO> response = personService.getPersonById(id);
        return ResponseEntity.ok(response);
    }
    @Operation(summary = "Retorna todas as pessoas em formato de lista")
    @ApiResponse(responseCode = "200", description = "Resposta da operação", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = PersonDTO.class)) })
    @ApiResponse(responseCode = "500", description = "Falha no processamento", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetail.class)))
    @ApiResponse(responseCode = "503", description = "Falha ao se comunicar com o banco de dados", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetail.class)))
    @GetMapping("/")
    public ResponseEntity<List<PersonDTO>>  findAll(){
        List<PersonDTO> response = personService.getAllPerson();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Cria uma pessoa")
    @ApiResponse(responseCode = "200", description = "Resposta da operação", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = PersonDTO.class)) })
    @ApiResponse(responseCode = "500", description = "Falha no processamento", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetail.class)))
    @ApiResponse(responseCode = "503", description = "Falha ao se comunicar com o banco de dados", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetail.class)))
    @PostMapping("/createPerson")
    public ResponseEntity<PersonDTO> create(@RequestBody PersonDTO personDTO){
        PersonDTO response = personService.createPerson(personDTO);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Atualiza os dados de uma pessoa")
    @ApiResponse(responseCode = "200", description = "Resposta da operação", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = PersonDTO.class)) })
    @ApiResponse(responseCode = "500", description = "Falha no processamento", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetail.class)))
    @ApiResponse(responseCode = "503", description = "Falha ao se comunicar com o banco de dados", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetail.class)))
    @PutMapping("/updatePerson/{id}")
    public ResponseEntity<PersonDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody PersonDTO personDTO){
        PersonDTO response = personService.updatePerson(personDTO, id);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Cria um endereço, ligado a uma pessoa")
    @ApiResponse(responseCode = "200", description = "Resposta da operação", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = AdressDTO.class)) })
    @ApiResponse(responseCode = "500", description = "Falha no processamento", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetail.class)))
    @ApiResponse(responseCode = "503", description = "Falha ao se comunicar com o banco de dados", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetail.class)))
    @PostMapping("/createAdress/{id}")
    public ResponseEntity<AdressDTO> createAdress(@Valid @PathVariable("id") Integer id, @RequestBody AdressDTO adressDTO){
        AdressDTO response = personService.createAdress(adressDTO, id);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Busca o endereço ligado a uma pessoa")
    @ApiResponse(responseCode = "200", description = "Resposta da operação", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = AdressDTO.class)) })
    @ApiResponse(responseCode = "500", description = "Falha no processamento", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetail.class)))
    @ApiResponse(responseCode = "503", description = "Falha ao se comunicar com o banco de dados", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetail.class)))
    @PostMapping("/getAdress/{id}")
    public ResponseEntity<List<AdressDTO>> getAdress(@Valid @PathVariable("id") Integer id){
        List<AdressDTO> response = personService.getAdressByPerson(id);
        return ResponseEntity.ok(response);
    }
}
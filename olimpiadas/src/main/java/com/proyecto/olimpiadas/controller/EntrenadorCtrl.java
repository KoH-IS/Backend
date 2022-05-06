package com.proyecto.olimpiadas.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.exception.ApiException;
import com.proyecto.olimpiadas.dto.ApiResponse;
import com.proyecto.olimpiadas.entity.Disciplina;
import com.proyecto.olimpiadas.entity.Entrenador;
import com.proyecto.olimpiadas.service.EntrenadorService;

@RestController
@RequestMapping("entrenador")
public class EntrenadorCtrl {

	@Autowired
	EntrenadorService svc;
	
	@GetMapping
	public ResponseEntity<List<Entrenador>> getEntrenadores(){
		return new ResponseEntity<>(svc.getEntrenadores(), HttpStatus.OK);
	}
	
	@GetMapping("/email")
	public ResponseEntity<Entrenador> getEntrenador(@PathVariable("email") String email){
		return new ResponseEntity<>(svc.getEntrenador(email), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<ApiResponse> createEntrenador(@Valid @RequestBody Entrenador in, BindingResult bindingResult){
		if(bindingResult.hasErrors())
			throw new ApiException(HttpStatus.BAD_REQUEST, bindingResult.getAllErrors().get(0).getDefaultMessage());
		return new ResponseEntity<>(svc.createEntrenador(in), HttpStatus.OK);
	}
	
	@PutMapping("/{id_entrenador}")
	public ResponseEntity<ApiResponse> updateEntrenador(@PathVariable("id_entrenador") Integer id_entrenador, @Valid @RequestBody Entrenador in, BindingResult bindingResult){
		if(bindingResult.hasErrors())
			throw new ApiException(HttpStatus.BAD_REQUEST, bindingResult.getAllErrors().get(0).getDefaultMessage());
		return new ResponseEntity<>(svc.updateEntrenador(in, id_entrenador), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id_entrenador}")
	public ResponseEntity<ApiResponse> deleteEntrenador(@PathVariable("id_entrenador") Integer id_entrenador){
		return new ResponseEntity<>(svc.deleteEntrenador(id_entrenador), HttpStatus.OK);
	}
	
	@PutMapping("/{id_competidor}/disciplina")
	public ResponseEntity<ApiResponse> updateEntrenadorDisciplina(@PathVariable("id_entrenador") Integer id_competidor, @RequestBody Disciplina in){
		return new ResponseEntity<>(svc.updateEntrenadorDisciplina(in, id_competidor), HttpStatus.OK);
	}
}
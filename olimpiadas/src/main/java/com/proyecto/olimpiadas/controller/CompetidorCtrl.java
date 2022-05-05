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
import com.proyecto.olimpiadas.entity.Competidor;
import com.proyecto.olimpiadas.entity.Disciplina;
import com.proyecto.olimpiadas.entity.Entrenador;
import com.proyecto.olimpiadas.service.CompetidorService;

@RestController
@RequestMapping("/competidor")
public class CompetidorCtrl {

	@Autowired
	CompetidorService svc;
	
	@GetMapping
	public ResponseEntity<List<Competidor>> getCompetidores(){
		return new ResponseEntity<>(svc.getCompetidores(), HttpStatus.OK);
	}
	
	@GetMapping("/{email}")
	public ResponseEntity<Competidor> getCompetidor(@PathVariable("email") String email){
		return new ResponseEntity<>(svc.getCompetidor(email), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<ApiResponse> createCompetidor(@Valid @RequestBody Competidor in, BindingResult bindingResult){
		if(bindingResult.hasErrors())
			throw new ApiException(HttpStatus.BAD_REQUEST, bindingResult.getAllErrors().get(0).getDefaultMessage());
		return new ResponseEntity<>(svc.createCompetidor(in), HttpStatus.OK);
	}
	
	@PutMapping("/{id_competidor}")
	public ResponseEntity<ApiResponse> updateCompetidor(@PathVariable("id_competidor") Integer id_competidor, @Valid @RequestBody Competidor in, BindingResult bindingResult){
		if(bindingResult.hasErrors())
			throw new ApiException(HttpStatus.BAD_REQUEST, bindingResult.getAllErrors().get(0).getDefaultMessage());
		return new ResponseEntity<>(svc.updateCompetidor(in, id_competidor), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id_competidor}")
	public ResponseEntity<ApiResponse> deleteCompetidor(@PathVariable("id_competidor") Integer id_competidor){
		return new ResponseEntity<>(svc.deleteCompetidor(id_competidor), HttpStatus.OK);
	}
	
	@PutMapping("/{id_competidor}/disciplina")
	public ResponseEntity<ApiResponse> updateCompetidorDisciplina(@PathVariable("id_competidor") Integer id_competidor, @RequestBody Disciplina in){
		return new ResponseEntity<>(svc.updateCompetidorDisciplina(in, id_competidor), HttpStatus.OK);
	}
	
	@PutMapping("/{id_competidor}/entrenador")
	public ResponseEntity<ApiResponse> updateCompetidorEntrenador(@PathVariable("id_competidor") Integer id_competidor, @RequestBody Entrenador in){
		return new ResponseEntity<>(svc.updateCompetidorEntrenador(in, id_competidor), HttpStatus.OK);
	}
}
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
import com.proyecto.olimpiadas.entity.Juez;
import com.proyecto.olimpiadas.service.JuezService;

@RestController
@RequestMapping("/juez")
public class JuezCtrl {

	@Autowired
	JuezService svc;
	
	@GetMapping
	public ResponseEntity<List<Juez>> getJueces(){
		return new ResponseEntity<>(svc.getJueces(), HttpStatus.OK);
	}
	
	@GetMapping("/{email}")
	public ResponseEntity<Juez> getJuez(@PathVariable("email") String email){
		return new ResponseEntity<>(svc.getJuez(email), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<ApiResponse> createJuez(@Valid @RequestBody Juez in, BindingResult bindingResult){
		if(bindingResult.hasErrors())
			throw new ApiException(HttpStatus.BAD_REQUEST, bindingResult.getAllErrors().get(0).getDefaultMessage());
		return new ResponseEntity<>(svc.createJuez(in), HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse> updateJuez(@PathVariable("id") Integer id, @Valid @RequestBody Juez in, BindingResult bindingResult){
		if(bindingResult.hasErrors())
			throw new ApiException(HttpStatus.BAD_REQUEST, bindingResult.getAllErrors().get(0).getDefaultMessage());
		return new ResponseEntity<>(svc.updateJuez(in, id), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteJuez(@PathVariable("id") Integer id){
		return new ResponseEntity<>(svc.deleteJuez(id), HttpStatus.OK);
	}
	
	@PutMapping("/{id}/disciplina")
	public ResponseEntity<ApiResponse> updateJuezDisciplina(@PathVariable("id") Integer id, @RequestBody Disciplina in){
		return new ResponseEntity<>(svc.updateJuezDisciplina(in, id), HttpStatus.OK);
	}
}
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
import com.proyecto.olimpiadas.dto.DtoDisciplinaList;
import com.proyecto.olimpiadas.entity.Disciplina;
import com.proyecto.olimpiadas.service.DisciplinaService;

@RestController
@RequestMapping("/disciplina")
public class DisciplinaCtrl {
	
	@Autowired
	DisciplinaService svc;
	
	@GetMapping
	public ResponseEntity<List<DtoDisciplinaList>> getDisciplinas(){
		return new ResponseEntity<>(svc.getDisciplinas(), HttpStatus.OK);
	}
	
	@GetMapping("/{disciplina}")
	public ResponseEntity<Disciplina> getDisciplina(@PathVariable("disciplina") String disciplina){
		return new ResponseEntity<>(svc.getDisciplina(disciplina), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<ApiResponse> createDisciplina(@Valid @RequestBody Disciplina in, BindingResult bindingResult){
		if(bindingResult.hasErrors())
			throw new ApiException(HttpStatus.BAD_REQUEST, bindingResult.getAllErrors().get(0).getDefaultMessage());
		return new ResponseEntity<>(svc.createDisciplina(in), HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse> updateDisciplina(@PathVariable("id") Integer id, @Valid @RequestBody Disciplina in, BindingResult bindingResult){
		if(bindingResult.hasErrors())
			throw new ApiException(HttpStatus.BAD_REQUEST, bindingResult.getAllErrors().get(0).getDefaultMessage());
		return new ResponseEntity<>(svc.updateDisciplina(id, in), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteDisciplina(@PathVariable("id") Integer id){
		return new ResponseEntity<>(svc.deleteDisciplina(id), HttpStatus.OK);
	}
}
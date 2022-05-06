package com.proyecto.olimpiadas.service;

import java.util.List;

import com.proyecto.olimpiadas.dto.ApiResponse;
import com.proyecto.olimpiadas.entity.Disciplina;
import com.proyecto.olimpiadas.entity.Entrenador;

public interface EntrenadorService {

	public List<Entrenador> getEntrenadores();
	public Entrenador getEntrenador(String email);
	public ApiResponse createEntrenador(Entrenador in);
	public ApiResponse updateEntrenador(Entrenador in, Integer id_entrenador);
	public ApiResponse deleteEntrenador(Integer id_entrenador);
	
	public ApiResponse updateEntrenadorDisciplina(Disciplina in, Integer id_entrenador);
}
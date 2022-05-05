package com.proyecto.olimpiadas.service;

import java.util.List;

import com.proyecto.olimpiadas.dto.ApiResponse;
import com.proyecto.olimpiadas.entity.Competidor;
import com.proyecto.olimpiadas.entity.Disciplina;
import com.proyecto.olimpiadas.entity.Entrenador;

public interface CompetidorService {

	public List<Competidor> getCompetidores();
	public Competidor getCompetidor(String email);
	public ApiResponse createCompetidor(Competidor in);
	public ApiResponse updateCompetidor(Competidor in, Integer id_competidor);
	public ApiResponse deleteCompetidor(Integer id_competidor);
	
	public ApiResponse updateCompetidorDisciplina(Disciplina in, Integer id_competidor);
	public ApiResponse updateCompetidorEntrenador(Entrenador in, Integer id_competidor);
}
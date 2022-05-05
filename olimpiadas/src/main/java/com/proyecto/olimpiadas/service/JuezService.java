package com.proyecto.olimpiadas.service;

import java.util.List;

import com.proyecto.olimpiadas.dto.ApiResponse;
import com.proyecto.olimpiadas.entity.Disciplina;
import com.proyecto.olimpiadas.entity.Juez;

public interface JuezService {

	public List<Juez> getJueces();
	public Juez getJuez(String email);
	public ApiResponse createJuez(Juez in);
	public ApiResponse updateJuez(Juez in, Integer id);
	public ApiResponse deleteJuez(Integer id);
	
	public ApiResponse updateJuezDisciplina(Disciplina disciplina, Integer id);
}
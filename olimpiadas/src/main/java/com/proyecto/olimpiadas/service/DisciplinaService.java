package com.proyecto.olimpiadas.service;

import java.util.List;

import com.proyecto.olimpiadas.dto.ApiResponse;
import com.proyecto.olimpiadas.dto.DtoDisciplinaList;
import com.proyecto.olimpiadas.entity.Disciplina;

public interface DisciplinaService {
	
	public List<DtoDisciplinaList> getDisciplinas();
	public Disciplina getDisciplina(String disciplina);
	public ApiResponse createDisciplina(Disciplina in);
	public ApiResponse updateDisciplina(Integer id, Disciplina disciplina);
	public ApiResponse deleteDisciplina(Integer id);
}
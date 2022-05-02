package com.proyecto.olimpiadas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.proyecto.exception.ApiException;
import com.proyecto.olimpiadas.dto.ApiResponse;
import com.proyecto.olimpiadas.entity.Disciplina;
import com.proyecto.olimpiadas.repository.DisciplinaRepository;

@Service
public class DisciplinaServiceImp implements DisciplinaService {
	
	@Autowired
	DisciplinaRepository repo;

	@Override
	public List<Disciplina> getDisciplinas() {
		return repo.findByStatus(1);
	}
	
	@Override
	public Disciplina getDisciplina(String disciplina) {
		Disciplina disciplinaGet = repo.findByDisciplinaAndStatus(disciplina, 1);
		if(disciplina != null)
			return disciplinaGet;
		else
			throw new ApiException(HttpStatus.NOT_FOUND, "disciplina no existe");
	}

	@Override
	public ApiResponse createDisciplina(Disciplina in) {
		Disciplina disciplina = repo.findByDisciplinaAndStatus(in.getDisciplina(), 0);
		if(disciplina != null) {
			updateDisciplina(disciplina.getId(), in);
			return new ApiResponse("disciplina activada");
		}
		try {
			in.setStatus(1);
			repo.save(in);
		}catch(DataIntegrityViolationException e) {
			if(e.getLocalizedMessage().contains("disciplina"))
				throw new ApiException(HttpStatus.BAD_REQUEST, "disciplina ya existe");
			if(e.getLocalizedMessage().contains("descripcion"))
				throw new ApiException(HttpStatus.BAD_REQUEST, "descripción ya existe");
		}
		return new ApiResponse("Disciplina creada");
	}

	@Override
	public ApiResponse updateDisciplina(Integer id, Disciplina disciplina) {
		try {
			repo.updateDisciplina(id, disciplina.getDisciplina(), disciplina.getDescripcion());
		}catch(DataIntegrityViolationException e) {
			if(e.getLocalizedMessage().contains("disciplina"))
				throw new ApiException(HttpStatus.BAD_REQUEST, "disciplina ya existe");
		}
		return new ApiResponse("disciplina actualizada");
	}

	@Override
	public ApiResponse deleteDisciplina(Integer id) {
		if(repo.deleteDisciplina(id) > 0)
			return new ApiResponse("disciplina removida");
		else
			throw new ApiException(HttpStatus.BAD_REQUEST, "disciplina no puede ser eliminada");
	}
}

package com.proyecto.olimpiadas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.proyecto.exception.ApiException;
import com.proyecto.olimpiadas.dto.ApiResponse;
import com.proyecto.olimpiadas.entity.Competidor;
import com.proyecto.olimpiadas.entity.Disciplina;
import com.proyecto.olimpiadas.entity.Entrenador;
import com.proyecto.olimpiadas.repository.CompetidorRepository;
import com.proyecto.olimpiadas.repository.DisciplinaRepository;
import com.proyecto.olimpiadas.repository.EntrenadorRepository;

@Service
public class CompetidorServiceImp implements CompetidorService {

	@Autowired
	CompetidorRepository repo;
	
	@Autowired
	DisciplinaRepository disciplinaRepo;
	
	@Autowired
	EntrenadorRepository entrenadorRepo;
	
	@Override
	public List<Competidor> getCompetidores() {
		return repo.findByStatus(1);
	}

	@Override
	public Competidor getCompetidor(String email) {
		Competidor competidor = repo.findByEmailAndStatus(email, 1);
		if(competidor != null) {
			competidor.setDisciplina(disciplinaRepo.getDisciplina(competidor.getDisciplina_id()));
			competidor.setEntrenador(entrenadorRepo.getEntrenador(competidor.getEntrenador_id()));
			return competidor;
		}else
			throw new ApiException(HttpStatus.NOT_FOUND, "competidor no existe");
	}

	@Override
	public ApiResponse createCompetidor(Competidor in) {
		Competidor competidor = repo.findByEmailAndStatus(in.getEmail(), 0);
		if(competidor != null) {
			updateCompetidor(in, competidor.getId_competidor());
			return new ApiResponse("competidor activado");
		}else {
			try {
				in.setStatus(1);
				repo.save(in);
			}catch(DataIntegrityViolationException e) {
				if(e.getLocalizedMessage().contains("email"))
					throw new ApiException(HttpStatus.BAD_REQUEST, "competidor email ya existe");
			}
			return new ApiResponse("competidor creado");
		}
	}

	@Override
	public ApiResponse updateCompetidor(Competidor in, Integer id) {
		try {
			repo.updateCompetidor(id, in.getNombre(), in.getApellido_paterno(), in.getApellido_materno(), in.getEmail());
		}catch(DataIntegrityViolationException e) {
			if(e.getLocalizedMessage().contains("email"))
				throw new ApiException(HttpStatus.BAD_REQUEST, "competidor email ya existe");
		}
		return new ApiResponse("competidor actualizado");
	}

	@Override
	public ApiResponse deleteCompetidor(Integer id) {
		if(repo.deleteCompetidor(id) > 0)
			return new ApiResponse("competidor eliminado");
		else
			throw new ApiException(HttpStatus.BAD_REQUEST, "competidor no puede ser eliminado");
	}

	@Override
	public ApiResponse updateCompetidorDisciplina(Disciplina in, Integer id) {
		try {
			if(repo.updateCompetidorDisciplina(in.getId(), id) > 0)
				return new ApiResponse("se actualiza la disciplina del competidor");
			else
				throw new ApiException(HttpStatus.BAD_REQUEST, "la disciplina del competidor no puede ser actualizada");
		}catch(DataIntegrityViolationException e) {
			throw new ApiException(HttpStatus.NOT_FOUND, "disciplina no encontrada");
		}
	}

	@Override
	public ApiResponse updateCompetidorEntrenador(Entrenador in, Integer id) {
		try {
			if(repo.updateCompetidorEntrenador(in.getId_entrenador(), id) > 0)
				return new ApiResponse("se actualiza el entrenador del competidor");
			else
				throw new ApiException(HttpStatus.BAD_REQUEST, "el entrenador del competidor no puede ser actualizado");
		}catch(DataIntegrityViolationException e) {
			throw new ApiException(HttpStatus.NOT_FOUND, "entrenador no encontrado");
		}
	}
}

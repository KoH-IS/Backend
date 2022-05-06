package com.proyecto.olimpiadas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.proyecto.exception.ApiException;
import com.proyecto.olimpiadas.dto.ApiResponse;
import com.proyecto.olimpiadas.entity.Disciplina;
import com.proyecto.olimpiadas.entity.Entrenador;
import com.proyecto.olimpiadas.repository.DisciplinaRepository;
import com.proyecto.olimpiadas.repository.EntrenadorRepository;

@Service
public class EntrenadorServiceImp implements EntrenadorService{

	@Autowired
	EntrenadorRepository repo;
	
	@Autowired
	DisciplinaRepository disciplinaRepo;

	@Override
	public List<Entrenador> getEntrenadores() {
		return repo.findByStatus(1);
	}

	@Override
	public Entrenador getEntrenador(String email) {
		Entrenador entrenador = repo.findByEmailAndStatus(email, 1);
		if(entrenador != null) {
			entrenador.setDisciplina(disciplinaRepo.getDisciplina(entrenador.getDisciplina_id()));
			return entrenador;
		}else
			throw new ApiException(HttpStatus.NOT_FOUND, "entrenador no existe");
	}

	@Override
	public ApiResponse createEntrenador(Entrenador in) {
		Entrenador entrenador = repo.findByEmailAndStatus(in.getEmail(), 0);
		if(entrenador != null) {
			updateEntrenador(in, entrenador.getId_entrenador());
			return new ApiResponse("entrenador activado");
		}else {
			try {
				in.setStatus(1);
				repo.save(in);
			}catch(DataIntegrityViolationException e) {
				if(e.getLocalizedMessage().contains("email"))
					throw new ApiException(HttpStatus.BAD_REQUEST, "entrenador email ya existe");
			}
			return new ApiResponse("entrenador creado");
		}
	}

	@Override
	public ApiResponse updateEntrenador(Entrenador in, Integer id_entrenador) {
		try {
			repo.updateEntrenador(id_entrenador, in.getNombre(), in.getApellido_paterno(), in.getApellido_materno(), in.getEmail());
		}catch(DataIntegrityViolationException e) {
			if(e.getLocalizedMessage().contains("email"))
				throw new ApiException(HttpStatus.BAD_REQUEST, "entrenador email ya existe");
		}
		return new ApiResponse("entrenador actualizado");
	}

	@Override
	public ApiResponse deleteEntrenador(Integer id_entrenador) {
		if(repo.deleteEntrenador(id_entrenador) > 0)
			return new ApiResponse("entrenador eliminado");
		else
			throw new ApiException(HttpStatus.BAD_REQUEST, "entrenador no puede ser eliminado");
	}

	@Override
	public ApiResponse updateEntrenadorDisciplina(Disciplina in, Integer id_entrenador) {
		try {
			if(repo.updateEntrenadorDisciplina(in.getId(), id_entrenador) > 0)
				return new ApiResponse("se actualiza la disciplina del entrenador");
			else
				throw new ApiException(HttpStatus.BAD_REQUEST, "la disciplina del entrenador no puede ser actualizada");
		}catch(DataIntegrityViolationException e) {
			throw new ApiException(HttpStatus.NOT_FOUND, "disciplina no encontrada");
		}
	}
}
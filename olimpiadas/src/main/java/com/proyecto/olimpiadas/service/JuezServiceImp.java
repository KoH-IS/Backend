package com.proyecto.olimpiadas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.proyecto.exception.ApiException;
import com.proyecto.olimpiadas.dto.ApiResponse;
import com.proyecto.olimpiadas.entity.Disciplina;
import com.proyecto.olimpiadas.entity.Juez;
import com.proyecto.olimpiadas.repository.DisciplinaRepository;
import com.proyecto.olimpiadas.repository.JuezRepository;

@Service
public class JuezServiceImp implements JuezService{
	
	@Autowired
	JuezRepository juezRepo;
	
	@Autowired
	DisciplinaRepository disciplinaRepo;

	@Override
	public List<Juez> getJueces() {
		return juezRepo.findByStatus(1); 
	}

	@Override
	public Juez getJuez(String email) {
		Juez juez = juezRepo.findByEmailAndStatus(email, 1);
		if(juez != null) {
			juez.setDisciplina(disciplinaRepo.getById(juez.getDisciplina_id()));
			return juez;
		}else
			throw new ApiException(HttpStatus.NOT_FOUND, "juez no existe");
	}

	@Override
	public ApiResponse createJuez(Juez in) {
		Juez juez = juezRepo.findByEmailAndStatus(in.getEmail(), 0);
		if(juez != null) {
			updateJuez(in, juez.getId());
			return new ApiResponse("juez activado");
		}else {
			try {
				in.setStatus(1);
				juezRepo.save(in);
			}catch(DataIntegrityViolationException e) {
				if(e.getLocalizedMessage().contains("email"))
					throw new ApiException(HttpStatus.BAD_REQUEST, "juez email ya existe");
			}
			return new ApiResponse("juez creado");
		}
	}

	@Override
	public ApiResponse updateJuez(Juez in, Integer id) {
		try {
			juezRepo.updateJuez(id, in.getNombre(), in.getApellido_paterno(), in.getApellido_materno(), in.getEmail());
		}catch(DataIntegrityViolationException e) {
			if(e.getLocalizedMessage().contains("email"))
				throw new ApiException(HttpStatus.BAD_REQUEST, "juez email ya existe");
		}
		return new ApiResponse("juez actualizado");
	}

	@Override
	public ApiResponse deleteJuez(Integer id) {
		if(juezRepo.deleteJuez(id) > 0)
			return new ApiResponse("juez eliminado");
		else
			throw new ApiException(HttpStatus.BAD_REQUEST, "juez no puede ser eliminado");
	}

	@Override
	public ApiResponse updateJuezDisciplina(Disciplina disciplina, Integer id) {
		try {
			if(juezRepo.updateJuezDisciplina(disciplina.getId(), id) > 0)
				return new ApiResponse("se actualiza la disciplina del juez");
			else
				throw new ApiException(HttpStatus.BAD_REQUEST, "la disciplina del juez no puede ser actualizada");
		}catch(DataIntegrityViolationException e) {
			throw new ApiException(HttpStatus.NOT_FOUND, "disciplina no encontrada");
		}
	}
}
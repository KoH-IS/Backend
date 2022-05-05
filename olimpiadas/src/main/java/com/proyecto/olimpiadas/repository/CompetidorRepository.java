package com.proyecto.olimpiadas.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.proyecto.olimpiadas.entity.Competidor;

@Repository
public interface CompetidorRepository extends JpaRepository<Competidor, Integer>{

	@Query(value = "SELECT * FROM competidor WHERE id_competidor = :id_competidor AND status = 1", nativeQuery = true)
	Competidor getCompetidor(Integer id_competidor);
	
	Competidor findByEmailAndStatus(@Param("email") String email, @Param("status") Integer status);
	
	List<Competidor> findByStatus(Integer status);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE competidor "
			+ "SET nombre = :nombre, "
			+ "apellido_paterno = :apellido_paterno, "
			+ "apellido_materno = :apellido_materno, "
			+ "email = :email, "
			+ "status =: 1 "
			+ "WHERE id_competidor = :id_competidor", nativeQuery = true)
	Integer updateCompetidor(
			@Param("id_competidor") Integer id_competidor,
			@Param("nombre") String nombre,
			@Param("apellido_paterno") String apellido_paterno,
			@Param("apellido_materno") String apellido_materno,
			@Param("email") String email);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE competidor SET status = 0 WHERE id_competidor = :id_competidor AND status = 1", nativeQuery = true)
	Integer deleteCompetidor(@Param("id_competidor") Integer id_competidor);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE competidor SET disciplina_id = :disciplina_id WHERE id_competidor = :id_competidor AND status = 1", nativeQuery = true)
	Integer updateCompetidorDisciplina(@Param("disciplina_id") Integer disciplina_id, @Param("id_competidor") Integer id_competidor);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE competidor SET entrenador_id = :entrenador_id WHERE id_competidor = :id_competidor AND status = 1", nativeQuery = true)
	Integer updateCompetidorEntrenador(@Param("entrenador_id") Integer entrenador_id, @Param("id_competidor") Integer id_competidor);
}
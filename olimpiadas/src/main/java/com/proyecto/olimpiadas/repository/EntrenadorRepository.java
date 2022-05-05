package com.proyecto.olimpiadas.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.proyecto.olimpiadas.entity.Entrenador;

@Repository
public interface EntrenadorRepository extends JpaRepository<Entrenador, Integer>{

	@Query(value = "SELECT * FROM entrenador WHERE id_entrenador = :id_entrenador AND status = 1", nativeQuery = true)
	Entrenador getEntrenador(Integer id_entrenador);
	
	Entrenador findByEmailAndStatus(@Param("email") String email, @Param("status") Integer status);
	
	List<Entrenador> findByStatus(Integer status);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE entrenador "
			+ "SET nombre = :nombre, "
			+ "apellido_paterno = :apellido_paterno, "
			+ "apellido_materno = :apellido_materno, "
			+ "email = :email, "
			+ "status =: 1 "
			+ "WHERE id_entrenador = :id_entrenador", nativeQuery = true)
	Integer updateEntrenador(
			@Param("id_entrenador") Integer id_entrenador,
			@Param("nombre") String nombre,
			@Param("apellido_paterno") String apellido_paterno,
			@Param("apellido_materno") String apellido_materno,
			@Param("email") String email);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE entrenador SET status = 0 WHERE id_entrenador = :id_entrenador AND status = 1", nativeQuery = true)
	Integer deleteEntrenador(@Param("id_entrenador") Integer id_entrenador);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE entrenador SET disciplina_id = :disciplina_id WHERE id_entrenador = :id_entrenador AND status = 1", nativeQuery = true)
	Integer updateEntrenadorDisciplina(@Param("disciplina_id") Integer disciplina_id, @Param("id_entrenador") Integer id_entrenador);
}
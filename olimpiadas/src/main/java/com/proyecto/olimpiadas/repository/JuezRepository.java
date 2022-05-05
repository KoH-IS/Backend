package com.proyecto.olimpiadas.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.proyecto.olimpiadas.entity.Juez;

@Repository
public interface JuezRepository extends JpaRepository<Juez, Integer>{

	Juez findByEmailAndStatus(@Param("email") String email, @Param("status") Integer status);
	
	List<Juez> findByStatus(Integer status);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE juez "
			+ "SET nombre = :nombre, "
			+ "apellido_paterno = :apellido_paterno, "
			+ "apellido_materno = :apellido_materno, "
			+ "email = :email, "
			+ "status =: 1 "
			+ "WHERE id = :id", nativeQuery = true)
	Integer updateJuez(
			@Param("id") Integer id,
			@Param("nombre") String nombre,
			@Param("apellido_paterno") String apellido_paterno,
			@Param("apellido_materno") String apellido_materno,
			@Param("email") String email);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE juez SET status = 0 WHERE id = :id AND status = 1", nativeQuery = true)
	Integer deleteJuez(@Param("id") Integer id);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE juez SET disciplina_id =:disciplina_id WHERE id = :id AND status = 1", nativeQuery = true)
	Integer updateJuezDisciplina(@Param("disciplina_id") Integer disciplina_id, @Param("id") Integer id);
}
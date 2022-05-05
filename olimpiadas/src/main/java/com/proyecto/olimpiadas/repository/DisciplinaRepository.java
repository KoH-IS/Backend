package com.proyecto.olimpiadas.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.proyecto.olimpiadas.entity.Disciplina;

@Repository
public interface DisciplinaRepository extends JpaRepository<Disciplina, Integer>{
	
	@Query(value = "SELECT * FROM disciplina WHERE id = :id AND status = 1", nativeQuery = true)
	Disciplina getDisciplina(Integer id);

	Disciplina findByDisciplinaAndStatus(@Param("disciplina") String disciplina, @Param("status") Integer status);
	
	List<Disciplina> findByStatus(Integer status);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE disciplina "
			+ "SET disciplina = :disciplina, "
				+ "descripcion = :descripcion "
				+ "WHERE id = :id AND status = 1", nativeQuery = true)
	Integer updateDisciplina(
			@Param("id") int id,
			@Param("disciplina") String disciplina,
			@Param("descripcion") String descripcion);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE disciplina SET status = 0 WHERE id = :id AND status = 1", nativeQuery = true)
	Integer deleteDisciplina(@Param("id") Integer id);
}
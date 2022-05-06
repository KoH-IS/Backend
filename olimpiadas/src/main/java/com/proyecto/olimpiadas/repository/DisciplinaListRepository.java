package com.proyecto.olimpiadas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.olimpiadas.dto.DtoDisciplinaList;

@Repository
public interface DisciplinaListRepository extends JpaRepository<DtoDisciplinaList, Integer> {

	List<DtoDisciplinaList> findByStatus(Integer status);
}
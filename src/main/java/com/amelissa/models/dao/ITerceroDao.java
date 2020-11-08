package com.amelissa.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.amelissa.models.entity.Tercero;

public interface ITerceroDao extends CrudRepository<Tercero, Long> {
	
	@Query("select t from Tercero t where t.documento = ?1")
	public Tercero findByDocumento(Integer documento);

}

package com.amelissa.models.service;

import java.util.List;

import com.amelissa.models.entity.Tercero;

public interface ITerceroService {
	
public List<Tercero> findAll();
	
	public void save(Tercero tercero);
	
	public Tercero findOne(Long id);
	
	public void delete(Long id);
	
	public Tercero findByDocumento(Integer documento);

}

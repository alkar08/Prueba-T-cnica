package com.amelissa.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amelissa.models.dao.ITerceroDao;
import com.amelissa.models.entity.Tercero;

@Service
public class TerceroServiceImpl implements ITerceroService{

	@Autowired
	private ITerceroDao terceroDao;
	
	@Transactional(readOnly = true)
	@Override
	public List<Tercero> findAll() {
		return (List<Tercero>) terceroDao.findAll();
	}

	@Override
	public void save(Tercero tercero) {
		terceroDao.save(tercero);
		
	}

	@Transactional(readOnly = true)
	@Override
	public Tercero findOne(Long id) {
		return terceroDao.findById(id).orElse(null);
	}

	@Override
	public void delete(Long id) {
		terceroDao.deleteById(id);
		
	}
	
	@Transactional(readOnly = true)
	@Override
	public Tercero findByDocumento(Integer documento) {
		return terceroDao.findByDocumento(documento);
	}
	

}

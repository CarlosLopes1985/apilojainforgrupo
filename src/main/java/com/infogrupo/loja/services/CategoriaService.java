package com.infogrupo.loja.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infogrupo.loja.entity.Categoria;
import com.infogrupo.loja.repository.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	//Para spring boot 2.2.x usar o optional 
	public Categoria buscar(Integer id) {
		
		Categoria categorias = categoriaRepository.findOne(id); 
		
		return categorias;
	}
}
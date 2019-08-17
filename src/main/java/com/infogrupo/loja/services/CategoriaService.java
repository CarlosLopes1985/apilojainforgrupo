package com.infogrupo.loja.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infogrupo.loja.entity.Categoria;
import com.infogrupo.loja.repository.CategoriaRepository;
import com.infogrupo.loja.services.exception.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	//Para spring boot 2.2.x usar o optional 
	public Categoria buscar(Integer id) {
		
		Categoria categorias = categoriaRepository.findOne(id); 
		
		if(categorias == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " +id + ", Tipo: " +Categoria.class.getName());
		}
		return categorias;
	}
	
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return categoriaRepository.save(obj);
	}
}

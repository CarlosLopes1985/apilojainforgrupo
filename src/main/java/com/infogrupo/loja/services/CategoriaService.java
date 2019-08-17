package com.infogrupo.loja.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.infogrupo.loja.entity.Categoria;
import com.infogrupo.loja.repository.CategoriaRepository;
import com.infogrupo.loja.services.exception.DataIntegrityException;
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

	public Categoria update(Categoria obj) {
		
		buscar(obj.getId());
		
		return categoriaRepository.save(obj);
	}

	public void delete(Integer id) {
		buscar(id);
		try {
			categoriaRepository.delete(id);	
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir categorias que tenha produtos associados");	
		}
	}

	public List<Categoria> findAll() {
		return categoriaRepository.findAll();
	}
	
	public Page<Categoria>findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return categoriaRepository.findAll(pageRequest);
	}
	
}

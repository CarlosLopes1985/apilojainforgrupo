package com.infogrupo.loja.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.infogrupo.loja.dto.CategoriaDTO;
import com.infogrupo.loja.dto.ClienteDTO;
import com.infogrupo.loja.entity.Categoria;
import com.infogrupo.loja.entity.Cliente;
import com.infogrupo.loja.repository.ClienteRepository;
import com.infogrupo.loja.services.exception.DataIntegrityException;
import com.infogrupo.loja.services.exception.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	// Para spring boot 2.2.x usar o optional
	public Cliente buscar(Integer id) {

		Cliente Clientes = clienteRepository.findOne(id);

		if (Clientes == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName());
		}
		return Clientes;
	}
	
	public Cliente update(Cliente obj) {
		
		buscar(obj.getId());
		
		return clienteRepository.save(obj);
	}

	public void delete(Integer id) {
		buscar(id);
		try {
			clienteRepository.delete(id);	
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir Clientes que tenha produtos associados");	
		}
	}

	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}
	
	public Page<Cliente>findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return clienteRepository.findAll(pageRequest);
	}
	
	public Cliente fromDto(ClienteDTO obj) {
		return new Cliente(obj.getId(),obj.getNome(),obj.getEmail(),null,null);
	}
	
	public Categoria fromDto(CategoriaDTO obj) {
		return new Categoria(obj.getId(), obj.getNome());
	}
}

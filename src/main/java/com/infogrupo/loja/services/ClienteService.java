package com.infogrupo.loja.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infogrupo.loja.entity.Cliente;
import com.infogrupo.loja.repository.ClienteRepository;
import com.infogrupo.loja.services.exception.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository ClienteRepository;

	// Para spring boot 2.2.x usar o optional
	public Cliente buscar(Integer id) {

		Cliente Clientes = ClienteRepository.findOne(id);

		if (Clientes == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName());
		}

		return Clientes;
	}
}

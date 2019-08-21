package com.infogrupo.loja.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.infogrupo.loja.dto.ClienteDTO;
import com.infogrupo.loja.dto.ClienteNewDTO;
import com.infogrupo.loja.entity.Cidade;
import com.infogrupo.loja.entity.Cliente;
import com.infogrupo.loja.entity.Endereco;
import com.infogrupo.loja.entity.enums.TipoCliente;
import com.infogrupo.loja.repository.CidadeRepository;
import com.infogrupo.loja.repository.ClienteRepository;
import com.infogrupo.loja.repository.EnderecoRepository;
import com.infogrupo.loja.services.exception.DataIntegrityException;
import com.infogrupo.loja.services.exception.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj = clienteRepository.save(obj);
		
		enderecoRepository.save(obj.getEnderecos());
		
		return obj;
	}
	
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
		
		Cliente newObj = buscar(obj.getId());
		updateData(newObj,obj);
		
		return clienteRepository.save(newObj);
	}

	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
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
	
	public Cliente fromDto(ClienteNewDTO objDto) {
		
		Cliente cli = new Cliente(null, objDto.getNome(), objDto.getEmail(), objDto.getCpfOuCnpj(), TipoCliente.toEnum(objDto.getTipo()));
		
		Cidade cid = cidadeRepository.findOne(objDto.getCidadeId());
		
		Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep(), cli, cid);
		
		cli.getEnderecos().add(end);
		cli.getTelefones().add(objDto.getTelefone1());
		
		if(objDto.getTelefone2() != null) {
			cli.getTelefones().add(objDto.getTelefone2());
		}
		
		if(objDto.getTelefone3() != null) {
			cli.getTelefones().add(objDto.getTelefone3());
		}
		
		return cli;
	}
}

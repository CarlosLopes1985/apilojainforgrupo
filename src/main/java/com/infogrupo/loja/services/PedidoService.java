package com.infogrupo.loja.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infogrupo.loja.entity.Pedido;
import com.infogrupo.loja.repository.PedidoRepository;
import com.infogrupo.loja.services.exception.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	//Para spring boot 2.2.x usar o optional 
	public Pedido buscar(Integer id) {
		
		Pedido pedidos = pedidoRepository.findOne(id); 
		
		if(pedidos == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " +id + ", Tipo: " +Pedido.class.getName());
		}
		
		return pedidos;
	}
}

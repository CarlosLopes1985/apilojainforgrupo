package com.infogrupo.loja.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infogrupo.loja.entity.ItemPedido;
import com.infogrupo.loja.entity.PagamentoComBoleto;
import com.infogrupo.loja.entity.Pedido;
import com.infogrupo.loja.entity.enums.EstadoPagamento;
import com.infogrupo.loja.repository.ItemPedidoRepository;
import com.infogrupo.loja.repository.PagamentoRepository;
import com.infogrupo.loja.repository.PedidoRepository;
import com.infogrupo.loja.repository.ProdutoRepository;
import com.infogrupo.loja.services.exception.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	//Para spring boot 2.2.x usar o optional 
	public Pedido buscar(Integer id) {
		
		Pedido pedidos = pedidoRepository.findOne(id); 
		
		if(pedidos == null) {
			throw new ObjectNotFoundException("Objeto nã encontrado! Id: " +id + ", Tipo: " +Pedido.class.getName());
		}
		
		return pedidos;
	}

	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if(obj.getPagamento() instanceof PagamentoComBoleto ) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());
		}
		
		obj = pedidoRepository.save(obj);
		pagamentoRepository.save(obj.getPagamento());
		
		for (ItemPedido ip : obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setPreco(produtoRepository.findOne(ip.getProduto().getId()).getPreco());
			ip.setPedido(obj);
		}
		
		itemPedidoRepository.save(obj.getItens());
		
		return obj;
	}
}

package com.infogrupo.loja.dto;

import java.io.Serializable;

import com.infogrupo.loja.entity.Produto;

public class ProdutoDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nome;
	private Double preco;
	
	
	
	public ProdutoDTO(Produto obj) {
		super();
		id = obj.getId();
		nome = obj.getNome();
		preco = obj.getPreco();
	}
	public ProdutoDTO() {
		super();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Double getPreco() {
		return preco;
	}
	public void setPreco(Double preco) {
		this.preco = preco;
	}
	
	
	}

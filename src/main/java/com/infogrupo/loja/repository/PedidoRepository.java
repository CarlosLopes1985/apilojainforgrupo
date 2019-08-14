package com.infogrupo.loja.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infogrupo.loja.entity.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

}

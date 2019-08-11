package com.infogrupo.loja.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infogrupo.loja.entity.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer> {

}

package com.exemple.cadastro.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.exemple.cadastro.entity.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{

	public Page<Produto> findByNome(String nomeProduto, Pageable page);

}

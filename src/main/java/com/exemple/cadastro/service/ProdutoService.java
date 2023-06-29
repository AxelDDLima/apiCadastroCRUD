package com.exemple.cadastro.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.exemple.cadastro.entity.Produto;
import com.exemple.cadastro.repositories.ProdutoRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProdutoService {
	
	private ProdutoRepository produtoRepository;
	
	//Essa é uma maneira de colocar em uma varial statica assim implementando um fake bd
	//private static Map<Long, Cliente> listaPessoa = new HashMap<>();
	
	public Page<Produto> buscarTodosRegistros(PageRequest page){
		return produtoRepository.findAll(page);
	}
	
	public ResponseEntity<Produto> buscarRegistroId(Long id){
		if(produtoRepository.existsById(id)) {
			return ResponseEntity.status(HttpStatus.OK).body(produtoRepository.findById(id).get());
		}
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}
	
	public ResponseEntity<Produto> registrarProduto(Produto produto){
		Produto registrarProduto = produtoRepository.save(produto);
		return ResponseEntity.status(HttpStatus.CREATED).body(registrarProduto);
	}
	
	public ResponseEntity<Produto> editarRegistroId(Long id, Produto produto){
		if(produtoRepository.existsById(id)) {
			Produto editarProduto = produtoRepository.save(produto);
			return ResponseEntity.status(HttpStatus.OK).body(editarProduto);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}
	
	public ResponseEntity<String> removerRegistroId(Long id){
		if(produtoRepository.existsById(id)) {
			produtoRepository.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).body("Registro Deletado com sucesso");			
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Registro não encontrado");
	}
}

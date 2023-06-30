package com.exemple.cadastro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.exemple.cadastro.entity.Produto;
import com.exemple.cadastro.service.ProdutoService;

@RestController
@RequestMapping("produto")
public class ProdutoController {
	
	@Autowired
	ProdutoService service;
	
	@GetMapping
	public Page<Produto> buscarTodosRegistros(@RequestParam(defaultValue = "0") Integer pagina, @RequestParam(defaultValue = "10") Integer itensPorPagina){		
		return service.buscarTodosRegistros(PageRequest.of(pagina, itensPorPagina));
	}
	
	@GetMapping("buscarPorNome")
	public Page<Produto> buscarPorNome(@RequestParam String nomeProduto , @RequestParam(defaultValue = "0") Integer pagina, @RequestParam(defaultValue = "10") Integer itensPorPagina){		
		return service.buscarPorNome(nomeProduto.trim().toUpperCase(), PageRequest.of(pagina, itensPorPagina));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Produto> buscarRegistroId(@PathVariable Long id){
		return service.buscarRegistroId(id);
	}
	
	@PostMapping
	public ResponseEntity<Produto> registrarId(@RequestBody Produto produto){
		return service.registrarProduto(produto);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Produto> editarRegistroId(@PathVariable Long id,@RequestBody Produto produto){
		produto.setId(id);
		return service.editarRegistroId(id, produto);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> removerRegistroId(@PathVariable Long id){
		return service.removerRegistroId(id);
	}
}

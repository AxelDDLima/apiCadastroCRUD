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

import com.exemple.cadastro.entity.Cliente;
import com.exemple.cadastro.service.ClienteService;

@RestController
@RequestMapping("registro")
public class ClienteController {
	
	@Autowired
	ClienteService service;
	
	@GetMapping
	public Page<Cliente> buscarTodosRegistros(@RequestParam(defaultValue = "0") Integer pagina, @RequestParam(defaultValue = "2") Integer itensPorPagina){		
		return service.buscarTodosRegistros(PageRequest.of(pagina, itensPorPagina));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> buscarRegistroId(@PathVariable Long id){
		return service.buscarRegistroId(id);
	}
	
	@PostMapping
	public ResponseEntity<Cliente> registrarId(@RequestBody Cliente cliente){
		return service.registrarCliente(cliente);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Cliente> editarRegistroId(@PathVariable Long id,@RequestBody Cliente cliente){
		return service.editarRegistroId(id, cliente);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> removerRegistroId(@PathVariable Long id){
		return service.removerRegistroId(id);
	}
}

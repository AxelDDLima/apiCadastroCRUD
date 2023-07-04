package com.exemple.cadastro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exemple.cadastro.entity.Funcionario;
import com.exemple.cadastro.service.FuncionarioService;

@RestController
@RequestMapping("registroFuncionario")
public class FuncionarioController {
	
	@Autowired
	FuncionarioService service;

	@PostMapping
	public ResponseEntity<Funcionario> registrarId(@RequestBody Funcionario funcionario){
		return service.registrarFuncionario(funcionario);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Funcionario> editarRegistroId(@PathVariable Long id,@RequestBody Funcionario funcionario){
		funcionario.setId(id);
		return service.editarRegistroId(id, funcionario);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> removerRegistroId(@PathVariable Long id){
		return service.removerRegistroId(id);
	}
}

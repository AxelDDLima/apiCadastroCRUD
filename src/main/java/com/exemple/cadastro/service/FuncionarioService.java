package com.exemple.cadastro.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.exemple.cadastro.entity.Funcionario;
import com.exemple.cadastro.repositories.FuncionarioRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FuncionarioService {
	
	private FuncionarioRepository funcionarioRepository;
	
	public ResponseEntity<Funcionario> registrarFuncionario(Funcionario funcionario){
		Funcionario registrarFuncionario = funcionarioRepository.save(funcionario);
		return ResponseEntity.status(HttpStatus.CREATED).body(registrarFuncionario);
	}
	
	public ResponseEntity<Funcionario> editarRegistroId(Long id, Funcionario funcionario){
		if(funcionarioRepository.existsById(id)) {
			Funcionario editarFuncionario = funcionarioRepository.save(funcionario);
			return ResponseEntity.status(HttpStatus.OK).body(editarFuncionario);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}
	
	public ResponseEntity<String> removerRegistroId(Long id){
		if(funcionarioRepository.existsById(id)) {
			funcionarioRepository.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).body("Registro Deletado com sucesso");			
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Registro não encontrado");
	}
}

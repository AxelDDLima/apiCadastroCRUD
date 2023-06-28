package com.exemple.cadastro.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.exemple.cadastro.entity.Cliente;
import com.exemple.cadastro.repositories.ClienteRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ClienteService {
	
	private ClienteRepository repository;
	
	//Essa é uma maneira de colocar em uma varial statica assim implementando um fake bd
	//private static Map<Long, Cliente> listaPessoa = new HashMap<>();
	
	public Page<Cliente> buscarTodosRegistros(PageRequest page){
		return repository.findAll(page);
	}
	
	public ResponseEntity<Cliente> buscarRegistroId(Long id){
		if(repository.existsById(id)) {
			return ResponseEntity.status(HttpStatus.OK).body(repository.findById(id).get());
		}
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}
	
	public ResponseEntity<Cliente> registrarCliente(Cliente cliente){
		Cliente registrarCliente = repository.save(cliente);
		return ResponseEntity.status(HttpStatus.CREATED).body(registrarCliente);
	}
	
	public ResponseEntity<Cliente> editarRegistroId(Long id, Cliente cliente){
		if(repository.existsById(id)) {
			Cliente editarCliente = repository.save(cliente);
			return ResponseEntity.status(HttpStatus.OK).body(editarCliente);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}
	
	public ResponseEntity<String> removerRegistroId(Long id){
		if(repository.existsById(id)) {
			repository.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).body("Registro Deletado com sucesso");			
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Registro não encontrado");
	}
}

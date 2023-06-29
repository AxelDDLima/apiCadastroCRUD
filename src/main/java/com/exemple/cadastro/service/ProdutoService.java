package com.exemple.cadastro.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.exemple.cadastro.entity.Cliente;
import com.exemple.cadastro.entity.Endereco;
import com.exemple.cadastro.repositories.ClienteRepository;
import com.exemple.cadastro.repositories.EnderecoRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProdutoService {
	
	private ClienteRepository clienteRepository;
	private EnderecoRepository enderecoRepository;
	
	//Essa é uma maneira de colocar em uma varial statica assim implementando um fake bd
	//private static Map<Long, Cliente> listaPessoa = new HashMap<>();
	
	public Page<Cliente> buscarTodosRegistros(PageRequest page){
		return clienteRepository.findAll(page);
	}
	
	public ResponseEntity<Cliente> buscarRegistroId(Long id){
		if(clienteRepository.existsById(id)) {
			return ResponseEntity.status(HttpStatus.OK).body(clienteRepository.findById(id).get());
		}
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}
	
	public ResponseEntity<Cliente> registrarCliente(Cliente cliente){
		Set<Endereco> enderecos = cliente.getEnderecos();
		cliente.setEnderecos(new HashSet<>());
		
		Cliente registrarCliente = clienteRepository.save(cliente);
		
		for(Endereco endereco : enderecos) {		
			endereco.setCliente(Cliente.builder().id(cliente.getId()).build());
			cliente.getEnderecos().add(enderecoRepository.save(endereco));
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(registrarCliente);
	}
	
	public ResponseEntity<Cliente> editarRegistroId(Long id, Cliente cliente){
		if(clienteRepository.existsById(id)) {
			Cliente editarCliente = clienteRepository.save(cliente);
			return ResponseEntity.status(HttpStatus.OK).body(editarCliente);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}
	
	public ResponseEntity<String> removerRegistroId(Long id){
		if(clienteRepository.existsById(id)) {
			clienteRepository.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).body("Registro Deletado com sucesso");			
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Registro não encontrado");
	}
}

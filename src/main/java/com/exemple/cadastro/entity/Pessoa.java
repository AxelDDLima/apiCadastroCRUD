package com.exemple.cadastro.entity;

import java.util.Date;

import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class Pessoa {
	
	private String nome;
	private String sobrenome;
	private Date dataNascimento;
	private String cpf;
	private String sexo;
}

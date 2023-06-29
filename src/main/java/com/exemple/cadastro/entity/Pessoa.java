package com.exemple.cadastro.entity;

import java.util.Date;
import org.hibernate.validator.constraints.br.CPF;

import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class Pessoa {
	@Size(min = 3, max = 50)
	private String nome;
	@Size(min = 3, max = 50)
	private String sobrenome;
	private Date dataNascimento;
	@CPF(message = "CPF inválido")
	private String cpf;
	@Size(min = 1, max = 1)
    @Pattern(regexp = "[MF]")
	private String sexo;
}

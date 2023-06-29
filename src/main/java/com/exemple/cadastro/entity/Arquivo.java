package com.exemple.cadastro.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Arquivo {
	
	private String nomeArquivo;
	private String linkArquivo;
	private String tipoArquivo;
	private long tamanhoArquivo;
	
}

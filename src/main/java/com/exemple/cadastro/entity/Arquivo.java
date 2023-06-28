package com.exemple.cadastro.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Arquivo {
	
	private String nomeArquivo;
	private String linkArquivo;
	private String tipoArquivo;
	private long tamanhoArquivo;
	
}

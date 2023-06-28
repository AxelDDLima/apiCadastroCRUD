package com.exemple.cadastro.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;

@Getter
@Component
public class ArquvioStoregePropeties {
	
	@Value("${arquivo.uploadDir}")
	private String uploadDIR;
}

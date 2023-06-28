package com.exemple.cadastro.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import com.exemple.cadastro.config.ArquvioStoregePropeties;

import exception.ArquivoException;
import exception.ArquivoNaoEncontradoException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ArquivoService {
	
	private final Path fileStoreLocation;
	
	public ArquivoService(ArquvioStoregePropeties arquvivoStorage) {
		this.fileStoreLocation =  Paths.get(arquvivoStorage.getUploadDIR()).toAbsolutePath().normalize();
		
		try {
			Files.createDirectories(this.fileStoreLocation);
		} catch (IOException e) {
			throw new ArquivoException("Erro IOException", e);
		}
	}
	
	public String getContentType(HttpServletRequest request, Resource resource) {
		String contentType = null;
		try {
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		} catch (Exception e) {
			log.error("Não foi possível determinar o tipo do arquivo");
		}
		
		if(contentType == null){
			contentType = "application/octet-stream";
		}
		return contentType;
	}
	
	public String salvarArquivo(MultipartFile file) {
		String nomeArquivo = StringUtils.cleanPath(file.getOriginalFilename());
		
		try {
			if(nomeArquivo.contains("..")){
				throw new ArquivoException("Erro metodo salvarArquivo!");
			}
			Path targetLocation = this.fileStoreLocation.resolve(nomeArquivo);
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
			
			return nomeArquivo;
		} catch (IOException e) {
			throw new ArquivoException("Erro IOException", e);
		}
	}
	
	public Resource carregarArquivo(String nomeArquivo) {
		try {
			Path filePath = this.fileStoreLocation.resolve(nomeArquivo).normalize();
			Resource resource = new UrlResource(filePath.toUri());
			if(resource.exists()) {
				return resource;
			} else {
				throw new ArquivoNaoEncontradoException("Arquivo não encontrado!");
			}		
		} catch (Exception e) {
			throw new ArquivoNaoEncontradoException("Não foi possível determinar o tipo do arquivo", e);
		}
	}
	
}

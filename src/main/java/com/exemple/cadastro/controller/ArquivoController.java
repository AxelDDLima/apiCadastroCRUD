package com.exemple.cadastro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.exemple.cadastro.entity.Arquivo;
import com.exemple.cadastro.service.ArquivoService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/uploadArquivos")
public class ArquivoController {
	@Autowired
	public ArquivoService service;
	
	@PostMapping("/salvarArquivo")
	public Arquivo uploadarquivo(MultipartFile file) {
		String nomeArquivo = service.salvarArquivo(file);
		
		String caminhoArquivo = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/uploadArquivos/dowloadArquivo")
				.path(nomeArquivo)
				.toString();
		
		return new Arquivo(nomeArquivo, caminhoArquivo, file.getContentType(), file.getSize());
	}
	
	@GetMapping("/dowloadArquivo/{nomeArquivo}")
	public ResponseEntity<Resource> downloadArquivo(@PathVariable String nomeArquivo, HttpServletRequest request){
		
		Resource resource = service.carregarArquivo(nomeArquivo);
		String contentType = service.getContentType(request, resource);
		
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; fileame=\""+ resource.getFilename()+"\"")
				.body(resource);
	} 
}

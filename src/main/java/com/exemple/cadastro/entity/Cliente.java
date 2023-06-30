package com.exemple.cadastro.entity;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
@Entity
public class Cliente extends Pessoa{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Integer desconto;
	
	//One = cliente e many = endereco
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.REFRESH, orphanRemoval = true)
	@Builder.Default
	@JsonManagedReference
	@Fetch(FetchMode.JOIN)
	private Set<Endereco> enderecos = new HashSet<>();
}

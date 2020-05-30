package br.com.alvaro.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
public class Fornecedor {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
	
	private String cnpj;
	
	private String nome;
	
	@OneToMany
	@Cascade({CascadeType.ALL})
	List<Preco> precos = new ArrayList<Preco>();
	
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public List<Preco> getPrecos() {
		return precos;
	}
	
	
}

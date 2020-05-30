package br.com.alvaro.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Preco {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private BigDecimal preco;
	private Integer quantidade_minima;
	
	
	public BigDecimal getPreco() {
		return preco;
	}
	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
	public Integer getQuantidade_minima() {
		return quantidade_minima;
	}
	public void setQuantidade_minima(Integer quantidade_minima) {
		this.quantidade_minima = quantidade_minima;
	}
	
	

}

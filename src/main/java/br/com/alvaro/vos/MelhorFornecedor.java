package br.com.alvaro.vos;

import java.math.BigDecimal;

import br.com.alvaro.model.Fornecedor;

public class MelhorFornecedor {
	
	Fornecedor fornecedor;
	BigDecimal preco;
	
	public Fornecedor getFornecedor() {
		return fornecedor;
	}
	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
	public BigDecimal getPreco() {
		return preco;
	}
	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

}

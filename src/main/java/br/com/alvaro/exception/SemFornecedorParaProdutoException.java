package br.com.alvaro.exception;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import br.com.alvaro.model.Produto;

public class SemFornecedorParaProdutoException extends RuntimeException{

	private static final String NAO_HA_FORNECEDOR_PRODUTO = "Não há fornecedor para o(s) produto(s): ";
	
	private static final long serialVersionUID = 2264373647128722357L;
	
	public SemFornecedorParaProdutoException(List<Optional<Produto>> produtosSemFornecedor){
		super(NAO_HA_FORNECEDOR_PRODUTO + produtosSemFornecedor.stream()
        .map(Object::toString)
        .collect(Collectors.joining(", \n ")));
	}
	
	public SemFornecedorParaProdutoException(Produto produtoSemFornecedor){
		super(NAO_HA_FORNECEDOR_PRODUTO  + produtoSemFornecedor.getNome() + ".");
	}

}

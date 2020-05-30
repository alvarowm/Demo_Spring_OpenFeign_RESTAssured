package br.com.alvaro.filter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.alvaro.vos.ProdutoDoPedido;

@Service
public class ProdutosFilter {
	
	public List<ProdutoDoPedido> filtarProdutosComQuantidade(List<ProdutoDoPedido> produtos) {
		produtos = (List<ProdutoDoPedido>) produtos.stream().filter(p -> p.getQuantidade() > 0).collect(Collectors.toList ()); 
		return produtos;
	}

}

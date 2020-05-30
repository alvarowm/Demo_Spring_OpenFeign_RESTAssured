package br.com.alvaro.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.alvaro.model.Produto;

public interface ProdutoRepository extends CrudRepository<Produto, String> {
}

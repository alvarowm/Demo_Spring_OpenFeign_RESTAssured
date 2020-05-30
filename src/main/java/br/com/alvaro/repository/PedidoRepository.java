package br.com.alvaro.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.alvaro.model.Pedido;

public interface PedidoRepository extends CrudRepository<Pedido, Long> {

}

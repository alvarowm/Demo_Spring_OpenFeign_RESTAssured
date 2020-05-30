package br.com.alvaro.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alvaro.exception.SemFornecedorParaProdutoException;
import br.com.alvaro.model.Pedido;
import br.com.alvaro.service.PedidoService;
import br.com.alvaro.vos.ProdutoDoPedido;

@RestController
@RequestMapping("/dev")
public class PedidoController {
	
	@Autowired
	PedidoService service;

	
	@PostMapping("/novo-pedido")
	public List<Pedido> postPedido(@RequestBody List<ProdutoDoPedido> produtos) throws SemFornecedorParaProdutoException {
		return service.criarPedidorPorProdutosEQuantidade(produtos);
	}

	@GetMapping("/pedidos")
	public Iterable<Pedido> getPedidos() {
		return service.getAllPedidos();
	}

}

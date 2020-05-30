package br.com.alvaro.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alvaro.exception.SemFornecedorParaProdutoException;
import br.com.alvaro.filter.ProdutosFilter;
import br.com.alvaro.model.Fornecedor;
import br.com.alvaro.model.Item;
import br.com.alvaro.model.Pedido;
import br.com.alvaro.repository.PedidoRepository;
import br.com.alvaro.repository.ProdutoRepository;
import br.com.alvaro.vos.MelhorFornecedor;
import br.com.alvaro.vos.ProdutoDoPedido;

@Service
public class PedidoService {

	@Autowired
	FornecedoresService fornecedoresHandler;

	@Autowired
	ProdutoRepository produtoRepository;

	@Autowired
	ProdutosFilter produtosFilter;

	@Autowired
	PedidoRepository pedidoRepository;

	public List<Pedido> criarPedidorPorProdutosEQuantidade(List<ProdutoDoPedido> produtos)
			throws SemFornecedorParaProdutoException {
		produtos = produtosFilter.filtarProdutosComQuantidade(produtos);

		HashMap<String, List<Fornecedor>> fornecedores = fornecedoresHandler
				.selecionarFornecedoresPorProdutoEQuantidade(produtos);

		Map<String, Pedido> pedidos = new HashMap<>();

		produtos.stream().forEach(p -> {
			MelhorFornecedor melhorFornecedor = null;

			melhorFornecedor = fornecedoresHandler.selecionarOMelhorFornececedorPorProdutoEQUantidade(fornecedores, p);

			if (pedidos.get(melhorFornecedor.getFornecedor().getCnpj()) == null) {
				Pedido pedido = popularPedido(p, melhorFornecedor);
				pedidos.put(melhorFornecedor.getFornecedor().getCnpj(), pedido);
			} else {
				adicionarNovoItem(p, melhorFornecedor, pedidos.get(melhorFornecedor.getFornecedor().getCnpj()));
			}

		});

		List<Pedido> listaDePedidos = pedidos.values().stream().collect(Collectors.toList());

		pedidoRepository.saveAll(listaDePedidos);

		salvarPedidor(listaDePedidos);

		return listaDePedidos;
	}

	@Transactional
	private void salvarPedidor(List<Pedido> listaDePedidos) {
		pedidoRepository.saveAll(listaDePedidos);
	}

	private void adicionarNovoItem(ProdutoDoPedido p, MelhorFornecedor melhorFornecedor, Pedido pedido) {
		Item item = new Item();
		item.setQuantidade(p.getQuantidade());
		item.setPreco(melhorFornecedor.getPreco());
		item.setProduto(produtoRepository.findById(p.getId()).get());
		item.setTotal(melhorFornecedor.getPreco().multiply(BigDecimal.valueOf(p.getQuantidade())));
		pedido.getItens().add(item);
	}

	private Pedido popularPedido(ProdutoDoPedido p, MelhorFornecedor melhorFornecedor) {
		Pedido pedido = new Pedido();
		pedido.setFornecedor(melhorFornecedor.getFornecedor());
		adicionarNovoItem(p, melhorFornecedor, pedido);
		return pedido;
	}

	public Iterable<Pedido> getAllPedidos() {
		return pedidoRepository.findAll();
	}

}

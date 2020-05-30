package br.com.alvaro.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alvaro.client.IBuscaDeFornecedoresClient;
import br.com.alvaro.exception.SemFornecedorParaProdutoException;
import br.com.alvaro.model.Fornecedor;
import br.com.alvaro.model.Preco;
import br.com.alvaro.model.Produto;
import br.com.alvaro.repository.ProdutoRepository;
import br.com.alvaro.vos.MelhorFornecedor;
import br.com.alvaro.vos.ProdutoDoPedido;

@Service
public class FornecedoresService {

	@Autowired
	IBuscaDeFornecedoresClient fornecedoresClient;

	@Autowired
	ProdutoRepository produtoRepository;

	private MelhorFornecedor melhorFornecedor;

	public HashMap<String, List<Fornecedor>> selecionarFornecedoresPorProdutoEQuantidade(
			List<ProdutoDoPedido> produtos) throws SemFornecedorParaProdutoException {
		Map<String, List<Fornecedor>> fornecedores = new HashMap<>();
		List<Optional<Produto>> produtosSemFornecedor = new ArrayList<>();
		produtos.parallelStream().forEach(p -> {
			List<Fornecedor> listaDeFornecedores = fornecedoresClient.getFornecedorPorId(p.getId());
			if (listaDeFornecedores == null || listaDeFornecedores.isEmpty()) {
				Optional<Produto> produto = produtoRepository.findById(p.getId());
				produtosSemFornecedor.add(produto);
			}
			fornecedores.put(p.getId(), listaDeFornecedores);
		});

		if (!produtosSemFornecedor.isEmpty()) {
			throw new SemFornecedorParaProdutoException(produtosSemFornecedor);
		}

		return (HashMap<String, List<Fornecedor>>) fornecedores;
	}

	public MelhorFornecedor selecionarOMelhorFornececedorPorProdutoEQUantidade(
			HashMap<String, List<Fornecedor>> fornecedores, ProdutoDoPedido produto)
			throws SemFornecedorParaProdutoException {
		this.melhorFornecedor = null;

		MelhorFornecedor melhorFornecedor = selecionarOMelhorFornececedorPorProdutoEQuantidade(fornecedores.get(produto.getId()), produto);
		if (melhorFornecedor.getFornecedor() != null) {
			callback(melhorFornecedor);
		}

		if (this.melhorFornecedor == null)
			throw new SemFornecedorParaProdutoException(produtoRepository.findById(produto.getId()).get());

		return this.melhorFornecedor;
	}

	private MelhorFornecedor selecionarOMelhorFornececedorPorProdutoEQuantidade(List<Fornecedor> fornecedores,
			ProdutoDoPedido produto) {
		MelhorFornecedor melhorFornecedor = new MelhorFornecedor();

		for (Fornecedor fornecedor : fornecedores) {
			BigDecimal menorPreco = null;
			for (Preco preco : fornecedor.getPrecos())
				if (preco.getQuantidade_minima() <= produto.getQuantidade())
					if (menorPreco == null || preco.getPreco().compareTo(menorPreco) == -1) {
						melhorFornecedor.setPreco(preco.getPreco());
						melhorFornecedor.setFornecedor(fornecedor);
					}
		}

		return melhorFornecedor;
	}

	private void callback(MelhorFornecedor melhorFornecedor) {
		this.melhorFornecedor = melhorFornecedor;
	}

}

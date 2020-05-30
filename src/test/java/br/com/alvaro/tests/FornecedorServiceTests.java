package br.com.alvaro.tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.alvaro.DesafioApplication;
import br.com.alvaro.model.Fornecedor;
import br.com.alvaro.service.FornecedoresService;
import br.com.alvaro.vos.MelhorFornecedor;
import br.com.alvaro.vos.ProdutoDoPedido;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=DesafioApplication.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class FornecedorServiceTests {
	
	@Autowired
	FornecedoresService service;
	
	@Test
	public void testeSelecionarFornecedoresPorProdutoEQuantidade() {
		List<ProdutoDoPedido> pedidos = new ArrayList<>();
		ProdutoDoPedido pedido = new ProdutoDoPedido();
		pedido.setId("7894900011517");
		pedido.setQuantidade(10);
		pedidos.add(pedido);
		
		HashMap<String, List<Fornecedor>> fornecedores = service.selecionarFornecedoresPorProdutoEQuantidade(pedidos);
		assertEquals(fornecedores.size(), 1);
		assertNotNull(fornecedores.get("7894900011517"));
	}
	
	@Test
	public void testeSelecionarOMelhorFornecedor() {
		List<ProdutoDoPedido> pedidos = new ArrayList<>();
		ProdutoDoPedido pedido = new ProdutoDoPedido();
		pedido.setId("7891991010856");
		pedido.setQuantidade(10);
		pedidos.add(pedido);
		
		MelhorFornecedor melhorFornecedor = 
				service.selecionarOMelhorFornececedorPorProdutoEQUantidade(service.selecionarFornecedoresPorProdutoEQuantidade(pedidos), pedido);
		
		assertEquals(melhorFornecedor.getFornecedor().getNome(), "Fornecedor 3");
		
	}

}

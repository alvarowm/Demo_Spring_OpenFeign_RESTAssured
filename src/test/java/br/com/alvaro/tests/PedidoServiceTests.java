package br.com.alvaro.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.alvaro.DesafioApplication;
import br.com.alvaro.model.Pedido;
import br.com.alvaro.service.PedidoService;
import br.com.alvaro.vos.ProdutoDoPedido;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=DesafioApplication.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class PedidoServiceTests {
	
	@Autowired
	PedidoService service;
	
	@Test
	public void testeCriarPedidos() {
		List<ProdutoDoPedido> produtos = new ArrayList<ProdutoDoPedido>();
		ProdutoDoPedido p1 = new ProdutoDoPedido();
		p1.setId("7894900011517");
		p1.setQuantidade(10);
		produtos.add(p1);
		
		ProdutoDoPedido p2 = new ProdutoDoPedido();
		p2.setId("7891910000197");
		p2.setQuantidade(1);
		produtos.add(p2);
		
		ProdutoDoPedido p3 = new ProdutoDoPedido();
		p3.setId("7892840222949");
		p3.setQuantidade(1);
		produtos.add(p3);
			
		List<Pedido> pedidos = service.criarPedidorPorProdutosEQuantidade(produtos);
		assertEquals(pedidos.size(), 2);
		
		assertTrue(pedidos.get(0).getFornecedor().getNome().equals("Fornecedor 2") || pedidos.get(0).getFornecedor().getNome().equals("Fornecedor 2"));
		
		if (pedidos.get(0).getFornecedor().getNome().equals("Fornecedor 2")) {
			assertEquals(pedidos.get(0).getFornecedor().getPrecos().size(), 2);
		} else {
			assertEquals(pedidos.get(1).getFornecedor().getPrecos().size(), 2);
		}
	}
	
	@Test
	public void testeVerificarQuantidadesETotais() {
		List<ProdutoDoPedido> produtos = new ArrayList<ProdutoDoPedido>();
		ProdutoDoPedido p1 = new ProdutoDoPedido();
		p1.setId("7891910000197");
		p1.setQuantidade(17);
		produtos.add(p1);
		
		List<Pedido> pedidos = service.criarPedidorPorProdutosEQuantidade(produtos);
		
		assertNotNull(pedidos);
		
		assertEquals(pedidos.get(0).getItens().size(), 1);
		
		assertEquals(pedidos.get(0).getItens().get(0).getQuantidade(), 17);
		
		assertEquals(pedidos.get(0).getItens().get(0).getPreco(), new BigDecimal("1.99"));
		
		assertEquals(pedidos.get(0).getItens().get(0).getTotal(), new BigDecimal("33.83"));
	}
	
	@Test
	public void testeVerificarAgrupamentos() {
		List<ProdutoDoPedido> produtos = new ArrayList<ProdutoDoPedido>();
		ProdutoDoPedido p1 = new ProdutoDoPedido();
		p1.setId("7894900011517");
		p1.setQuantidade(10);
		produtos.add(p1);
		
		ProdutoDoPedido p2 = new ProdutoDoPedido();
		p2.setId("7891910000197");
		p2.setQuantidade(1);
		produtos.add(p2);
		
		ProdutoDoPedido p3 = new ProdutoDoPedido();
		p3.setId("7891991010856");
		p3.setQuantidade(1);
		produtos.add(p3);
		
		ProdutoDoPedido p4 = new ProdutoDoPedido();
		p4.setId("7891910007110");
		p4.setQuantidade(1);
		produtos.add(p4);
			
		List<Pedido> pedidos = service.criarPedidorPorProdutosEQuantidade(produtos);
		
		assertEquals(pedidos.size(), 3);
		
		assertFalse(pedidos.stream().filter(p -> p.getFornecedor().getNome().equals("Fornecedor 1")).collect(Collectors.toList()).isEmpty());
		assertFalse(pedidos.stream().filter(p -> p.getFornecedor().getNome().equals("Fornecedor 2")).collect(Collectors.toList()).isEmpty());
		assertFalse(pedidos.stream().filter(p -> p.getFornecedor().getNome().equals("Fornecedor 3")).collect(Collectors.toList()).isEmpty());

	}

}

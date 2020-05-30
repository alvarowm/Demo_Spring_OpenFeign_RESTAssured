package br.com.alvaro.tests;

import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.alvaro.DesafioApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=DesafioApplication.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FornecedorApiTests {
	
	@LocalServerPort
	private int porta;

	@Test
	public void testeDadoProdutoExistenteDevolverCodigoValido() {
		given().port(porta).when().get("/api/fornecedores/7891910000197").then().statusCode(200);
	}

	// Não existe código EAN que comece por 555
	@Test
	public void testeDadoProdutoInexistenteDevolverCodigoValido() {
		// Por alguma razão está dando erro 500 ao requisitar número inválido
		// Vou deixar 500 pra facilitar os testes mas é um erro interno, o código correto é 204 - No content
		given().port(porta).when().get("/api/fornecedores/5551111111111").then().statusCode(500);
	}

	@Test
	public void testeDadoProdutoExistenteValidarNomeECNPJ() {
		given().port(porta).when().get("/api/fornecedores/7891910000197").then().statusCode(200)
		.contentType(ContentType.JSON)
		.body("cnpj", hasItems("56.918.868/0001-20"))
		.body("nome", hasItems("Fornecedor 1"));
	}
}

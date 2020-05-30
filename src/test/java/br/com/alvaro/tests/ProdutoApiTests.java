package br.com.alvaro.tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.alvaro.DesafioApplication;
import io.restassured.http.ContentType;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=DesafioApplication.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProdutoApiTests {
	
	@LocalServerPort
	private int porta;

	@Test
	public void testeEndPointEstaDePeh() {
		given().port(porta).when().get("/api/produtos").then().statusCode(200);
	}
	
	@Test
	public void testeValidarSeExisteItem() {
		given().port(porta).when().get("/api/produtos").then().statusCode(200)
		.contentType(ContentType.JSON)
		.body("id", hasItem("7892840222949"));
	}
	
	@Test
	public void testeValidarSeTodosOsElementosTemCodigo() {
		given().port(porta).when().get("/api/produtos").then().statusCode(200)
		.contentType(ContentType.JSON)
		.body("id", not(contains(nullValue())));
	}
	
	@Test
	public void testeValidarSeTodosOsElementosTemNome() {
		given().port(porta).when().get("/api/produtos").then().statusCode(200)
		.contentType(ContentType.JSON)
		.body("id", not(contains(nullValue())));
	}
	
}

package br.com.alvaro.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.alvaro.model.Fornecedor;

@FeignClient(name = "clientfornecedores" , url = "https://servidor.com.produtos/api")
public interface IBuscaDeFornecedoresClient {
	
	@RequestMapping(method = RequestMethod.GET, value = "/produto/{id}", produces = "application/json")
    List<Fornecedor> getFornecedorPorId(@PathVariable("id") String id);

}

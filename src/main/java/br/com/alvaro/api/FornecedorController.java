package br.com.alvaro.api;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alvaro.client.IBuscaDeFornecedoresClient;
import br.com.alvaro.model.Fornecedor;

@RestController
@RequestMapping("/api/fornecedores")
public class FornecedorController {
	
	@Autowired
	IBuscaDeFornecedoresClient fornecedoresClient;

	@GetMapping("/{id}")
    public Collection<Fornecedor> getFornecedoresById(@PathVariable(value="id")String id) {
        return fornecedoresClient.getFornecedorPorId(id);
    }
	
}

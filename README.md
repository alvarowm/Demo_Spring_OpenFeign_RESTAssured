# Demo com Spring, OpenFeign e REST Assured.

 Um pequena demonstração do uso de Spring Boot, Spring Data, Open Feign e Base H2 para cirar APIs REST que simulam um aplicativo de pedidos.
 
 Além disso há um cliente com Open Feign para escolher o melhor fornecedor para executar essa tarefa.

 O aplicativo sobe um container de servlets via Spring Boot, faz a gestão das dependências com Maven, testes com Junit e Rest Assured.

https://rest-assured.io/

 A base é uma base em meória principal, h2, eliminando a necessidade de qualquer instalação:

https://www.h2database.com/html/main.html

 A Integração das ferramentas conta com testes que sobem o contexto do Spring para verificar o status correto dos retornos, configurando a porta para evitar colisões.
 
 OpenFeign é um client HTTP simples inspirado, entre outras ferramentas, em retrofit:
 
 https://github.com/OpenFeign/feign
 
 

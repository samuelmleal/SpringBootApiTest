# SpringBootApiTest
Technical test with SpringBoot for Attornatus

O projeto foi criado tentando seguir as orientações do projeto, e pelas orientações
entendi que a melhor forma de fazer o armazenamento de Adress e Person seria utilizar
2 tabelas no banco de dados, utilizando o ID da pessoa, ocmo chave estrangeira para interligar
com o Adress. Dessa forma, o endereço que ficará armazenado na tabela Person, é o Adress
informado como principal. Fazendo a divisão nesse modelo, fica mais visível ao buscar no banco 
o endereço principal, e interligar os endereços com a pessoa determinada.


## Estrutura de Pastas

```
> src
	> main                     
		> java				(Estrutura padrão do java para maven)
			com.attornatus.config	(Configurações de LOg e Swagger)
			com.attornatus.controller	(Endpoints REST)
			com.attornatus.service	(Regras de negócio)
			com.attornatus.repository	(Acesso ao banco e manipulação de dados)
			XxxApplication.java		(Classe principal, responsável pela execução da aplicação)
			pom.xml			(Arquivo de gerenciamento de dependências do projeto)
                             
> test                              
	> java				(Estrutura padrão do junit para maven)
		com.br.example.controller	(Testes Controller)
		com.br.example.service		(Testes Services)
```

## API Docs

As rotas do serviço são documentadas com o padrão de open API
```bash
$ http://localhost:8080/swagger-ui/index.html
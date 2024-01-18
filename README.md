# Banking Microservices
[![NPM](https://img.shields.io/npm/l/react)](https://github.com/RenanClaude/project-banking-microservices/blob/main/LICENSE) 

# Sobre o projeto

Banking Microservices é uma aplicação Web back-end desenvolvida para efetuar pequenos serviços de cartão de crédito.
A aplicação consiste em um serviço para emissão de cartões, havendo a possibilidade de solicitar de acordo com a renda do cliente. Dessa forma a aplicação também contém um avaliador de crédito.

### Microservice de cliente (msclient)
Microservice responsável pelos clientes, podendo criar um novo e verificar dados de um existente.

### Microservice de cartões (mscards)
Microservice responsável pelos cartões. Nesse microservice é possível criar um cartão, e acessar cartões diponíveis de acordo com uma renda especificada, ou acessar os cartões de acordo com um cliente especificado.

### Microservice de avaliador de crédito (mscreditappraiser)
Microservice responsável pelas avaliações dos clientes.
Nesse microservice clientes podem ser avaliados para verificar a possiblidade de solicitarem cartões.
Também é possível verificar a situação do cliente, ou seja, saber quais cartões tem. Por fim, este microservice trabalha junto com o de cartões,
pois é também possível fazer a solicitação de emissão de um cartão fazendo uma requisição para o microservice de cartões!

# Tecnologias utilizadas
- Java
- Spring Boot
- Spring Cloud
- Eureka Server
- RabbitMQ
- Keycloak
- Gateway
- Docker
- JPA / Hibernate
- Maven

# Como executar o projeto
Pré-requisitos: Java 17

```bash
# clonar repositório
git clone https://github.com/devsuperior/sds1-wmazoni

# entrar na pasta do projeto back end
cd backend

# executar o projeto
./mvnw spring-boot:run
```

# Autor

Renan Claude Grossl

[Linkedin](https://www.linkedin.com/in/renan-claude-dev/)

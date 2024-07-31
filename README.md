# API Filmes em Java + Spring Boot

Esta API de filmes foi desenvolvida utilizando Java e Spring Boot. O projeto tem como objetivo fornecer uma base para o aprendizado e integração com Amazon S3 e RDS utilizando PostgreSQL.

## Funcionalidades

A API contempla as seguintes funcionalidades:

- **Cadastro de Filmes**: Permite o cadastro de filmes, incluindo o upload de imagens associadas aos filmes para o Amazon S3 e o armazenamento dos dados dos filmes no Amazon RDS com PostgreSQL.
  
- **Consulta de Filmes**: Oferece um endpoint para a consulta dos filmes cadastrados, com suporte a paginação. Os dados são recuperados do banco de dados PostgreSQL no Amazon RDS.

## Tecnologias Utilizadas

- **Java**: Linguagem de programação principal.
- **Spring Boot**: Framework para simplificar a configuração e o desenvolvimento da API.
- **Amazon S3**: Serviço para armazenamento de objetos, utilizado para armazenar as imagens dos filmes.
- **Amazon RDS**: Serviço de banco de dados relacional, configurado com PostgreSQL para armazenar os dados dos filmes.
- **PostgreSQL**: Sistema de gerenciamento de banco de dados relacional.

## Endpoints

### Cadastro de Filmes

- **Método**: POST
- **URL**: `/api/filmes`
- **Descrição**: Cadastra um novo filme, incluindo o upload de uma imagem.

### Consulta de Filmes

- **Método**: GET
- **URL**: `/api/filmes`
- **Descrição**: Consulta os filmes cadastrados com suporte a paginação.
- **Parametros**: 
   - page (opcional): Número da página para paginação (padrão é 0).
   - size (opcional): Número de itens por página (padrão é 10).

# pessoa-api

Esta aplicação foi desenvolvida para submissão da vaga de Desenvolvedor na Softplan.

## Link's disponíveis para teste da aplicação

A aplicação foi disponibilizada no link abaixo para testes:

    https://softplan-pessoa-api.herokuapp.com/

Abaixo está sendo disponibilizado alguns exemplos de uso da aplicação utilizando uma ferramenta para requisições, 
no meu caso foi utilizada a Postman ou utilizar a ferramenta swagger-ui. É importante informar que esta implementada autenticação Basic, onde pode esta 
adicionando no header da requisição o seguinte Authorization, referente ao usuário admin e senha 123:

    Basic YWRtaW46MTIz

<b>[GET] Funcionalidade de buscar por id uma pessoa</b>
    
    https://softplan-pessoa-api.herokuapp.com/api/pessoa/1
    
<b>[GET] Funcionalidade de listar pessoas</b>

    https://softplan-pessoa-api.herokuapp.com/api/pessoa

<b>[POST] Funcionalidade de criar pessoas</b>

    https://softplan-pessoa-api.herokuapp.com/api/pessoa
    
Exemplo de corpo para realização da requisição post da funcionalidade acima

    {
      "cpf": "06388346430",
      "dataNascimento": "2019-10-11",
      "email": "admin@admin.com",
      "nacionalidade": "string",
      "naturalidade": "string",
      "nome": "Adalberto Brito",
      "sexo": "string"
    }

<b>[PUT] Funcionalidade de editar pessoas</b>

    https://softplan-pessoa-api.herokuapp.com/api/pessoa/1
    
Exemplo de corpo para realização da requisição post da funcionalidade acima

    {
      "cpf": "06388346430",
      "dataNascimento": "2019-10-11",
      "email": "admin@admin.com",
      "nacionalidade": "string",
      "naturalidade": "string",
      "nome": "Pessoa Alterada",
      "sexo": "string"
    }

<b>[DELETE] Funcionalidade de deletar pessoa</b>

    https://softplan-pessoa-api.herokuapp.com/api/pessoa/1


## Execução dos testes

Para execução dos testes, para isso ẽ necessário levantar o compose de nome services.yml com o banco postgres 
contido na pasta /src/main/docker, usando o seguinte comando:

     docker-compose -f /src/main/docker/services.yml up -d


    ./mvnw verify

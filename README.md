# Rápido Chamados

Sistrma para gerenciamento e acompanhamento de solicitação de chamados

## Tecnologias Utilizadas

- Java 17
- Angular 16
- Spring Boot
- JWT (JSON Web Tokens)
- JPA (Java Persistence API)
- Angular (com Angular Material)
- Bootstrap

## Pré-requisitos

- Java 17
- Maven
- Node.js e npm (para o frontend Angular)

## Configuração do Backend

1. Clone o repositório:

    ```bash
    git clone git@github.com:luks-santos/chamados-projeto-ufn.git
    ```

2. Navegue até o diretório do backend:

    ```bash
    cd ./backend-chamados-projeto-ufn
    ```

3. Configure as propriedades do banco de dados em `src/main/resources/application.properties`.

4. Execute o aplicativo Spring Boot:

    ```bash
    ./mvnw spring-boot:run
    ```

## Configuração do Frontend

1. Navegue até o diretório do frontend:

    ```bash
    cd ./frontend-chamados-projeto-ufn
    ```

2. Instale as dependências:

    ```bash
    npm install
    ```

3. Configure a URL da API no arquivo `proxy.conf.js`.

4. Execute o aplicativo Angular:

    ```bash
    npm run start
    ```

5. Abra o navegador e vá para `http://localhost:4200/` para acessar a aplicação.

## Funcionalidades Principais

- CRUD de Chamados
- CRUD de Assunto, Categoria e Comentário
- CRUD de Usuário

## Estrutura do Banco de dados

![Banco de Dados](./imgs/model.png)

## Front End

![Página Inicial](./imgs/principal.png)
*Página inicial.*

![Cadastro Chamados](./imgs/chamados.png)
*Cadastro de Chamados*

![Acompanhamento Chamados](./imgs/visua-chamados.png)
*Acompanhamento de Chamados*

## Contato

- [LinkedIn](https://www.linkedin.com/in/lucas-bt/)


## Configuração do Back-end
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens)
![MySQL](https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white)

1. Clone o repositório:

    ```bash
    git clone git@github.com:luks-santos/chamados-projeto-ufn.git
    ```

2. Navegue até o diretório do backend:

    ```bash
    cd ./backend-chamados-projeto-ufn
    ```

3. Configure as propriedades do banco de dados em: `src/main/resources/application.properties`
   
4. Execute o aplicativo Spring Boot:

    ```bash
    ./mvnw spring-boot:run
    ```
5. Cadastre um usuário administrador, faça uma requisição POST para `http://localhost:8080/api/auth/register` com o seguinte corpo da requisição:

```json
{
  "login": "admin",
  "password": "admin",
  "role": "ADMIN",
  "name": "admin",
  "cellphone": "(99) 99999-9999" 
}
```

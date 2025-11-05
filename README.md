# 🐾 API Pet Columbia

## 📌 Visão Geral
A **API Pet Columbia** é uma API REST desenvolvida em Java com Spring Boot, projetada para gerenciar agendamentos de serviços como banho e tosa para pets. Permite o cadastro e gerenciamento de usuários, pets e agendamentos, com funcionalidades exclusivas para administradores.

🎯 **Novo:** Arquitetura Limpa (Clean Architecture) implementada para módulos Owner e Pet!

---

## 📝 Funcionalidades Principais

- ✅ Cadastro, login e gerenciamento de perfil de usuários.
- 🐶 Cadastro e gerenciamento de pets.
- 📅 Agendamento e gerenciamento de serviços.
- 📖 Visualização do histórico de agendamentos.

**Para administradores:**
- 📊 Acesso ao dashboard administrativo.

---

## 🏗️ Arquitetura

O projeto foi migrado para **Clean Architecture**:

---

## ⚙️ Tecnologias Utilizadas

- **Java 21+** – Linguagem principal.
- **Spring Boot** – Framework da aplicação.
- **H2 Database** – Banco em memória para desenvolvimento.
- **PostgreSQL** – Banco usado em produção.
- **Maven** – Gerenciador de dependências e build.
- **Swagger** – Documentação interativa dos endpoints.
- **JWT** – Autenticação baseada em token.
- **Clean Architecture** – Padrão arquitetural para separação de camadas.

---

## 🛠️ Setup

### ✅ Pré-requisitos
- Java 21+
- Maven 3.8+
- PostgreSQL (apenas para produção)

### 📦 Instalação

1. Clone o repositório:
   ```bash
   git clone https://github.com/Sprint-Grupo-9/api-pet-columbia.git
   ```

2. Acesse o diretório do projeto:
   ```bash
   cd api-pet-columbia
   ```

3. Instale as dependências:
   ```bash
   mvn install
   ```
   Ou, se estiver usando alguma IDE, atualize via `pom.xml`.

---

## ⚙️ Configuração

Crie o arquivo `src/main/resources/application.properties` com as seguintes propriedades:

### Comum:
```properties
spring.application.name=api-pet-columbia
spring.application.version=1.0
spring.jpa.show-sql=true
server.error.include-message=always
server.error.include-binding-errors=always
spring.jpa.defer-datasource-initialization=true
jwt.secret=your_jwt_secret
jwt.validity=3600000
```

### Para banco H2 (desenvolvimento):
```properties
spring.jpa.properties.hibernate.format_sql=true
spring.h2.console.enabled=true
spring.datasource.url=jdbc:h2:mem:api
spring.datasource.username=admin
spring.datasource.password=admin
```

### Para banco PostgreSQL (produção):
```properties
spring.datasource.url=jdbc:postgresql:your_database_url
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.format_sql=true
```

---

## 🚀 Execução

Execute o projeto com:

```bash
mvn spring-boot:run
```

Ou inicie a classe principal `ApiPetColumbiaApplication.java` pela sua IDE (como IntelliJ).

---

## 🧪 Como Testar

### 🔹 1. Swagger UI
- Acesse: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
- Explore e envie requisições diretamente via navegador.

### 🔹 2. Postman ou Insomnia
- Use os endpoints documentados no Swagger.
- Apenas os endpoints de **cadastro de usuário** e **login** não exigem autenticação.
- Para os demais, gere um token JWT e envie no cabeçalho:

```http
Authorization: <seu_token>
```

> ⚠️ **Importante:** envie apenas o token, **sem** o prefixo `Bearer`.

### 🔹 3. Console H2
- Acesse: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
- Use:
   - JDBC URL: `jdbc:h2:mem:api`
   - Username: `admin`
   - Password: `admin`

---

## 🤝 Contribuindo

1. Faça um **Fork** do projeto
2. Crie uma nova branch:
   ```bash
   git checkout -b feature/NovaFuncionalidade
   ```
3. Faça suas alterações e commits:
   ```bash
   git commit -m "Adiciona NovaFuncionalidade"
   ```
4. Envie para seu fork:
   ```bash
   git push origin feature/NovaFuncionalidade
   ```
5. Abra um **Pull Request** para o repositório original.

---

## ✨ Agradecimentos

- Equipe de desenvolvimento 💻
- Contribuidores 👥
- Comunidade open source 🌍
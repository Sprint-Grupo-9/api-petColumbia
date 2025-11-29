# 🐾 API Pet Columbia

## 📌 Visão Geral
A **API Pet Columbia** é uma API REST desenvolvida em Java com Spring Boot, projetada para gerenciar agendamentos de serviços como banho e tosa para pets. Permite o cadastro e gerenciamento de usuários, pets e agendamentos, com funcionalidades exclusivas para administradores.

🎯 **Novo:** Arquitetura Limpa (Clean Architecture) implementada para módulos Owner e Pet!

🎯 **Novo:** Docker implementado e ajustes nos ambientes de desenvolvimento e produção.

---

## 📝 Funcionalidades Principais

- ✅ Cadastro, login e gerenciamento de perfil de usuários.
- 🐶 Cadastro e gerenciamento de pets.
- 📅 Agendamento e gerenciamento de serviços.
- 📖 Visualização do histórico de agendamentos.

**Para administradores:**
- 📊 Acesso ao dashboard administrativo.

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
- **Docker** – Containerização da aplicação e banco de dados.

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

## 🚀 Como Rodar o Projeto

### ▶️ Via IntelliJ IDEA (perfis `dev` ou `h2`)

1. Abra o projeto no IntelliJ e localize a classe principal `ApiPetColumbiaApplication.java`.
2. Clique na setinha de **Run/Play** ao lado da classe e selecione **Modify Run Configuration...** (ou **Edit Configurations...** no menu superior).
3. No campo **Active profiles**, informe **apenas um** dos perfis abaixo:
   - `dev` – uso típico em desenvolvimento com configurações padrão.
   - `h2` – uso com banco H2 em memória.
4. Salve a configuração e clique em **Run** para iniciar a aplicação com o perfil escolhido.

Você também pode rodar via Maven:

```bash
mvn spring-boot:run -Dspring-boot.run.profiles=dev
# ou
mvn spring-boot:run -Dspring-boot.run.profiles=h2
```

---

## 🐳 Docker (uso básico)

A API pode ser executada via Docker usando variáveis de ambiente.

1. Na raiz do projeto, copie o arquivo `.env.example` para `.env`:
   ```powershell
   copy .env.example .env
   ```
2. Edite o arquivo `.env` e defina o perfil desejado, por exemplo:
   ```env
   # Ambiente de Desenvolvimento
   SPRING_PROFILES_ACTIVE=dev
   # ou
   # SPRING_PROFILES_ACTIVE=h2
   ```
3. Suba os containers:
   ```powershell
   docker-compose up -d
   ```

- Console H2 (quando usar perfil `h2`): http://localhost:80/h2-console
- Swagger: http://localhost:80/swagger-ui/index.html

> Para cenários mais avançados (produção, PostgreSQL externo, múltiplos arquivos `.env`), use este mesmo padrão como base e ajuste as variáveis conforme necessário.

---

## ⚙️ Configuração

As propriedades de ambiente já estão organizadas em perfis (`dev`, `h2`, `prod`) nos arquivos `application-*.properties`. Caso precise criar ou ajustar configurações manuais, use como referência:

### Comum (exemplo):
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

### Para banco H2 (exemplo):
```properties
spring.jpa.properties.hibernate.format_sql=true
spring.h2.console.enabled=true
spring.datasource.url=jdbc:h2:mem:api
spring.datasource.username=admin
spring.datasource.password=admin
```

### Para banco PostgreSQL (exemplo):
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

## 🧪 Como Testar

### 🔹 Swagger UI
- Acesse: [http://localhost:80/swagger-ui/index.html]
- Explore e envie requisições diretamente pelo navegador.

### 🔹 Postman ou Insomnia
- Use os endpoints documentados no Swagger.
- Apenas os endpoints de **cadastro de usuário** e **login** não exigem autenticação.
- Para os demais, gere um token JWT e envie no cabeçalho:

```http
Authorization: <seu_token>
```

> ⚠️ **Importante:** envie apenas o token, **sem** o prefixo `Bearer`.

### 🔹 Console H2 (quando perfil `h2` estiver ativo)
- Acesse: [http://localhost:80/h2-console](http://localhost:8080/h2-console)
- Use:
  - JDBC URL: `jdbc:h2:mem:testepet`
  - Username: `admin`
  - Password: `admin`

---

## 🤝 Contribuindo

1. Faça um **Fork** do projeto.
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
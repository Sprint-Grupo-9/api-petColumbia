# 🐾 API Pet Columbia

## 📌 Visão Geral
A **API Pet Columbia** é uma API REST desenvolvida em Java com Spring Boot, projetada para gerenciar agendamentos de serviços como banho e tosa para pets. Permite o cadastro e gerenciamento de usuários, pets e agendamentos, com funcionalidades exclusivas para administradores.

🎯 **Novo:** Arquitetura Limpa (Clean Architecture) implementada em todo o projeto!

🎯 **Novo:** Docker implementado e ajustes nos ambientes de desenvolvimento e produção.

🎯 **Novo:** Cache com Redis em use cases de leitura (ofertas, horários disponíveis, agenda e dashboard) para melhorar desempenho.

---

## 📝 Funcionalidades Principais

- ✅ Cadastro, login e gerenciamento de perfil de usuários (owners)
- 🐶 Cadastro e gerenciamento de pets
- 📅 Agendamento e gerenciamento de serviços (pet offerings)
- 👥 Consulta de funcionários por serviços
- 📖 Visualização do histórico de agendamentos
- 📊 Dashboard administrativo com métricas e estatísticas
- 🔒 Autenticação JWT e autorização de endpoints
- ⚡ Cache Redis para otimização de consultas

---

## ⚙️ Tecnologias Utilizadas

- **Java 21+** – Linguagem principal.
- **Spring Boot** – Framework da aplicação.
- **H2 Database** – Banco em memória para desenvolvimento.
- **PostgreSQL** – Banco usado em produção.
- **Redis** – Armazenamento de cache (obrigatório em produção, opcional em dev/h2).
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
- Docker (opcional, recomendado para subir Redis e banco)
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
   - `dev` – uso típico em desenvolvimento com Postgres local.
   - `h2` – uso com banco H2 em memória.
4. Salve a configuração e clique em **Run** para iniciar a aplicação com o perfil escolhido.

Você também pode rodar via Maven:

```bash
mvn spring-boot:run -Dspring-boot.run.profiles=dev
# ou
mvn spring-boot:run -Dspring-boot.run.profiles=h2
```

> **📝 Nota sobre Cache:**
> - Por padrão, os perfis `dev` e `h2` usam cache em memória (simple).
> - Para habilitar Redis, configure as variáveis no arquivo `.env` (consulte `.env.example`).

---

## 🐳 Docker

1. **Configure as variáveis de ambiente:**
   - Crie um arquivo `.env` na raiz do projeto
   - Use o `.env.example` como referência para configurar seu perfil (`dev`, `h2` ou `prod`)

2. **Inicie os containers:**
   ```powershell
   docker-compose up -d
   ```

3. **Acesse a aplicação:**
   - Swagger: http://localhost:8080/swagger-ui/index.html
   - Console H2: http://localhost:8080/h2-console (apenas perfil `h2`)

> **💡 Dica:** Veja exemplos completos de configuração para cada perfil no arquivo `.env.example`.

---

## 🏗️ Arquitetura

O projeto utiliza **Clean Architecture** com separação de responsabilidades em camadas:

```
src/main/java/br/com/petcolumbia/api_pet_columbia/
├── core/                          # Regras de negócio (independente de frameworks)
│   ├── domain/                    # Entidades e modelos de domínio
│   ├── application/               # Casos de uso e DTOs
│   │   ├── usecase/              # Lógica de aplicação
│   │   ├── command/              # Commands para operações
│   │   └── dto/                  # DTOs de resposta
│   └── adapter/                   # Interfaces (ports)
└── infrastructure/                # Implementações técnicas
    ├── web/                       # Controllers REST
    ├── jpa/                       # Repositórios JPA
    ├── security/                  # Configuração de segurança
    ├── cache/                     # Configuração de cache
    └── dto/                       # DTOs de requisição e mappers
```

**Todos os módulos utilizam Clean Architecture:**
- Owner (usuários)
- Pet (animais de estimação)
- Appointment (agendamentos)
- Employee (funcionários)
- Pet Offering (serviços oferecidos)
- Dashboard (métricas e estatísticas)

---

## ⚙️ Perfis de Execução

| Perfil | Banco de Dados | Cache | Uso Recomendado |
|--------|---------------|-------|-----------------|
| `h2` | H2 (em memória) | Simple (Redis opcional) | Desenvolvimento rápido, testes |
| `dev` | PostgreSQL (local) | Simple (Redis opcional) | Desenvolvimento |
| `prod` | PostgreSQL | Redis (obrigatório) | Produção |

> **💡 Configuração:** As propriedades estão em `application-*.properties`. Para variáveis de ambiente e cache, consulte `.env.example`.

---

## 🧪 Como Testar

### 🔹 Swagger UI
- Acesse: http://localhost:8080/swagger-ui/index.html
- Explore e envie requisições diretamente pelo navegador.

### 🔹 Postman ou Insomnia
- Use os endpoints documentados no Swagger.
- **Endpoints públicos (não requerem autenticação):**
  - `POST /api/owners` - Cadastro de usuário
  - `POST /api/owners/login` - Login
  - `GET /api/pet-offerings` - Listar serviços oferecidos
- **Endpoints protegidos:** Requerem token JWT no cabeçalho:

```http
Authorization: <seu_token_jwt>
```

> ⚠️ **Importante:** Envie apenas o token, **sem** o prefixo `Bearer`.

### 🔹 Console H2 (quando perfil `h2` estiver ativo)
- Acesse: http://localhost:8080/h2-console
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
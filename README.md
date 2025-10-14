# API Todo List

Uma API REST feita para desenvolver mais a minha compreensão em swagger e jwt. Api de tarefas (Todo List) desenvolvida com Spring Boot, incluindo autenticação JWT, documentação Swagger e sistema de priorização automática.

## Funcionalidades

- **CRUD completo de tarefas** (Create, Read, Update, Delete)
- **Autenticação JWT** com Spring Security
- **Sistema de usuários** com diferentes perfis (USER/ADMIN)
- **Priorização automática** de tarefas próximas do vencimento
- **Documentação interativa** com Swagger UI
- **Agendamento de tarefas** com Spring Scheduler
- **Persistência** com PostgreSQL e JPA/Hibernate
- **Validação** de dados de entrada

## 🛠️ Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.5.4**
- **Spring Security** (Autenticação JWT)
- **Spring Data JPA** (Persistência)
- **PostgreSQL** (Banco de dados)
- **Swagger/OpenAPI 3** (Documentação)
- **Lombok** (Redução de boilerplate)
- **Maven** (Gerenciamento de dependências)

## Modelo de Dados

### Task (Tarefa)

- `id` - Identificador único
- `title` - Título da tarefa
- `description` - Descrição detalhada
- `priority` - Prioridade (URGENT, HIGH, MEDIUM, LOW)
- `completed` - Status de conclusão
- `createdAt` - Data/hora de criação
- `expiredAt` - Data/hora de vencimento
- `user` - Usuário proprietário da tarefa

### User (Usuário)

- `id` - Identificador único (UUID)
- `username` - Email do usuário
- `password` - Senha criptografada
- `cpf` - CPF do usuário
- `name` - Nome completo
- `userRole` - Perfil (USER/ADMIN)
- `tasks` - Lista de tarefas do usuário

## Funcionalidades Especiais

### Priorização Automática

O sistema possui um serviço de agendamento que executa diariamente à meia-noite, verificando tarefas não concluídas que estão próximas do vencimento e automaticamente aumenta sua prioridade.

### Segurança

- Autenticação baseada em JWT
- Controle de acesso por perfis de usuário
- Senhas criptografadas
- Proteção de endpoints sensíveis

## Como Executar

### Pré-requisitos

- Java 17+
- Maven 3.6+
- PostgreSQL 12+

### Configuração do Banco de Dados

1. Crie um banco de dados PostgreSQL:

```sql
CREATE DATABASE tasks;
```

2. Configure as credenciais no `application.properties`:

```properties
spring.datasource.url={URL_DO_SEU_BANCO_DE_DADOS}
spring.datasource.username={SEU_USUARIO}
spring.datasource.password={SUA_SENHA}
```

### Executando a Aplicação

```bash
# Clone o repositório
git clone https://github.com/EricSouzaDosSantos/api-swagger-todo-list.git

# Entre no diretório
cd api-todo-list

# Execute a aplicação
./mvnw spring-boot:run
```

A aplicação estará disponível em: `http://localhost:8080`

## Documentação da API

### Swagger UI

Acesse a documentação interativa da API em:

```
http://localhost:8080/swagger-ui.html
```

### Endpoints Principais

#### Tasks

- `POST /tasks` - Criar nova tarefa
- `GET /tasks` - Listar todas as tarefas
- `GET /tasks/{id}` - Buscar tarefa por ID
- `PUT /tasks/{id}` - Atualizar tarefa
- `DELETE /tasks/{id}` - Excluir tarefa

#### Exemplos de Requisição

**Criar Tarefa:**

\\ POST /tasks
```json

{
  "title": "Finalizar documentação da API",
  "description": "Criar README completo com exemplos",
  "priority": "HIGH",
  "completed": false,
  "expiredAt": "2024-12-31T23:59:59"
}

```

**Resposta:**

```json
{
  "id": 1,
  "title": "Finalizar documentação da API",
  "description": "Criar README completo com exemplos",
  "priority": "HIGH",
  "completed": false,
  "createdAt": "2024-10-14T10:00:00",
  "expiredAt": "2024-12-31T23:59:59"
}
```

## Configurações

### Prioridades de Task

- `URGENT` - Prioridade máxima
- `HIGH` - Alta prioridade
- `MEDIUM` - Prioridade média
- `LOW` - Baixa prioridade

### Perfis de Usuário

- `USER` - Usuário padrão
- `ADMIN` - Administrador do sistema

## 🧪 Testes

Execute os testes da aplicação:

```bash
./mvnw test
```

## 🤝 Contribuição

1. Faça um fork do projeto
2. Crie uma branch para sua feature (`git checkout -b feature/AmazingFeature`)
3. Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. Push para a branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request

## Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para detalhes.

## Autor

**Eric Souza dos Santos**

- GitHub: [@EricSouzaDosSantos](https://github.com/EricSouzaDosSantos)

---

⭐ Se este projeto foi útil para você, considere dar uma estrela no repositório!

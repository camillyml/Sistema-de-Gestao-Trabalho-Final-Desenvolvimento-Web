# Sistema de Gestão de Vendas

[![Java](https://img.shields.io/badge/Java-21-blue.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.4-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![MySQL](https://img.shields.io/badge/MySQL-8.0-orange.svg)](https://www.mysql.com/)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

Sistema completo de gestão de vendas desenvolvido com Spring Boot, implementando operações CRUD para clientes, produtos, fornecedores e vendas, com logging automático via AOP e front-end responsivo.

## Funcionalidades

### Back-end
- Cadastro de Clientes (com endereços)
- Cadastro de Produtos
- Cadastro de Fornecedores  
- Registro de Vendas (com múltiplos itens)
- Listagem de todas as entidades
- Busca por ID e CPF/CNPJ
- Exclusão de registros
- Validações com Bean Validation
- Logging automático com AOP
- Tratamento de exceções

### Front-end
- Interface responsiva (mobile-first)
- Páginas de cadastro para todas as entidades
- Páginas de listagem com cards dinâmicos
- Comunicação com API via Fetch
- Feedback visual para usuário
- Menu principal centralizado

## Tecnologias Utilizadas

### Back-end
- **Java 21** - Linguagem de programação
- **Spring Boot 3.2.4** - Framework principal
- **Spring Data JPA** - Persistência e ORM
- **Spring Web MVC** - API REST
- **Spring AOP** - Programação Orientada a Aspectos
- **Bean Validation** - Validação de dados
- **MySQL** - Banco de dados relacional
- **H2 Database** - Banco em memória para testes
- **Maven** - Gerenciamento de dependências

### Front-end
- **HTML5** - Estrutura das páginas
- **CSS3** - Estilização e Flexbox
- **JavaScript** - Interatividade e Fetch API
- **Design Responsivo** - Adaptação para mobile

## Modelagem do Banco de Dados

### Entidades Principais
- **Cliente** -> EnderecoCliente (1 para N)
- **Venda** -> ItemVenda (1 para N)
- **Venda** -> Cliente (N para 1)
- **ItemVenda** -> Produto (N para 1)
- **Fornecedor** (independente)

## Como Executar

### Pré-requisitos
- Java 21 ou superior
- MySQL 8.0
- Maven

### Configuração do Banco
1. Crie um banco MySQL:
```sql
CREATE DATABASE database;
```

2. Configure o `application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/database?serverTimezone=UTC
spring.datasource.username=ppi
spring.datasource.password=sua_senha
```

### Execução
```bash
# Clonar repositório
git clone https://github.com/seu-usuario/sistema-vendas.git

# Entrar no diretório
cd sistema-vendas

# Compilar e executar
mvn spring-boot:run
```

Acesse: `http://localhost:8080`

## Endpoints da API

### Clientes
| Método | URL | Descrição |
|--------|-----|-----------|
| POST | `/clientes` | Criar cliente com endereço |
| GET | `/clientes` | Listar todos |
| GET | `/clientes/{id}` | Buscar por ID |
| GET | `/clientes/cpf/{cpf}` | Buscar por CPF |
| DELETE | `/clientes/{id}` | Excluir cliente |

### Produtos
| Método | URL | Descrição |
|--------|-----|-----------|
| POST | `/produtos` | Criar produto |
| GET | `/produtos` | Listar todos |
| GET | `/produtos/{id}` | Buscar por ID |
| GET | `/produtos/nome/{nome}` | Buscar por nome |
| GET | `/produtos/preco/{precoLimite}` | Filtrar por preço |
| PUT | `/produtos/{id}` | Atualizar produto |
| DELETE | `/produtos/{id}` | Excluir produto |

### Fornecedores
| Método | URL | Descrição |
|--------|-----|-----------|
| POST | `/fornecedores` | Criar fornecedor |
| GET | `/fornecedores` | Listar todos |
| GET | `/fornecedores/{id}` | Buscar por ID |
| PUT | `/fornecedores/{id}` | Atualizar fornecedor |
| DELETE | `/fornecedores/{id}` | Excluir fornecedor |

### Vendas
| Método | URL | Descrição |
|--------|-----|-----------|
| POST | `/vendas` | Registrar venda |
| GET | `/vendas` | Listar todas |
| GET | `/vendas/{id}` | Buscar por ID |
| DELETE | `/vendas/{id}` | Excluir venda |

## Exemplos de Requisições

### Criar Cliente
```json
POST /clientes
{
  "nome": "João Silva",
  "cpf": "123.456.789-00",
  "endereco": "Rua A, 123"
}
```

### Registrar Venda
```json
POST /vendas
{
  "clienteId": 1,
  "itens": [
    {
      "produtoId": 1,
      "quantidade": 2,
      "descontoPercentual": 10.0
    }
  ]
}
```

## Logging com AOP

O sistema implementa logging automático para todas as operações da camada de serviço, exibindo:
- Nome do método executado
- Parâmetros recebidos
- Tempo de execução (ms)
- Exceções lançadas

Exemplo de saída no console:
```
===== [AOP LOG] Entrando em VendaService.criarVenda =====
Parâmetros recebidos: [VendaRequestDTO[clienteId=1, itens=[...]]]
Tempo de execução de VendaService.criarVenda: 45 ms
===== [AOP LOG] Saindo de VendaService.criarVenda =====
```

## Front-end Responsivo

O sistema conta com interface adaptável para diferentes tamanhos de tela:
- Smartphones (até 600px): Layout em coluna
- Tablets (601px - 1024px): Layout híbrido
- Desktop (acima 1024px): Cards e grids

## Melhorias Futuras

- Paginação nas listagens
- Autenticação e autorização
- Relatórios de vendas
- Dashboard com gráficos
- Filtros avançados
- Exportação para PDF/Excel

## Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

## Autor

Desenvolvido como trabalho acadêmico para disciplina de Desenvolvimento Web II.

---

**Nota:** Este projeto foi desenvolvido com foco educacional para demonstrar conceitos de Spring Boot, AOP, REST APIs e integração com front-end.

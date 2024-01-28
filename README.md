# 🚀 Projeto - Sistema de Transações Financeiras

Este é um projeto que demonstra o funcionamento de um sistema de transações financeiras.

## 🚩 Classes:

### ⊳ Cliente
    • Atributos:
      • id (Long)
      • nome (String)
      • cpf (String)

### ⊳ Empresa
    • Atributos:
      • id (Long)
      • nome (String)
      • cnpj (String)
      • saldo (BigDecimal)

### ⊳ Transacao
    • Atributos:
      • id (Long)
      • tipo (TipoTransacao)
      • valor (BigDecimal)
      • data (LocalDateTime)
      • cliente (Cliente)
      • empresa (Empresa)

### Relacionamentos:

Cliente 1 --- * Transacao: Um cliente pode ter várias transações.

Empresa 1 --- * Transacao: Uma empresa pode ter várias transações.

## Diagrama:

![Diagrama.png](img%2FDiagrama.png)

## Tipos de Transações

O atributo tipo da classe Transação é um tipo enumerado que representa os diferentes tipos de transações que podem ser realizadas no sistema. Os valores possíveis para este atributo são:

- DEPÓSITO: Representa um depósito realizado por um cliente.
- SAQUE: Representa um saque realizado por um cliente.

## 📚 Tecnologias Utilizadas:

- Java 17+
- Spring Boot
- H2 Database 
- JUnit e Mockito
- Swagger - http://localhost:8080/swagger-ui/index.html#/

## 🏗️ Estrutura:

O projeto está estruturado da seguinte forma:

- `src/main/java`: Código-fonte da aplicação
- `src/test/java`: Testes unitários
- `pom.xml`: Arquivo de configuração do Maven

## 🛠️ Como Rodar o Projeto:

1. Clone o repositório para o seu ambiente local.
2. Importe o projeto em sua IDE preferida.
3. Certifique-se de ter o Java e o Maven instalados em sua máquina.
4. Configure as propriedades do banco de dados no arquivo `application.properties`.
5. Execute a aplicação.

## 📌 Pontos de melhoria:

- **Implementação de Autenticação e Autorização:** Adicionar autenticação e autorização para restringir o acesso a determinadas partes da aplicação e proteger os dados do usuário.
- **Implementação de Logging e Monitoramento:** Adicionar logging e monitoramento para acompanhar o desempenho da aplicação, identificar problemas e analisar o comportamento do usuário.
- **Implementação de Funcionalidades Adicionais:** Adicionar novas funcionalidades ou aprimorar as existentes com base no feedback dos usuários e nas necessidades do negócio.

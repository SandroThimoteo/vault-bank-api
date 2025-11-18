# Arquitetura - Vault Bank API

## 1. Visão Geral
Sistema bancário backend construído com arquitetura em camadas:

controller → service → repository → domain

Foco em: transações, segurança, consistência e concorrência.

---

## 2. Entidades Centrais

### Customer
- id
- name
- email
- cpf
- passwordHash

### Account
- id
- accountNumber
- balance (DECIMAL(19,4))
- ownerId
- version (para optimistic locking)
- createdAt

### Transaction
- id
- accountId
- type (DEPOSIT/WITHDRAW/TRANSFER)
- amount
- balanceAfter
- reference
- createdAt

---

## 3. Regras-chave da arquitetura

- BigDecimal para manipulação de valores
- @Transactional nas operações financeiras
- Lock pessimista para garantir consistência (`SELECT … FOR UPDATE`)
- Flyway para versionamento do banco
- JWT para segurança
- Testcontainers para testes de integração

---

## 4. Módulos futuros

- `vault-auth`
- `vault-transactions`
- `vault-customer`
- `vault-accounts`
- `vault-analytics`

---

## 5. Fluxo de uma transferência

1. Validar usuário
2. Carregar contas com lock
3. Validar saldo
4. Debitar conta origem
5. Creditar conta destino
6. Registrar transação
7. Commit da transação

---

## 6. Padrões usados
- DTOs para entrada/saída
- Services puros, sem regras em controllers
- Repositórios apenas para acesso ao banco
- Exceptions customizadas
- Idempotência em transferências

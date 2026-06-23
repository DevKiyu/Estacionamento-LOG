# 🅿️ Sistema de Automação de Estacionamento

> Sistema completo para gerenciamento e automação de estacionamentos, desenvolvido em Java. Controla entrada e saída de veículos, calcula tarifas automaticamente e gera relatórios gerenciais.

---

## 📋 Índice

- [Sobre o Projeto](#-sobre-o-projeto)
- [Funcionalidades](#-funcionalidades)
- [Tecnologias Utilizadas](#-tecnologias-utilizadas)
- [Pré-requisitos](#-pré-requisitos)
- [Instalação e Execução](#-instalação-e-execução)
- [Estrutura do Projeto](#-estrutura-do-projeto)
- [Como Usar](#-como-usar)
- [Regras de Negócio](#-regras-de-negócio)
- [Contribuindo](#-contribuindo)
- [Licença](#-licença)

---

## 📖 Sobre o Projeto

O **Sistema de Automação de Estacionamento** é uma aplicação Java que simula e gerencia o fluxo de veículos em um estacionamento. O sistema automatiza o controle de vagas, o cálculo de tarifas por tempo de permanência e o registro de movimentações, substituindo processos manuais por uma solução digital eficiente.

---

## ✅ Funcionalidades

- 🚗 **Registro de entrada** de veículos com captura automática de data e hora
- 🚪 **Registro de saída** e cálculo automático do tempo de permanência
- 💰 **Cálculo de tarifa** baseado em faixas de tempo configuráveis
- 🅿️ **Controle de vagas** disponíveis em tempo real
- 🔍 **Consulta de veículo** por placa
- 📊 **Relatórios gerenciais** com faturamento diário, semanal e mensal
- 🏷️ **Suporte a mensalistas** e diferentes categorias de clientes
- 📁 **Persistência de dados** em arquivo ou banco de dados

---

## 🛠️ Tecnologias Utilizadas

| Tecnologia | Versão | Finalidade |
|---|---|---|
| Java | 17+ | Linguagem principal |

---


### 1. Clone o repositório

```bash
git clone https://github.com/DevKiyu/Estacionamento-LOG
cd estacionamento-java
```

### 2. Configure o banco de dados

Edite o arquivo `src/main/resources/application.properties`:

```properties
db.url=jdbc:mysql://localhost:3306/estacionamento
db.user=seu_usuario
db.password=sua_senha
```

### 3. Execute as migrations

```bash
mvn flyway:migrate
```

### 4. Compile e execute

```bash
mvn clean install
mvn exec:java -Dexec.mainClass="com.estacionamento.Main"
```

### Execução com H2 (sem banco externo)

```bash
mvn exec:java -Dexec.mainClass="com.estacionamento.Main" -Dprofile=dev
```

---

## 🗂️ Estrutura do Projeto

```
estacionamento-java/
│
├── src/
│   ├── main/
│   │   ├── java/com/estacionamento/
│   │   │   ├── Main.java                  # Ponto de entrada
│   │   │   ├── model/
│   │   │   │   ├── Veiculo.java           # Entidade veículo
│   │   │   │   ├── Ticket.java            # Registro de entrada/saída
│   │   │   │   └── Vaga.java              # Entidade vaga
│   │   │   ├── service/
│   │   │   │   ├── EstacionamentoService.java  # Lógica principal
│   │   │   │   └── TarifaService.java          # Cálculo de tarifas
│   │   │   ├── repository/
│   │   │   │   ├── VeiculoRepository.java
│   │   │   │   └── TicketRepository.java
│   │   │   └── util/
│   │   │       └── DateTimeUtil.java
│   │   └── resources/
│   │       ├── application.properties
│   │       └── db/migration/              # Scripts SQL (Flyway)
│   │
│   └── test/
│       └── java/com/estacionamento/
│           ├── service/
│           │   └── TarifaServiceTest.java
│           └── model/
│               └── TicketTest.java
│
├── pom.xml
└── README.md
```


### Tabela de Tarifas (padrão)

| Tempo de Permanência | Tarifa |
|---|---|
| Até 30 minutos | R$ 5,00 (fracionado) |
| 1ª hora | R$ 10,00 |
| Horas adicionais | R$ 5,00 / hora |
| Diária máxima | R$ 50,00 |

> As tarifas são configuráveis via arquivo `application.properties` ou painel administrativo.

### Tipos de Veículos Suportados

- 🚗 Carro

## 📄 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

---

<p align="center">Desenvolvido com ☕ e Java</p>

# Bot Telegram Validador de CPF

## Descrição

Projeto desenvolvido em Java com o objetivo de criar um bot no Telegram capaz de validar números de CPF enviados pelos usuários.

A comunicação com a API do Telegram foi realizada manualmente utilizando requisições HTTP, sem o uso de bibliotecas externas que encapsulam a API, seguindo as regras propostas no trabalho.

---

## Funcionalidades

* Recebimento de mensagens via Telegram
* Validação automática de CPF
* Resposta informando se o CPF é válido ou inválido
* Comando `/start`

---

## Tecnologias Utilizadas

* Java
* API oficial do Telegram
* HttpURLConnection
* Regex

---

## Estrutura do Projeto

```text
Main.java
```

---

## Como Executar

### 1. Clonar o repositório

```bash
git clone https://github.com/ericlima777/telegram-bot-cpf
```

---

### 2. Abrir o projeto

Abrir a pasta do projeto no VS Code ou IntelliJ.

---

### 3. Inserir o token do bot

No arquivo `Main.java`, alterar:

```java
private static final String TOKEN = "8964182455:AAFrkmEMMZnNqBuX7PPd_nf6KzSAGzTpx4w";
```

---

### 4. Compilar o projeto

```bash
javac Main.java
```

---

### 5. Executar

```bash
java Main
```

---

## Como Utilizar

1. Abrir o bot no Telegram
2. Enviar o comando:

```text
/start
```

3. Enviar um CPF para validação

Exemplo:

```text
12345678909
```

---

## Exemplo de Resposta

CPF válido:

```text
✅ CPF válido!
```

CPF inválido:

```text
❌ CPF inválido!
```

---

## Integrantes

1 - Giovanna
2 - Pietro
3-  Rhyan
4-  haniel
5- Junior
6- Érick

---

## Observações

O projeto utiliza apenas bibliotecas padrão do Java e realiza comunicação direta com a API oficial do Telegram, respeitando os requisitos do trabalho.

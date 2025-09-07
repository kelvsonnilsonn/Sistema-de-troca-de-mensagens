# 🗂️ Message Application

Um sistema de troca de mensagens entre usuários desenvolvido em Java com Spring Boot, apresentando uma interface via linha de comando (CLI).

## ✨ Funcionalidades

- **👤 Autenticação de Usuários**
  - Login com username e senha
  - Registro de novos usuários
  - Logout

- **✉️ Sistema de Mensagens**
  - Envio de mensagens para outros usuários
  - Leitura de mensagens recebidas
  - Listagem de mensagens enviadas e recebidas
  - Atualização e exclusão de mensagens

- **🔒 Validações e Segurança**
  - Validação de formato de username e senha
  - Verificação de permissões para leitura de mensagens
  - tratamento de exceções personalizadas

## 🛠️ Tecnologias Utilizadas

- **Java 21**
- **Spring 3.5.0**
- **Spring Data JPA** (com H2 database em memória).
- **Lombok** para redução de boilerplate code
- **Maven** para gerenciamento de dependências

## 📋 Pré-requisitos

- JDK 21+
- Maven 3.6+
- Terminal/Command Prompt funcional

## 🚀 Como Executar

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/kelvsonnilsonn/Sistema-de-troca-de-mensagens
   cd message-application
   mvn clean compile
   mvn spring-boot:run
   Interaja com o sistema através do menu no terminal
   ```

## 🎮 Como Usar

### 1. Primeiro Acesso
- Ao iniciar a aplicação, escolha a opção **"2. Registrar novo usuário"**
- Digite um username e senha seguindo as regras de validação abaixo

### 2. Regras de Validação

**📝 Username:**
- Mínimo de **3 caracteres**
- Deve conter **pelo menos 1 letra**
- Permite letras, números e underscore (_)
- Exemplos válidos: `kelvin_123`, `ana23`, `usuario`

**🔑 Password:**
- Mínimo de **3 caracteres**
- Deve **começar com letra maiúscula**
- Permite letras e números
- Exemplos válidos: `Senha123`, `A123`, `Teste45`

### 3. Fluxo Principal

1.  **Login** com suas credenciais registradas
2.  **Enviar mensagem** para outros usuários (é necessário conhecer o ID do destinatário)
3.  **Ler mensagens** recebidas
4.  **Listar** histórico de mensagens (enviadas e recebidas)
5.  **Logout** quando finalizar a sessão

## 🏗️ Estrutura do Projeto
``` text
src/
├── main/
│   ├── java/com/orizon/system/message/
│   │   ├── application/          # Camada de aplicação (CLI)
│   │   ├── config/              # Configurações e utilitários
│   │   ├── domain/
│   │   │   ├── model/           # Entidades do domínio
│   │   │   ├── services/        # Serviços de domínio
│   │   │   ├── valueobjects/    # Objetos de valor
│   │   │   └── exceptions/      # Exceções personalizadas
│   │   ├── repositories/        # Interfaces de repositório
│   │   └── MessageApplication.java # Classe principal
│   └── resources/
│       └── application.properties
```

## 🔧 Configuração

O projeto utiliza um banco **H2 em memória**, perfeito para desenvolvimento e testes. As configurações podem ser ajustadas no arquivo `application.properties`:

```properties
# Configurações do H2 (Banco em Memória)
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

# H2 Console (acessível em http://localhost:8080/h2-console)
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

# Configurações do JPA/Hibernate
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=false
```

Caso queira um banco real como MySQL ou PostgreSQL, basta alterar as configurações no `properties`.

## 🧪 Exceções Personalizadas

O sistema inclui tratamento de exceções específicas para melhor feedback ao usuário:

- **`InvalidIdentifierException`** - ID inválido ou não encontrado
- **`InvalidMessageContentException`** - Conteúdo de mensagem inválido ou vazio  
- **`InvalidMessageReceiverException`** - Tentativa de ler mensagem de outro usuário

## 📝 Notas de Desenvolvimento

- ✨ **Interface Amigável**: Mensagens exibidas em formato formatado com bordas estilizadas
- 🔐 **Gestão de Estado**: Sistema mantém o estado do usuário logado através da classe `AccountConfigurations`
- ✅ **Validações Robustas**: Todas as operações são validadas conforme as regras de negócio
- 🗃️ **Banco Volátil**: O banco H2 é reiniciado a cada execução da aplicação (dados temporários)

---

**Desenvolvido por Kelvson Nilson** - 2025

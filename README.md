# Sistema de Gerenciamento de Pets 🐾

## 📋 Descrição
Este é um sistema de gerenciamento de pets desenvolvido com Kotlin e Spring Boot. O projeto visa fornecer uma API RESTful para gerenciamento de informações sobre pets, incluindo cadastro, consulta, atualização e remoção de dados.

## 🚀 Tecnologias Utilizadas
- Kotlin
- Spring Boot
- Gradle
- JPA/Hibernate
- PostgreSQL
- Docker (opcional)

## 📋 Pré-requisitos
- JDK 17 ou superior
- Gradle 8.x
- PostgreSQL (se não estiver usando Docker)

## 🔧 Configuração do Ambiente

### 1. Clone o repositório
```bash
git clone https://github.com/seu-usuario/pets.git
cd pets
```

### 2. Configuração do Banco de Dados
O projeto utiliza PostgreSQL como banco de dados. Você pode configurar as credenciais no arquivo `application.properties` ou `application.yml`.

### 3. Executando o Projeto
```bash
# Usando Gradle
./gradlew bootRun

# Ou usando o wrapper do Gradle
./gradlew build
java -jar build/libs/pets-0.0.1-SNAPSHOT.jar
```

## 📚 Documentação da API
A documentação da API está disponível através do Swagger UI quando o projeto estiver em execução:
```
http://localhost:8080/swagger-ui.html
```

## 🏗️ Estrutura do Projeto
```
src/
├── main/
│   ├── kotlin/
│   │   └── com/
│   │       └── pets/
│   │           ├── controllers/
│   │           ├── models/
│   │           ├── repositories/
│   │           ├── services/
│   │           └── PetsApplication.kt
│   └── resources/
│       └── application.properties
└── test/
    └── kotlin/
        └── com/
            └── pets/
```

## 🧪 Testes
Para executar os testes do projeto:
```bash
./gradlew test
```

## 📝 Licença
Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

## 👥 Contribuição
1. Faça o fork do projeto
2. Crie uma branch para sua feature (`git checkout -b feature/AmazingFeature`)
3. Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. Push para a branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request

## 📫 Contato
Seu Nome - [@seu_twitter](https://twitter.com/seu_twitter) - email@exemplo.com

Link do Projeto: [https://github.com/seu-usuario/pets](https://github.com/seu-usuario/pets)
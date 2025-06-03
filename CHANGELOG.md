# Registro de Alterações

Todos as alterações notáveis neste projeto serão documentadas neste arquivo.

O formato é baseado em [Keep a Changelog](https://keepachangelog.com/pt-BR/1.0.0/),
e este projeto adere ao [Versionamento Semântico](https://semver.org/lang/pt-BR/).

## [Não Lançado]

### Adicionado
- Implementação inicial do gerenciamento de Espécies
  - Controller, Service e Repository
  - DTOs para transferência de dados
  - Tratamento de exceções
- Criação da entidade Raça com anotações Hibernate
- Configuração inicial do projeto com estrutura base
- Configuração do Gradle e extensões Kotlin
- Arquivo `.gitignore` para IntelliJ e arquivos de banco de dados

### Alterado
- Refatoração dos métodos relacionados a Espécies
  - Remoção de tipos de retorno `Unit` redundantes
  - Correção de pequenos erros de digitação

### Removido
- Função de comparação redundante

## [0.1.0] - 2025-06-03

### Adicionado
- Inicialização do projeto
- Estrutura base do projeto
- Configuração do Gradle
- Extensões Kotlin
- Aplicação base

### Notas de Desenvolvimento
- Commit inicial: 8ef944c
- Desenvolvido por: Douglas Dreer 
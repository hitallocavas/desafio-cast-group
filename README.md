# Gerenciador de Documentos

Aplicação para gerenciamento e cálculo de diferenças entre conteúdos de documentos


## 1. Funcionalidades

- Inserção de Documentos
- Cálculo de diferença entre conteúdos de arquivos

## 2. Configurações

Para executar o projeto, siga as instruções abaixo:

    1. Faça clone deste projeto em algum diretório do seu computador
    2. Entre no novo diretório gerado a partir do clone
    3. Execute ./mvnw clean install
    4. Execute java -jar document-manager-0.0.1-SNAPSHOT.jar

## 3. Execução de Testes

    1. Para executar testes sem relatórios de cobertura, execute ./mvnw test
    2. Para executar testes com relatórios de cobertura, execute ./mvnw test jacoco:report
        _Relatórios estarão disponíveis no diretório target\site\jacoco\index.html_



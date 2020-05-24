# Gerenciador de Documentos

Aplicação para gerenciamento e cálculo de diferenças entre conteúdos de documentos


## 1. Funcionalidades

- Inserção de Documentos
- Cálculo de diferença entre conteúdos de arquivos

## 2. Especificações

Para construção da Api, as seguintes tecnologias foram utilizadas
    
    1. Java 11 
    2. Maven
    3. JUnit
    4. Mockito
    5. Rest Assured

## 3. Configurações

Para executar o projeto, siga as instruções abaixo:
    
    1. Para executar este projeto é necessário ter o Java 11 instalado na sua máquina. 
       Verifique se o mesmo está instalado, ou instale caso necessário.
    2. Faça clone deste projeto em algum diretório do seu computador
    3. Entre no novo diretório gerado a partir do clone
    4. Execute ./mvnw clean install
    5. Na pasta target, execute java -jar document-manager-0.0.1-SNAPSHOT.jar

## 4. Execução de Testes

    1. Para executar testes sem relatórios de cobertura, execute ./mvnw test
    2. Para executar testes com relatórios de cobertura, execute ./mvnw test jacoco:report
    3. O acesso aos relatórios gerados pelo jacoco estão disponíveis no arquivo 
       index.html no diretório target/site/jacoco/      

## 5. Documentação de Testes

- Confira a documentação para execução no [Postman](https://www.postman.com/) a partir da 
[Documentação da Api de Gerenciamento de Documentos](https://explore.postman.com/templates/8664/gerenciador-de-documentos)


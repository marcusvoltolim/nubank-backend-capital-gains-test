## Decisões técnicas e arquiteturais
1. Usei o JAVA 21 que contempla diversas funcionalidades como Streams, Record, Switch Expression, Var;
2. Optei por crias as classes de DTOs para melhorar a legibilidade e manutenibilidade;
3. Todos os parâmetros e variáveis privadas tem o modificador `final`;  
4. O cálculo das taxas das operações foram implementados na classe [OperationsTaxCalculator.java](src/main/java/com/nubank/capital/gains/OperationsTaxCalculator.java);
5. A classe [Main.java](src/main/java/com/nubank/capital/gains/Main.java) contém apenas o método main para manipular entrada/saída padrões;
6. Implementei os 8 cenários de testes da documentação.

## Uso de bibliotecas/plugins:
1. GSON - para facilitar a manipulação da entrada/saída no formato JSON;
2. Junit 5 - para implementar os testes unitários;
3. maven-shade-plugin - para criar um "JAR Executável" para facilitar a execução do projeto;
4. maven-surefire-plugin - para executar os testes via Maven;

## Compilar e Executar e Tests:
1. Necessário JDK21 e Maven 3
2. Buildar projeto: `mvn clean install`
3. Executar projeto: `java -jar target/capital-gains-1.0.0.jar` (dentro da pasta raiz do projeto)
4. Executar os testes unitários:`mvn test`

## Docker
1. `docker image build -t capital-gains:latest .`
2. `docker container run -it capital-gains:latest`

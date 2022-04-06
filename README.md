# Sample Todo application showcasing acceptance and unit testing 

## Run main
```
mvn exec:java -Dexec.mainClass="com.se.todos.Main"  -Dexec.args="\"resources/todos.json\" \"Refactor\""
```

## How to use

Build and test unit tests with a coverage report:

```
mvn clean package
```

Build and test acceptance tests with a coverage report:

```
mvn clean verify
```

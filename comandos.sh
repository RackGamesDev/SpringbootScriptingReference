#!/bin/bash
#Iniciar proyecto
#Se puede hacer desde start.spring.io o con este comando
mvn archetype:generate \
  -DarchetypeGroupId=org.springframework.boot \
  -DarchetypeArtifactId=spring-boot-sample-maven-archetype \
  -DarchetypeVersion=3.1.5 \
  -DgroupId=com.ejemplo \
  -DartifactId=SpringbootScriptingReference \
  -Dversion=0.0.1-SNAPSHOT \
  -Dpackage=com.ejemplo.SpringbootScriptingReference \
  -B

#Inicializar
mvn install
./mvnw -U clean install

#Instalar dependencias
#Ir a mvnrepository o central.sonatype y pegar en pom.xml <dependency><groupId>org.springframework.boot</groupId><artifactId>spring-boot-starter-web</artifactId><version>4.1.0-M2</version></dependency>
./mvnw dependency:resolve
mvn dependency:list

#Compilar
./mvnw clean package

#Ejecutar (por defecto se abre en 8080, si no lo que ponga en application.properties)
mvn spring-boot:run -DskipTests
./mvnw spring-boot:run

#Ejecutar tests
mvn test

#Compilar y ejecutar (cambiar antes parametros de dev a prod)
mvn package -DskipTests
java -jar target/SpringbootScriptingReference-0.0.1-SNAPSHOT.jar

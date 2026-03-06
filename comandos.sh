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


#Ejecutar
mvn spring-boot:run
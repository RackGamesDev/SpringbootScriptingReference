package com.ejemplo.SpringbootScriptingReference;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.ejemplo.SpringbootScriptingReference.Services.EjemploService;

@SpringBootApplication
public class SpringbootScriptingReferenceApplication {

	public static void main(String[] args) {
		//Si se comenta esta linea y se pone otra cosa en su lugar, sera lo que se ejecute al abrir la aplicacion
		ApplicationContext context = SpringApplication.run(SpringbootScriptingReferenceApplication.class, args); //Contenedor IOC
		var servicio = context.getBean(EjemploService.class); //Haciendo un Bean (proceso) de Spring a partir del contenedor IOC, los Beans deben de ser clases con @Component/@Service, @Repository, @Controller
		servicio.ejemplo(6); //Usandolo
	}

}

package com.ejemplo.SpringbootScriptingReference.Services;

import org.springframework.stereotype.Service;

import com.ejemplo.SpringbootScriptingReference.Interfaces.GenericoService;

//Esta clase se usa por EjemploService de forma indirecta mediante inyeccion de dependencias, lo cual es mas escalable usando interfaces
@Service
public class ExternoService implements GenericoService { //Usa la interfaz intermediaria de ejemplo entre servicios
    @Override
    public void procesar(int numero) { //Ejemplo de servicio que usualmente se conectaria con alguna api
        System.out.println("es " + numero);
    }
}

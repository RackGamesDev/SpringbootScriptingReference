package com.ejemplo.SpringbootScriptingReference.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ejemplo.SpringbootScriptingReference.Interfaces.GenericoService;

//@Component
@Service //Usado como Bean (lo mismo que Component)
public class EjemploService {

    final private GenericoService servicio;

    @Autowired //Esto es opcional si la clase/servicio tiene solo un constructor, indica que constructor usara al hacer el bean
    public EjemploService (GenericoService genericoService) { //Esta seria la forma correcta de hacer dependencias, mediante interfaces genericas
        this.servicio = genericoService;
    }

    public void ejemplo() { //Servicio interno de la aplicacion
        //var externoService = new ExternoService(); //Es dependiente de otro servicio, esto no es bueno porque no se puede testear aisladamente y no es escalable
        //externoService.procesar(3);
        servicio.procesar(4); //Asi si, su uso seria var servicio = new EjemploService(new ExternoService()); servicio.ejemplo();
        
    }
    
    /*public void setServicio(GenericoService servicio) { //Si se hace con un setter es que la dependencia es opcional
        this.servicio = servicio;
    }*/
}

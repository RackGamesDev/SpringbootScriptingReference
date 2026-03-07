package com.ejemplo.SpringbootScriptingReference.Interfaces;

public interface GenericoService { //Intermediario entre un servicio y otro para mejorar escalabilidad y inyecciones de dependencias
    void procesar(int numero);
}

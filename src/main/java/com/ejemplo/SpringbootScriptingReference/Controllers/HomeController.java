package com.ejemplo.SpringbootScriptingReference.Controllers;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;



@Controller //Reconocido por Springboot como controller
public class HomeController {

    @Value("${app.propiedad-personalizada}") //Uso de propiedades de application.properties
    private String appPropiedad;

    @RequestMapping("/") //Registrar una ruta
    public String index() {
        System.out.println("hola" + appPropiedad);
        return "index.html"; //Lo que devuelve es el archivo index.html en resources/static
    }

    @RequestMapping("dinamico")
    public String htmlDinamico(Model model) {
        model.addAttribute("name", "asdfhlasdfh");
        return "index";
    }
    

}

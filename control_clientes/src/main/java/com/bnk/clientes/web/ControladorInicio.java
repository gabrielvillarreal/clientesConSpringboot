package com.bnk.clientes.web;

import com.bnk.clientes.domain.Persona;
import com.bnk.clientes.servicio.IPersonaService;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

//@RestController 
@Controller
@Slf4j //acceso a la variable log
public class ControladorInicio {
//  COMENTO ESTO PORQUE AHORA VAMOS A USAR UNA DB SQL PARA TRAER DATOS Y NO ALGO ESTATICO    
//    @Value("${index.saludo}")
//    private String saludo;
    @Autowired
    //private IPersonaDao ipersonaDao; es la capa de datos lo saco
    private IPersonaService ipersonaService; //traigo la capa de servicio
      
    @GetMapping("/")
    public String inicio(Model model, @AuthenticationPrincipal User user){
        var personas = ipersonaService.listarPersonas();
        model.addAttribute("personas", personas);
        log.info("usario que hizo el login: " + user );
        
        var saldoTotal=0D; //inicializo en 0 decimal el double
        for(var p: personas){
            saldoTotal=saldoTotal + p.getSaldo();
        }
        model.addAttribute("saldoTotal", saldoTotal); //le paso al modelo la suma total de saldo
        model.addAttribute("totalClientes", personas.size()); //paso a modelo la cantidad de clientes
       
        
//        var mensaje="hola mundo con thymeleaf";
//        var persona = new Persona();
//        persona.setNombre("juan");
//        persona.setApellido("jj");
//        persona.setEmail("juanjj@gmail.com");
//        persona.setTelefono("1234412223");
//        
//        var persona2 = new Persona();
//        persona2.setNombre("juana");
//        persona2.setApellido("viale");
//        persona2.setEmail("juanitaV@gmail.com");
//        persona2.setTelefono("15284557789");
        
//        List<Persona> personas = new ArrayList();
//        personas.add(persona);
//        personas.add(persona2);
        
        
        log.info("ejecutando el controlador spring MVC");
        //log.debug("mas detalle del controlador");
//        model.addAttribute("mensaje", mensaje);
//        model.addAttribute("saludo", saludo);
//        model.addAttribute("persona", persona);
        //model.addAttribute("personas", personas);
        return "index";
    }
    
    @GetMapping("/agregar")
    public String agregar(Persona persona){
        return "modificar";
    }
    
    @PostMapping("guardar")
    public String guardar(@Valid Persona persona, Errors errores){
        if(errores.hasErrors()){
            return "modificar";
        }
        ipersonaService.guardar(persona);
        return "redirect:/";
    }
    
    @GetMapping("/editar/{idPersona}")//con esto no hace falta inicializar el objeto, lo hace spring al llamar a persona
    public String editar(Persona persona, Model model){
        persona = ipersonaService.encontrarPersona(persona);
        model.addAttribute("persona", persona);
        return "modificar"; //retorno a la vista modificar
    }
    
    @GetMapping("/eliminar")
    public String eliminar(Persona persona){
        ipersonaService.eliminar(persona);
        return "redirect:/";
    }
    
}

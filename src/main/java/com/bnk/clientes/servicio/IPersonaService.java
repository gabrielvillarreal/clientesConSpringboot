/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bnk.clientes.servicio;

import com.bnk.clientes.domain.Persona;
import java.util.List;

/**
 *
 * @author villa
 */
public interface IPersonaService {
    
    public List<Persona> listarPersonas();
    
    public void guardar(Persona persona);
    
    public void eliminar(Persona persona);
    
    public Persona encontrarPersona(Persona persona);
}

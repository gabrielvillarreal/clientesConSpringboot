/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bnk.clientes.servicio;

import com.bnk.clientes.domain.Persona;
import com.bnk.clientes.dao.IPersonaDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author villa
 */
@Service
public class PersonaServiceImpl implements IPersonaService{

    @Autowired
    private IPersonaDao ipersonaDao;
    
    @Override
    @Transactional(readOnly = true)
    public List<Persona> listarPersonas() {
        return (List<Persona>) ipersonaDao.findAll();
    }

    @Override
    @Transactional
    public void guardar(Persona persona) {
        ipersonaDao.save(persona);
    }

    @Override
    @Transactional
    public void eliminar(Persona persona) {
        ipersonaDao.delete(persona);
    }

    @Override
    @Transactional(readOnly = true)
    public Persona encontrarPersona(Persona persona) {
        return ipersonaDao.findById(persona.getIdPersona()).orElse(null);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bnk.clientes.dao;

import com.bnk.clientes.domain.Persona;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author villa
 */
public interface IPersonaDao extends CrudRepository<Persona, Long>{
    
}

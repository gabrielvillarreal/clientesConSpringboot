/*
 * podemos usar CrudRepository, pero JpaRepository tiene algunas mejoras
* JpaRepository<nombre de la clase, tipo de variable del id>
 */
package com.bnk.clientes.dao;

import com.bnk.clientes.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author villa
 */
public interface IUsuarioDao extends JpaRepository<Usuario, Long>{
    Usuario findByUsername(String username); //este metodo regresa un usuario, pasado por username
}



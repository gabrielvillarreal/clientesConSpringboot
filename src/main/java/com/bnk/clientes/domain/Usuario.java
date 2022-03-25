/*
 * Mapeo la clase Usuario
 */
package com.bnk.clientes.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

/**
 *
 * @author villa
 */
@Entity //defino que esta clase va a ser una entidad
@Data//uso lombok para que cree todos los getters y setters y constructor de la clase
@Table(name="usuario") //le ponemos el nombre de como figura en la db para que no tenga problema
public class Usuario implements Serializable{
    private static final long serialVersionUID=1L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long idUsuario;
    
    @NotEmpty
    private String username;
    
    @NotEmpty
    private String password;
    
    //mapeo entre la clase de usuario y la de rol oneToMany
    @OneToMany
    @JoinColumn(name="id_usuario")
    private List<Rol> roles;
    
    
}

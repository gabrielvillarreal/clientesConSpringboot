/*
 * Mapeo de la clase de Rol
 */
package com.bnk.clientes.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

/**
 *
 * @author villa
 */
@Entity //defino que esta clase va a ser una entidad
@Data//uso lombok para que cree todos los getters y setters y constructor de la clase
@Table(name="rol") //le ponemos el nombre de como figura en la db para que no tenga problema
public class Rol implements Serializable{
    private static final long serialVersionUID=1L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long idRol;
    
    @NotEmpty
    private String nombre;
}

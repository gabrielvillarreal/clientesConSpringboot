/*
 * Clase de utileria para poder generar los password que usamos en la db. 
 */
package com.bnk.clientes.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author villa
 */
public class EncriptarPassword {
    public static void main(String[] args) {
        
        var password = "123"; //mi password
        System.out.println("password:" + password);
        System.out.println("password encriptado: " + encriptarPassword(password));
        
    }
    
    public static String encriptarPassword(String password){
        //para encriptar con spring
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password); //regreso el password encriptado
    }
}

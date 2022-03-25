/*
 * clase para agregarle seguridad al sistema
 */
package com.bnk.clientes.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author villa
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
   
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
//       //se desactiva el usuario por default y creamos en memoria
//       //CONFIGURO LA AUTENTICACION POR MEMORIA
//       auth.inMemoryAuthentication()
//               .withUser("admin")
//               .password("{noop}123")
//               .roles("ADMIN", "USER")
//               .and()
//               .withUser("user")
//               .password("{noop}123") //para que no codifique el password con {noop}
//               .roles("USER")
//               ;
//    }
    
    @Autowired
    private UserDetailsService userDetailsService;
    
    
    
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    
    @Autowired //lo inyecta spring de manera automatica con esta anotacion
    public void configurerGlobal(AuthenticationManagerBuilder build) throws Exception{
        build.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        //CONFIGURO LA AUTORIZACION
        http.authorizeRequests()
                .antMatchers("/editar/**", "/agregar/**", "/eliminar")
                    .hasRole("ADMIN")
                .antMatchers("/")
                    .hasAnyRole("USER", "ADMIN")
                .and()
                    .formLogin()
                    .loginPage("/login")
                .and()
                    .exceptionHandling().accessDeniedPage("/errores/403")
                ;
    }
}

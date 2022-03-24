/*
 * Convertimos esta clase en una clase de Servicio y la usa con SpringSecurity
 */
package com.bnk.clientes.servicio;

import com.bnk.clientes.dao.IUsuarioDao;
import com.bnk.clientes.domain.Rol;
import com.bnk.clientes.domain.Usuario;
import java.util.ArrayList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author villa
 */
@Service("userDetailsService") //tiene que ser tal cual ese nombre
@Slf4j //para poder escribir en consola
public class UsuarioService implements UserDetailsService {

    @Autowired
    private IUsuarioDao iUsuarioDao;
    
    @Override //obtengo el objeto de usuario filtrado por un username
    @Transactional(readOnly=true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = iUsuarioDao.findByUsername(username);
        if(usuario == null){
            throw new UsernameNotFoundException(username);
        }
        
        var roles = new ArrayList<GrantedAuthority>(); //envolvemos los roles en grantedauthority
                
            for(Rol rol: usuario.getRoles()){
                roles.add(new SimpleGrantedAuthority(rol.getNombre())); //con simplegrantedAuthority lo envuelvo a los roles, creando un nuevo objeto de esa clase y le pasamos el nombre del rol que estamos recuperando
                
            }    
            return new User(usuario.getUsername(), usuario.getPassword(), roles);
    }
    
}

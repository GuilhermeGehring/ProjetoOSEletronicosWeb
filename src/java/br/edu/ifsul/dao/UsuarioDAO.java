/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Usuario;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author guilherme
 */
public class UsuarioDAO extends DAOGenerico<Usuario> implements Serializable {
    
    public UsuarioDAO() {
        super(Usuario.class);
    }
        
    @Override
    public Usuario getObjectById(Object id) throws Exception {
        List<Usuario> usuarios = getListaObjetos();
        System.out.println("ID: " + id);
        for(Usuario usuario : usuarios)
            if(Objects.equals(usuario.getNomeUsuario(), id))
                return usuario;
        return usuarios.get(0);
        
    }   
    
}

package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Usuario;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.ejb.Stateful;

@Stateful
public class UsuarioDAO extends DAOGenerico<Usuario> implements Serializable {
    
    public UsuarioDAO() {
        super(Usuario.class);
    }

    @Override
    public List<Usuario> getListaObjetos() {              
        String jpql = "from Usuarios";
        return em.createQuery(jpql).getResultList();    
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

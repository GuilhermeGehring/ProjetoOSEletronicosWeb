package br.edu.ifsul.controle;

import br.edu.ifsul.dao.UsuarioDAO;
import br.edu.ifsul.modelo.Usuario;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named(value = "controleUsuario")
@ViewScoped
public class ControleUsuario implements Serializable {
    
    @EJB
    private UsuarioDAO dao;

    private Usuario objeto;

    public ControleUsuario() {
    }

    public String listar(){
        return "/privado/usuario/crud?faces-redirect=true";
    }

    public void novo(){
        objeto = new Usuario();
    }

    public void alterar(Object id){
        try {
            objeto = dao.getObjectById(id);
        } catch (Exception e){
            Util.mensagemErro("Erro ao recuperar objeto: " +                     
                Util.getMensagemErro(e));
        } 
    }

    public void excluir(Object id){
        try {
            objeto = dao.getObjectById(id);
            dao.remover(objeto);
            Util.mensagemInformacao("Objeto removido com sucesso!");
        } catch (Exception e){
            Util.mensagemErro("Erro ao remover objeto: " + 
                Util.getMensagemErro(e));
        }
    }

    public void salvar(){
        try {
            if (objeto.getNomeUsuario() == null){
                dao.persist(objeto);
            } else {
                dao.merge(objeto);
            }
            Util.mensagemInformacao("Objeto persistido com sucesso!");            
        } catch(Exception e){
            Util.mensagemErro("Erro ao persistir objeto: " + 
                Util.getMensagemErro(e));
        }
    }

    public UsuarioDAO getDao() {
        return dao;
    }

    public Usuario getObjeto() {
        return objeto;
    }
}

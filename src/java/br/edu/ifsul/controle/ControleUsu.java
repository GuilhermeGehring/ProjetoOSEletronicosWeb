/*
 * 
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.UsuarioDAO;
import br.edu.ifsul.modelo.PessoaFisica;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import br.edu.ifsul.modelo.Usuario;
import br.edu.ifsul.util.Util;
import java.io.Serializable;

/**
 *
 * @author Telmo
 */
@Named(value = "controleUsu")
@ViewScoped
public class ControleUsu implements Serializable {

    @EJB
    private UsuarioDAO dao;

    private Usuario objeto;

    public ControleUsu() {
    }

    public String listar() {
        return "/privado/usuario/crudusuario?faces-redirect=true";
    }

    public void novo() {
        setObjeto(new Usuario());
    }

    public Usuario getObjeto() {
        return objeto;
    }

    public void setObjeto(Usuario objeto) {
        this.objeto = objeto;
    }

    public UsuarioDAO getDao() {
        return dao;
    }

    public void alterar(Object id) {
        try {
            System.out.println("ControleUsu - altera : " + id);
            //System.out.println(dao.getObjectById(id));
            setObjeto(dao.getObjectById(id));
            System.out.println("ControleUsu - encontrou : " + getObjeto());
        } catch (Exception e) {
            Util.mensagemErro("Erro ao recuperar objeto: "
                    + Util.getMensagemErro(e));
        }
    }

    public void excluir(Object id) {
        try {
            setObjeto(dao.getObjectById(id));
            dao.remover(getObjeto());
            Util.mensagemInformacao("Objeto removido com sucesso!");
        } catch (Exception e) {
            Util.mensagemErro("Erro ao remover objeto: "
                    + Util.getMensagemErro(e));
        }
    }

    public void salvar() {
        try {
            //id nesse caso não é gerado pelo BD
            dao.persist(getObjeto());
            Util.mensagemInformacao("Objeto persistido com sucesso!");
        } catch (Exception e) {
            Util.mensagemErro("Erro ao persistir objeto: "
                    + Util.getMensagemErro(e));
        }
    }

    public String verificaTipo(Usuario u) {

        if ((u instanceof Usuario) && (u instanceof PessoaFisica)) {
            return PessoaFisica.class.getSimpleName();
        } else {
            return Usuario.class.getSimpleName();
        }

    }
}
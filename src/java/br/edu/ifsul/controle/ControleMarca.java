/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.MarcaDAO;
import br.edu.ifsul.modelo.Marca;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author 20171pf.cc0178
 */
@Named(value = "controleMarca")
@ViewScoped
public class ControleMarca implements Serializable {
    
    @EJB
    private MarcaDAO dao;

    private Marca objeto;

    public ControleMarca(){

    }
    
    public String listar(){
        return "/privado/marca/crudmarca?faces-redirect=true";
    }

    public void novo(){
        objeto = new Marca();
    }

    public MarcaDAO getDao() {
        return dao;
    }
    
    
    
    public void alterar(Object id){
                try {
                        setObjeto(dao.getObjectById(id));            
                } catch (Exception e){
                        Util.mensagemErro("Erro ao recuperar objeto: " + 
                                        Util.getMensagemErro(e));
                } 
        }

        public void excluir(Object id){
                try {
                        setObjeto(dao.getObjectById(id));
                        dao.remover(getObjeto());
                        Util.mensagemInformacao("Objeto removido com sucesso!");
                } catch (Exception e){
                        Util.mensagemErro("Erro ao remover objeto: " + 
                                        Util.getMensagemErro(e));
                }
        }

        public void salvar(){
                try {
                        if (getObjeto().getId() == null){
                                dao.persist(getObjeto());
                        } else {
                                dao.merge(getObjeto());
                        }
                        Util.mensagemInformacao("Objeto persistido com sucesso!");            
                } catch(Exception e){
                        Util.mensagemErro("Erro ao persistir objeto: " + 
                                        Util.getMensagemErro(e));
                }
        }

    public Marca getObjeto() {
        return objeto;
    }

    public void setObjeto(Marca objeto) {
        this.objeto = objeto;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.ProdutoDAO;
import br.edu.ifsul.dao.ServicoDAO;
import br.edu.ifsul.dao.EquipamentoDAO;
import br.edu.ifsul.dao.ItemServicoDAO;
import br.edu.ifsul.dao.OrdemServicoDAO;
import br.edu.ifsul.dao.PessoaFisicaDAO;
import br.edu.ifsul.dao.UsuarioDAO;
import br.edu.ifsul.modelo.ItemServico;
import br.edu.ifsul.modelo.OrdemServico;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author 20171pf.cc0178
 */
@Named(value = "controleOrdemServico")
@ViewScoped
public class ControleOrdemServico implements Serializable {

    private OrdemServico objeto;

    @EJB
    private OrdemServicoDAO dao;

    @EJB
    private UsuarioDAO daoUsuario;// o .xhtml poderia referenciar diretamente o ControleUsuario, caso o mesmo já exista

    @EJB
    private PessoaFisicaDAO daoPessoaFisica;// o .xhtml poderia referenciar diretamente o ControlePessoaFisica, caso o mesmo já exista

    @EJB
    private ServicoDAO daoServico;// o .xhtml poderia referenciar diretamente o ControleServico, caso o mesmo já exista

    @EJB
    private EquipamentoDAO daoEquipamento;// o .xhtml poderia referenciar diretamente o ControleEquipamento, caso o mesmo já exista

    @EJB
    private ProdutoDAO daoProduto;// o .xhtml poderia referenciar diretamente o ControleProduto, caso o mesmo já exista

    @EJB
    private ItemServicoDAO daoItemServico;

    public ControleOrdemServico() {

    }

    public String listar() {
        return "/privado/ordemservico/listar?faces-redirect=true";
    }

    public void novo() {
        objeto = new OrdemServico();
    }

    public void alterar(Object id) {
        try {
            objeto = dao.getObjectById(id);
        } catch (Exception e) {
            Util.mensagemErro("Erro ao recuperar objeto: "
                    + Util.getMensagemErro(e));
        }
    }

    public void excluir(Object id) {
        try {
            objeto = dao.getObjectById(id);
            dao.remover(objeto);
            Util.mensagemInformacao("Objeto removido com sucesso!");
        } catch (Exception e) {
            Util.mensagemErro("Erro ao remover objeto: "
                    + Util.getMensagemErro(e));
        }
    }

    public void salvar() {
        try {
            if (objeto.getId() == null) {
                dao.persist(objeto);
            } else {
                dao.merge(objeto);
            }
            Util.mensagemInformacao("Objeto persistido com sucesso!");
        } catch (Exception e) {
            Util.mensagemErro("Erro ao persistir objeto: "
                    + Util.getMensagemErro(e));
        }
    }

    public void atualizaValorUnitario() {
        if (itemServico.getValorUnitario() == null) {

        }
    }

    public OrdemServico getObjeto() {
        return objeto;
    }

    public void setObjeto(OrdemServico objeto) {
        this.objeto = objeto;
    }

    public OrdemServicoDAO getDao() {
        return dao;
    }

    public void setDao(OrdemServicoDAO dao) {
        this.dao = dao;
    }

    public UsuarioDAO getDaoUsuario() {
        return daoUsuario;
    }

    public void setDaoUsuario(UsuarioDAO daoUsuario) {
        this.daoUsuario = daoUsuario;
    }

    public PessoaFisicaDAO getDaoPessoaFisica() {
        return daoPessoaFisica;
    }

    public void setDaoPessoaFisica(PessoaFisicaDAO daoPessoaFisica) {
        this.daoPessoaFisica = daoPessoaFisica;
    }

    public ServicoDAO getDaoServico() {
        return daoServico;
    }

    public void setDaoServico(ServicoDAO daoServico) {
        this.daoServico = daoServico;
    }

    public EquipamentoDAO getDaoEquipamento() {
        return daoEquipamento;
    }

    public void setDaoEquipamento(EquipamentoDAO daoEquipamento) {
        this.daoEquipamento = daoEquipamento;
    }

    public ProdutoDAO getDaoProduto() {
        return daoProduto;
    }

    public void setDaoProduto(ProdutoDAO daoProduto) {
        this.daoProduto = daoProduto;
    }

    private ItemServico itemServico;
    private Boolean novoItemServico;

    public void novoItemServico() {
        itemServico = new ItemServico();
        novoItemServico = true;
    }

    public void alterarItemServico(int index) {
        itemServico = objeto.getItemServicos().get(index);
        novoItemServico = false;
    }

    public void salvarItemServico() {
        if (novoItemServico) {
            objeto.adicionarServico(itemServico);
        }
        Util.mensagemInformacao("Serviço adicionado com sucesso");
    }

    public void removerItemServico(int index) {
        objeto.removerServico(index);
        Util.mensagemInformacao("Serviço removido com sucesso");
    }

    public ItemServicoDAO getDaoItemServico() {
        return daoItemServico;
    }

    public void setDaoItemServico(ItemServicoDAO daoItemServico) {
        this.daoItemServico = daoItemServico;
    }

    public ItemServico getItemServico() {
        return itemServico;
    }

    public void setItemServico(ItemServico itemServico) {
        this.itemServico = itemServico;
    }

    public Boolean getNovoItemServico() {
        return novoItemServico;
    }

    public void setNovoItemServico(Boolean novoItemServico) {
        this.novoItemServico = novoItemServico;
    }

}

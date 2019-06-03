/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.ItemServico;
import java.io.Serializable;
import javax.ejb.Stateful;

@Stateful
public class ItemServicoDAO extends DAOGenerico<ItemServico> implements Serializable {

    public ItemServicoDAO() {
        super(ItemServico.class);
    }

}

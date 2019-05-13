/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.converters.ConverterOrdem;
import br.edu.ifsul.modelo.Cidade;
import br.edu.ifsul.modelo.Estado;
import java.io.Serializable;
import javax.ejb.Stateful;

@Stateful
public class CidadeDAO<TipoGenerics> extends DAOGenerico<Cidade> implements Serializable {
    
    public CidadeDAO(){
        super(Cidade.class);
        // classePersistente = Estado.class;
        // inicializar as ordenações possiveis        
        listaOrdem.add(new Ordem("id", "ID", "="));
        listaOrdem.add(new Ordem("nome", "Nome", "like"));
        // definir qual a ordenação padrão no caso o segundo elemento da lista (indice 1)
        ordemAtual = listaOrdem.get(1);
        // inicializar o conversor com a lista de ordens
        converterOrdem = new ConverterOrdem(listaOrdem);
    }
}	

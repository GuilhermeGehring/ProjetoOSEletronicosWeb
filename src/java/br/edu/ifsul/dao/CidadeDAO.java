  /*
 * 
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.converter.ConverterOrdem;
import br.edu.ifsul.modelo.Cidade;
import br.edu.ifsul.modelo.Estado;
import java.io.Serializable;
import javax.ejb.Stateful;
/**
 *
 * @author Telmo
 */
@Stateful
public class CidadeDAO extends DAOGenerico<Cidade> implements Serializable {
    
    public CidadeDAO(){
        
        super(Cidade.class);   
        
        // inicializar as ordenações possiveis        
        listaOrdem.add(new Ordem("id", "ID", "="));
        listaOrdem.add(new Ordem("nome", "Nome", "like"));
        // definir qual a ordenação padrão no caso o segundo elemento da lista (indice 1)
        ordemAtual = listaOrdem.get(1);
        // inicializar o conversor com a lista de ordens
        converterOrdem = new ConverterOrdem(listaOrdem);
    }  
}

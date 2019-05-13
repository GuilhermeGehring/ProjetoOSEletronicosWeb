package br.edu.ifsul.dao; 

import br.edu.ifsul.converters.ConverterOrdem;
import javax.ejb.Stateful;
import br.edu.ifsul.modelo.Estado;
import java.io.Serializable;

@Stateful
public class EstadoDAO<TipoGenerics> extends DAOGenerico<Estado> implements Serializable {
    
    public EstadoDAO(){
        super(Estado.class);
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

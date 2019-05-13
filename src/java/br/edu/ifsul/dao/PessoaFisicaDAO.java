package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.PessoaFisica;
import java.io.Serializable;
import javax.ejb.Stateful;

@Stateful
public class PessoaFisicaDAO extends DAOGenerico<PessoaFisica> implements Serializable {
    
    public PessoaFisicaDAO() {
        super(PessoaFisica.class);
    }            
}

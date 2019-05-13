/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.PessoaFisica;
import java.io.Serializable;

/**
 *
 * @author guilherme
 */
public class PessoaFisicaDAO extends DAOGenerico<PessoaFisica> implements Serializable {
    
    public PessoaFisicaDAO() {
        super(PessoaFisica.class);
    }            
}

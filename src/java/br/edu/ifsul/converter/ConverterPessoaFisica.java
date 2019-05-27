package br.edu.ifsul.converter;

import br.edu.ifsul.dao.PessoaFisicaDAO;
import br.edu.ifsul.modelo.PessoaFisica;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;

@Named(value = "converterPessoaFisica") 
@RequestScoped
public class ConverterPessoaFisica implements Serializable, Converter {
    
    @EJB
    private PessoaFisicaDAO dao;
    
    public ConverterPessoaFisica(){
        
    }
    
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if (string == null || string.equals("Selecione")){
            return null;
        }
       
        return  dao.find(Integer.parseInt(string));
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o == null){
            return null;
        }
        PessoaFisica obj = (PessoaFisica) o;
        return obj.getNomeUsuario();
    }
}

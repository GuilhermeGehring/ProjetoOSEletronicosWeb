package br.edu.ifsul.converter;

import br.edu.ifsul.dao.CidadeDAO;
import br.edu.ifsul.modelo.Cidade;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;

@Named(value = "converterCidade") 
@RequestScoped
public class ConverterCidade implements Serializable, Converter {
    
    @EJB
    private CidadeDAO dao;
    
    public ConverterCidade(){
        
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
        Cidade obj = (Cidade) o;
        return obj.getId().toString();
    }
}

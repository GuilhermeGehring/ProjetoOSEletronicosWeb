package br.edu.ifsul.converter;

import br.edu.ifsul.dao.UsuarioDAO;
import br.edu.ifsul.modelo.Usuario;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;

@Named(value = "converterUsuario") 
@RequestScoped
public class ConverterUsuario implements Serializable, Converter {
    
    @EJB
    private UsuarioDAO dao;
    
    public ConverterUsuario(){
        
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
        Usuario obj = (Usuario) o;
        return obj.getNomeUsuario();
    }
}

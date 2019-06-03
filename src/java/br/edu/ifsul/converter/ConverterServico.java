package br.edu.ifsul.converter;

import br.edu.ifsul.dao.ServicoDAO;
import br.edu.ifsul.modelo.Servico;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;

@Named(value = "converterServico")
@RequestScoped
public class ConverterServico implements Serializable, Converter {

    @EJB
    private ServicoDAO dao;

    public ConverterServico() {

    }

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if (string == null || string.equals("Selecione")) {
            return null;
        }

        return dao.find(Integer.parseInt(string));
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o == null) {
            return null;
        }
        Servico obj = (Servico) o;
        return obj.getId().toString();
    }
}

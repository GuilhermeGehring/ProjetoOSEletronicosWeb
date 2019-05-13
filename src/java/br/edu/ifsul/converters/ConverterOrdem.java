package br.edu.ifsul.converters;

import br.edu.ifsul.dao.Ordem;
import java.io.Serializable;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * @author Prof. Me. Jorge Luis Boeira Bavaresco
 * @email jorge.bavaresco@passofundo.ifsul.edu.br
 * @organization IFSUL - Campus Passo Fundo
 */
@FacesConverter(value = "converterOrdem")
public class ConverterOrdem implements Serializable, Converter {

    private List<Ordem> listaOrdem;

    public ConverterOrdem(List<Ordem> listaOrdem) {
        this.listaOrdem = listaOrdem;
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String string) {        
        Ordem retorno = null;
        for (Ordem o : listaOrdem) {
            if (o.getAtributo().equals(string)) {
                retorno = o;
            }
        }
        return retorno;
    }

    @Override
    public String getAsString(FacesContext arg0, UIComponent arg1, Object o) {
        if (o == null) {
            return null;
        }
        Ordem ordem = (Ordem) o;
        return ordem.getAtributo();
    }

    public List<Ordem> getListaOrdem() {
        return listaOrdem;
    }

    public void setListaOrdem(List<Ordem> listaOrdem) {
        this.listaOrdem = listaOrdem;
    }

}

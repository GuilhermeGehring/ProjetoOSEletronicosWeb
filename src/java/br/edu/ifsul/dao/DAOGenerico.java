
package br.edu.ifsul.dao;

import br.edu.ifsul.converters.ConverterOrdem;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Telmo
 */
public class DAOGenerico<TipoGenerics> implements Serializable {

    private List<TipoGenerics> listaObjetos;
    private List<TipoGenerics> listaTodos;
    @PersistenceContext(unitName = "ProjetoOSEletronicosWebPU")
    protected EntityManager em;
   
    final Class<TipoGenerics> classePersistente;
            
    public DAOGenerico(Class<TipoGenerics> clazz) {
       this.classePersistente = clazz;
    }

    public List<TipoGenerics> getListaObjetos() {
        String jpql = "from " + classePersistente.getSimpleName();
        String where = "";
        // removendo caracteres para proteger de sql injection
        filtro = filtro.replaceAll("[';-]", "");
        if (filtro.length() > 0) {
           switch (ordemAtual.getOperador()) {
                case "=":
                    // tratamento para caso digitem com id selecionado algo que não é numero não gerar exceção
                    if (ordemAtual.getAtributo().equals("id")) {
                        try {
                            Integer i = Integer.parseInt(filtro);
                        } catch (Exception e) {
                            filtro = "0";
                        }
                    }
                    where += " where " + ordemAtual.getAtributo() + " = '" + filtro + "' ";
                    break;
                case "like":
                     where += " where upper(" + ordemAtual.getAtributo() + ") like '" + filtro.toUpperCase() + "%' ";
                    break;
            }                        
        }
        jpql += where;
        jpql += " order by " + ordemAtual.getAtributo();
        System.out.println("JPQL: " + jpql);
        totalObjetos = em.createQuery(jpql).getResultList().size();
        return em.createQuery(jpql).
                setFirstResult(posicaoAtual).
                setMaxResults(maximoObjetos).getResultList();
    }
    
    public List<TipoGenerics> getListaTodos() {
        String jpql = "from " + classePersistente.getSimpleName() + " order by " + ordemAtual.getAtributo();
        return em.createQuery(jpql).getResultList();
    }
    /*public List<TipoGenerics> getListaObjetos() {
       
        String jpql = "from " + classePersistente.getSimpleName();
        return em.createQuery(jpql).getResultList();
    }


    public List<TipoGenerics> getListaTodos() {
            String jpql = "from " + classePersistente.getSimpleName();
            
            return em.createQuery(jpql).getResultList();
    }*/

    public void persist(TipoGenerics obj) throws Exception {
            em.persist(obj);
    }

    public void merge(TipoGenerics obj) throws Exception {
            em.merge(obj);
    }

    public TipoGenerics getObjectById(Object id) throws Exception {
            return (TipoGenerics) em.find(classePersistente, id);
    }

    public void remover(TipoGenerics obj) throws Exception {
            obj = em.merge(obj);
            em.remove(obj);
    }
    
    protected String filtro = "";
    protected List<Ordem> listaOrdem = new ArrayList<>();
    protected Ordem ordemAtual;
    protected ConverterOrdem converterOrdem;
    protected Integer maximoObjetos = 8;
    protected Integer posicaoAtual = 0;
    protected Integer totalObjetos = 0;	    

    public void primeiro() {
        posicaoAtual = 0;
    }

    public void anterior() {
        posicaoAtual -= maximoObjetos;
        if (posicaoAtual < 0) {
            posicaoAtual = 0;
        }
    }

    public void proximo() {
        if (posicaoAtual + maximoObjetos < totalObjetos) {
            posicaoAtual += maximoObjetos;
        }
    }

    public void ultimo() {
        int resto = totalObjetos % maximoObjetos;
        if (resto > 0) {
            posicaoAtual = totalObjetos - resto;
        } else {
            posicaoAtual = totalObjetos - maximoObjetos;
        }
    }

    public String getMensagemNavegacao() {
        int ate = posicaoAtual + maximoObjetos;
        if (ate > totalObjetos) {
            ate = totalObjetos;
        }
        if (totalObjetos > 0) {
            return "Listagem de " + (posicaoAtual + 1) + " até " + ate
                    + " de " + totalObjetos + " registros";
        } else {
            return "Nenhum registro encontrado!";
        }
    }   

    public ConverterOrdem getConverterOrdem() {
        return converterOrdem;
    }

    public void setConverterOrdem(ConverterOrdem converterOrdem) {
        this.converterOrdem = converterOrdem;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public String getFiltro() {
        return filtro;
    }

    public void setFiltro(String filtro) {
        this.filtro = filtro;
    }

    public List<Ordem> getListaOrdem() {
        return listaOrdem;
    }

    public void setListaOrdem(List<Ordem> listaOrdem) {
        this.listaOrdem = listaOrdem;
    }

    public Ordem getOrdemAtual() {
        return ordemAtual;
    }

    public void setOrdemAtual(Ordem ordemAtual) {
        this.ordemAtual = ordemAtual;
    }

    public Integer getMaximoObjetos() {
        return maximoObjetos;
    }

    public void setMaximoObjetos(Integer maximoObjetos) {
        this.maximoObjetos = maximoObjetos;
    }

    public Integer getPosicaoAtual() {
        return posicaoAtual;
    }

    public void setPosicaoAtual(Integer posicaoAtual) {
        this.posicaoAtual = posicaoAtual;
    }

    public Integer getTotalObjetos() {
        return totalObjetos;
    }

    public void setTotalObjetos(Integer totalObjetos) {
        this.totalObjetos = totalObjetos;
    }        

}

package com.adisolucoes.adimanager.model;

import com.adisolucoes.adimanager.dao.LazyDAO;
import com.adisolucoes.adimanager.exceptions.ErroBancoDadosException;
import com.adisolucoes.adimanager.filtros.LazyFiltro;
import com.adisolucoes.adimanager.util.jsf.FacesUtils;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 *
 * @author ADI Soluções
 */
public class LazyBean<T> extends LazyDataModel<T> {

    private final LazyDAO<T> DAO;
    private final LazyFiltro filtro;

    public LazyBean(LazyDAO<T> DAO, LazyFiltro filtro) {
        this.DAO = DAO;
        this.filtro = filtro;
    }

    @Override
    public List<T> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map filters) {
        try {
            filtro.setCount(true);
            filtro.setPrimeiro(first);
            filtro.setQuantidade(pageSize);
            setRowCount(DAO.quantidadeLazy(filtro));
            filtro.setCount(false);
            filtro.setPrimeiro(first);
            filtro.setQuantidade(pageSize);
            List<T> lista = DAO.buscarLazy(filtro);
            if (sortField != null) {
                Collections.sort(lista, new BeanComparator(sortField, sortOrder));
            }
            return lista;
        } catch (ErroBancoDadosException ex) {
            Logger.getLogger(LazyBean.class.getName()).log(Level.SEVERE, null, ex);
            FacesUtils.showFacesMessage("Ocorreu um erro no Banco de Dados. Contacte o suporte", 1);
        }
        return null;
    }

    @Override
    public T getRowData(String rowKey) {
        T objeto = null;
        try {
            objeto = DAO.buscarPorId(Long.parseLong(rowKey));
        } catch (ErroBancoDadosException ex) {
            Logger.getLogger(LazyBean.class.getName()).log(Level.SEVERE, null, ex);
            FacesUtils.showFacesMessage("Ocorreu um erro no Banco de Dados. Contacte o suporte", 1);
        }
        return objeto;
    }

}

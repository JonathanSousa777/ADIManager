package com.adisolucoes.adimanager.dao;

import com.adisolucoes.adimanager.exceptions.ErroBancoDadosException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.criteria.CriteriaQuery;

/**
 *
 * @author ADI Soluções
 */
public abstract class DAO<T> implements Serializable {

    @Inject
    protected EntityManager manager;

    private final Class<T> classe;
    protected EntityTransaction trx;

    public DAO(Class<T> classe) {
        this.classe = classe;
    }

    public void salvar(T t) throws ErroBancoDadosException {
        try {
            trx = manager.getTransaction();
            trx.begin();
            manager.persist(t);
            trx.commit();
        } catch (Exception ex) {
            throw new ErroBancoDadosException(ex.getMessage());
        }
    }

    public void excluir(Object o) throws ErroBancoDadosException {
        try {
            trx = manager.getTransaction();
            trx.begin();
            T entityToBeRemoved = manager.getReference(classe, o);
            manager.remove(entityToBeRemoved);
            trx.commit();
        } catch (Exception ex) {
            throw new ErroBancoDadosException(ex.getMessage());
        }
    }

    public void atualizar(T t) throws ErroBancoDadosException {
        try {
            trx = manager.getTransaction();
            trx.begin();
            manager.merge(t);
            trx.commit();
        } catch (Exception ex) {
            throw new ErroBancoDadosException(ex.getMessage());
        }
    }

    @SuppressWarnings("UnusedAssignment")
    public List<T> listarTodos() throws ErroBancoDadosException {
        try {
            List<T> lista = new ArrayList();
            CriteriaQuery<T> query = manager.getCriteriaBuilder().createQuery(classe);
            query.select(query.from(classe));
            lista = manager.createQuery(query).getResultList();
            return lista;
        } catch (Exception ex) {
            throw new ErroBancoDadosException(ex.getMessage());
        }
    }

    public T buscarPorId(Long id) throws ErroBancoDadosException {
        try {
            T object = null;
            object = manager.find(classe, id);
            return object;
        } catch (Exception ex) {
            throw new ErroBancoDadosException(ex.getMessage());
        }
    }
}

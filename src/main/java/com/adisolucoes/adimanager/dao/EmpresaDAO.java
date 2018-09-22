package com.adisolucoes.adimanager.dao;

import com.adisolucoes.adimanager.exceptions.ErroBancoDadosException;
import com.adisolucoes.adimanager.filtros.EmpresaFiltro;
import com.adisolucoes.adimanager.filtros.LazyFiltro;
import com.adisolucoes.adimanager.model.Cliente;
import com.adisolucoes.adimanager.model.Empresa;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author ADI Manager
 */
public class EmpresaDAO extends DAO<Empresa> implements LazyDAO<Empresa>, Serializable {

    private static final Logger LOG = Logger.getLogger(EmpresaDAO.class.getName());

    public EmpresaDAO() {
        super(Empresa.class);
    }

    public Empresa buscarPorCnpj(String cnpj) throws ErroBancoDadosException {
        Empresa empresa = null;
        try {
            String sql = "SELECT e FROM Empresa e WHERE e.cnpj LIKE :cnpj";
            TypedQuery<Empresa> query = manager.createQuery(sql, Empresa.class);
            query.setParameter("cnpj", cnpj);
            List<Empresa> empresas = new ArrayList<Empresa>();
            empresas = query.getResultList();
            if (!empresas.isEmpty()) {
                empresa = query.getResultList().get(0);
            }
        } catch (NonUniqueResultException | IllegalArgumentException ex) {
            throw new ErroBancoDadosException(ex.getMessage());
        } catch (NoResultException ex) {
            empresa = null;
        }
        return empresa;
    }

    public List<Empresa> buscarPorCliente(Cliente cliente) throws ErroBancoDadosException {
        List<Empresa> empresas = new ArrayList<>();
        try {
            String sql = "SELECT e FROM Empresa e WHERE e.proprietario.id = :idCliente";
            TypedQuery<Empresa> query = manager.createQuery(sql, Empresa.class);
            query.setParameter("idCliente", cliente.getId());
            empresas = query.getResultList();
        } catch (NonUniqueResultException | IllegalArgumentException ex) {
            throw new ErroBancoDadosException(ex.getMessage());
        } catch (NoResultException ex) {
            empresas = new ArrayList<>();
        }
        return empresas;
    }

    @Override
    public int quantidadeLazy(LazyFiltro filtro) throws ErroBancoDadosException {
        EmpresaFiltro empresaFiltro = (EmpresaFiltro) filtro;
        String sql = gerarSQL(empresaFiltro);
        Query query = manager.createQuery(sql);
        query.setFirstResult(empresaFiltro.getPrimeiro());
        query.setMaxResults(empresaFiltro.getQuantidade());
        preencherParametros(empresaFiltro, query);
        Long count = (Long) query.getSingleResult();
        return count.intValue();
    }

    @Override
    public List<Empresa> buscarLazy(LazyFiltro filtro) throws ErroBancoDadosException {
        EmpresaFiltro empresaFiltro = (EmpresaFiltro) filtro;
        String sql = gerarSQL(empresaFiltro);
        Query query = manager.createQuery(sql);
        query.setFirstResult(empresaFiltro.getPrimeiro());
        query.setMaxResults(empresaFiltro.getQuantidade());
        preencherParametros(empresaFiltro, query);
        List<Empresa> empresas = query.getResultList();
        return empresas;
    }

    private String gerarSQL(EmpresaFiltro filtro) {
        String sql = "";
        sql += filtro.isCount() ? "SELECT COUNT(e) FROM Empresa e WHERE e.id IS NOT NULL " : "SELECT e FROM Empresa e WHERE e.id IS NOT NULL ";
        sql += (filtro.getNome() != null && !filtro.getNome().isEmpty()) ? "AND e.nome LIKE :nome " : "";
        sql += (filtro.getCnpj() != null && !filtro.getCnpj().isEmpty()) ? "AND e.cnpj LIKE :cnpj " : "";
        sql += (filtro.getProprietario() != null) ? "AND e.proprietario.id = :idProprietario" : "";
        sql += " ORDER BY e.nome ASC";
        return sql;
    }

    private void preencherParametros(EmpresaFiltro filtro, Query query) {
        if (filtro.getNome() != null && !filtro.getNome().isEmpty()) {
            query.setParameter("nome", "%" + filtro.getNome() + "%");
        }

        if (filtro.getCnpj() != null && !filtro.getCnpj().isEmpty()) {
            query.setParameter("cnpj", "%" + filtro.getCnpj() + "%");
        }

        if (filtro.getProprietario() != null) {
            query.setParameter("idProprietario", filtro.getProprietario().getId());
        }
    }
}

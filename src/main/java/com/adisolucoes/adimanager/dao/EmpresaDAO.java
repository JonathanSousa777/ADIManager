package com.adisolucoes.adimanager.dao;

import com.adisolucoes.adimanager.exceptions.ErroBancoDadosException;
import com.adisolucoes.adimanager.filtros.EmpresaFiltro;
import com.adisolucoes.adimanager.filtros.LazyFiltro;
import com.adisolucoes.adimanager.model.Empresa;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;
import javax.persistence.Query;

/**
 *
 * @author ADI Manager
 */
public class EmpresaDAO extends DAO<Empresa> implements LazyDAO<Empresa>, Serializable {

    private static final Logger LOG = Logger.getLogger(EmpresaDAO.class.getName());

    public EmpresaDAO() {
        super(Empresa.class);
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

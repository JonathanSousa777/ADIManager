package com.adisolucoes.adimanager.dao;

import com.adisolucoes.adimanager.exceptions.ErroBancoDadosException;
import com.adisolucoes.adimanager.filtros.IndicacaoFiltro;
import com.adisolucoes.adimanager.filtros.LazyFiltro;
import com.adisolucoes.adimanager.model.Indicacao;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;
import javax.persistence.Query;

/**
 *
 * @author ADI Soluções
 */
public class IndicacaoDAO extends DAO<Indicacao> implements LazyDAO<Indicacao>, Serializable {

    private static final Logger LOG = Logger.getLogger(IndicacaoDAO.class.getName());

    public IndicacaoDAO() {
        super(Indicacao.class);
    }

    @Override
    public int quantidadeLazy(LazyFiltro filtro) throws ErroBancoDadosException {
        IndicacaoFiltro indicacaoFiltro = (IndicacaoFiltro) filtro;
        String sql = gerarSQL(indicacaoFiltro);
        Query query = manager.createQuery(sql);
        query.setFirstResult(indicacaoFiltro.getPrimeiro());
        query.setMaxResults(indicacaoFiltro.getQuantidade());
        preencherParametros(indicacaoFiltro, query);
        Long count = (Long) query.getSingleResult();
        return count.intValue();
    }

    @Override
    public List<Indicacao> buscarLazy(LazyFiltro filtro) throws ErroBancoDadosException {
        IndicacaoFiltro indicacaoFiltro = (IndicacaoFiltro) filtro;
        String sql = gerarSQL(indicacaoFiltro);
        Query query = manager.createQuery(sql);
        query.setFirstResult(indicacaoFiltro.getPrimeiro());
        query.setMaxResults(indicacaoFiltro.getQuantidade());
        preencherParametros(indicacaoFiltro, query);
        List<Indicacao> indicacoes = query.getResultList();
        return indicacoes;
    }

    private String gerarSQL(IndicacaoFiltro filtro) {
        String sql = "";
        sql += filtro.isCount() ? "SELECT COUNT(i) FROM Indicacao i WHERE i.id IS NOT NULL " : "SELECT i FROM Indicacao i WHERE i.id IS NOT NULL ";
        sql += (filtro.getDescricao() != null && !filtro.getDescricao().isEmpty()) ? "AND i.descricao LIKE :descricao " : "";
        sql += (filtro.getCodigo() != null && !filtro.getCodigo().isEmpty()) ? "AND i.codigoIdentificador LIKE :codigoIdentificador " : "";
        sql += (filtro.getClienteAtivo() != null && filtro.getClienteAtivo() != null) ? "AND i.clienteAtivo.id = :idClienteAtivo " : "";
        sql += (filtro.getClientePassivo() != null && filtro.getClientePassivo() != null) ? "AND i.clientePassivo.id = :idClientePassivo " : "";
        sql += (filtro.getDataCadastroDe() != null) ? "AND i.data >= :dataCadastroDe " : "";
        sql += (filtro.getDataCadastroAte() != null) ? "AND i.data <= :dataCadastroAte " : "";
        sql += " ORDER BY i.data DESC";
        return sql;
    }

    private void preencherParametros(IndicacaoFiltro filtro, Query query) {
        if (filtro.getDescricao() != null && !filtro.getDescricao().isEmpty()) {
            query.setParameter("descricao", "%" + filtro.getDescricao() + "%");
        }
        if (filtro.getCodigo() != null && !filtro.getCodigo().isEmpty()) {
            query.setParameter("codigoIdentificador", "%" + filtro.getCodigo() + "%");
        }
        if (filtro.getClienteAtivo() != null && filtro.getClienteAtivo() != null) {
            query.setParameter("idClienteAtivo", filtro.getClienteAtivo().getId());
        }
        if (filtro.getClientePassivo() != null && filtro.getClientePassivo() != null) {
            query.setParameter("idClientePassivo", filtro.getClientePassivo().getId());
        }
        if (filtro.getDataCadastroDe() != null) {
            query.setParameter("dataCadastroDe", filtro.getDataCadastroDe());
        }
        if (filtro.getDataCadastroAte() != null) {
            query.setParameter("dataCadastroAte", filtro.getDataCadastroAte());
        }
    }
}

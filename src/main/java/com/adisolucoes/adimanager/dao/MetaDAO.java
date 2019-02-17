package com.adisolucoes.adimanager.dao;

import com.adisolucoes.adimanager.exceptions.ErroBancoDadosException;
import com.adisolucoes.adimanager.filtros.LazyFiltro;
import com.adisolucoes.adimanager.filtros.MetaFiltro;
import com.adisolucoes.adimanager.model.Meta;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;
import javax.persistence.Query;

/**
 *
 * @author ADI Manager
 */
public class MetaDAO extends DAO<Meta> implements LazyDAO<Meta>, Serializable {

    private static final Logger LOG = Logger.getLogger(MetaDAO.class.getName());

    public MetaDAO() {
        super(Meta.class);
    }

    @Override
    public int quantidadeLazy(LazyFiltro filtro) throws ErroBancoDadosException {
        MetaFiltro metaFiltro = (MetaFiltro) filtro;
        String sql = gerarSQL(metaFiltro);
        Query query = manager.createQuery(sql);
        query.setFirstResult(metaFiltro.getPrimeiro());
        query.setMaxResults(metaFiltro.getQuantidade());
        preencherParametros(metaFiltro, query);
        Long count = (Long) query.getSingleResult();
        return count.intValue();
    }

    @Override
    public List<Meta> buscarLazy(LazyFiltro filtro) throws ErroBancoDadosException {
        MetaFiltro metaFiltro = (MetaFiltro) filtro;
        String sql = gerarSQL(metaFiltro);
        Query query = manager.createQuery(sql);
        query.setFirstResult(metaFiltro.getPrimeiro());
        query.setMaxResults(metaFiltro.getQuantidade());
        preencherParametros(metaFiltro, query);
        List<Meta> metas = query.getResultList();
        return metas;
    }

    private String gerarSQL(MetaFiltro filtro) {
        String sql = "";
        sql += filtro.isCount() ? "SELECT COUNT(m) FROM Meta m WHERE m.id IS NOT NULL " : "SELECT m FROM Meta m WHERE m.id IS NOT NULL ";
        sql += (filtro.getDescricao() != null && !filtro.getDescricao().isEmpty()) ? "AND m.descricao LIKE :descricao " : "";
        sql += (filtro.getDataInicioDe() != null) ? "AND m.dataInicio >= :dataInicioDe " : "";
        sql += (filtro.getDataInicioAte() != null) ? "AND m.dataInicio <= :dataInicioAte " : "";
        sql += (filtro.getDataFimDe() != null) ? "AND m.dataFim >= :dataFimDe " : "";
        sql += (filtro.getDataFimAte() != null) ? "AND m.dataFim <= :dataFimAte " : "";
        sql += (filtro.getConcluida() != null) ? "AND m.concluida = :concluida " : "";
        sql += (filtro.getTipoPrioridadeMeta() != null) ? "AND m.tipoPrioridadeMeta = :tipoMeta " : "";
        sql += " ORDER BY m.dataInicio DESC";
        return sql;
    }

    private void preencherParametros(MetaFiltro filtro, Query query) {
        if (filtro.getDescricao() != null && !filtro.getDescricao().isEmpty()) {
            query.setParameter("descricao", "%" + filtro.getDescricao() + "%");
        }
        if (filtro.getDataInicioDe() != null && !filtro.getDataInicioDe().equals("")) {
            query.setParameter("dataInicioDe", filtro.getDataInicioDe());
        }
        if (filtro.getDataInicioAte() != null && !filtro.getDataInicioAte().equals("")) {
            query.setParameter("dataInicioAte", filtro.getDataInicioAte());
        }
        if (filtro.getDataFimDe() != null && !filtro.getDataFimDe().equals("")) {
            query.setParameter("dataFimDe", filtro.getDataFimDe());
        }
        if (filtro.getDataFimAte() != null && !filtro.getDataFimAte().equals("")) {
            query.setParameter("dataFimAte", filtro.getDataFimAte());
        }
        if (filtro.getConcluida() != null) {
            query.setParameter("concluida", filtro.getConcluida().equalsIgnoreCase("concluida"));
        }
        if (filtro.getTipoPrioridadeMeta() != null) {
            query.setParameter("tipoMeta", filtro.getTipoPrioridadeMeta());
        }
    }
}

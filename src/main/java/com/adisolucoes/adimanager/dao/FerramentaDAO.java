package com.adisolucoes.adimanager.dao;

import com.adisolucoes.adimanager.exceptions.ErroBancoDadosException;
import com.adisolucoes.adimanager.filtros.FerramentaFiltro;
import com.adisolucoes.adimanager.filtros.LazyFiltro;
import com.adisolucoes.adimanager.model.Ferramenta;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;
import javax.persistence.Query;

/**
 *
 * @author ADI Manager
 */
public class FerramentaDAO extends DAO<Ferramenta> implements LazyDAO<Ferramenta>, Serializable {

    private static final Logger LOG = Logger.getLogger(FerramentaDAO.class.getName());

    public FerramentaDAO() {
        super(Ferramenta.class);
    }

    @Override
    public int quantidadeLazy(LazyFiltro filtro) throws ErroBancoDadosException {
        FerramentaFiltro ferramentaFiltro = (FerramentaFiltro) filtro;
        String sql = gerarSQL(ferramentaFiltro);
        Query query = manager.createQuery(sql);
        query.setFirstResult(ferramentaFiltro.getPrimeiro());
        query.setMaxResults(ferramentaFiltro.getQuantidade());
        preencherParametros(ferramentaFiltro, query);
        Long count = (Long) query.getSingleResult();
        return count.intValue();
    }

    @Override
    public List<Ferramenta> buscarLazy(LazyFiltro filtro) throws ErroBancoDadosException {
        FerramentaFiltro ferramentaFiltro = (FerramentaFiltro) filtro;
        String sql = gerarSQL(ferramentaFiltro);
        Query query = manager.createQuery(sql);
        query.setFirstResult(ferramentaFiltro.getPrimeiro());
        query.setMaxResults(ferramentaFiltro.getQuantidade());
        preencherParametros(ferramentaFiltro, query);
        List<Ferramenta> ferramentas = query.getResultList();
        return ferramentas;
    }

    private String gerarSQL(FerramentaFiltro filtro) {
        String sql = "";
        sql += filtro.isCount() ? "SELECT COUNT(f) FROM Ferramenta f WHERE f.id IS NOT NULL " : "SELECT f FROM Ferramenta f WHERE f.id IS NOT NULL ";
        sql += (filtro.getNome() != null && !filtro.getNome().isEmpty()) ? "AND f.nome LIKE :nome " : " ";
        sql += (filtro.getDesenvolvedora() != null && !filtro.getDesenvolvedora().isEmpty()) ? "AND f.desenvolvedora LIKE :desenvolvedora " : " ";
        sql += (filtro.getProjeto() != null) ? "AND :projeto MEMBER OF f.projetos " : " ";
        sql += (!filtro.isTodas()) ? "AND f.paga = :paga " : " ";
        sql += " ORDER BY f.nome ASC";
        return sql;
    }

    private void preencherParametros(FerramentaFiltro filtro, Query query) {
        if (filtro.getNome() != null && !filtro.getNome().isEmpty()) {
            query.setParameter("nome", "%" + filtro.getNome() + "%");
        }
        if (filtro.getDesenvolvedora() != null && !filtro.getDesenvolvedora().isEmpty()) {
            query.setParameter("desenvolvedora", "%" +  filtro.getDesenvolvedora() + "%");
        }
        if (filtro.getProjeto() != null) {
            query.setParameter("projeto", filtro.getProjeto());
        }
        if (!filtro.isTodas()) {
            query.setParameter("paga", filtro.isPaga());
        }
    }
}

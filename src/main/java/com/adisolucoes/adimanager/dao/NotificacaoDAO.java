package com.adisolucoes.adimanager.dao;

import com.adisolucoes.adimanager.exceptions.ErroBancoDadosException;
import com.adisolucoes.adimanager.filtros.LazyFiltro;
import com.adisolucoes.adimanager.filtros.NotificacaoFiltro;
import com.adisolucoes.adimanager.model.Notificacao;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Query;
import org.apache.log4j.Logger;

/**
 *
 * @author Junior Sy
 */
public class NotificacaoDAO extends DAO<Notificacao> implements LazyDAO<Notificacao>, Serializable{
    
    private static final Logger LOG = Logger.getLogger(NotificacaoDAO.class.getName());
    
    public NotificacaoDAO(){
        super(Notificacao.class);
    }

    @Override
    public int quantidadeLazy(LazyFiltro filtro) throws ErroBancoDadosException {
        NotificacaoFiltro notificacaoFiltro = (NotificacaoFiltro) filtro;
        String sql = gerarSQL(notificacaoFiltro);
        Query query = manager.createQuery(sql);
        query.setFirstResult(notificacaoFiltro.getPrimeiro());
        query.setMaxResults(notificacaoFiltro.getQuantidade());
        preencherParametros(notificacaoFiltro, query);
        Long count = (Long) query.getSingleResult();
        return count.intValue();
    }

    @Override
    public List<Notificacao> buscarLazy(LazyFiltro filtro) throws ErroBancoDadosException {
        NotificacaoFiltro notificacaoFiltro = (NotificacaoFiltro) filtro;
        String sql = gerarSQL(notificacaoFiltro);
        Query query = manager.createQuery(sql);
        query.setFirstResult(notificacaoFiltro.getPrimeiro());
        query.setMaxResults(notificacaoFiltro.getQuantidade());
        preencherParametros(notificacaoFiltro, query);
        List<Notificacao> notificoes = query.getResultList();
        return notificoes;
    }    

    private String gerarSQL(NotificacaoFiltro filtro) {
        String sql = "";
        sql += filtro.isCount() ? "SELECT COUNT(n) FROM Notificacao n WHERE n.id IS NOT NULL " : "SELECT n FROM Notificacao n WHERE n.id IS NOT NULL ";
        sql += (filtro.getTipoPrioridade() != null) ? "AND n.tipoPrioridadeNotificacao = :prioridade " : "";
        sql += (filtro.getDataInicio() != null && !filtro.getDataInicio().equals("")) ? "AND n.dataInicio = :dataInicio " : "";
        sql += (filtro.getDataFinal() != null && !filtro.getDataFinal().equals("")) ? "AND n.dataFinal = :dataFinal " : "";
        sql += (filtro.getStatus() != null) ? "AND n.status = :status" : "";
        sql += " ORDER BY n.tipoPrioridadeNotificacao ASC";
        return sql;
    }

    private void preencherParametros(NotificacaoFiltro filtro, Query query) {
        if(filtro.getTipoPrioridade() != null){
            query.setParameter("prioridade", filtro.getTipoPrioridade());
        }
        
        if(filtro.getDataInicio() != null && !filtro.getDataInicio().equals("")) {
            query.setParameter("dataInicio", filtro.getDataInicio());
        }
        
        if(filtro.getDataFinal() != null && !filtro.getDataFinal().equals("")){
            query.setParameter("dataFinal", filtro.getDataFinal());
        }
        
        if(filtro.getStatus() != null) {
          query.setParameter("status", filtro.getStatus().equalsIgnoreCase("ativado"));
        }
    }
}

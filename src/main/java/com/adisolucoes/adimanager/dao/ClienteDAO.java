package com.adisolucoes.adimanager.dao;

import com.adisolucoes.adimanager.exceptions.ErroBancoDadosException;
import com.adisolucoes.adimanager.filtros.ClienteFiltro;
import com.adisolucoes.adimanager.filtros.LazyFiltro;
import com.adisolucoes.adimanager.model.Cliente;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;
import javax.persistence.Query;

/**
 *
 * @author ADI Soluções
 */
public class ClienteDAO extends DAO<Cliente> implements LazyDAO<Cliente>, Serializable {

    private static final Logger LOG = Logger.getLogger(ClienteDAO.class.getName());

    public ClienteDAO() {
        super(Cliente.class);
    }

    @Override
    public int quantidadeLazy(LazyFiltro filtro) throws ErroBancoDadosException {
        ClienteFiltro clienteFiltro = (ClienteFiltro) filtro;
        String sql = gerarSQL(clienteFiltro);
        Query query = manager.createQuery(sql);
        query.setFirstResult(clienteFiltro.getPrimeiro());
        query.setMaxResults(clienteFiltro.getQuantidade());
        preencherParametros(clienteFiltro, query);
        Long count = (Long) query.getSingleResult();
        return count.intValue();
    }

    @Override
    public List<Cliente> buscarLazy(LazyFiltro filtro) throws ErroBancoDadosException {
        ClienteFiltro clienteFiltro = (ClienteFiltro) filtro;
        String sql = gerarSQL(clienteFiltro);
        Query query = manager.createQuery(sql);
        query.setFirstResult(clienteFiltro.getPrimeiro());
        query.setMaxResults(clienteFiltro.getQuantidade());
        preencherParametros(clienteFiltro, query);
        List<Cliente> clientes = query.getResultList();
        return clientes;
    }

    private String gerarSQL(ClienteFiltro filtro) {
        String sql = "";
        sql += filtro.isCount() ? "SELECT COUNT(c) FROM Cliente c WHERE c.id IS NOT NULL " : "SELECT c FROM Cliente c WHERE c.id IS NOT NULL ";
        sql += (filtro.getNome() != null && !filtro.getNome().isEmpty()) ? "AND c.pessoa.nome LIKE :nome " : " ";
        sql += (filtro.getCpfCnpj() != null && !filtro.getCpfCnpj().isEmpty()) ? "AND c.pessoa.cpfCnpj LIKE :cpfCnpj " : " ";
        sql += (filtro.getCodigoIdentificador() != null && !filtro.getCodigoIdentificador().isEmpty()) ? "AND c.codigoIdentificador LIKE :codigoIdentificador" : " ";
        sql += (filtro.getProjeto() != null) ? "AND :projeto MEMBER OF c.projetos " : " ";
        sql += " ORDER BY c.pessoa.nome ASC";
        return sql;
    }

    private void preencherParametros(ClienteFiltro filtro, Query query) {
        if (filtro.getNome() != null && !filtro.getNome().isEmpty()) {
            query.setParameter("nome", "%" + filtro.getNome() + "%");
        }
        if (filtro.getCpfCnpj() != null && !filtro.getCpfCnpj().isEmpty()) {
            query.setParameter("cpfCnpj", "%" + filtro.getCpfCnpj() + "%");
        }
        if (filtro.getCodigoIdentificador() != null && !filtro.getCodigoIdentificador().isEmpty()) {
            query.setParameter("codigoIdentificador", "%" + filtro.getCodigoIdentificador() + "%");
        }
        if (filtro.getProjeto() != null) {
            query.setParameter("projeto", filtro.getProjeto());
        }
    }
}

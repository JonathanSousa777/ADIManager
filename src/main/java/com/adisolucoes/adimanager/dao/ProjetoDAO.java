package com.adisolucoes.adimanager.dao;

import com.adisolucoes.adimanager.exceptions.ErroBancoDadosException;
import com.adisolucoes.adimanager.model.Cliente;
import com.adisolucoes.adimanager.model.Projeto;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author ADI Soluções
 */
public class ProjetoDAO extends DAO<Projeto> implements Serializable {

    private static final Logger LOG = Logger.getLogger(UsuarioDAO.class.getName());

    public ProjetoDAO() {
        super(Projeto.class);
    }

    public List<Projeto> buscarPorCliente(Cliente cliente) throws ErroBancoDadosException {
        List<Projeto> projetos = new ArrayList<>();
        try {
            String sql = "SELECT p FROM Projeto p WHERE p.cliente.id = :idCliente";
            TypedQuery<Projeto> query = manager.createQuery(sql, Projeto.class);
            query.setParameter("idCliente", cliente.getId());
            projetos = query.getResultList();
        } catch (NonUniqueResultException | IllegalArgumentException ex) {
            throw new ErroBancoDadosException(ex.getMessage());
        } catch (NoResultException ex) {
            projetos = new ArrayList<>();
        }
        return projetos;
    }
}

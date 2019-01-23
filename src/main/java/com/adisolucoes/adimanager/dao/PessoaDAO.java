package com.adisolucoes.adimanager.dao;

import com.adisolucoes.adimanager.exceptions.ErroBancoDadosException;
import com.adisolucoes.adimanager.exceptions.ErroNenhumaPessoaEncontrada;
import com.adisolucoes.adimanager.model.Pessoa;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author ADI Manager
 */
public class PessoaDAO extends DAO<Pessoa> implements Serializable {

    private static final Logger LOG = Logger.getLogger(PessoaDAO.class.getName());

    public PessoaDAO() {
        super(Pessoa.class);
    }

    public Pessoa buscarPorCpfCnpj(String cpfCnpj) throws ErroBancoDadosException {
        Pessoa pessoa = null;
        try {
            String sql = "SELECT p FROM Pessoa p WHERE p.cpfCnpj LIKE :cpfCnpj";
            TypedQuery<Pessoa> query = manager.createQuery(sql, Pessoa.class);
            query.setParameter("cpfCnpj", cpfCnpj);
            List<Pessoa> pessoas = new ArrayList<Pessoa>();
            pessoas = query.getResultList();
            if (!pessoas.isEmpty()) {
                pessoa = query.getResultList().get(0);
            }
        } catch (NonUniqueResultException | IllegalArgumentException ex) {
            throw new ErroBancoDadosException(ex.getMessage());
        } catch (NoResultException ex) {
            pessoa = null;
        }
        return pessoa;
    }

    public Pessoa buscarUltimaPessoaCadastrada() throws ErroNenhumaPessoaEncontrada, ErroBancoDadosException {
        Pessoa pessoa = null;
        try {
            String sql = "SELECT p FROM Pessoa p ORDER BY p.id DESC";
            TypedQuery<Pessoa> query = manager.createQuery(sql, Pessoa.class);
            query.setMaxResults(1);
            pessoa = query.getSingleResult();
        } catch (NonUniqueResultException | IllegalArgumentException ex) {
            throw new ErroBancoDadosException(ex.getMessage());
        } catch (NoResultException ex) {
            throw new ErroNenhumaPessoaEncontrada();
        }
        return pessoa;
    }
}

package com.adisolucoes.adimanager.dao;

import com.adisolucoes.adimanager.enumerations.TipoUsuario;
import com.adisolucoes.adimanager.exceptions.ErroBancoDadosException;
import com.adisolucoes.adimanager.model.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author ADI Soluções
 */
public class UsuarioDAO extends DAO<Usuario> implements Serializable {

    private static final Logger LOG = Logger.getLogger(UsuarioDAO.class.getName());

    public UsuarioDAO() {
        super(Usuario.class);
    }

    public List<Usuario> buscarPorNomeTipoUsuario(String nome, TipoUsuario tipoUsuario) throws ErroBancoDadosException {
        try {
            List<Usuario> listaUsuarios = new ArrayList<>();
            TypedQuery<Usuario> query;
            String sql = "SELECT u FROM Usuario u WHERE u.pessoa.nome LIKE :nome";
            if (tipoUsuario != null) {
                sql += " AND u.tipoUsuario= :tipoUsuario";
            }
            sql += " ORDER BY u.pessoa.nome";
            query = manager.createQuery(sql, Usuario.class);
            query.setParameter("nome", "%" + nome + "%");
            if (tipoUsuario != null) {
                query.setParameter("tipoUsuario", tipoUsuario);
            }
            listaUsuarios = query.getResultList();
            return listaUsuarios;
        } catch (NonUniqueResultException | IllegalArgumentException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new ErroBancoDadosException(ex.getMessage());
        } catch (NoResultException ex) {
            return null;
        }
    }

    public Usuario buscarPorLoginSenha(String login, String senha) throws ErroBancoDadosException {
        Usuario usuario = null;
        try {
            String sql = "SELECT u FROM Usuario u WHERE u.login = :login AND u.senha =:senha";
            TypedQuery<Usuario> query = manager.createQuery(sql, Usuario.class);
            query.setParameter("login", login);
            query.setParameter("senha", senha);
            usuario = query.getSingleResult();
        } catch (NonUniqueResultException | IllegalArgumentException ex) {
            throw new ErroBancoDadosException(ex.getMessage());
        } catch (NoResultException ex) {
            usuario = null;
        }
        return usuario;
    }
}

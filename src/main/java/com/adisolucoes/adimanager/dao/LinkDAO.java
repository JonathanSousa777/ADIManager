/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adisolucoes.adimanager.dao;

import com.adisolucoes.adimanager.exceptions.ErroBancoDadosException;
import com.adisolucoes.adimanager.model.Link;
import com.adisolucoes.adimanager.model.Usuario;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author jonat
 */
public class LinkDAO extends DAO<Link> implements Serializable {

    private static final Logger LOG = Logger.getLogger(LinkDAO.class.getName());

    public LinkDAO() {
        super(Link.class);
    }

    public Link buscarPorHash(String hash) throws ErroBancoDadosException {
        Link link = null;
        try {
            String sql = "SELECT l FROM Link l WHERE l.hash = :hash AND l.ativo = true";
            TypedQuery<Link> query = manager.createQuery(sql, Link.class);
            query.setParameter("hash", hash);
            link = query.getSingleResult();
        } catch (NonUniqueResultException ex) {
            LOG.log(Level.SEVERE, null, ex);
        } catch (NoResultException ex) {
            link = null;
        } catch (IllegalArgumentException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new ErroBancoDadosException(ex.getMessage());
        }
        return link;
    }

    public Link buscarPorUsuario(Usuario usuario) throws ErroBancoDadosException {
        Link link = null;
        try {
            String sql = "SELECT l FROM Link l WHERE l.usuario.id = :idUsuario AND l.ativo = true";
            TypedQuery<Link> query = manager.createQuery(sql, Link.class);
            query.setParameter("idUsuario", usuario.getId());
            link = query.getSingleResult();
        } catch (NonUniqueResultException ex) {
            LOG.log(Level.SEVERE, null, ex);
        } catch (NoResultException ex) {
            link = null;
        } catch (IllegalArgumentException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new ErroBancoDadosException(ex.getMessage());
        }
        return link;
    }
}

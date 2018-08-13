package com.adisolucoes.adimanager.dao;

import com.adisolucoes.adimanager.model.Projeto;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author ADI Soluções
 */
public class ProjetoDAO extends DAO<Projeto> implements Serializable {

    private static final Logger LOG = Logger.getLogger(UsuarioDAO.class.getName());

    public ProjetoDAO() {
        super(Projeto.class);
    }
}

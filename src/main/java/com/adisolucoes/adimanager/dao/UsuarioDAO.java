package com.adisolucoes.adimanager.dao;

import com.adisolucoes.adimanager.model.Usuario;
import java.io.Serializable;

/**
 *
 * @author ADI Soluções
 */
public class UsuarioDAO extends DAO<Usuario> implements Serializable {

    public UsuarioDAO() {
        super(Usuario.class);
    }

}

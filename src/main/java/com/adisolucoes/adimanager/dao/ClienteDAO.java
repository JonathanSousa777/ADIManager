package com.adisolucoes.adimanager.dao;

import com.adisolucoes.adimanager.model.Cliente;
import java.io.Serializable;
import java.util.logging.Logger;

/**
 *
 * @author ADI Soluções
 */
public class ClienteDAO extends DAO<Cliente> implements Serializable{
    
    private static final Logger LOG = Logger.getLogger(UsuarioDAO.class.getName());
    
    public ClienteDAO() {
        super(Cliente.class);
    }
    
}

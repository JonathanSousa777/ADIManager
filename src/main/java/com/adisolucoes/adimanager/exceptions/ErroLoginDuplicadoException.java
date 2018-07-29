package com.adisolucoes.adimanager.exceptions;

import com.adisolucoes.adimanager.model.Usuario;

/**
 *
 * @author ADI Soluções
 */
public class ErroLoginDuplicadoException extends Exception {

    private Usuario usuario;

    public ErroLoginDuplicadoException() {
        super("Já existe usuário cadastrado com este login");
    }

    public ErroLoginDuplicadoException(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}

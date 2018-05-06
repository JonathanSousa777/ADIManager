package com.adisolucoes.adimanager.exceptions;

/**
 *
 * @author ADI Soluções
 */
public class ErroBancoDadosException extends Exception {

    public ErroBancoDadosException() {
        super("Ocorreu um Erro na operação do banco de Dados");
    }

    public ErroBancoDadosException(String msg) {
        super(msg);
    }
}

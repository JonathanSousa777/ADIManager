package com.adisolucoes.adimanager.exceptions;

/**
 *
 * @author ADI Soluções
 */
public class ErroPessoaDuplicadoException extends Exception {

    public ErroPessoaDuplicadoException() {
        super("Já existe uma Pessoa cadastrada com esse CPF/CNPJ");
    }
}

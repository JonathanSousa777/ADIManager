package com.adisolucoes.adimanager.exceptions;

/**
 *
 * @author ADI Soluções
 */
public class ErroNenhumaPessoaEncontrada extends Exception {

    public ErroNenhumaPessoaEncontrada() {
        super("Nenhuma pessoa encontrada na consulta");
    }

    public ErroNenhumaPessoaEncontrada(String msg) {
        super(msg);
    }
}

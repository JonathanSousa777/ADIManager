package com.adisolucoes.adimanager.exceptions;

/**
 *
 * @author ADI Soluções
 */
public class ErroValidacaoExclusao extends Exception {

    public ErroValidacaoExclusao() {
        super("O registro não pode ser excluído");
    }

    public ErroValidacaoExclusao(String msg) {
        super(msg);
    }
}

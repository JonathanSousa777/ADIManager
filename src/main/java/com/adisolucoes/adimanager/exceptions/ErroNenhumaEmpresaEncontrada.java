package com.adisolucoes.adimanager.exceptions;

/**
 *
 * @author ADI Soluções
 */
public class ErroNenhumaEmpresaEncontrada extends Exception {

    public ErroNenhumaEmpresaEncontrada() {
        super("Nenhuma empresa encontrada na consulta");
    }

    public ErroNenhumaEmpresaEncontrada(String msg) {
        super(msg);
    }
}

package com.adisolucoes.adimanager.exceptions;

/**
 *
 * @author ADI Soluções
 */
public class ErroEmpresaDuplicadaException extends Exception {

    public ErroEmpresaDuplicadaException() {
        super("Já existe uma Empresa cadastrada com esse CNPJ");
    }
}

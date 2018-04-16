package com.adisolucoes.adimanager.enumerations;

import java.io.Serializable;

/**
 *
 * @author Jonathan Sousa
 */
public enum TipoUsuario implements Serializable {
    MASTER("Master"),
    FUNCIONARIO("Funcionário"),
    CLIENTE("Cliente");

    private String descricao;

    private TipoUsuario(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}

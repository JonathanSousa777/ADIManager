package com.adisolucoes.adimanager.enumerations;

import java.io.Serializable;

/**
 *
 * @author Jonathan Sousa
 */
public enum TipoPrioridadeMeta implements Serializable {
    NORMAL("Normal"),
    EVOLUCAO("Evolução"),
    ALTA("Alta");

    private String descricao;

    private TipoPrioridadeMeta(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}

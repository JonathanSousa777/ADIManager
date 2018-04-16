package com.adisolucoes.adimanager.enumerations;

import java.io.Serializable;

/**
 *
 * @author Jonathan Sousa
 */
public enum TipoPagamento implements Serializable {
    MENSAL("Mensal"),
    TRIMESTRAL("Trimestral"),
    ANUAL("Anual");

    private String descricao;

    private TipoPagamento(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}

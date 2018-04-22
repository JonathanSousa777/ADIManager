package com.adisolucoes.adimanager.enumerations;

import java.io.Serializable;

/**
 *
 * @author Jonathan Sousa
 */
public enum StatusDespesa implements Serializable {
    PAGO("Pago"),
    NEGOCIACAO("Negociação"),
    VENCIDO("Vencido");

    private String descricao;

    private StatusDespesa(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}

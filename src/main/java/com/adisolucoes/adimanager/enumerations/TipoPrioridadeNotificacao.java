package com.adisolucoes.adimanager.enumerations;

import java.io.Serializable;

/**
 *
 * @author Jonathan Sousa
 */
public enum TipoPrioridadeNotificacao implements Serializable {
    AVISO("Aviso"),
    CLIENTE("Cliente"),
    LEMBRETE("Lembrete"),
    FINANCEIRO("Financeiro"),
    ANIVERSARIO("Aniversario"),
    ENTRETETIMENTO("Entretenimento");

    private String descricao;

    private TipoPrioridadeNotificacao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
package com.adisolucoes.adimanager.enumerations;

import java.io.Serializable;

/**
 *
 * @author Jonathan Sousa
 */
public enum UF implements Serializable {

    AC("AC", "Acre"),
    AL("AL", "Alagoas"),
    AM("AM", "Amazonas"),
    AP("AP", "Amapá"),
    BA("BA", "Bahia"),
    CE("CE", "Ceará"),
    DF("DF", "Distrito Federal"),
    ES("ES", "Espírito Santo"),
    GO("GO", "Goiás"),
    MA("MA", "Maranhão"),
    MG("MG", "Minas Gerais"),
    MS("MS", "Mato Grosso do Sul"),
    MT("MT", "Mato Grosso"),
    PA("PA", "Pará"),
    PB("PB", "Paraíba"),
    PE("PE", "Pernambuco"),
    PI("PI", "Piauí"),
    PR("PR", "Paraná"),
    RJ("RJ", "Rio de Janeiro"),
    RN("RN", "Rio Grande do Norte"),
    RS("RS", "Rio Grande do Sul"),
    RO("RO", "Rondônia"),
    RR("RR", "Roraima"),
    SC("SC", "Santa Catarina"),
    SE("SE", "Sergipe"),
    SP("SP", "São Paulo"),
    TO("TO", "Tocantins");

    private final String sigla;
    private final String descricao;

    private UF(String sigla, String descricao) {
        this.sigla = sigla;
        this.descricao = descricao;
    }

    public String getSigla() {
        return sigla;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }

}

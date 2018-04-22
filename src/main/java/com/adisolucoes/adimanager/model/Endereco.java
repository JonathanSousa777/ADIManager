package com.adisolucoes.adimanager.model;

import com.adisolucoes.adimanager.enumerations.UF;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 *
 * @author Jonathan Sousa
 */
@Embeddable
public class Endereco implements Serializable {

    private String logradouro;
    private String numero;
    private String complemento;
    private String cidade;
    private String bairro;
    private UF uf;
    private String cep;

    public Endereco() {
    }

    @Column(nullable = false)
    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    @Column(length = 10)
    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    @Column(length = 120, nullable = false)
    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    @Column(length = 120, nullable = false)
    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    @Enumerated(EnumType.STRING)
    @Column(length = 100, nullable = false)
    public UF getUf() {
        return uf;
    }

    public void setUf(UF uf) {
        this.uf = uf;
    }

    @Column(length = 45)
    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

}

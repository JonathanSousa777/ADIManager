package com.adisolucoes.adimanager.model;

import com.adisolucoes.adimanager.enumerations.UF;
import java.io.Serializable;
import javax.persistence.Embeddable;

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

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

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

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public UF getUf() {
        return uf;
    }

    public void setUf(UF uf) {
        this.uf = uf;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

}

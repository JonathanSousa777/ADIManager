package com.adisolucoes.adimanager.filtros;

import com.adisolucoes.adimanager.model.Projeto;

/**
 *
 * @author ADI Manager
 */
public class ClienteFiltro implements LazyFiltro {

    private Integer primeiro;
    private Integer quantidade;
    private boolean count;
    private String nome;
    private String cpfCnpj;
    private String codigoIdentificador;
    private Projeto projeto;

    public Integer getPrimeiro() {
        return primeiro;
    }

    public void setPrimeiro(Integer primeiro) {
        this.primeiro = primeiro;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public boolean isCount() {
        return count;
    }

    public void setCount(boolean count) {
        this.count = count;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public String getCodigoIdentificador() {
        return codigoIdentificador;
    }

    public void setCodigoIdentificador(String codigoIdentificador) {
        this.codigoIdentificador = codigoIdentificador;
    }

    public Projeto getProjeto() {
        return projeto;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }

}

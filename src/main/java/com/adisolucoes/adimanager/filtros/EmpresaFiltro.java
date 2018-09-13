package com.adisolucoes.adimanager.filtros;

import com.adisolucoes.adimanager.model.Cliente;

/**
 *
 * @author ADI Manager
 */
public class EmpresaFiltro implements LazyFiltro{
    
    private Integer primeiro;
    private Integer quantidade;
    private boolean count;
    private boolean todas;
    private String nome;
    private String cnpj;
    private Cliente cliente;

        
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

    public Boolean getTodas() {
        return todas;
    }

    public void setTodas(Boolean todas) {
        this.todas = todas;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}

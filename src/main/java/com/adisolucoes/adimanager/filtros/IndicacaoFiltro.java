package com.adisolucoes.adimanager.filtros;

import com.adisolucoes.adimanager.model.Cliente;
import java.util.Date;

/**
 *
 * @author ADI Manager
 */
public class IndicacaoFiltro implements LazyFiltro {

    private Integer primeiro;
    private Integer quantidade;
    private boolean count;
    private String descricao;
    private String codigo;
    private Date dataCadastroDe;
    private Date dataCadastroAte;
    private Cliente clienteAtivo;
    private Cliente clientePassivo;

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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Date getDataCadastroDe() {
        return dataCadastroDe;
    }

    public void setDataCadastroDe(Date dataCadastroDe) {
        this.dataCadastroDe = dataCadastroDe;
    }

    public Date getDataCadastroAte() {
        return dataCadastroAte;
    }

    public void setDataCadastroAte(Date dataCadastroAte) {
        this.dataCadastroAte = dataCadastroAte;
    }

    public Cliente getClienteAtivo() {
        return clienteAtivo;
    }

    public void setClienteAtivo(Cliente clienteAtivo) {
        this.clienteAtivo = clienteAtivo;
    }

    public Cliente getClientePassivo() {
        return clientePassivo;
    }

    public void setClientePassivo(Cliente clientePassivo) {
        this.clientePassivo = clientePassivo;
    }

}

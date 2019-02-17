package com.adisolucoes.adimanager.filtros;

import com.adisolucoes.adimanager.enumerations.TipoPrioridadeMeta;
import java.util.Date;

/**
 *
 * @author ADI Soluções
 */
public class MetaFiltro implements LazyFiltro {

    private Integer primeiro;
    private Integer quantidade;
    private boolean count;
    private String concluida;
    private String descricao;
    private Date dataInicioDe;
    private Date dataInicioAte;
    private Date dataFimDe;
    private Date dataFimAte;
    private TipoPrioridadeMeta tipoPrioridadeMeta;

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

    public String getConcluida() {
        return concluida;
    }

    public void setConcluida(String concluida) {
        this.concluida = concluida;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataInicioDe() {
        return dataInicioDe;
    }

    public void setDataInicioDe(Date dataInicioDe) {
        this.dataInicioDe = dataInicioDe;
    }

    public Date getDataInicioAte() {
        return dataInicioAte;
    }

    public void setDataInicioAte(Date dataInicioAte) {
        this.dataInicioAte = dataInicioAte;
    }

    public Date getDataFimDe() {
        return dataFimDe;
    }

    public void setDataFimDe(Date dataFimDe) {
        this.dataFimDe = dataFimDe;
    }

    public Date getDataFimAte() {
        return dataFimAte;
    }

    public void setDataFimAte(Date dataFimAte) {
        this.dataFimAte = dataFimAte;
    }

    public TipoPrioridadeMeta getTipoPrioridadeMeta() {
        return tipoPrioridadeMeta;
    }

    public void setTipoPrioridadeMeta(TipoPrioridadeMeta tipoPrioridadeMeta) {
        this.tipoPrioridadeMeta = tipoPrioridadeMeta;
    }

}

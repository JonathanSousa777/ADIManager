package com.adisolucoes.adimanager.filtros;

import com.adisolucoes.adimanager.enumerations.TipoPrioridadeNotificacao;
import java.util.Date;

/**
 *
 * @author ADI Manager
 */
public class NotificacaoFiltro implements LazyFiltro{

    private Integer primeiro;
    private Integer quantidade;
    private boolean count;
    private TipoPrioridadeNotificacao tipoPrioridade;
    private Date dataInicio;
    private Date dataFinal;
    private String status;

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

    public TipoPrioridadeNotificacao getTipoPrioridade() {
        return tipoPrioridade;
    }

    public void setTipoPrioridade(TipoPrioridadeNotificacao tipoPrioridade) {
        this.tipoPrioridade = tipoPrioridade;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }
    
    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

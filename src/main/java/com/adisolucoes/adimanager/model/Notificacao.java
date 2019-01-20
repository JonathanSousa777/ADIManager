package com.adisolucoes.adimanager.model;

import com.adisolucoes.adimanager.enumerations.TipoPrioridadeNotificacao;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Jonathan Sousa
 */
@Entity
@Table(name = "tab_notificacao")
public class Notificacao implements Serializable {

    private long id;
    private String descricao;
    private String responsavel;
    private Date dataInicio;
    private Date dataFinal;
    private TipoPrioridadeNotificacao tipoPrioridadeNotificacao;
    private boolean lida;
    private boolean status;

    public Notificacao() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Lob
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_inicio", nullable = false)
    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_final", nullable = false)
    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_prioridade_notificacao")
    public TipoPrioridadeNotificacao getTipoPrioridadeNotificacao() {
        return tipoPrioridadeNotificacao;
    }

    public void setTipoPrioridadeNotificacao(TipoPrioridadeNotificacao tipoPrioridadeNotificacao) {
        this.tipoPrioridadeNotificacao = tipoPrioridadeNotificacao;
    }

    @Column(name = "lida")
    public boolean isLida() {
        return lida;
    }

    public void setLida(boolean lida) {
        this.lida = lida;
    }
    
    @Column(name = "status")
    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Notificacao other = (Notificacao) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

}

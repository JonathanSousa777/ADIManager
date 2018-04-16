package com.adisolucoes.adimanager.model;

import com.adisolucoes.adimanager.enumerations.TipoPrioridadeMeta;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Jonathan Sousa
 */
@Entity
@Table(name = "tab_meta")
public class Meta implements Serializable {

    private long id;
    private String descricao;
    private Date dataInicio;
    private Date dataFim;
    private TipoPrioridadeMeta tipoPrioridadeMeta;
    private boolean concluida;

    public Meta() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    @Enumerated(EnumType.STRING)
    public TipoPrioridadeMeta getTipoPrioridadeMeta() {
        return tipoPrioridadeMeta;
    }

    public void setTipoPrioridadeMeta(TipoPrioridadeMeta tipoPrioridadeMeta) {
        this.tipoPrioridadeMeta = tipoPrioridadeMeta;
    }

    public boolean isConcluida() {
        return concluida;
    }

    public void setConcluida(boolean concluida) {
        this.concluida = concluida;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + (int) (this.id ^ (this.id >>> 32));
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
        final Meta other = (Meta) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

}

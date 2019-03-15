package com.adisolucoes.adimanager.model;

import com.adisolucoes.adimanager.enumerations.TipoPrioridadeMeta;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Jonathan Sousa
 */
@Entity
@Table(name = "tab_metas")
public class Meta implements Serializable {

    private Long id;
    private String descricao;
    private Date dataInicio;
    private Date dataFim;
    private TipoPrioridadeMeta tipoPrioridadeMeta;
    private boolean concluida;

    public Meta() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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
    @Column(name = "data_fim", nullable = false)
    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_prioridade_meta")
    public TipoPrioridadeMeta getTipoPrioridadeMeta() {
        return tipoPrioridadeMeta;
    }

    public void setTipoPrioridadeMeta(TipoPrioridadeMeta tipoPrioridadeMeta) {
        this.tipoPrioridadeMeta = tipoPrioridadeMeta;
    }

    @Column(name = "concluida")
    public boolean isConcluida() {
        return concluida;
    }

    public void setConcluida(boolean concluida) {
        this.concluida = concluida;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.id);
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
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}

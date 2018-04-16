package com.adisolucoes.adimanager.model;

import com.adisolucoes.adimanager.enumerations.TipoPrioridadeNotificacao;
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
@Table(name = "tab_notificacao")
public class Notificacao implements Serializable {

    private long id;
    private String descricao;
    private Date duracao;
    private TipoPrioridadeNotificacao tipoPrioridadeNotificacao;
    private boolean lida;

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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDuracao() {
        return duracao;
    }

    public void setDuracao(Date duracao) {
        this.duracao = duracao;
    }

    @Enumerated(EnumType.STRING)
    public TipoPrioridadeNotificacao getTipoPrioridadeNotificacao() {
        return tipoPrioridadeNotificacao;
    }

    public void setTipoPrioridadeNotificacao(TipoPrioridadeNotificacao tipoPrioridadeNotificacao) {
        this.tipoPrioridadeNotificacao = tipoPrioridadeNotificacao;
    }

    public boolean isLida() {
        return lida;
    }

    public void setLida(boolean lida) {
        this.lida = lida;
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

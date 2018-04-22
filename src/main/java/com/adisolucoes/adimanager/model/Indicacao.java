package com.adisolucoes.adimanager.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Jonathan Sousa
 */
@Entity
@Table(name = "tab_indicacao")
public class Indicacao implements Serializable {

    private long id;
    private long codigoClientePassivo;
    private Cliente clienteAtivo;
    private Date data;
    private String descricao;

    public Indicacao() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "codigo_cliente_passivo", nullable = false)
    public long getCodigoClientePassivo() {
        return codigoClientePassivo;
    }

    public void setCodigoClientePassivo(long codigoClientePassivo) {
        this.codigoClientePassivo = codigoClientePassivo;
    }

    @ManyToOne
    @JoinColumn(name = "cliente_ativo_id", nullable = false)
    public Cliente getClienteAtivo() {
        return clienteAtivo;
    }

    public void setClienteAtivo(Cliente clienteAtivo) {
        this.clienteAtivo = clienteAtivo;
    }

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + (int) (this.id ^ (this.id >>> 32));
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
        final Indicacao other = (Indicacao) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

}

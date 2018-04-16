package com.adisolucoes.adimanager.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Jonathan Sousa
 */
@Entity
@Table(name = "tab_indicacao")
public class Indicacao implements Serializable {

    private long id;
    private Cliente clientePassivo;
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

    @OneToOne
    public Cliente getClientePassivo() {
        return clientePassivo;
    }

    public void setClientePassivo(Cliente clientePassivo) {
        this.clientePassivo = clientePassivo;
    }

    @OneToOne
    public Cliente getClienteAtivo() {
        return clienteAtivo;
    }

    public void setClienteAtivo(Cliente clienteAtivo) {
        this.clienteAtivo = clienteAtivo;
    }

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

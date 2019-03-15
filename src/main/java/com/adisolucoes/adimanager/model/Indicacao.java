package com.adisolucoes.adimanager.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
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
 * @author ADI Soluçoes
 */
@Entity
@Table(name = "tab_indicacoes")
public class Indicacao implements Serializable {

    private Long id;
    private Cliente clientePassivo;
    private Cliente clienteAtivo;
    private Date data;
    private String descricao;
    private String codigoIdentificador;

    public Indicacao() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "tab_clientes_passivos_id", nullable = false)
    public Cliente getClientePassivo() {
        return clientePassivo;
    }

    public void setClientePassivo(Cliente clientePassivo) {
        this.clientePassivo = clientePassivo;
    }

    @ManyToOne
    @JoinColumn(name = "tab_clientes_ativos_id", nullable = false)
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

    @Column(name = "codigo_identificador")
    public String getCodigoIdentificador() {
        return codigoIdentificador;
    }

    public void setCodigoIdentificador(String codigoIdentificador) {
        this.codigoIdentificador = codigoIdentificador;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.id);
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
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}

package com.adisolucoes.adimanager.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Jonathan Sousa
 */
@Entity
@Table(name = "tab_plano")
public class Plano implements Serializable {

    private long id;
    private String descricao;
    private String apelido;
    private BigDecimal valor;
    private CategoriaPlano categoriaPlano;
    private String cor;
    private Promocao promocao;

    public Plano() {
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

    @Column(length = 18, nullable = false)
    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    @Column(nullable = false, precision = 20, scale = 2)
    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    public CategoriaPlano getCategoriaPlano() {
        return categoriaPlano;
    }

    public void setCategoriaPlano(CategoriaPlano categoriaPlano) {
        this.categoriaPlano = categoriaPlano;
    }

    @Column(nullable = false, length = 15)
    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    @ManyToOne
    @JoinColumn(name = "promocao_id")
    public Promocao getPromocao() {
        return promocao;
    }

    public void setPromocao(Promocao promocao) {
        this.promocao = promocao;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + (int) (this.id ^ (this.id >>> 32));
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
        final Plano other = (Plano) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

}

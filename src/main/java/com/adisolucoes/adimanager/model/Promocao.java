package com.adisolucoes.adimanager.model;

import com.adisolucoes.adimanager.enumerations.TipoPromocao;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;

/**
 *
 * @author Jonathan Sousa
 */
public class Promocao implements Serializable {

    private long id;
    private BigDecimal novoValor;
    private TipoPromocao tipoPromocao;
    private BigDecimal valorDesconto;
    private Date dataInicio;
    private Date dataFim;
    private String descricao;
    private boolean status;
    private List<Plano> planos;

    public Promocao() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BigDecimal getNovoValor() {
        return novoValor;
    }

    public void setNovoValor(BigDecimal novoValor) {
        this.novoValor = novoValor;
    }

    @Enumerated(EnumType.STRING)
    public TipoPromocao getTipoPromocao() {
        return tipoPromocao;
    }

    public void setTipoPromocao(TipoPromocao tipoPromocao) {
        this.tipoPromocao = tipoPromocao;
    }

    public BigDecimal getValorDesconto() {
        return valorDesconto;
    }

    public void setValorDesconto(BigDecimal valorDesconto) {
        this.valorDesconto = valorDesconto;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @OneToMany
    public List<Plano> getPlanos() {
        return planos;
    }

    public void setPlanos(List<Plano> planos) {
        this.planos = planos;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + (int) (this.id ^ (this.id >>> 32));
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
        final Promocao other = (Promocao) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

}

package com.adisolucoes.adimanager.model;

import com.adisolucoes.adimanager.enumerations.TipoPromocao;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Jonathan Sousa
 */
@Entity
@Table(name = "tab_promocoes")
public class Promocao implements Serializable {

    private Long id;
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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "novo_valor", nullable = false, precision = 20, scale = 2)
    public BigDecimal getNovoValor() {
        return novoValor;
    }

    public void setNovoValor(BigDecimal novoValor) {
        this.novoValor = novoValor;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_promocao")
    public TipoPromocao getTipoPromocao() {
        return tipoPromocao;
    }

    public void setTipoPromocao(TipoPromocao tipoPromocao) {
        this.tipoPromocao = tipoPromocao;
    }

    @Column(name = "valor_desconto", nullable = false, precision = 20, scale = 2)
    public BigDecimal getValorDesconto() {
        return valorDesconto;
    }

    public void setValorDesconto(BigDecimal valorDesconto) {
        this.valorDesconto = valorDesconto;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Column(name = "ativada")
    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @OneToMany(mappedBy = "promocao")
    public List<Plano> getPlanos() {
        return planos;
    }

    public void setPlanos(List<Plano> planos) {
        this.planos = planos;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + Objects.hashCode(this.id);
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
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}

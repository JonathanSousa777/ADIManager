package com.adisolucoes.adimanager.model;

import com.adisolucoes.adimanager.enumerations.TipoPagamento;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Jonathan Sousa
 */
@Entity
@Table(name = "tab_projeto")
public class Projeto implements Serializable {

    private long id;
    private String descricao;
    private Date dataInicio;
    private Date dataFim;
    private BigDecimal valor;
    private BigDecimal desconto;
    private String nomeSite;
    private String url;
    private List<Fase> fases;
    private boolean concluido;
    private byte[] arquivo;
    private List<Acrescimo> acrescimos;
    private String codigoidentificador;
    private Plano plano;
    private boolean pago;
    private String observacaoComplementar;
    private Cliente cliente;
    private Date dataVencimento;
    private String formaPagamento;
    private TipoPagamento tipoPagamento;
    private List<Ferramenta> ferramentas;
    private Servidor servidor;

    public Projeto() {
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

    @Temporal(TemporalType.DATE)
    @Column(name = "data_inicio", nullable = false)
    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "data_fim", nullable = false)
    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    @Column(nullable = false, precision = 20, scale = 2)
    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    @Column(nullable = false, precision = 20, scale = 2)
    public BigDecimal getDesconto() {
        return desconto;
    }

    public void setDesconto(BigDecimal desconto) {
        this.desconto = desconto;
    }

    @Column(nullable = false, length = 50)
    public String getNomeSite() {
        return nomeSite;
    }

    public void setNomeSite(String nomeSite) {
        this.nomeSite = nomeSite;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @ManyToMany
    @JoinTable(name = "tab_projeto_tab_fase", joinColumns = @JoinColumn(name = "projeto_id"),
            inverseJoinColumns = @JoinColumn(name = "fase_id"))
    public List<Fase> getFases() {
        return fases;
    }

    public void setFases(List<Fase> fases) {
        this.fases = fases;
    }

    @Column(name = "concluido")
    public boolean isConcluido() {
        return concluido;
    }

    public void setConcluido(boolean concluido) {
        this.concluido = concluido;
    }

    @Lob
    @Basic(fetch = FetchType.LAZY, optional = true)
    public byte[] getArquivo() {
        return arquivo;
    }

    public void setArquivo(byte[] arquivo) {
        this.arquivo = arquivo;
    }

    @ManyToMany
    @JoinTable(name = "tab_projeto_tab_ferramenta", joinColumns = @JoinColumn(name = "projeto_id"),
            inverseJoinColumns = @JoinColumn(name = "ferramenta_id"))
    public List<Ferramenta> getFerramentas() {
        return ferramentas;
    }

    public void setFerramentas(List<Ferramenta> ferramentas) {
        this.ferramentas = ferramentas;
    }

    @ManyToMany
    @JoinTable(name = "tab_projeto_tab_acrescimo", joinColumns = @JoinColumn(name = "projeto_id"),
            inverseJoinColumns = @JoinColumn(name = "acrescimo_id"))
    public List<Acrescimo> getAcrescimos() {
        return acrescimos;
    }

    public void setAcrescimos(List<Acrescimo> acrescimos) {
        this.acrescimos = acrescimos;
    }

    @Column(name = "codigo_identificador", length = 30)
    public String getCodigoidentificador() {
        return codigoidentificador;
    }

    public void setCodigoidentificador(String codigoidentificador) {
        this.codigoidentificador = codigoidentificador;
    }

    @ManyToOne
    @JoinColumn(name = "plano_id", nullable = false)
    public Plano getPlano() {
        return plano;
    }

    public void setPlano(Plano plano) {
        this.plano = plano;
    }

    @Column(name = "pago")
    public boolean isPago() {
        return pago;
    }

    public void setPago(boolean pago) {
        this.pago = pago;
    }

    @Column(name = "observacao_complementar")
    public String getObservacaoComplementar() {
        return observacaoComplementar;
    }

    public void setObservacaoComplementar(String observacaoComplementar) {
        this.observacaoComplementar = observacaoComplementar;
    }

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "data_vencimento", nullable = false)
    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    @Column(name = "forma_pagamento", nullable = false, length = 40)
    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_pagamento")
    public TipoPagamento getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(TipoPagamento tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    @ManyToOne
    @JoinColumn(name = "servidor_id", nullable = false)
    public Servidor getServidor() {
        return servidor;
    }

    public void setServidor(Servidor servidor) {
        this.servidor = servidor;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + (int) (this.id ^ (this.id >>> 32));
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
        final Projeto other = (Projeto) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

}

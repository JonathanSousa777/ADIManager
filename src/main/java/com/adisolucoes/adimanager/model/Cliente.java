package com.adisolucoes.adimanager.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Jonathan Sousa
 */
@Entity
@Table(name = "tab_cliente")
public class Cliente implements Serializable {

    private long id;
    private Empresa empresa;
    private String comoConheceu;
    private List<Projeto> projetos;
    private String observacao;
    private List<Indicacao> indicacoes;
    private String codigoIdentificador;

    public Cliente() {
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
    @JoinColumn(name = "empresa_id")
    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    @Column(name = "como_conheceu")
    public String getComoConheceu() {
        return comoConheceu;
    }

    public void setComoConheceu(String comoConheceu) {
        this.comoConheceu = comoConheceu;
    }

    @OneToMany(mappedBy = "cliente")
    public List<Projeto> getProjetos() {
        return projetos;
    }

    public void setProjetos(List<Projeto> projetos) {
        this.projetos = projetos;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    @OneToMany(mappedBy = "clienteAtivo", cascade = CascadeType.ALL)
    public List<Indicacao> getIndicacoes() {
        return indicacoes;
    }

    public void setIndicacoes(List<Indicacao> indicacoes) {
        this.indicacoes = indicacoes;
    }

    @Column(name = "codigo_identificador", length = 30)
    public String getCodigoIdentificador() {
        return codigoIdentificador;
    }

    public void setCodigoIdentificador(String codigoIdentificador) {
        this.codigoIdentificador = codigoIdentificador;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + (int) (this.id ^ (this.id >>> 32));
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
        final Cliente other = (Cliente) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

}

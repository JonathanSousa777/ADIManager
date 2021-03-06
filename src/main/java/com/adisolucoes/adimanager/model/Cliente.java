package com.adisolucoes.adimanager.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author ADI Soluções
 */
@Entity
@Table(name = "tab_clientes")
public class Cliente implements Serializable {

    private Long id;
    private String comoConheceu;
    private List<Projeto> projetos;
    private String observacao;
    private List<Indicacao> indicacoes;
    private String codigoIdentificador;
    private Pessoa pessoa;

    public Cliente() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL}, orphanRemoval = false, targetEntity = Pessoa.class)
    @JoinColumn(name = "tab_pessoas_id")
    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.id);
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
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}

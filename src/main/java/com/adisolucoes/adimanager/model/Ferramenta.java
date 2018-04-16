package com.adisolucoes.adimanager.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author Jonathan Sousa
 */
@Entity
@Table(name = "tab_ferramenta")
public class Ferramenta implements Serializable {

    private long id;
    private String descricao;
    private String url;
    private boolean paga;
    private BigDecimal valor;
    private String vantagens;
    private String desvantagens;
    private String notaInstrucao;
    private List<Projeto> projetos;
    private String desenvolvedora;
    private String avaliacao;

    public Ferramenta() {
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isPaga() {
        return paga;
    }

    public void setPaga(boolean paga) {
        this.paga = paga;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getVantagens() {
        return vantagens;
    }

    public void setVantagens(String vantagens) {
        this.vantagens = vantagens;
    }

    public String getDesvantagens() {
        return desvantagens;
    }

    public void setDesvantagens(String desvantagens) {
        this.desvantagens = desvantagens;
    }

    public String getNotaInstrucao() {
        return notaInstrucao;
    }

    public void setNotaInstrucao(String notaInstrucao) {
        this.notaInstrucao = notaInstrucao;
    }

    public String getDesenvolvedora() {
        return desenvolvedora;
    }

    public void setDesenvolvedora(String desenvolvedora) {
        this.desenvolvedora = desenvolvedora;
    }

    public String getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(String avaliacao) {
        this.avaliacao = avaliacao;
    }

    @ManyToMany(mappedBy = "ferramentas")
    public List<Projeto> getProjetos() {
        return projetos;
    }

    public void setProjetos(List<Projeto> projetos) {
        this.projetos = projetos;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + (int) (this.id ^ (this.id >>> 32));
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
        final Ferramenta other = (Ferramenta) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

}

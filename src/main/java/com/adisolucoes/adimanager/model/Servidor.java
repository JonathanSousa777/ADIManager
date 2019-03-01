package com.adisolucoes.adimanager.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Jonathan Sousa
 */
@Entity
@Table(name = "tab_servidor")
public class Servidor implements Serializable {

    private Long id;
    private String codigoIdentificador;
    private String url;
    private String nomePlano;
    private BigDecimal valor;
    private List<Projeto> projetos;
    private int gigas;
    private String empresaDesenvolvedora;

    public Servidor() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "codigo_identificador", length = 30)
    public String getCodigoIdentificador() {
        return codigoIdentificador;
    }

    public void setCodigoIdentificador(String codigoIdentificador) {
        this.codigoIdentificador = codigoIdentificador;
    }

    @Column(length = 255, nullable = false)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Column(name = "nome_plano", length = 60, nullable = false)
    public String getNomePlano() {
        return nomePlano;
    }

    public void setNomePlano(String nomePlano) {
        this.nomePlano = nomePlano;
    }

    @Column(nullable = false, scale = 2, precision = 20)
    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    @OneToMany(mappedBy = "servidor")
    public List<Projeto> getProjetos() {
        return projetos;
    }

    public void setProjetos(List<Projeto> projetos) {
        this.projetos = projetos;
    }

    @Column(nullable = false, length = 10)
    public int getGigas() {
        return gigas;
    }

    public void setGigas(int gigas) {
        this.gigas = gigas;
    }

    @Column(name = "empresa_desenvolvedora", nullable = false, length = 50)
    public String getEmpresaDesenvolvedora() {
        return empresaDesenvolvedora;
    }

    public void setEmpresaDesenvolvedora(String empresaDesenvolvedora) {
        this.empresaDesenvolvedora = empresaDesenvolvedora;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.id);
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
        final Servidor other = (Servidor) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}

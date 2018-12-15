package com.adisolucoes.adimanager.controller;

import com.adisolucoes.adimanager.dao.FerramentaDAO;
import com.adisolucoes.adimanager.dao.ProjetoDAO;
import com.adisolucoes.adimanager.exceptions.ErroBancoDadosException;
import com.adisolucoes.adimanager.filtros.FerramentaFiltro;
import com.adisolucoes.adimanager.model.Ferramenta;
import com.adisolucoes.adimanager.model.LazyBean;
import com.adisolucoes.adimanager.model.Projeto;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author ADI Soluções
 */
@Named
@ViewScoped
public class FerramentaBean implements Serializable {

    private static final Logger LOG = Logger.getLogger(Ferramenta.class.getName());

    @Inject
    private FerramentaDAO ferramentaDAO;

    @Inject
    private ProjetoDAO projetoDAO;

    private Ferramenta ferramenta;
    private Projeto projeto;
    private FerramentaFiltro ferramentaFiltro;
    private List<Projeto> projetos;
    private LazyBean<Ferramenta> modelo;

    public FerramentaBean() {
        projetos = new ArrayList<Projeto>();
    }

    @PostConstruct
    public void inicializar() {
        projeto = new Projeto();
        ferramentaFiltro = new FerramentaFiltro();
        carregarProjetos();
    }

    public void pesquisarLazy() {
        modelo = new LazyBean<Ferramenta>(ferramentaDAO, ferramentaFiltro);
    }

    public void carregarProjetos() {
        try {
            projetos = projetoDAO.listarTodos();
        } catch (ErroBancoDadosException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
    }

    public Ferramenta getFerramenta() {
        return ferramenta;
    }

    public void setFerramenta(Ferramenta ferramenta) {
        this.ferramenta = ferramenta;
    }

    public Projeto getProjeto() {
        return projeto;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }

    public FerramentaFiltro getFerramentaFiltro() {
        return ferramentaFiltro;
    }

    public void setFerramentaFiltro(FerramentaFiltro ferramentaFiltro) {
        this.ferramentaFiltro = ferramentaFiltro;
    }

    public List<Projeto> getProjetos() {
        return projetos;
    }

    public void setProjetos(List<Projeto> projetos) {
        this.projetos = projetos;
    }

    public LazyBean<Ferramenta> getModelo() {
        if (modelo == null) {
            pesquisarLazy();
        }
        return modelo;
    }

    public void setModelo(LazyBean<Ferramenta> modelo) {
        this.modelo = modelo;
    }

}

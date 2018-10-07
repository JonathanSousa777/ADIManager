package com.adisolucoes.adimanager.controller;

import com.adisolucoes.adimanager.dao.ClienteDAO;
import com.adisolucoes.adimanager.dao.IndicacaoDAO;
import com.adisolucoes.adimanager.exceptions.ErroBancoDadosException;
import com.adisolucoes.adimanager.filtros.IndicacaoFiltro;
import com.adisolucoes.adimanager.model.Cliente;
import com.adisolucoes.adimanager.model.Indicacao;
import com.adisolucoes.adimanager.model.LazyBean;
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
public class IndicacaoBean implements Serializable {

    private static final Logger LOG = Logger.getLogger(Indicacao.class.getName());

    @Inject
    private IndicacaoDAO indicacaoDAO;

    @Inject
    private ClienteDAO clienteDAO;

    private boolean buscaAvancada;
    private IndicacaoFiltro indicacaoFiltro;
    private List<Indicacao> indicacoes;
    private LazyBean<Indicacao> modelo;
    private List<Cliente> clientesAtivos;
    private List<Cliente> clientesPassivos;

    public IndicacaoBean() {
        indicacaoFiltro = new IndicacaoFiltro();
        buscaAvancada = false;
        clientesAtivos = new ArrayList<>();
        clientesPassivos = new ArrayList<>();
    }

    @PostConstruct
    public void inicializar() {
        carregarClientes();
    }

    public void carregarClientes() {
        try {
            clientesAtivos = clienteDAO.listarTodos();
            clientesPassivos = clientesAtivos;
        } catch (ErroBancoDadosException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
    }

    public void pesquisarLazy() {
        modelo = new LazyBean<Indicacao>(indicacaoDAO, indicacaoFiltro);
    }

    public IndicacaoFiltro getIndicacaoFiltro() {
        return indicacaoFiltro;
    }

    public void setIndicacaoFiltro(IndicacaoFiltro indicacaoFiltro) {
        this.indicacaoFiltro = indicacaoFiltro;
    }

    public IndicacaoDAO getIndicacaoDAO() {
        return indicacaoDAO;
    }

    public void setIndicacaoDAO(IndicacaoDAO indicacaoDAO) {
        this.indicacaoDAO = indicacaoDAO;
    }

    public boolean isBuscaAvancada() {
        return buscaAvancada;
    }

    public void setBuscaAvancada(boolean buscaAvancada) {
        this.buscaAvancada = buscaAvancada;
    }

    public List<Indicacao> getIndicacoes() {
        return indicacoes;
    }

    public void setIndicacoes(List<Indicacao> indicacoes) {
        this.indicacoes = indicacoes;
    }

    public List<Cliente> getClientesAtivos() {
        return clientesAtivos;
    }

    public void setClientesAtivos(List<Cliente> clientesAtivos) {
        this.clientesAtivos = clientesAtivos;
    }

    public List<Cliente> getClientesPassivos() {
        return clientesPassivos;
    }

    public void setClientesPassivos(List<Cliente> clientesPassivos) {
        this.clientesPassivos = clientesPassivos;
    }

    public LazyBean<Indicacao> getModelo() {
        if (modelo == null) {
            pesquisarLazy();
        }
        return modelo;
    }

    public void setModelo(LazyBean<Indicacao> modelo) {
        this.modelo = modelo;
    }

}

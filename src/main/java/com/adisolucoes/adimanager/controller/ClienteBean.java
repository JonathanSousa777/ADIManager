package com.adisolucoes.adimanager.controller;

import com.adisolucoes.adimanager.dao.ClienteDAO;
import com.adisolucoes.adimanager.dao.ProjetoDAO;
import com.adisolucoes.adimanager.exceptions.ErroBancoDadosException;
import com.adisolucoes.adimanager.filtros.ClienteFiltro;
import com.adisolucoes.adimanager.model.Cliente;
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
public class ClienteBean implements Serializable {

    private static final Logger LOG = Logger.getLogger(ClienteBean.class.getName());

    @Inject
    private ClienteDAO clienteDAO;

    @Inject
    private ProjetoDAO projetoDAO;

    private ClienteFiltro clienteFiltro;
    private boolean buscaAvancada;
    private List<Projeto> projetos;
    private LazyBean<Cliente> modelo;

    public ClienteBean() {
        projetos = new ArrayList<Projeto>();
        clienteFiltro = new ClienteFiltro();
    }

    @PostConstruct
    public void inicializar() {
        try {
            projetos = projetoDAO.listarTodos();
        } catch (ErroBancoDadosException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
    }

    public void pesquisarLazy() {
        modelo = new LazyBean<Cliente>(clienteDAO, clienteFiltro);
    }

    public List<Projeto> getProjetos() {
        return projetos;
    }

    public void setProjetos(List<Projeto> projetos) {
        this.projetos = projetos;
    }

    public boolean isBuscaAvancada() {
        return buscaAvancada;
    }

    public void setBuscaAvancada(boolean buscaAvancada) {
        this.buscaAvancada = buscaAvancada;
    }

    public LazyBean<Cliente> getModelo() {
        if (modelo == null) {
            pesquisarLazy();
        }
        return modelo;
    }

    public void setModelo(LazyBean<Cliente> modelo) {
        this.modelo = modelo;
    }

    public ClienteFiltro getClienteFiltro() {
        return clienteFiltro;
    }

    public void setClienteFiltro(ClienteFiltro clienteFiltro) {
        this.clienteFiltro = clienteFiltro;
    }

}

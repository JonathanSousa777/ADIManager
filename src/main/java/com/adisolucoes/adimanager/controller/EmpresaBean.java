package com.adisolucoes.adimanager.controller;

import com.adisolucoes.adimanager.dao.ClienteDAO;
import com.adisolucoes.adimanager.dao.EmpresaDAO;
import com.adisolucoes.adimanager.exceptions.ErroBancoDadosException;
import com.adisolucoes.adimanager.filtros.EmpresaFiltro;
import com.adisolucoes.adimanager.model.Cliente;
import com.adisolucoes.adimanager.model.Empresa;
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
public class EmpresaBean implements Serializable {

    private static final Logger LOG = Logger.getLogger(Empresa.class.getName());

    @Inject
    private EmpresaDAO empresaDAO;

    @Inject
    private ClienteDAO clienteDAO;

    private Empresa empresa;
    private Cliente cliente;
    private EmpresaFiltro empresaFiltro;
    private List<Cliente> clientes;
    private LazyBean<Empresa> modelo;

    public EmpresaBean() {
        clientes = new ArrayList<Cliente>();
    }

    @PostConstruct
    public void inicializar() {
        cliente = new Cliente();
        empresaFiltro = new EmpresaFiltro();
        carregarClientes();
    }

    public void carregarClientes() {
        try {
            clientes = clienteDAO.listarTodos();
        } catch (ErroBancoDadosException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
    }

    public void pesquisarLazy() {
        modelo = new LazyBean<Empresa>(empresaDAO, empresaFiltro);
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public EmpresaFiltro getEmpresaFiltro() {
        return empresaFiltro;
    }

    public void setEmpresaFiltro(EmpresaFiltro empresaFiltro) {
        this.empresaFiltro = empresaFiltro;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public LazyBean<Empresa> getModelo() {
        if (modelo == null) {
            pesquisarLazy();
        }
        return modelo;
    }

    public void setModelo(LazyBean<Empresa> modelo) {
        this.modelo = modelo;
    }
}

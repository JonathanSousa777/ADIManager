package com.adisolucoes.adimanager.controller;

import com.adisolucoes.adimanager.dao.ClienteDAO;
import com.adisolucoes.adimanager.dao.EmpresaDAO;
import com.adisolucoes.adimanager.enumerations.UF;
import com.adisolucoes.adimanager.exceptions.ErroBancoDadosException;
import com.adisolucoes.adimanager.exceptions.ErroEmpresaDuplicadaException;
import com.adisolucoes.adimanager.filtros.EmpresaFiltro;
import com.adisolucoes.adimanager.model.Cliente;
import com.adisolucoes.adimanager.model.Empresa;
import com.adisolucoes.adimanager.model.Endereco;
import com.adisolucoes.adimanager.model.LazyBean;
import com.adisolucoes.adimanager.util.crud.CrudUtils;
import com.adisolucoes.adimanager.util.jsf.FacesUtils;
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

    private static final Logger LOG = Logger.getLogger(EmpresaBean.class.getName());

    @Inject
    private EmpresaDAO empresaDAO;

    @Inject
    private ClienteDAO clienteDAO;

    private Empresa empresa;
    private Cliente cliente;
    private long codigo;
    private Empresa empresaSelecionada;
    private Endereco endereco;
    private final CrudUtils crudUtils;
    private EmpresaFiltro empresaFiltro;
    private List<Cliente> clientes;
    private LazyBean<Empresa> modelo;

    public EmpresaBean() {
        clientes = new ArrayList<Cliente>();
        empresa = new Empresa();
        crudUtils = new CrudUtils();
    }

    @PostConstruct
    public void inicializar() {
        cliente = new Cliente();
        endereco = new Endereco();
        empresa = new Empresa();
        empresa.setEndereco(new Endereco());
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

    public void salvar() throws ErroEmpresaDuplicadaException {
        try {
            if (empresa != null) {
                Empresa empresaExistente = empresaDAO.buscarPorCnpj(empresa.getCnpj());
                if (empresa.getId() == 0) {
                    if (empresaExistente != null) {
                        throw new ErroEmpresaDuplicadaException();
                    }
                    empresaDAO.salvar(empresa);
                    FacesUtils.showFacesMessage("Empresa salva com sucesso", 2);
                    limparForm();
                } else {
                    if (empresaExistente != null && empresaExistente.getId() != empresa.getId()) {
                        throw new ErroEmpresaDuplicadaException();
                    }
                    empresaDAO.atualizar(empresa);
                    FacesUtils.showFacesMessage("Empresa atualizada com sucesso", 2);
                }
            }
        } catch (ErroBancoDadosException ex) {
            LOG.log(Level.SEVERE, null, ex);
        } catch (ErroEmpresaDuplicadaException ex) {
            FacesUtils.showFacesMessage("Já existe uma empresa com o CNPJ informado", 1);
            LOG.log(Level.SEVERE, null, ex);
        }
    }

    public void preencherDadosPorCep() {
        crudUtils.preencherDadosPorCep(empresa.getEndereco());
    }

    public void excluirEmpresa() {
        try {
            if (empresaSelecionada != null) {
                empresaDAO.excluir(empresaSelecionada.getId());
                modelo = null;
                FacesUtils.showFacesMessage("Empresa excluida com sucesso!", 2);
            }
        } catch (ErroBancoDadosException ex) {
            LOG.log(Level.SEVERE, null, ex);
            FacesUtils.showFacesMessage("Error na conexão com o banco de dados!", 1);
        }
    }

    public void buscarEmpresa() {
        try {
            codigo = codigo / 483957299;
            empresa = empresaDAO.buscarPorId(codigo);
            System.out.println("MEU CNPJ: " + empresa.getCnpj());
        } catch (ErroBancoDadosException ex) {
            LOG.log(Level.SEVERE, "Erro ao recuperar a empresa", ex);
        }
    }

    private void limparForm() {
        empresa = new Empresa();
        empresa.setEndereco(new Endereco());
    }

    public UF[] getEstados() {
        return UF.values();
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

    public Empresa getEmpresaSelecionada() {
        return empresaSelecionada;
    }

    public void setEmpresaSelecionada(Empresa empresaSelecionada) {
        this.empresaSelecionada = empresaSelecionada;
    }

    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }
}

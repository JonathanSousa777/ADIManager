package com.adisolucoes.adimanager.controller;

import com.adisolucoes.adimanager.dao.ClienteDAO;
import com.adisolucoes.adimanager.dao.PessoaDAO;
import com.adisolucoes.adimanager.dao.ProjetoDAO;
import com.adisolucoes.adimanager.enumerations.Sexo;
import com.adisolucoes.adimanager.enumerations.UF;
import com.adisolucoes.adimanager.exceptions.ErroBancoDadosException;
import com.adisolucoes.adimanager.exceptions.ErroPessoaDuplicadoException;
import com.adisolucoes.adimanager.filtros.ClienteFiltro;
import com.adisolucoes.adimanager.model.Cliente;
import com.adisolucoes.adimanager.model.Endereco;
import com.adisolucoes.adimanager.model.LazyBean;
import com.adisolucoes.adimanager.model.Pessoa;
import com.adisolucoes.adimanager.model.Projeto;
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
public class ClienteBean implements Serializable {

    private static final Logger LOG = Logger.getLogger(ClienteBean.class.getName());

    @Inject
    private ClienteDAO clienteDAO;

    @Inject
    private ProjetoDAO projetoDAO;

    @Inject
    private PessoaDAO pessoaDAO;

    private Cliente cliente;
    private ClienteFiltro clienteFiltro;
    private boolean buscaAvancada;
    private final CrudUtils enderecoUtils;
    private List<Projeto> projetos;
    private LazyBean<Cliente> modelo;

    public ClienteBean() {
        projetos = new ArrayList<Projeto>();
        clienteFiltro = new ClienteFiltro();
        enderecoUtils = new CrudUtils();
        limparForm();
    }

    @PostConstruct
    public void inicializar() {
        try {
            projetos = projetoDAO.listarTodos();
        } catch (ErroBancoDadosException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
    }

    public void limparForm() {
        Pessoa pessoa = new Pessoa();
        pessoa.setEndereco(new Endereco());
        cliente = new Cliente();
        cliente.setPessoa(pessoa);
    }

    public void pesquisarLazy() {
        modelo = new LazyBean<Cliente>(clienteDAO, clienteFiltro);
    }

    public void salvar() {
        try {
            if (cliente != null) {
                clienteDAO.salvar(cliente);
                FacesUtils.showFacesMessage("Cliente salvo com sucesso!", 2);
                limparForm();
            }
        } catch (ErroBancoDadosException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
    }

    public void verificarCpfCnpj() throws ErroPessoaDuplicadoException {
        Pessoa pessoa = null;
        try {
            if (cliente.getPessoa() != null && !cliente.getPessoa().getCpfCnpj().equals("")) {
                pessoa = pessoaDAO.buscarPorCpfCnpj(cliente.getPessoa().getCpfCnpj());
            }
            if (pessoa != null) {
                FacesUtils.showFacesMessage("Já existe uma Pessoa com esse CPF/CNPJ", 1);
                throw new ErroPessoaDuplicadoException();
            } else {
                LOG.info("CPF/CNPJ livre para cadastro!");
            }
        } catch (ErroBancoDadosException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
    }

    public void preencherDadosPorCep() {
        enderecoUtils.preencherDadosPorCep(cliente.getPessoa().getEndereco());
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

    public Sexo[] getSexos() {
        return Sexo.values();
    }

    public UF[] getEstados() {
        return UF.values();
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

}

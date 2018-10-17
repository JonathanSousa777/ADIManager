package com.adisolucoes.adimanager.controller;

import com.adisolucoes.adimanager.dao.ClienteDAO;
import com.adisolucoes.adimanager.dao.IndicacaoDAO;
import com.adisolucoes.adimanager.exceptions.ErroBancoDadosException;
import com.adisolucoes.adimanager.filtros.IndicacaoFiltro;
import com.adisolucoes.adimanager.model.Cliente;
import com.adisolucoes.adimanager.model.Indicacao;
import com.adisolucoes.adimanager.model.LazyBean;
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
public class IndicacaoBean implements Serializable {

    private static final Logger LOG = Logger.getLogger(Indicacao.class.getName());

    @Inject
    private IndicacaoDAO indicacaoDAO;

    @Inject
    private ClienteDAO clienteDAO;

    private int codigo;
    private boolean buscaAvancada;
    private Indicacao indicacao;
    private Indicacao indicacaoSelecionada;
    private IndicacaoFiltro indicacaoFiltro;
    private List<Indicacao> indicacoes;
    private LazyBean<Indicacao> modelo;
    private List<Cliente> clientesAtivos;
    private List<Cliente> clientesPassivos;

    public IndicacaoBean() {
        limparForm();
    }

    @PostConstruct
    public void inicializar() {
        carregarClientes();
    }

    public void limparForm() {
        indicacao = new Indicacao();
        indicacaoFiltro = new IndicacaoFiltro();
        buscaAvancada = false;
        clientesAtivos = new ArrayList<>();
        clientesPassivos = new ArrayList<>();
    }

    public void carregarClientes() {
        try {
            clientesAtivos = clienteDAO.listarTodos();
            clientesPassivos = clientesAtivos;
        } catch (ErroBancoDadosException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
    }

    public void salvar() {
        try {
            if (indicacao != null) {
                if (indicacao.getId() == 0) {
                    indicacaoDAO.salvar(indicacao);
                    limparForm();
                    FacesUtils.showFacesMessage("Indicação salva com sucesso!", 2);
                } else {
                    indicacaoDAO.atualizar(indicacao);
                    FacesUtils.showFacesMessage("Indicação atualizada com sucesso!", 2);
                }
            }
        } catch (ErroBancoDadosException ex) {
            FacesUtils.showFacesMessage("Houve um erro no banco de dados, consulte o suporte!", 1);
            LOG.log(Level.SEVERE, null, ex);
        }
    }

    public void excluirIndicacao() {
        try {
            if (indicacaoSelecionada != null) {
                indicacaoDAO.excluir(indicacaoSelecionada.getId());
                modelo = null;
                FacesUtils.showFacesMessage("Indicação excluída com sucesso!", 2);
            }
        } catch (ErroBancoDadosException ex) {
            FacesUtils.showFacesMessage("Erro no banco de dados, contate o suporte!", 1);
            LOG.log(Level.SEVERE, null, ex);
        }
    }

    public void pesquisarLazy() {
        modelo = new LazyBean<Indicacao>(indicacaoDAO, indicacaoFiltro);
    }

    public void buscarIndicacao() {
        try {
            codigo = codigo / 483957299;
            indicacao = indicacaoDAO.buscarPorId(new Long(codigo));
        } catch (ErroBancoDadosException ex) {
            LOG.log(Level.SEVERE, null, ex);
            FacesUtils.showFacesMessage("Erro ao recuperar indicação!", 1);
        }
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

    public Indicacao getIndicacao() {
        return indicacao;
    }

    public void setIndicacao(Indicacao indicacao) {
        this.indicacao = indicacao;
    }

    public Indicacao getIndicacaoSelecionada() {
        return indicacaoSelecionada;
    }

    public void setIndicacaoSelecionada(Indicacao indicacaoSelecionada) {
        this.indicacaoSelecionada = indicacaoSelecionada;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        //Se negativo passa o valor  para positivo
        if (codigo < 0) {
            codigo = codigo * (-1);
        }
        this.codigo = codigo;
    }

}

package com.adisolucoes.adimanager.controller;

import com.adisolucoes.adimanager.dao.LazyDAO;
import com.adisolucoes.adimanager.dao.NotificacaoDAO;
import com.adisolucoes.adimanager.enumerations.TipoPrioridadeNotificacao;
import com.adisolucoes.adimanager.exceptions.ErroBancoDadosException;
import com.adisolucoes.adimanager.filtros.NotificacaoFiltro;
import com.adisolucoes.adimanager.model.LazyBean;
import com.adisolucoes.adimanager.model.Notificacao;
import com.adisolucoes.adimanager.util.jsf.FacesUtils;
import java.io.Serializable;
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
public class NotificacaoBean implements Serializable {
    
    private static final Logger LOG = Logger.getLogger(NotificacaoBean.class.getName());
    
    @Inject
    private NotificacaoDAO notificacaoDAO;
    
    private Notificacao notificacao;
    private Notificacao notificacao1Selecionada;
    private NotificacaoFiltro notificacaoFiltro;
    private LazyBean<Notificacao> modelo;
    
    public NotificacaoBean(){
        notificacao = new Notificacao();
    }
    
    @PostConstruct
    public void inicializar(){
        notificacaoFiltro = new NotificacaoFiltro();
    }
    
    public void pesquisarLazy(){
        modelo = new LazyBean<Notificacao>(notificacaoDAO, notificacaoFiltro);
    }
    
    public void salvar(){
        try {
            if (notificacao != null) {
                if (notificacao.getId() == 0) {
                   notificacao.setResponsavel(FacesUtils.getUsuarioLogado().getLogin());
                   notificacaoDAO.salvar(notificacao);
                   FacesUtils.showFacesMessage("Notificação salva com sucesso!", 2);
                   limparForm();
                }
            }
        } catch (ErroBancoDadosException ex) {
           LOG.log(Level.SEVERE, null, ex);
        }
    }
    
    public void excluirNotificacao(){
        try{
            if(this.notificacao1Selecionada != null){
                notificacaoDAO.excluir(notificacao1Selecionada.getId());
                modelo = null;
                FacesUtils.showFacesMessage("Notificação excluida com sucesso!", 2);
            }
        } catch(ErroBancoDadosException ex){
            LOG.log(Level.SEVERE, null, ex);
            FacesUtils.showFacesMessage("Error na conexao do banco", 1);
        }
    }
    
    public void limparForm(){
        notificacao = new Notificacao();
    }
    
    public TipoPrioridadeNotificacao[] getPrioridades(){
        return TipoPrioridadeNotificacao.values();
    }

    public Notificacao getNotificacao() {
        return notificacao;
    }

    public void setNotificacao(Notificacao notificacao) {
        this.notificacao = notificacao;
    }

    public LazyBean<Notificacao> getModelo() {
        if(modelo == null){
            pesquisarLazy();
        }
        return modelo;
    }

    public void setModelo(LazyBean<Notificacao> modelo) {
        this.modelo = modelo;
    }

    public NotificacaoFiltro getNotificacaoFiltro() {
        return notificacaoFiltro;
    }

    public void setNotificacaoFiltro(NotificacaoFiltro notificacaoFiltro) {
        this.notificacaoFiltro = notificacaoFiltro;
    }

    public Notificacao getNotificacao1Selecionada() {
        return notificacao1Selecionada;
    }

    public void setNotificacao1Selecionada(Notificacao notificacao1Selecionada) {
        this.notificacao1Selecionada = notificacao1Selecionada;
    }
    
}

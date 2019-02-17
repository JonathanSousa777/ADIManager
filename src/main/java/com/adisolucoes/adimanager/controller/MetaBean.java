package com.adisolucoes.adimanager.controller;

import com.adisolucoes.adimanager.dao.MetaDAO;
import com.adisolucoes.adimanager.enumerations.TipoPrioridadeMeta;
import com.adisolucoes.adimanager.exceptions.ErroBancoDadosException;
import com.adisolucoes.adimanager.filtros.MetaFiltro;
import com.adisolucoes.adimanager.model.LazyBean;
import com.adisolucoes.adimanager.model.Meta;
import com.adisolucoes.adimanager.util.jsf.FacesUtils;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author ADI Soluções
 */
@Named
@ViewScoped
public class MetaBean implements Serializable {

    private static final Logger LOG = Logger.getLogger(Meta.class.getName());

    @Inject
    private MetaDAO metaDAO;

    private int codigo;
    private Meta meta;
    private Meta metaSelecionada;
    private MetaFiltro metaFiltro;
    private boolean buscaAvancada;
    private List<Meta> metas;
    private LazyBean<Meta> modelo;

    public MetaBean() {
        limparForm();
    }

    public void pesquisarLazy() {
        modelo = new LazyBean<Meta>(metaDAO, metaFiltro);
    }

    public void limparForm() {
        meta = new Meta();
        metaFiltro = new MetaFiltro();
    }

    public void salvar() {
        try {
            if (meta != null) {
                if (meta.getId() == 0) {
                    metaDAO.salvar(meta);
                    limparForm();
                    FacesUtils.showFacesMessage("Meta salva com sucesso!", 2);
                } else {
                    metaDAO.atualizar(meta);
                    FacesUtils.showFacesMessage("Meta atualizada com sucesso!", 2);
                }
            }
        } catch (ErroBancoDadosException ex) {
            FacesUtils.showFacesMessage("Houve um erro no banco de dados, consulte o suporte!", 1);
            LOG.log(Level.SEVERE, null, ex);
        }
    }

    public void excluirMeta() {
        try {
            if (metaSelecionada != null) {
                if (metaSelecionada.isConcluida()) {
                    FacesUtils.showFacesMessage("Erro, metas concluídas não podem ser apagadas!", 1);
                } else {
                    metaDAO.excluir(metaSelecionada.getId());
                    modelo = null;
                    FacesUtils.showFacesMessage("Meta excluída com sucesso!", 2);
                }
            }
        } catch (ErroBancoDadosException ex) {
            FacesUtils.showFacesMessage("Erro no banco de dados, contate o suporte!", 1);
            LOG.log(Level.SEVERE, null, ex);
        }
    }

    public void buscarMeta() {
        try {
            codigo = codigo / 483957299;
            meta = metaDAO.buscarPorId(new Long(codigo));
        } catch (ErroBancoDadosException ex) {
            LOG.log(Level.SEVERE, null, ex);
            FacesUtils.showFacesMessage("Erro ao recuperar meta!", 1);
        }
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public MetaFiltro getMetaFiltro() {
        return metaFiltro;
    }

    public void setMetaFiltro(MetaFiltro metaFiltro) {
        this.metaFiltro = metaFiltro;
    }

    public List<Meta> getMetas() {
        return metas;
    }

    public void setMetas(List<Meta> metas) {
        this.metas = metas;
    }

    public TipoPrioridadeMeta[] getPrioridadesMeta() {
        return TipoPrioridadeMeta.values();
    }

    public boolean isBuscaAvancada() {
        return buscaAvancada;
    }

    public void setBuscaAvancada(boolean buscaAvancada) {
        this.buscaAvancada = buscaAvancada;
    }

    public LazyBean<Meta> getModelo() {
        if (modelo == null) {
            pesquisarLazy();
        }
        return modelo;
    }

    public void setModelo(LazyBean<Meta> modelo) {
        this.modelo = modelo;
    }

    public Meta getMetaSelecionada() {
        return metaSelecionada;
    }

    public void setMetaSelecionada(Meta metaSelecionada) {
        this.metaSelecionada = metaSelecionada;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

}

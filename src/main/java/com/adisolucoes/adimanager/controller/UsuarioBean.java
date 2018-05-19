package com.adisolucoes.adimanager.controller;

import com.adisolucoes.adimanager.dao.UsuarioDAO;
import com.adisolucoes.adimanager.enumerations.TipoUsuario;
import com.adisolucoes.adimanager.exceptions.ErroBancoDadosException;
import com.adisolucoes.adimanager.model.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Junior Sy
 */
@Named
@ViewScoped
public class UsuarioBean implements Serializable {

    private static final Logger LOG = Logger.getLogger(UsuarioBean.class.getName());

    @Inject
    private UsuarioDAO usuarioDAO;
    @Inject
    private Usuario usuario;

    private String nome;
    private TipoUsuario tipoUsuario;

    private List<Usuario> usuarioFiltrados;

    public List<Usuario> pesquisar() {
        try {
            usuarioFiltrados = new ArrayList<>();
            usuarioFiltrados = usuarioDAO.buscarPorNomeTipoUsuario(nome, tipoUsuario);
            nome = "";
            tipoUsuario = null;
        } catch (ErroBancoDadosException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
        return usuarioFiltrados;
    }

    public void preencherTabela() {
        try {
            usuarioFiltrados = usuarioDAO.listarTodos();
        } catch (ErroBancoDadosException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
    }

    public TipoUsuario[] getTiposUsuario() {
        return TipoUsuario.values();
    }

    public List<Usuario> getUsuarioFiltrados() {
        if (usuarioFiltrados == null) {
            preencherTabela();
        }
        return usuarioFiltrados;
    }

    public void setUsuarioFiltrados(List<Usuario> usuarioFiltrados) {
        this.usuarioFiltrados = usuarioFiltrados;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

}

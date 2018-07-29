package com.adisolucoes.adimanager.controller;

import com.adisolucoes.adimanager.dao.UsuarioDAO;
import com.adisolucoes.adimanager.enumerations.Sexo;
import com.adisolucoes.adimanager.enumerations.TipoUsuario;
import com.adisolucoes.adimanager.enumerations.UF;
import com.adisolucoes.adimanager.exceptions.ErroBancoDadosException;
import com.adisolucoes.adimanager.exceptions.ErroLoginDuplicadoException;
import com.adisolucoes.adimanager.model.Endereco;
import com.adisolucoes.adimanager.model.Pessoa;
import com.adisolucoes.adimanager.model.Usuario;
import com.adisolucoes.adimanager.util.jsf.FacesUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
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

    private Pessoa pessoa;
    private Endereco endereco;
    private String nome;
    private String senha;
    private TipoUsuario tipoUsuario;

    private List<Usuario> usuarioFiltrados;

    @PostConstruct
    public void inicializar() {
        pessoa = new Pessoa();
        endereco = new Endereco();
        pessoa.setEndereco(endereco);
        usuario = new Usuario();
        usuario.setAtivo(true);
        usuario.setPessoa(pessoa);
    }

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

    public void salvar() {
        try {
            usuario.setSenha(FacesUtils.md5(senha));
            usuario.setDataUltimoAcesso(new Date());
            usuarioDAO.verificarLoginDuplicado(usuario.getLogin());
            usuarioDAO.salvar(usuario);
            FacesUtils.showFacesMessage("Usuário salvo com sucesso!", 2);
            limparForm();
        } catch (ErroBancoDadosException ex) {
            LOG.log(Level.SEVERE, null, ex);
            FacesUtils.showFacesMessage("Ocorreu um erro no banco de dados, contate o suporte!", 1);
        } catch (ErroLoginDuplicadoException ex) {
            LOG.log(Level.SEVERE, null, ex);
            FacesUtils.showFacesMessage("Já existe um usuário com esse Login!", 1);
        } 
    }

    public void preencherTabela() {
        try {
            usuarioFiltrados = usuarioDAO.listarTodos();
        } catch (ErroBancoDadosException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
    }

    public void limparForm() {
        pessoa = new Pessoa();
        endereco = new Endereco();
        pessoa.setEndereco(endereco);
        usuario = new Usuario();
        usuario.setAtivo(true);
        usuario.setPessoa(pessoa);
    }

    public TipoUsuario[] getTiposUsuario() {
        return TipoUsuario.values();
    }

    public UF[] getEstados() {
        return UF.values();
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

    public Sexo[] getSexos() {
        return Sexo.values();
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

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}

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
import com.adisolucoes.adimanager.util.crud.CrudUtils;
import com.adisolucoes.adimanager.util.jsf.FacesUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
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
    private Usuario usuarioLogado;
    private long codigo;
    private CrudUtils enderecoUtils;
    private List<Usuario> usuarioFiltrados;

    @PostConstruct
    public void inicializar() {
        pessoa = new Pessoa();
        endereco = new Endereco();
        pessoa.setEndereco(endereco);
        usuario = new Usuario();
        usuario.setAtivo(true);
        usuario.setPessoa(pessoa);
        usuarioLogado = FacesUtils.getUsuarioLogado();
        enderecoUtils = new CrudUtils();
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
            if (usuario != null) {
                usuario.setDataUltimoAcesso(new Date());
                Usuario usuarioExistente = usuarioDAO.buscarPorLogin(usuario.getLogin());
                if (usuario.getId() == null) {
                    if (usuarioExistente != null) {
                        throw new ErroLoginDuplicadoException();
                    }
                    usuario.setSenha(FacesUtils.md5(senha));
                    usuarioDAO.salvar(usuario);
                    FacesUtils.showFacesMessage("Usuário salvo com sucesso!", 2);
                    limparForm();
                } else {
                    if (usuarioExistente != null && !Objects.equals(usuarioExistente.getId(), usuario.getId())) {
                        throw new ErroLoginDuplicadoException();
                    }
                    if (!senha.equals("")) {
                        usuario.setSenha(FacesUtils.md5(senha));
                    }
                    usuarioDAO.atualizar(usuario);
                    FacesUtils.showFacesMessage("Usuário atualizado com sucesso!", 2);
                }
            }
        } catch (ErroBancoDadosException ex) {
            LOG.log(Level.SEVERE, null, ex);
            FacesUtils.showFacesMessage("Ocorreu um erro no banco de dados, contate o suporte!", 1);
        } catch (ErroLoginDuplicadoException ex) {
            LOG.log(Level.SEVERE, null, ex);
            FacesUtils.showFacesMessage("Já existe um usuário com esse Login!", 1);
        }
    }

    public void buscarUsuario() {
        try {
            codigo = codigo / 483957299;
            usuario = usuarioDAO.buscarPorId(codigo);
        } catch (ErroBancoDadosException ex) {
            LOG.log(Level.SEVERE, null, ex);
            FacesUtils.showFacesMessage("Erro ao recuperar usuário!", 1);
        }
    }

    public void preencherTabela() {
        try {
            usuarioFiltrados = usuarioDAO.listarTodos();
        } catch (ErroBancoDadosException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
    }

    public void preencherDadosPorCep() {
        enderecoUtils.preencherDadosPorCep(usuario.getPessoa().getEndereco());
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

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }
}

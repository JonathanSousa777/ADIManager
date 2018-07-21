package com.adisolucoes.adimanager.controller;

import com.adisolucoes.adimanager.dao.LinkDAO;
import com.adisolucoes.adimanager.dao.UsuarioDAO;
import com.adisolucoes.adimanager.exceptions.ErroBancoDadosException;
import com.adisolucoes.adimanager.model.Usuario;
import com.adisolucoes.adimanager.util.jsf.FacesUtils;
import java.io.Serializable;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.NoResultException;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ADI Soluções
 */
@Named
@SessionScoped
public class LoginBean implements Serializable {

    private static final Logger LOG = Logger.getLogger(LoginBean.class.getName());

    @Inject
    private UsuarioDAO usuarioDAO;
    @Inject
    private LinkDAO linkDAO;
    private String login;
    private String senha;

    public String efetuarLogin() {
        String retorno = null;
        try {
            senha = FacesUtils.md5(senha);
            Usuario usuarioTemporario = usuarioDAO.buscarPorLoginSenha(login, senha);
            if (usuarioTemporario != null) {
                if (usuarioTemporario.isAtivo()) {
                    usuarioTemporario.setDataUltimoAcesso(new Date());
                    usuarioDAO.atualizar(usuarioTemporario);
                    FacesUtils.setUsuarioLogado(usuarioTemporario);
                    retorno = "pretty:dashboard";
                } else {
                    FacesUtils.showFacesMessage("Usuário encontra-se bloqueado", 3);
                }
            } else {
                FacesUtils.showFacesMessage("Usuário e/ou senha inválidos", 1);
            }
        } catch (ErroBancoDadosException ex) {
            LOG.log(Level.SEVERE, null, ex);
        } catch (NoResultException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
    
    public String efetuarLogoff() {
        FacesUtils.removerUsuario();
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        session.invalidate();
        return "pretty:login";
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}

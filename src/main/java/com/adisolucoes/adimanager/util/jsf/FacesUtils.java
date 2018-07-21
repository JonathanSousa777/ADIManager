package com.adisolucoes.adimanager.util.jsf;

import com.adisolucoes.adimanager.model.Usuario;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.omnifaces.util.Faces;

/**
 *
 * @author Jonathan Sousa
 */
public class FacesUtils {

    private static final Logger LOG = Logger.getLogger(FacesUtils.class.getName());

    public static void showFacesMessage(String texto, int tipo) {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        /*Manter a mensagem após o redirect*/
        ec.getFlash().setKeepMessages(true);
        switch (tipo) {
            case 1:
                fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, texto, "Erro"));
                break;
            case 2:
                fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, texto, "Informação"));
                break;
            case 3:
                fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, texto, "Aviso"));
                break;
            case 4:
                fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, texto, "Fatal"));
                break;
            default:
                break;
        }
    }

    public static String md5(String senha) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
        BigInteger hash = new BigInteger(1, md.digest(senha.getBytes()));
        String mSenha = hash.toString(16);
        return mSenha;
    }

    public synchronized static void setUsuarioLogado(Usuario usuario) {
        Faces.setSessionAttribute("usuario", usuario);
    }

    public synchronized static Usuario getUsuarioLogado() {
        return (Usuario) Faces.getSessionAttribute("usuario");
    }

    public synchronized static boolean existeUsuarioLogado() {
        return getUsuarioLogado() != null;
    }

    public synchronized static void removerUsuario() {
        removerSessionAttribute("usuario");
    }

    public synchronized static void removerSessionAttribute(String attributeName) {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession httpSession = (HttpSession) externalContext.getSession(false);
        httpSession.removeAttribute(attributeName);
    }
}

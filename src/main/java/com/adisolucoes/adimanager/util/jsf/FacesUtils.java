package com.adisolucoes.adimanager.util.jsf;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author Jonathan Sousa
 */
public class FacesUtils {

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
}

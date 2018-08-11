package com.adisolucoes.adimanager.listener;

import com.adisolucoes.adimanager.enumerations.TipoUsuario;
import com.adisolucoes.adimanager.model.Usuario;
import com.adisolucoes.adimanager.util.jsf.FacesUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import javax.faces.application.NavigationHandler;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

/**
 *
 * @author ADI Soluções
 */
public class LoginPhaseListener implements PhaseListener {

    private static final Logger LOG = Logger.getLogger(LoginPhaseListener.class.getName());
    private ExternalContext externalContext;

    @Override
    public void afterPhase(PhaseEvent event) {
        externalContext = event.getFacesContext().getExternalContext();
        List<String> paginasPermitidasLogado = new ArrayList(Arrays.asList("dashboard"));
        List<String> paginasPermitidas = new ArrayList(Arrays.asList("login","error_403"));
        FacesContext context = event.getFacesContext();
        String viewId = context.getViewRoot().getViewId();
        String pagina = viewId.substring(viewId.lastIndexOf("/") + 1, viewId.indexOf("."));
        NavigationHandler handler = context.getApplication().getNavigationHandler();
        boolean permitidoLogado = paginasPermitidasLogado.contains(pagina);
        boolean permitido = paginasPermitidas.contains(pagina);
        if (!permitido) {
            if (!loggedIn()) {
                handler.handleNavigation(context, null, "/login");
            }
            if (!permitidoLogado && loggedIn()) {
                if (!hasAcess(viewId)) {
                    handler.handleNavigation(context, null, "/error_403");
                }
            }
        }
    }

    private synchronized boolean loggedIn() {
        return FacesUtils.existeUsuarioLogado();
    }

    public boolean hasAcess(String viewId) {
        boolean retorno = false;
        Usuario usuarioLogado = FacesUtils.getUsuarioLogado();
        if (usuarioLogado != null) {
            TipoUsuario tipoUsuario = usuarioLogado.getTipoUsuario();
            for (String pagina : tipoUsuario.getPaginas()) {
                if (viewId.equals("/" + pagina)) {
                    retorno = true;
                    break;
                }
            }
        }
        return retorno;
    }

    @Override
    public void beforePhase(PhaseEvent event) {

    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }

}

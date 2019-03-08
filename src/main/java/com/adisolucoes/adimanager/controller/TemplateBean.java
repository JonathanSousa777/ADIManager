package com.adisolucoes.adimanager.controller;

import com.adisolucoes.adimanager.model.Usuario;
import com.adisolucoes.adimanager.util.jsf.FacesUtils;
import java.io.ByteArrayInputStream;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author ADI Soluções
 */
@Named
@RequestScoped
public class TemplateBean implements Serializable {

    private byte[] bytesImagem;
    private StreamedContent imagemUsuario;
    private Usuario usuarioLogado;

    public TemplateBean() {
        usuarioLogado = FacesUtils.getUsuarioLogado();
    }

    @PostConstruct
    public void inicializar() {
        carregarImagemUsuario();
    }

    public void carregarImagemUsuario() {
        if (usuarioLogado != null) {
            bytesImagem = usuarioLogado.getPessoa().getImagem();
            if (bytesImagem != null) {
                imagemUsuario = new DefaultStreamedContent(new ByteArrayInputStream(bytesImagem));
            } else {
                imagemUsuario = null;
            }
        }
    }

    public byte[] getBytesImagem() {
        return bytesImagem;
    }

    public void setBytesImagem(byte[] bytesImagem) {
        this.bytesImagem = bytesImagem;
    }

    public StreamedContent getImagemUsuario() {
        return imagemUsuario;
    }

    public void setImagemUsuario(StreamedContent imagemUsuario) {
        this.imagemUsuario = imagemUsuario;
    }

}

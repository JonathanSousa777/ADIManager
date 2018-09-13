package com.adisolucoes.adimanager.controller;

import com.adisolucoes.adimanager.model.Pessoa;
import com.adisolucoes.adimanager.util.jsf.ArquivoUtils;
import java.io.Serializable;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.apache.commons.lang3.RandomStringUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author ADI Soluções
 */
@Named
@ViewScoped
public class FotoBean implements Serializable {

    private final ArquivoUtils arquivoUtils;
    private Pessoa usuario;
    private String imagemTemporaria;

    public FotoBean() {
        this.arquivoUtils = new ArquivoUtils();
        imagemTemporaria = null;
    }

    public void limparFotos() {
        imagemTemporaria = null;
    }

    public void alterarImagem(FileUploadEvent event) {
        String aleatorio = RandomStringUtils.randomAlphabetic(100);
        imagemTemporaria = "/tmp/" + aleatorio + ".png";
        UploadedFile uploadedFile = event.getFile();
        byte[] img = uploadedFile.getContents();
        arquivoUtils.criaArquivo(img, imagemTemporaria);
    }

    public void salvarFoto() {

    }

    public Pessoa getUsuario() {
        return usuario;
    }

    public void setUsuario(Pessoa usuario) {
        this.usuario = usuario;
    }

    public String getImagemTemporaria() {
        System.out.println(imagemTemporaria);
        return imagemTemporaria;
    }

    public void setImagemTemporaria(String imagemTemporaria) {
        this.imagemTemporaria = imagemTemporaria;
    }

}

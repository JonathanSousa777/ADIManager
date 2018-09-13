package com.adisolucoes.adimanager.util.jsf;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author ADI Soluções
 */
public class ArquivoUtils {

    private static final Logger LOG = Logger.getLogger(ArquivoUtils.class.getName());
    private String raiz = "E:\\SI\\Netbeans\\Git\\ADIManager\\target\\ADIManager-1.0";
    //  private String raiz = "//Users//adisolucoes//NetBeansProjects//ADIManager-1.0//target//ADIManager-1.0";

    public void criaArquivo(byte[] bytes, String arquivo) {
        if (bytes != null) {
            File dir = new File(raiz + "\\tmp");
            File dirAvatar = new File(raiz + "\\avatares");
            if (!dir.exists()) {
                dir.mkdir();
            }
            if (!dirAvatar.exists()) {
                dirAvatar.mkdir();
            }
            try {
                FileUtils.writeByteArrayToFile(new File(raiz + arquivo), bytes);
            } catch (FileNotFoundException ex) {
                LOG.log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
    }
}

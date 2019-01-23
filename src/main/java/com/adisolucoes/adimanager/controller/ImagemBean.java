package com.adisolucoes.adimanager.controller;

import com.adisolucoes.adimanager.dao.EmpresaDAO;
import com.adisolucoes.adimanager.dao.PessoaDAO;
import com.adisolucoes.adimanager.exceptions.ErroBancoDadosException;
import com.adisolucoes.adimanager.exceptions.ErroNenhumaEmpresaEncontrada;
import com.adisolucoes.adimanager.exceptions.ErroNenhumaPessoaEncontrada;
import com.adisolucoes.adimanager.model.Empresa;
import com.adisolucoes.adimanager.model.Pessoa;
import com.adisolucoes.adimanager.util.jsf.FacesUtils;
import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author ADI Soluções
 */
@Named
@SessionScoped
public class ImagemBean implements Serializable {

    private static final Logger LOG = Logger.getLogger(ImagemBean.class.getName());

    @Inject
    private PessoaDAO pessoaDAO;

    @Inject
    private EmpresaDAO empresaDAO;

    private Pessoa pessoa;
    private Empresa empresa;
    private StreamedContent imagem;
    private long codigoPessoa;
    private long codigoEmpresa;
    private byte[] bytesImagem;

    public ImagemBean() {
    }

    public void carregarImagem() {
        //Verifica se a edição é de Pessoa ou Empresa
        if (pessoa != null || empresa != null) {
            if (pessoa != null) {
                //Se edição for de pessoa carrega a imagem da pessoa
                bytesImagem = pessoa.getImagem();
                if (bytesImagem != null) {
                    imagem = new DefaultStreamedContent(new ByteArrayInputStream(bytesImagem));
                } else {
                    imagem = null;
                }
            } else {
                //Caso não seja edição de pessoa carregará a imagem da empresa
                bytesImagem = empresa.getLogomarca();
                if (bytesImagem != null) {
                    imagem = new DefaultStreamedContent(new ByteArrayInputStream(bytesImagem));
                } else {
                    imagem = null;
                }
            }
        }
    }

    public void alterarImagem(FileUploadEvent event) throws ErroBancoDadosException {
        UploadedFile uploadedFile = event.getFile();
        byte[] conteudo = uploadedFile.getContents();
        imagem = new DefaultStreamedContent(new ByteArrayInputStream(conteudo));
        atualizarImagem(conteudo);
    }

    public void atualizarImagem(byte[] conteudo) {
        try {
            if (pessoa != null) {
                pessoa.setImagem(conteudo);
                pessoaDAO.atualizar(pessoa);
            } else if (empresa != null) {
                empresa.setLogomarca(conteudo);
                empresaDAO.atualizar(empresa);
            } else {
                bytesImagem = conteudo;
            }
        } catch (ErroBancoDadosException ex) {
            FacesUtils.showFacesMessage("Erro ao atualizar a imagem", 1);
            LOG.log(Level.SEVERE, null, ex);
        }
    }

    public void removerImagem() {
        atualizarImagem(null);
    }

    private void carregarPessoa() {
        try {
            codigoPessoa = codigoPessoa / 483957299;
            pessoa = pessoaDAO.buscarPorId(codigoPessoa);
            empresa = null;
        } catch (ErroBancoDadosException ex) {
            FacesUtils.showFacesMessage("Erro ao carregar a imagem", 1);
            LOG.info("Erro ao carregar pessoa pelo código (Imagem Bean)");
            LOG.log(Level.SEVERE, null, ex);
        }
    }

    private void carregarEmpresa() {
        try {
            codigoEmpresa = codigoEmpresa / 483957299;
            empresa = empresaDAO.buscarPorId(codigoEmpresa);
            pessoa = null;
        } catch (ErroBancoDadosException ex) {
            FacesUtils.showFacesMessage("Erro ao carregar a imagem", 1);
            LOG.info("Erro ao carregar empresa pelo código (Imagem Bean)");
            LOG.log(Level.SEVERE, null, ex);
        }
    }

    //Métodos utilizados nos cadastros
    public void prepararCadastro() {
        this.pessoa = null;
        this.empresa = null;
        this.imagem = null;
        this.bytesImagem = null;
        this.codigoEmpresa = 0;
        this.codigoPessoa = 0;
        carregarImagem();
    }

    public void salvarImagemPessoa() {
        try {
            //Assim que uma Pessoa é cadastrada, este método é assionado
            if (cadastrandoPessoa()) {
                Pessoa pessoaCadastrada = pessoaDAO.buscarUltimaPessoaCadastrada();
                pessoaCadastrada.setImagem(bytesImagem);
                pessoaDAO.atualizar(pessoaCadastrada);
            }
        } catch (ErroNenhumaPessoaEncontrada | ErroBancoDadosException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
    }

    public void salvarImagemEmpresa() {
        //Assim que uma Empresa é cadastrada, este método é assionado
        try {
            if (cadastrandoEmpresa()) {
                Empresa empresaCadastrada = empresaDAO.buscarUltimaEmpresaCadastrada();
                empresaCadastrada.setLogomarca(bytesImagem);
                empresaDAO.atualizar(empresaCadastrada);
            }
        } catch (ErroBancoDadosException | ErroNenhumaEmpresaEncontrada ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public StreamedContent getImagem() {
        return imagem;
    }

    public void setImagem(StreamedContent imagem) {
        this.imagem = imagem;
    }

    public long getCodigoPessoa() {
        return codigoPessoa;
    }

    public void setCodigoPessoa(long codigoPessoa) {
        if (codigoPessoa != 0) {
            this.codigoPessoa = codigoPessoa;
            carregarPessoa();
        }
        this.codigoPessoa = codigoPessoa;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public long getCodigoEmpresa() {
        return codigoEmpresa;
    }

    public void setCodigoEmpresa(long codigoEmpresa) {
        if (codigoEmpresa != 0) {
            this.codigoEmpresa = codigoEmpresa;
            carregarEmpresa();
        }
        this.codigoEmpresa = codigoEmpresa;
    }

    private boolean cadastrandoPessoa() {
        return this.codigoPessoa == 0;
    }

    private boolean cadastrandoEmpresa() {
        return this.codigoEmpresa == 0;
    }

}

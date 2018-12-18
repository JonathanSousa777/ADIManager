package com.adisolucoes.adimanager.util.crud;

import com.adisolucoes.adimanager.dao.EmpresaDAO;
import com.adisolucoes.adimanager.dao.PessoaDAO;
import com.adisolucoes.adimanager.enumerations.UF;
import com.adisolucoes.adimanager.exceptions.ErroBancoDadosException;
import com.adisolucoes.adimanager.exceptions.ErroEmpresaDuplicadaException;
import com.adisolucoes.adimanager.exceptions.ErroPessoaDuplicadoException;
import com.adisolucoes.adimanager.model.Empresa;
import com.adisolucoes.adimanager.model.Endereco;
import com.adisolucoes.adimanager.model.Pessoa;
import com.adisolucoes.adimanager.util.jsf.ConnectionUtils;
import com.adisolucoes.adimanager.util.jsf.FacesUtils;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;

/**
 *
 * @author ADI Soluções
 */
public class CrudUtils implements Serializable {

    @Inject
    private PessoaDAO pessoaDAO;

    @Inject
    private EmpresaDAO empresaDAO;

    private static final Logger LOG = Logger.getLogger(CrudUtils.class.getName());

    public Endereco preencherDadosPorCep(Endereco endereco) {
        try {
            String cep = endereco.getCep();
            if (cep != null || !cep.equals("")) {
                ConnectionUtils connectionUtils = new ConnectionUtils();
                Connection con = connectionUtils.getConnection("http://cep.republicavirtual.com.br/web_cep.php?cep=" + cep + "&formato=xml", null, null);
                Document doc = con.get();
                if (doc.getElementsByTag("resultado_txt").text().contains("cep não encontrado")) {
                    FacesUtils.showFacesMessage("CEP não encontrado, informe um CEP válido!", 1);
                } else {
                    UF uf = UF.fromDescricao(doc.getElementsByTag("uf").text());
                    endereco.setUf(uf);
                    endereco.setCidade(doc.getElementsByTag("cidade").text());
                    endereco.setBairro(doc.getElementsByTag("bairro").text());
                    endereco.setLogradouro(doc.getElementsByTag("logradouro").text());
                    endereco.setComplemento(doc.getElementsByTag("tipo_logradouro").text() + ": ");
                }
            }
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, null, ex);
            FacesUtils.showFacesMessage("Ouve um erro na conexão com o WebService, informe os dados de endereço forma manual!", 1);
        }
        return endereco;
    }

    public void verificarCpfCnpjCliente(String cpfCnpj) throws ErroPessoaDuplicadoException {
        Pessoa pessoa = null;
        try {
            if (cpfCnpj != null && !cpfCnpj.equals("")) {
                pessoa = pessoaDAO.buscarPorCpfCnpj(cpfCnpj);
            }
            if (pessoa != null) {
                FacesUtils.showFacesMessage("Já existe uma Pessoa com esse CPF/CNPJ", 1);
                throw new ErroPessoaDuplicadoException();
            } else {
                LOG.info("CPF/CNPJ livre para cadastro!");
            }
        } catch (ErroBancoDadosException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
    }

    public void verificarCpfCnpjEmpresa(String cpfCnpj) throws ErroEmpresaDuplicadaException {
        Empresa empresa = null;
        try {
            if (cpfCnpj != null && !cpfCnpj.equals("")) {
                empresa = empresaDAO.buscarPorCnpj(cpfCnpj);
            } 
            if (empresa != null) {
                FacesUtils.showFacesMessage("Já existe uma Empresa com esse CNPJ", 1);
                throw new ErroEmpresaDuplicadaException();
            } else {
                LOG.info("CNPJ livre para cadastro!");
            }
        } catch (ErroBancoDadosException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
    }
}

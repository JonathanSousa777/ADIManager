package com.adisolucoes.adimanager.controller;

import com.adisolucoes.adimanager.enumerations.Sexo;
import com.adisolucoes.adimanager.enumerations.TipoUsuario;
import com.adisolucoes.adimanager.enumerations.UF;
import com.adisolucoes.adimanager.model.Endereco;
import com.adisolucoes.adimanager.model.Pessoa;
import com.adisolucoes.adimanager.model.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Junior Sy
 */
@Named
@ViewScoped
public class UsuarioBean implements Serializable {

    private List<Usuario> usuarioFiltrados;

    @PostConstruct
    public void init() {
        usuarioFiltrados = new ArrayList<Usuario>();

        Endereco endereco = new Endereco();
        endereco.setBairro("Sao Paulo");
        endereco.setCep("64027.080");
        endereco.setCidade("Barueri");
        endereco.setLogradouro("Quadra-30 Lote-21 Casa-B");
        endereco.setNumero("456");
        endereco.setUf(UF.DF);

        Pessoa pessoa = new Pessoa();
        pessoa.setCpfCnpj("098764343");
        pessoa.setNome("Jonathan Silva Sousa");
        pessoa.setDataAtualizacao(new Date());
        pessoa.setDataCadastro(new Date());
        pessoa.setDataNascimento(new Date());
        pessoa.setEmail("marioadi157@gmail");
        pessoa.setEndereco(endereco);
        pessoa.setNacionalidade("Brasileiro");
        pessoa.setObservacao("Referenciada");
        pessoa.setSexo(Sexo.FEMININO);
        pessoa.setTelefone("0101001");

        Usuario usuario = new Usuario();
        usuario.setDataAtualizacao(new Date());
        usuario.setDataCadastro(new Date());
        usuario.setDataUltimoAcesso(new Date());
        usuario.setLogin("JUnior teste");
        usuario.setPessoa(pessoa);
        usuario.setSenha("22323");
        usuario.setStatus(true);
        usuario.setTipoUsuario(TipoUsuario.MASTER);
        usuario.setPessoa(pessoa);
        
        Endereco endereco2 = new Endereco();
        endereco2.setBairro("Sao Paulo");
        endereco2.setCep("64027.080");
        endereco2.setCidade("Barueri");
        endereco2.setLogradouro("Quadra-30 Lote-21 Casa-B");
        endereco2.setNumero("456");
        endereco2.setUf(UF.DF);

        Pessoa pessoa2 = new Pessoa();
        pessoa2.setCpfCnpj("098764343");
        pessoa2.setNome("Jonathan Silva Sousa");
        pessoa2.setDataAtualizacao(new Date());
        pessoa2.setDataCadastro(new Date());
        pessoa2.setDataNascimento(new Date());
        pessoa2.setEmail("marioadi157@gmail");
        pessoa2.setEndereco(endereco2);
        pessoa2.setNacionalidade("Brasileiro");
        pessoa2.setObservacao("Referenciada");
        pessoa2.setSexo(Sexo.FEMININO);
        pessoa2.setTelefone("0101001");

        Usuario usuario2 = new Usuario();
        usuario2.setDataAtualizacao(new Date());
        usuario2.setDataCadastro(new Date());
        usuario2.setDataUltimoAcesso(new Date());
        usuario2.setLogin("JUnior teste");
        usuario2.setPessoa(pessoa2);
        usuario2.setSenha("22323");
        usuario2.setStatus(true);
        usuario2.setTipoUsuario(TipoUsuario.MASTER);
        usuario2.setPessoa(pessoa2);
        
       usuarioFiltrados.add(usuario);
       usuarioFiltrados.add(usuario2);

    }

    public List<Usuario> getUsuarioFiltrados() {
        return usuarioFiltrados;
    }

    public void setUsuarioFiltrados(List<Usuario> usuarioFiltrados) {
        this.usuarioFiltrados = usuarioFiltrados;
    }

}

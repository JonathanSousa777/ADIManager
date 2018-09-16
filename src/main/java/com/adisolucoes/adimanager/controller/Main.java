package com.adisolucoes.adimanager.controller;

import com.adisolucoes.adimanager.enumerations.Sexo;
import com.adisolucoes.adimanager.enumerations.TipoPagamento;
import com.adisolucoes.adimanager.enumerations.TipoUsuario;
import com.adisolucoes.adimanager.enumerations.UF;
import com.adisolucoes.adimanager.model.Acrescimo;
import com.adisolucoes.adimanager.model.Campanha;
import com.adisolucoes.adimanager.model.CategoriaLancamento;
import com.adisolucoes.adimanager.model.CategoriaPlano;
import com.adisolucoes.adimanager.model.Cliente;
import com.adisolucoes.adimanager.model.Empresa;
import com.adisolucoes.adimanager.model.Endereco;
import com.adisolucoes.adimanager.model.Fase;
import com.adisolucoes.adimanager.model.Ferramenta;
import com.adisolucoes.adimanager.model.Indicacao;
import com.adisolucoes.adimanager.model.Pessoa;
import com.adisolucoes.adimanager.model.Plano;
import com.adisolucoes.adimanager.model.Projeto;
import com.adisolucoes.adimanager.model.Servidor;
import com.adisolucoes.adimanager.model.Usuario;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

/**
 *
 * @author Jonathan Sousa
 */
public class Main {

    public static void main(String[] args) {

        EntityManagerFactory factory;
        factory = Persistence.createEntityManagerFactory("ADIManagerPU");
        EntityManager manager = getEntityManager(factory);
        EntityTransaction trx = manager.getTransaction();
        trx.begin();

        //TESTE AQUI
        inserirEmpresa(manager);
        trx.commit();
        manager.close();
        close(factory);

    }

    //TESTE INSERIR FASE
    public static void inserirFase(EntityManager manager) {
        try {
            Fase fase = new Fase();
            fase.setDataFim(new Date());
            fase.setDataInicio(new Date());
            fase.setDescricao("Faze teste2");
            manager.persist(fase);
            System.out.println("----------OK-------------");
        } catch (PersistenceException ex) {
            System.out.println("-----------Falha------------");
        }
    }

    //TESTE INSERIR ACRESCIMO
    public static void inserirAcrescimo(EntityManager manager) {
        try {
            Acrescimo acrescimo = new Acrescimo();
            acrescimo.setDescricao("Testando 1");
            acrescimo.setValor(new BigDecimal(250));
            manager.persist(acrescimo);
            System.out.println("----------OK-------------");
        } catch (PersistenceException ex) {
            System.out.println("-----------Falha------------");
        }
    }

    //TESTE INSERIR CAMPANHA
    public static void inserirCampanha(EntityManager manager) {
        try {
            Campanha campanha = new Campanha();
            campanha.setLocalidade("Shopping");
            campanha.setDataFim(new Date());
            campanha.setDataInicio(new Date());
            campanha.setDescricao("Novos aumentos");
            campanha.setConcluida(true);
            manager.persist(campanha);
            System.out.println("----------OK-------------");
        } catch (PersistenceException ex) {
            System.out.println("-----------Falha------------");
        }
    }

    //TESTE INSERIR CATEGORIA LANÇAMENTO
    public static void inserirCategoriaLancamento(EntityManager manager) {
        try {
            CategoriaLancamento categoriaLancamento = new CategoriaLancamento();
            categoriaLancamento.setDescricao("Internet");
            manager.persist(categoriaLancamento);
            System.out.println("----------OK-------------");
        } catch (PersistenceException ex) {
            System.out.println("-----------Falha------------");
        }
    }

    //TESTE INSERIR CATEGORIA PLANO
    public static void inserirCategoriaPlano(EntityManager manager) {
        try {
            CategoriaPlano categoriaPlano = new CategoriaPlano();
            categoriaPlano.setDescricao("Plano com os mais variádos recursos de softwares");
            categoriaPlano.setNome("Ecommerce");
            manager.persist(categoriaPlano);
            System.out.println("----------OK-------------");
        } catch (PersistenceException ex) {
            System.out.println("-----------Falha------------");
        }
    }

    //TESTE INSERIR EMPRESA
    public static void inserirEmpresa(EntityManager manager) {
        try {
            Endereco endereco = new Endereco();
            endereco.setBairro("Porto Alegre");
            endereco.setCep("64027.080");
            endereco.setCidade("Teresina");
            endereco.setLogradouro("Quadra-30 Lote-21 Casa-B");
            endereco.setNumero("456");
            endereco.setUf(UF.DF);

            Pessoa pessoa = new Pessoa();
            pessoa.setCpfCnpj("5641651555");
            pessoa.setNome("Gabriel Barbosa");
            pessoa.setDataAtualizacao(new Date());
            pessoa.setDataCadastro(new Date());
            pessoa.setDataNascimento(new Date());
            pessoa.setEmail("gabriel@gmail");
            pessoa.setEndereco(endereco);
            pessoa.setNacionalidade("Brasileiro");
            pessoa.setObservacao("Referenciada");
            pessoa.setSexo(Sexo.FEMININO);
            pessoa.setTelefone("45665468");

            Cliente cliente = new Cliente();
            cliente.setCodigoIdentificador("cliGarb");
            cliente.setComoConheceu("Campanha");
            cliente.setObservacao("Novo desse mês");
            cliente.setPessoa(pessoa);
            manager.persist(cliente);

            Empresa empresa = new Empresa();
            empresa.setEmail("devs@gmail.com");
            empresa.setCnpj("0000.111/0000,0");
            empresa.setDescricao("Empresa de desenvolvimento");
            empresa.setEndereco(endereco);
            empresa.setNome("Devs Top");
            empresa.setTelefone("99898349");
            empresa.setProprietario(cliente);

            manager.persist(empresa);
            System.out.println("----------OK-------------");
        } catch (PersistenceException ex) {
            System.out.println("-----------Falha------------");
        }
    }

    //TESTE INSERIR CLIENTE
    public static void inserirCliente(EntityManager manager) {
        try {
            Endereco endereco = new Endereco();
            endereco.setBairro("Promorarr");
            endereco.setCep("64027.080");
            endereco.setCidade("Teresina");
            endereco.setLogradouro("Quadra-30 Lote-21 Casa-B");
            endereco.setNumero("456");
            endereco.setUf(UF.DF);

            Pessoa pessoa = new Pessoa();
            pessoa.setCpfCnpj("05183216304");
            pessoa.setNome("Daniel Julio");
            pessoa.setDataAtualizacao(new Date());
            pessoa.setDataCadastro(new Date());
            pessoa.setDataNascimento(new Date());
            pessoa.setEmail("teste@gmail");
            pessoa.setEndereco(endereco);
            pessoa.setNacionalidade("Brasileiro");
            pessoa.setObservacao("Referenciada");
            pessoa.setSexo(Sexo.FEMININO);
            pessoa.setTelefone("88888888");

            Cliente cliente = new Cliente();
            cliente.setCodigoIdentificador("cli12");
            cliente.setComoConheceu("Campanha");
            cliente.setObservacao("Novo desse mês");
            cliente.setPessoa(pessoa);

            manager.persist(cliente);
            System.out.println("--------OK-------------");
        } catch (PersistenceException ex) {
            System.out.println("-----------Falha------------");
        }
    }

    //TESTE INSERIR PESSOA
    public static void inserirPessoa(EntityManager manager) {
        try {
            Endereco endereco = new Endereco();
            endereco.setBairro("Promorar");
            endereco.setCep("64027.080");
            endereco.setCidade("Teresina");
            endereco.setLogradouro("Quadra-30 Lote-21 Casa-B");
            endereco.setNumero("456");
            endereco.setUf(UF.DF);

            Pessoa pessoa = new Pessoa();
            pessoa.setCpfCnpj("05183216304");
            pessoa.setNome("Jonathan Silva Sousa");
            pessoa.setDataAtualizacao(new Date());
            pessoa.setDataCadastro(new Date());
            pessoa.setDataNascimento(new Date());
            pessoa.setEmail("teste@gmail");
            pessoa.setEndereco(endereco);
            pessoa.setNacionalidade("Brasileiro");
            pessoa.setObservacao("Referenciada");
            pessoa.setSexo(Sexo.FEMININO);
            pessoa.setTelefone("999999999");
            System.out.println("----------OK-------------");

            manager.persist(pessoa);
        } catch (PersistenceException ex) {
            System.out.println("-----------Falha------------");
        }
    }

    //TESTE INSERIR INDICAÇÃO
    public static void inserirIndicacao(EntityManager manager) {
        try {
            Cliente cliente1 = manager.find(Cliente.class, new Long(1));
            Cliente cliente2 = manager.find(Cliente.class, new Long(2));

            Indicacao indicacao = new Indicacao();
            indicacao.setDescricao("Indicado hoje");
            indicacao.setData(new Date());
            indicacao.setClienteAtivo(cliente1);
            indicacao.setCodigoClientePassivo(cliente2.getId());

            manager.persist(indicacao);
            System.out.println("----------OK-------------");
        } catch (PersistenceException ex) {
            System.out.println("-----------Falha------------");
        }
    }

    //TESTE INSERIR USUÁRIO
    public static void inserirUsuario(EntityManager manager) {
        try {
            Endereco endereco = new Endereco();
            endereco.setBairro("Porto Alegre");
            endereco.setCep("64027.080");
            endereco.setCidade("Barueri");
            endereco.setLogradouro("Quadra-30 Lote-21 Casa-B");
            endereco.setNumero("456");
            endereco.setUf(UF.DF);

            Pessoa pessoa = new Pessoa();
            pessoa.setCpfCnpj("654546545");
            pessoa.setNome("Jonathan Sousa");
            pessoa.setDataAtualizacao(new Date());
            pessoa.setDataCadastro(new Date());
            pessoa.setDataNascimento(new Date());
            pessoa.setEmail("jon@gmail");
            pessoa.setEndereco(endereco);
            pessoa.setNacionalidade("Brasileiro");
            pessoa.setObservacao("Referenciada");
            pessoa.setSexo(Sexo.FEMININO);
            pessoa.setTelefone("0101001");

            Usuario usuario = new Usuario();
            usuario.setDataUltimoAcesso(new Date());
            usuario.setLogin("master");
            usuario.setPessoa(pessoa);
            usuario.setSenha("1");
            usuario.setAtivo(true);
            usuario.setTipoUsuario(TipoUsuario.MASTER);
            usuario.setPessoa(pessoa);

            manager.persist(usuario);
            System.out.println("-------------OK------------");
        } catch (PersistenceException ex) {
            System.out.println("------------FALHA----------");
        }
    }

    //TESTE INSERIR PROJETO
    public static void inserirProjeto(EntityManager manager) {
        try {
            Endereco endereco = new Endereco();
            endereco.setBairro("Promorarr");
            endereco.setCep("64027.080");
            endereco.setCidade("Teresina");
            endereco.setLogradouro("Quadra-30 Lote-21 Casa-B");
            endereco.setNumero("456");
            endereco.setUf(UF.DF);

            Pessoa pessoa = new Pessoa();
            pessoa.setCpfCnpj("05183216304");
            pessoa.setNome("Daniel Julio");
            pessoa.setDataAtualizacao(new Date());
            pessoa.setDataCadastro(new Date());
            pessoa.setDataNascimento(new Date());
            pessoa.setEmail("teste@gmail");
            pessoa.setEndereco(endereco);
            pessoa.setNacionalidade("Brasileiro");
            pessoa.setObservacao("Referenciada");
            pessoa.setSexo(Sexo.FEMININO);
            pessoa.setTelefone("88888888");

            Cliente cliente = new Cliente();
            cliente.setCodigoIdentificador("cli12");
            cliente.setComoConheceu("Campanha");
            cliente.setObservacao("Novo desse mês");
            cliente.setPessoa(pessoa);
            manager.persist(cliente);

            CategoriaPlano categoriaPlano = new CategoriaPlano();
            categoriaPlano.setDescricao("Plano com os mais variádos recursos de softwares");
            categoriaPlano.setNome("Ecommerce");
            manager.persist(categoriaPlano);

            Plano plano = new Plano();
            plano.setDescricao("Maior controle financeiro");
            plano.setApelido("GOLD");
            plano.setCor("#654654");
            plano.setPromocao(null);
            plano.setValor(new BigDecimal(124.99));
            plano.setCategoriaPlano(categoriaPlano);
            manager.persist(plano);

            Servidor servidor = new Servidor();
            servidor.setCodigoIdentificador("HG");
            servidor.setEmpresaDesenvolvedora("HOSTGATOR");
            servidor.setGigas(30);
            servidor.setNomePlano("BUSINNES");
            servidor.setUrl("www.hostgator.com");
            servidor.setValor(new BigDecimal(35));
            servidor.setProjetos(new ArrayList<Projeto>());
            manager.persist(servidor);

            Projeto projeto = new Projeto();
            projeto.setCodigoidentificador("VS");
            projeto.setConcluido(true);
            projeto.setDataFim(new Date());
            projeto.setDataInicio(new Date());
            projeto.setDataVencimento(new Date());
            projeto.setDesconto(new BigDecimal(20));
            projeto.setDescricao("Site PRO da Vikstar");
            projeto.setFormaPagamento("Espécie");
            projeto.setNomeSite("Vikstar");
            projeto.setObservacaoComplementar("Desenvolvido para ser testado");
            projeto.setPago(true);
            projeto.setTipoPagamento(TipoPagamento.ANUAL);
            projeto.setUrl("www.vikstar.com.br");
            projeto.setValor(new BigDecimal(1498));
            projeto.setCliente(cliente);
            projeto.setPlano(plano);
            projeto.setServidor(servidor);

            manager.persist(projeto);
            System.out.println("----------OK-------------");
        } catch (Exception ex) {
            System.out.println("-----------Falha------------");
            System.out.println("Error: " + ex.getMessage());
        }
    }

    //TESTE INSERIR FERRAMENTA
    public static void inserirFerramenta(EntityManager manager) {
        try {
            Endereco endereco = new Endereco();
            endereco.setBairro("Porto Alegre");
            endereco.setCep("64027.080");
            endereco.setCidade("Teresina");
            endereco.setLogradouro("Quadra-36 Lote-23 Casa-B");
            endereco.setNumero("456");
            endereco.setUf(UF.DF);

            Pessoa pessoa = new Pessoa();
            pessoa.setCpfCnpj("2342343243");
            pessoa.setNome("Bartolomeu Rodrigues");
            pessoa.setDataAtualizacao(new Date());
            pessoa.setDataCadastro(new Date());
            pessoa.setDataNascimento(new Date());
            pessoa.setEmail("bart@gmail");
            pessoa.setEndereco(endereco);
            pessoa.setNacionalidade("Brasileiro");
            pessoa.setObservacao("Aroazes");
            pessoa.setSexo(Sexo.FEMININO);
            pessoa.setTelefone("88888888");

            Cliente cliente = new Cliente();
            cliente.setCodigoIdentificador("cliBar");
            cliente.setComoConheceu("Facebook");
            cliente.setObservacao("Novo desse mês");
            cliente.setPessoa(pessoa);
            manager.persist(cliente);

            CategoriaPlano categoriaPlano = new CategoriaPlano();
            categoriaPlano.setDescricao("Plano para administradores");
            categoriaPlano.setNome("Admin");
            manager.persist(categoriaPlano);

            Plano plano = new Plano();
            plano.setDescricao("Maior controle financeiro");
            plano.setApelido("GOLD");
            plano.setCor("#654654");
            plano.setPromocao(null);
            plano.setValor(new BigDecimal(124.99));
            plano.setCategoriaPlano(categoriaPlano);
            manager.persist(plano);

            Servidor servidor = new Servidor();
            servidor.setCodigoIdentificador("HG");
            servidor.setEmpresaDesenvolvedora("HOSTGATOR");
            servidor.setGigas(30);
            servidor.setNomePlano("BUSINNES");
            servidor.setUrl("www.hostgator.com");
            servidor.setValor(new BigDecimal(35));
            servidor.setProjetos(new ArrayList<Projeto>());
            manager.persist(servidor);

            Projeto projeto = new Projeto();
            projeto.setCodigoidentificador("VS");
            projeto.setConcluido(true);
            projeto.setDataFim(new Date());
            projeto.setDataInicio(new Date());
            projeto.setDataVencimento(new Date());
            projeto.setDesconto(new BigDecimal(20));
            projeto.setDescricao("Site PRO da Vikstar");
            projeto.setFormaPagamento("Espécie");
            projeto.setNomeSite("Vikstar");
            projeto.setObservacaoComplementar("Desenvolvido para ser testado");
            projeto.setPago(true);
            projeto.setTipoPagamento(TipoPagamento.ANUAL);
            projeto.setUrl("www.vikstar.com.br");
            projeto.setValor(new BigDecimal(1498));
            projeto.setCliente(cliente);
            projeto.setPlano(plano);
            projeto.setServidor(servidor);

            List<Projeto> projetos = new ArrayList<Projeto>();
            projetos.add(projeto);

            Ferramenta ferramenta = new Ferramenta();
            ferramenta.setAvaliacao("Excelente para formulários");
            ferramenta.setDescricao("Para formulários avançados");
            ferramenta.setDesenvolvedora("FiveTeam");
            ferramenta.setDesvantagens("Lenta e Cara");
            ferramenta.setNome("FormsPro");
            ferramenta.setNotaInstrucao("CONTATO");
            ferramenta.setPaga(true);
            ferramenta.setUrl("http://www.formspro.com.br");
            ferramenta.setValor(new BigDecimal(120));
            ferramenta.setVantagens("Rápida e Barata");
            ferramenta.setProjetos(projetos);
            manager.persist(ferramenta);

            List<Ferramenta> ferramentas = new ArrayList<>();
            ferramentas.add(ferramenta);
            projeto.setFerramentas(ferramentas);
            manager.persist(projeto);

            System.out.println("----------OK-------------");
        } catch (Exception ex) {
            System.out.println("-----------Falha------------");
            System.out.println("Error: " + ex.getMessage());
        }
    }

    public static EntityManager getEntityManager(EntityManagerFactory factory) {
        return factory.createEntityManager();
    }

    public static void close(EntityManagerFactory factory) {
        factory.close();
    }
}

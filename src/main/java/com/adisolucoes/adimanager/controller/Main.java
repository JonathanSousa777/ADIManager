package com.adisolucoes.adimanager.controller;

import com.adisolucoes.adimanager.enumerations.Sexo;
import com.adisolucoes.adimanager.enumerations.UF;
import com.adisolucoes.adimanager.model.Acrescimo;
import com.adisolucoes.adimanager.model.Campanha;
import com.adisolucoes.adimanager.model.CategoriaLancamento;
import com.adisolucoes.adimanager.model.CategoriaPlano;
import com.adisolucoes.adimanager.model.Cliente;
import com.adisolucoes.adimanager.model.Empresa;
import com.adisolucoes.adimanager.model.Endereco;
import com.adisolucoes.adimanager.model.Fase;
import com.adisolucoes.adimanager.model.Indicacao;
import com.adisolucoes.adimanager.model.Pessoa;
import java.math.BigDecimal;
import java.util.Date;
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
        inserirFase(manager);

        trx.commit();
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
            endereco.setBairro("Promorar");
            endereco.setCep("64027.080");
            endereco.setCidade("Teresina");
            endereco.setLogradouro("Quadra-30 Lote-21 Casa-B");
            endereco.setNumero("456");
            endereco.setUf(UF.DF);

            Empresa empresa = new Empresa();
            empresa.setEmail("empresa@gmail.com");
            empresa.setCnpj("0000.111/0000,0");
            empresa.setDescricao("Vende plásticos");
            empresa.setEndereco(endereco);
            empresa.setNome("Razão Fantasma");
            empresa.setTelefone("99999999");

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

    public static EntityManager getEntityManager(EntityManagerFactory factory) {
        return factory.createEntityManager();
    }

    public static void close(EntityManagerFactory factory) {
        factory.close();
    }
}

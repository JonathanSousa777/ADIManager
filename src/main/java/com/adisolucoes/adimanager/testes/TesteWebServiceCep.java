package com.adisolucoes.adimanager.testes;

import java.io.IOException;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 *
 * @author ADI Soluções
 */
public class TesteWebServiceCep {

    static private String estado = "";
    static private String cidade = "";
    static private String bairro = "";
    static private String tipoLogradouro = "";
    static private String logradouro = "";
    static private String cep = "64027080"; //INFORME UM CEP PARA TESTE
    static String url = "http://cep.republicavirtual.com.br/web_cep.php?cep=" + cep + "&formato=xml";

    public static void main(String[] args) throws IOException {
        Connection connection = Jsoup.connect(url);
        Document doc = connection.get();

        estado = doc.getElementsByTag("uf").text();
        bairro = doc.getElementsByTag("bairro").text();
        tipoLogradouro = doc.getElementsByTag("tipo_logradouro").text();
        logradouro = doc.getElementsByTag("logradouro").text();
        cidade = doc.getElementsByTag("cidade").text();

        System.out.println("Endereço completo:\n Estado: " + estado + "\n Cidade: " + cidade + "\n Bairro: " + bairro + "\n Tipo do Logradouro: " + tipoLogradouro + "\n Logradouro: " + logradouro);
    }
}

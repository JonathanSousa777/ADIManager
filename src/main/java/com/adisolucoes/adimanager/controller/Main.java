package com.adisolucoes.adimanager.controller;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Jonathan Sousa
 */
public class Main {

    public static void main(String[] args) {
        EntityManagerFactory factory;
        factory = Persistence.createEntityManagerFactory("ADIManagerPU");
        factory.close();
    }
}

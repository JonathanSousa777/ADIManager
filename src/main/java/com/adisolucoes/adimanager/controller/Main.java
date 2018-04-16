/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    }
}

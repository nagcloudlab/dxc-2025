package com.example;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class App1 {

    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory= Persistence.createEntityManagerFactory("myPU");

        EntityManager entityManager=entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        //...

        entityManager.getTransaction().commit();
        entityManager.close();

        entityManagerFactory.close();
    }

}

package jpa;



import jpa.domain.Device;

import javax.persistence.*;

public class mainJPA
{


    public static void main( String[] args )
    {
        System.out.println("AppJPA - start - v0.0.1");
        EntityManagerFactory entityManagerFactory;
        EntityManager entityManager;

        entityManagerFactory = Persistence.createEntityManagerFactory("myDatabase");
        entityManager = entityManagerFactory.createEntityManager();

        Device device1 = new Device("001-000-000-000");
        Device device2 = new Device("002-000-000-000");
        Device device3 = new Device("003-000-000-000");




        entityManager.getTransaction().begin();
        entityManager.persist(device1);
        entityManager.persist(device2);
        entityManager.persist(device3);
        entityManager.getTransaction().commit();



        System.out.println("close...");
        entityManager.close();
        entityManagerFactory.close();
    }





}

package jpa;



import jpa.domain.Device;
import jpa.domain.Location;
import jpa.domain.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

        User user1 = new User("radek", "radek@wp.pl", "radekpsw1");
        User user2 = new User("radek", "radekwp.pl", "radekpsw2");

        Location loc1 = new Location(123.1, 321.3);
        Location loc2 = new Location(123213.1, 322431.3);
        Location loc3 = new Location(4444.9, 55555.2);
        Location loc4 = new Location(878788, 435346.2323);

        //List<Location> locations = new ArrayList<>(Arrays.asList(loc1, loc2, loc3));
        //device1.setLocations(locations);

        device1.setLocations(new ArrayList<>(Arrays.asList(loc1, loc2, loc3)));

        device1.setLocations(new ArrayList<>(Arrays.asList(loc4)));


        entityManager.getTransaction().begin();
        entityManager.persist(device1);
        entityManager.persist(device2);
        entityManager.persist(device3);
        entityManager.persist(user1);
        entityManager.persist(user2);
        entityManager.getTransaction().commit();

       // entityManager.refresh(device1);

//        entityManager.getTransaction().begin();
//        entityManager.remove(device1);
//        entityManager.getTransaction().commit();



        System.out.println("close...");
        entityManager.close();
        entityManagerFactory.close();
    }





}

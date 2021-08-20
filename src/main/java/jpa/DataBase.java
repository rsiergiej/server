package jpa;



import com.google.gson.Gson;
import jpa.domain.Device;
import jpa.domain.Location;
import jpa.domain.TempHumPress;
import jpa.domain.User;

import javax.persistence.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Thread.sleep;

public class DataBase
{
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    public DataBase() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("myDatabase");
        this.entityManager = entityManagerFactory.createEntityManager();
    }


    public void close()
    {
        this.entityManager.close();
        this.entityManagerFactory.close();
    }


    private void addObj(Object var)
    {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(var);
        this.entityManager.getTransaction().commit();
    }

    private void mergeObj(Object var)
    {
        this.entityManager.getTransaction().begin();
        this.entityManager.merge(var);
        this.entityManager.getTransaction().commit();
    }

//    private void refreshObj(Object var)
//    {
//        this.entityManager.getTransaction().begin();
//        this.entityManager.refresh(var);
//        this.entityManager.getTransaction().commit();
//    }

    public  void addDataToBase()
    {

        Device device1 = new Device("001-000-000-000");
        Device device2 = new Device("002-000-000-000");
        Device device3 = new Device("003-000-000-000");

        User user1 = new User("radek", "radek@wp.pl", "radekpsw1");
        User user2 = new User("radek", "radekwp.pl", "radekpsw2");

        Location loc1 = new Location(123.1, 321.3);
        sleepSec(1);
        Location loc2 = new Location(123213.1, 322431.3);
        sleepSec(1);
        Location loc3 = new Location(4444.9, 55555.2);
        sleepSec(1);
        Location loc4 = new Location(878788, 435346.2323);

        TempHumPress data1 = new TempHumPress(20.0, 20, 1010);
        TempHumPress data2 = new TempHumPress(-10.4, 80, 1030);
        TempHumPress data3 = new TempHumPress(30.3, 30, 1023);
        TempHumPress data4 = new TempHumPress(-5.123, 10, 1016);
        TempHumPress data5 = new TempHumPress(-23.123, 70, 1011);
        TempHumPress data6 = new TempHumPress(-10.123, 40, 1013);

        List<Location> locations1 = new ArrayList<>(Arrays.asList(loc1, loc2, loc3));
        device1.setLocations(locations1);
       //device1.setLocations(new ArrayList<>(Arrays.asList(loc1, loc2, loc3)));
        List<Location> locations2 = new ArrayList<>(Arrays.asList(loc4));
        device2.setLocations(locations2);

        List<TempHumPress> datas1 = new ArrayList<>(Arrays.asList(data1, data2, data3, data4));
        device1.setTempHumPress(datas1);
        List<TempHumPress> datas3 = new ArrayList<>(Arrays.asList(data5, data6));
        device3.setTempHumPress(datas3);


        entityManager.getTransaction().begin();
        entityManager.persist(device1);
        entityManager.persist(device2);
        entityManager.persist(device3);
        entityManager.persist(user1);
        entityManager.persist(user2);
        entityManager.getTransaction().commit();

        entityManager.refresh(device1);

//        entityManager.getTransaction().begin();
//        entityManager.remove(device1);
//        entityManager.getTransaction().commit();

    }



    private static void sleepSec(int sec)
    {
        try {
            sleep(1000 * sec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Device findBySerialNumber(String serialNumber)
    {
        TypedQuery<Device> query = this.entityManager
                .createQuery("select c from Device c where c.serialNumber in :serialNum", Device.class)
                .setParameter("serialNum", serialNumber);
        Device result;
        try {
            result = query.getSingleResult();
            System.out.println("Device this number is in the database -> " + result.toString());
        }catch (NoResultException e) {
            System.out.println("The device is not in the database ->" + e);
            return null;
        }
        return result;
    }


    public void addJsonToDataBase(String jsonString)
    {
        Gson gson2 = new Gson();
        Device deviceFromJson = gson2.fromJson(jsonString, Device.class);

        if(deviceFromJson.getSerialNumber() == null) return;
        if(deviceFromJson.checkSerialNumber() == false) return;



        Device dev = findBySerialNumber(deviceFromJson.getSerialNumber());

        if(dev == null)
        {
            dev = new Device(deviceFromJson.getSerialNumber());
            addObj(dev);
            System.out.println("ADD DEVICE TO DATABASE: " + dev.toString());
        }

        try {
            if(deviceFromJson.getLocations().get(0) != null)
            {
                Location location = deviceFromJson.getLocations().get(0);
                System.out.println(location.toString());

                List<Location> listLoc = dev.getLocations();
                listLoc.add(location);
                dev.setLocations(listLoc);


                System.out.println(dev.toStringLocation());

                entityManager.getTransaction().begin();
                entityManager.merge(dev);
                entityManager.getTransaction().commit();


              //  refreshObj(dev);
            }
        }catch (NullPointerException e) {
            System.out.println("DEVICE no have location: " + e);
        }

















//		Device dev = new Device("001-000-000-099");
//		Location loc1 = new Location(123.1, 321.3);
//		TempHumPress data1 = new TempHumPress(20.0, 20, 1010);
//		List<Location> locations1 = new ArrayList<>(Arrays.asList(loc1));
//		dev.setLocations(locations1);
//		List<TempHumPress> datas1 = new ArrayList<>(Arrays.asList(data1));
//		dev.setTempHumPress(datas1);

//		Gson gson = new Gson();
//		String json = gson.toJson(dev);
//		System.out.println("json: " + json);



//		System.out.println("dev:2 " + createDeviceFromJson.getSerialNumber() + " TIME DEV:" + createDeviceFromJson.getCreateTimestamp() +
//				" TIME LOC: "  + createDeviceFromJson.getLocations().get(0).getTimestamp() +
//				" TIME THP: "  + createDeviceFromJson.getTempHumPress().get(0).getTimestamp());


        //	Device saveDevice = new Device(createDeviceFromJson.getSerialNumber());





    }





}

package application;

import jpa.DataBase;
import updServer.UdpServer;


public class mainApp
{

    public static DataBase dataBase;




    public static void main( String[] args )
    {

        System.out.println("App - start - v0.0.1");

        dataBase = new DataBase();

        UdpServer udpServer = new UdpServer(7110);
        udpServer.start();







    }
}

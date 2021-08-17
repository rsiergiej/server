package application;

public class mainApp
{


    public static void main( String[] args )
    {

        System.out.println("App - start - v0.0.1");

        udpServer udpServer = new udpServer(7110);
        udpServer.start();







    }
}

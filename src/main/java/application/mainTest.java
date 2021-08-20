package application;

import updServer.UdpServer;

public class mainTest {

    public static void main( String[] args )
    {

        UdpServer udpServer = new UdpServer(7110);
        udpServer.start();

//        String regex = "\\d{3}\\-\\d{3}\\-\\d{3}-\\d{3}";
//        String data = "123-321-123-432";
//        System.out.println(data.matches(regex));
//
//        String data1 = "123-321-123-";
//        System.out.println(data1.matches(regex));
//
//        String data2 = "123-321-123-43s";
//        System.out.println(data2.matches(regex));
//
//        String data3 = "123-321-123-432as";
//        System.out.println(data3.matches(regex));


    }










}

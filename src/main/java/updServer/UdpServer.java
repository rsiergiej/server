package updServer;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.*;

import application.mainApp;
import jpa.DataBase;
import org.apache.commons.codec.binary.Hex;
import org.json.JSONException;
import org.json.JSONObject;


public class UdpServer extends Thread {
	private boolean running;

	private static DatagramSocket socket;
	private byte[] buf = new byte[1024];
	private String strBuf;

	private static DatagramPacket packet;
	private static InetAddress address;
	private static int port;

	private int portServer;



	public UdpServer(int port) {
		this.portServer = port;
		this.running = false;
	}


	public void run() {
		System.out.println("RUN UDP SERVER port: " + portServer);
		try {
			this.socket = new DatagramSocket(portServer);
			this.packet = new DatagramPacket(buf, buf.length);

			this.running = true;
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		while (running) {
			Arrays.fill(buf, (byte) '\0');
			try {
				socket.receive(packet);
				strBuf = new String(packet.getData(), 0, packet.getLength(), "UTF-8");
				System.out.println("UDP RX: ip: " + packet.getAddress() + ", port: " + packet.getPort() + ", data(" + packet.getLength() + "):" + strBuf + " hex:" + Hex.encodeHexString(strBuf.getBytes()));
				//System.out.println(Integer.toHexString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				JSONObject jsonObject = new JSONObject(strBuf);
				System.out.println("UDP RX: JSONObject: " + jsonObject.toString());

				mainApp.dataBase.addJsonToDataBase(strBuf);

			}catch (JSONException err){
				System.err.println("Error " + err.toString());
			}
		}
		socket.close();
	}



//	static void sendData(String str) {
//		// byte[] buf2 = new byte[128];
//
//		// String str = String.valueOf(iterData);
//		// buf2 = str.getBytes();
//		// iterData++;
//		try {
//
//			packet = new DatagramPacket(str.getBytes(), str.length(), address, port);
//
//			socket.send(packet);
//			System.out.println("Send: ip: " + packet.getAddress() + ", port: " + packet.getPort() + ", len: "
//					+ packet.getLength() + ", data: " + new String(packet.getData()));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}





//		try {
//			JSONObject jsonObject = new JSONObject(new String(buf));
//			System.out.println("UDP RX: JSONObject: " + jsonObject.toString());
//
//
//
//
//
//
//			addJsonToDataBase(new String(buf));
//		}catch (JSONException err){
//
//			System.err.println("Error " + err.toString());
//		}

//		String serialNumber = jsonObject.getJSONObject("device").getString("serialNumber");
//		System.out.println("SN: " + serialNumber); // dziala prawidlowo
//
//		String temp = jsonObject.getJSONObject("temphumpress").getString("temperature");
//		System.out.println("SN: " + temp); // dziala prawidlowo

//		JSONArray arr = jsonObject.getJSONArray("device"); //tu nie dziala :(
//		for (int i = 0; i < arr.length(); i++) {
//			//String temperature = arr.getJSONObject(i).getString("temperature");
//			System.out.println(arr.getJSONObject(i));
//		}
//	}
//}
}
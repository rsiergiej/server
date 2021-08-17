package application;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;
import org.apache.commons.codec.binary.Hex;
import org.json.JSONException;
import org.json.JSONObject;



public class udpServer extends Thread 
{
	private boolean running;
	
	private static DatagramSocket socket;
	private byte[] buf = new byte[1024];

	private static DatagramPacket packet;
	private static InetAddress address;
	private static int port;
	
	private int portServer;

	public udpServer(int port)
	{
		this.portServer = port;
		this.running = false;
	}
	

	public void run() 
	{
		System.out.println("RUN UDP SERVER port: " + portServer);
		try 
		{
			socket = new DatagramSocket(portServer);
			packet = new DatagramPacket(buf, buf.length);
			
			this.running = true;
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		while (running) 
		{
			Arrays.fill(buf, (byte) '\0');
			try {
				socket.receive(packet);
				String str = new String(packet.getData(), 0, packet.getLength(), "UTF-8");
				System.out.println("UDP RX: ip: " + packet.getAddress() + ", port: " + packet.getPort() + ", data(" + packet.getLength() +"):" + str + " hex:" + Hex.encodeHexString(str.getBytes()));
				//System.out.println(Integer.toHexString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

			try {
			     JSONObject jsonObject = new JSONObject(new String(buf));
			     System.out.println("UDP RX: JSONObject: " + jsonObject.toString());
			}catch (JSONException err){
	
			     System.err.println("Error " + err.toString());
			}


			
		}
		socket.close();
	}

	static void sendData(String str) {
		// byte[] buf2 = new byte[128];

		// String str = String.valueOf(iterData);
		// buf2 = str.getBytes();
		// iterData++;
		try {

			packet = new DatagramPacket(str.getBytes(), str.length(), address, port);

			socket.send(packet);
			System.out.println("Send: ip: " + packet.getAddress() + ", port: " + packet.getPort() + ", len: "
					+ packet.getLength() + ", data: " + new String(packet.getData()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

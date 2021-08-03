//package model.reseau;
//
//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.io.OutputStream;
//import java.io.OutputStreamWriter;
//import java.net.ServerSocket;
//import java.net.Socket;
//import java.net.UnknownHostException;
//import java.util.ArrayList;
//
//import controller.Controller;
//import controller.IController;
//import util.Debug;
//import util.Position;
//import view.MainMenu;
//
//public class Client /*extends Thread*/ implements IDataRequest{
//	String ipAdress ;
//	static int noPort = 4321;
//	Socket socket;
//
//	public Client(String ip){
//		this.ipAdress = ip;
//		this.setClientConnection();
//	}
//
//	public void setClientConnection() {
//		try {
//
//			Debug.log("tentative de connexion");
//			socket = new Socket(ipAdress, noPort);
//			Debug.log("tentative reussite");
//
//		} catch (UnknownHostException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//	public void closeClientConnection() {
//		try {
//			socket.close();
//			Debug.log("Client : fermeture de la connexion avec le serveur.");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//
//	}
//
//	public static void main(String[] args)
//	{
//		Client testClient = new Client("127.0.0.1");
//		testClient.setClientConnection();
//	}
//
//	@Override
//	public void RequestSender(String data) { //methode prend un string et l'envoie
//
//		try {
//			OutputStream os = socket.getOutputStream();
//			OutputStreamWriter osw = new OutputStreamWriter(os);
//			BufferedWriter brw = new BufferedWriter(osw);
//			brw.write(data+"\n");
//			brw.flush();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//	@Override
//	public String RequestReceiver() {	//methode qui reçoi une requete et retourn un string
//		String data = "";
//		try {
//			InputStream is = socket.getInputStream();
//			InputStreamReader isr = new InputStreamReader(is);
//			BufferedReader sbr = new BufferedReader(isr);
//			data += sbr.readLine();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return data;
//	}
//
//	@Override
//	public String parceObjectToString(boolean info,Position pos) {
//		String tempContainer = pos.getX()+","+pos.getY()+"\n";
//		return tempContainer;
//	}
//
//	@Override
//	public Position parceStringToObject(String line) {
//		String tempX = "",tempY = "";
//		boolean isX = true;
//		for (char value: line.toCharArray()) {
//			if (value != ',' && isX) {
//				tempX += value+"";
//			}else if(value != ',') {
//				tempY += value+"";
//			}
//			if(value == ',') {
//				isX = false;
//			}
//		}
//		return new Position((Integer. parseInt(tempX)), (Integer. parseInt(tempY)));
//	}
//
//	@Override
//	public ArrayList<String> receiveRequestBoolString() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public void sendRequestBoolString() {
//		// TODO Auto-generated method stub
//
//	}
//
//}
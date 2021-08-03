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
//import java.util.ArrayList;
//
//import controller.Controller;
//import controller.IController;
//import util.Debug;
//import util.Position;
//
//public class Server /*extends Thread*/ implements IDataRequest{
//
//	static int noPort = 4321;
//	ServerSocket ssSocket;
//	Socket socket;
//
//	public Server() {
//		this.setServerConnection();
//	}
//
//	public void setServerConnection() {
//
//
//		try {
//			// port sur lequel le serveur ecoute et attend une connexion
//			this.ssSocket = new ServerSocket(noPort); // creation de l'objet pour etablir une connexion
//			//Thread. sleep(2400);
//			Debug.log("Serveur : attente de connexion d'un client...");
//			this.socket = ssSocket.accept(); // attente de connexion d'un client
//			Debug.log("connection etabli");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} /*catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}*/
//	}
//
//
//	public void closeConnection() {
//		try {
//			this.socket.close();
//			this.ssSocket.close();
//			Debug.log("Serveur : fermeture de la connexion avec le client.");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//
//
//	public static void main(String[] args)
//	{
//		Server testServer = new Server();
//		testServer.setServerConnection();
//	}
//
//	@Override
//	public void RequestSender(String data) { //methode prend un string et l'envoie
//
//		try {
//			OutputStream os = socket.getOutputStream();
//			OutputStreamWriter osw = new OutputStreamWriter(os);
//			BufferedWriter brw = new BufferedWriter(osw);
//			brw.write(data);
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
//			data = sbr.readLine();
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
//			if (value != ',' && isX == true) {
//				tempX += value+"";
//			}else if(value != ',' && isX == false) {
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
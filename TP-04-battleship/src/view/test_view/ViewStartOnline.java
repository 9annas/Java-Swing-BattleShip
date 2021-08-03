//package view;
//
//import java.awt.Dimension;
//import java.awt.GridLayout;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JPanel;
//
//import model.network.Client;
//import model.network.Server;
//import controller.IController;
//import util.Debug;
//
//public class ViewStartOnline implements ActionListener{
//
//	private IController controller;
//	private JFrame TypeUserMenu;
//	private JButton host,client;
//
//	public ViewStartOnline(IController controller)
//	{
//		this.controller = controller;
//	}
//
//	void userTypeMenu() {
//		TypeUserMenu = new JFrame("BattleShip");
//		TypeUserMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		TypeUserMenu.setLocation(600, 200);
//		TypeUserMenu.setSize(400,150);
//
//		TypeUserMenu.setLayout(new GridLayout(1,2));
//
//		JPanel ligne1 = new JPanel();
//		host = new JButton("Host a game");
//		ligne1.add(host);
//		host.setPreferredSize(new Dimension(150, 40));
//		host.addActionListener(this);
//		this.TypeUserMenu.add(ligne1);
//
//		JPanel ligne2 = new JPanel();
//		client = new JButton("join a game");
//		ligne2.add(client);
//		client.setPreferredSize(new Dimension(150, 40));
//		client.addActionListener(this);
//		this.TypeUserMenu.add(ligne2);
//
//		TypeUserMenu.setVisible(true);
//	}
//
//	/*public static void main(String[] args)
//	{
//		ViewStartOnline test = new ViewStartOnline();
//		test.userTypeMenu();
//	}*/
//
//	@Override
//	public void actionPerformed(ActionEvent e) {
//		if (e.getActionCommand().equals("Host a game")) {
//			controller.startOnline(new Server());
//			Debug.log("choix : host a game");
//			//this.TypeUserMenu.dispose();
//		}else {
//			ViewJoinHost clientView = new ViewJoinHost(controller);
//			clientView.clientViewInit();
//			Debug.log("choix : join a game");
//			this.TypeUserMenu.dispose();
//		}
//	}
//}
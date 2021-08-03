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
//import javax.swing.JTextField;
//
//import controller.IController;
//import model.network.Client;
//import util.Debug;
//
//public class ViewJoinHost implements ActionListener {
//
//	private String ipAdress = "";
//	private IController controller;
//	private JFrame frameIp;
//	private JButton client;
//	private JTextField ipAdressField;
//
//	public ViewJoinHost(IController controller) {
//		this.controller = controller;
//	}
//
//	public void clientViewInit() {
//		frameIp = new JFrame("BattleShip");
//		frameIp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frameIp.setLocation(600, 200);
//		frameIp.setSize(400,150);
//
//		frameIp.setLayout(new GridLayout(2,1));
//
//		JPanel ligne1 = new JPanel();
//		ipAdressField = new JTextField();
//		ligne1.add(ipAdressField);
//		ipAdressField.setPreferredSize(new Dimension(150, 40));
//		ipAdressField.addActionListener(this);
//		this.frameIp.add(ligne1);
//
//		JPanel ligne2 = new JPanel();
//		client = new JButton("Join");
//		ligne2.add(client);
//		client.setPreferredSize(new Dimension(150, 40));
//		client.addActionListener(this);
//		this.frameIp.add(ligne2);
//
//		frameIp.setVisible(true);
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
//		if (e.getActionCommand().equals("Join")) {
//			this.ipAdress = this.ipAdressField.getText();
//			if(!this.ipAdress.equals("")) {
//				controller.startOnline(new Client(this.ipAdress));
//				Debug.log("init client");
//				Debug.log(ipAdress);
//				//this.frameIp.dispose();
//			}
//			Debug.log("game joined");
//		}
//	}
//}
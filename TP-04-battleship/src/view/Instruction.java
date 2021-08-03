package view;

import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Instruction {
	
	public Instruction() {
		JFrame unFrame = new JFrame("battlship");
		unFrame.setLayout(new FlowLayout());
		JLabel myText = new JLabel("           Each ship must be placed horizontally or vertically across grid spaces—not diagonally—and the ships can't hang off the grid.");
				/*+ " Ships can touch each other, but they can't occupy the same grid space. You canno)t change the position of the ships after the game begins");*/
		//JLabel myText1 = new JLabel("          vertically across grid spaces—not diagonally—and");
		//JLabel myText2 = new JLabel("           ");
		//JLabel myText3 = new JLabel();
		unFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		unFrame.setLocation(600, 200);
		unFrame.setSize(320, 500);
		unFrame.add(myText);
		//unFrame.add(myText1);
		//unFrame.add(myText2);
		//unFrame.add(myText3);
		
		unFrame.setVisible(true);
	}
}

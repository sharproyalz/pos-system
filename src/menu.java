import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import java.awt.Color;
import javax.swing.border.*;
import java.awt.geom.*;
import javax.swing.table.JTableHeader;
import java.util.ArrayList;
import java.util.List;

public class menu {

    private JFrame frame;

    public menu() {
    	//Home/Menu Page

		//logoutIcon.setIcon(new ImageIcon("C:/Users/Jobie Ann Eullo/Downloads/logoutBtn.png"));
		frame = new JFrame("Menu");
		ImageIcon i = new ImageIcon("C:\\Users\\Jobie Ann Eullo\\OneDrive\\Desktop\\button\\logout.png");
		JLabel logoutIcon = new JLabel(i);
		JLabel label = new JLabel("HOBBIE NATION");
		JButton pos = new JButton("POS");
		JButton inventory = new JButton("INVENTORY");

		//Menu Frame
		frame.setLayout(null);
    	frame.setBounds(10,10,600,400);
    	frame.setVisible(false);
    	frame.setLocationRelativeTo (null);
    	frame.setResizable(false);
    	frame.getContentPane().setBackground(new Color(14, 25, 70));

    	//Menu Add Attribute
    	frame.add(pos);
    	frame.add(inventory);
    	frame.add(logoutIcon);
    	frame.add(label);

    	//Menu SetBounds
		logoutIcon.setBounds(550,10,30,30);

		//logoutIcon.setIcon(new ImageIcon("C:/Users/Jobie Ann Eullo/Downloads/logoutBtn.png"));
    	label.setBounds(80,50,550,100);
		label.setFont (new Font ("Constantia", Font.BOLD, 50));
		label.setForeground(Color.WHITE);
    	pos.setBounds(80,200,200,80);
    	inventory.setBounds(300,200,200,80);
    	pos.setEnabled(true);
		pos.setBackground(new Color(76, 154, 175));
		pos.setBorder(new EmptyBorder(10,10,10,10));
		pos.setFont (new Font ("Constantia", Font.BOLD, 18));
		//pos.setForeground(Color.WHITE);
		inventory.setEnabled(true);
		inventory.setBackground(new Color(174, 216, 238));
		inventory.setBorder(new EmptyBorder(10,10,10,10));
		inventory.setFont (new Font ("Constantia", Font.BOLD, 18));
		//inventory.setForeground(Color.WHITE);

		//Inventory Button
		inventory.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
	    			frame.setVisible(false);
		    		showInventoryFrame();
	    	}
	    });

	     //POS Button
		pos.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
		    		frame.setVisible(false);
		    		showPOSFrame();
	    	}
	    });
	    //Logout icon
	    logoutIcon.addMouseListener(new MouseAdapter(){
	    	public void mouseClicked(MouseEvent e){
	    			frame.setVisible(false);
		    		/*showLoginFrame();*/

	    	}
	    });

    }

    public JFrame getFrame() {
        return frame;
    }

    private static void showInventoryFrame() {
        // Instantiate the test class and display its frame
        JFrame inventoryFrame = new inventory().getFrame();
        inventoryFrame.setVisible(true);
    }

    private static void showPOSFrame() {
        // Instantiate the test class and display its frame
        JFrame POSFrame = new pos().getFrame();
        POSFrame.setVisible(true);
    }
    /*private static void showLoginFrame() {
        // Instantiate the test class and display its frame
        JFrame showLoginFrame = new login().getFrame();
        showLoginFrame.setVisible(true);
    }*/


}
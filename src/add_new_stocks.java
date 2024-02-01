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

public class add_new_stocks {
	private JFrame frame;

    public add_new_stocks() {
    	// Add new stocks
		JFrame frmAddNewStocks = new JFrame("Add New Stocks");
		JLabel lblAddNewStocks = new JLabel("Add New Stocks");
		JLabel lblItemNo = new JLabel("Item No:");
		JLabel lblItemName = new JLabel("Item Name:");
		JLabel lblQuantity= new JLabel("Quantity:");
		JTextField txtItemNo = new JTextField();
		JTextField txtItemName = new JTextField();
		JTextField txtQuantity = new JTextField();
		JButton btnSaveNewStocks = new JButton("Save");
		JButton btnCancelNewStocks = new JButton("Cancel");

		// Add new stocks Frame
		frmAddNewStocks.setLayout(null);
		frmAddNewStocks.setBounds(10,10,400,300);
		frmAddNewStocks.setVisible(false);
		frmAddNewStocks.setLocationRelativeTo (null);

		// Add new stocks Add Attribute
		frmAddNewStocks.add(lblAddNewStocks);
		frmAddNewStocks.add(lblItemNo);
		frmAddNewStocks.add(lblItemName);
		frmAddNewStocks.add(lblQuantity);
		frmAddNewStocks.add(txtItemNo);
		frmAddNewStocks.add(txtItemName);
		frmAddNewStocks.add(txtQuantity);
		frmAddNewStocks.add(btnSaveNewStocks);
		frmAddNewStocks.add(btnCancelNewStocks);

		// Add new stocks SetBounds
		lblAddNewStocks.setBounds(150,10,200,20);
		lblItemNo.setBounds(50,50,100,20);
		txtItemNo.setBounds(150,50,150,20);
		lblItemName.setBounds(50,80,100,20);
		txtItemName.setBounds(150,80,150,20);
		lblQuantity.setBounds(50,110,100,20);
		txtQuantity.setBounds(150,110,150,20);
		btnSaveNewStocks.setBounds(50,160,120,30);
		btnCancelNewStocks.setBounds(180,160,120,30);
    }

	public JFrame getFrame() {
        return frame;
    }


}
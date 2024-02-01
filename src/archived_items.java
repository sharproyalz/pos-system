import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;

import javax.swing.border.*;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.List;

public class archived_items {

	private JFrame frame;

    public archived_items() {
    	//Inventory Page
		frame = new JFrame("Inventory");
		String rows [][]= fetchInventoryData();
        String col[] = {"Item No.", "Item Name", "Category", "Description","Price","Quantity","Date Added"};
		DefaultTableModel modeltb = new DefaultTableModel(rows,col);
		JTable invtable = new JTable(modeltb);
		JScrollPane sp = new JScrollPane(invtable);
		JTableHeader TblHeader = invtable.getTableHeader();
		JButton back = new JButton("Back");
		JLabel invent = new JLabel("Inventory");
		JButton restore = new JButton("Restore Item");
		JLabel legend = new JLabel("Legends:");
		JLabel red = new JLabel ("Low In Stocks");
		JLabel green = new JLabel ("Over Stocks");

	    //Inventory Frame
		frame.setLayout(null);
    	frame.setBounds(10,10,1000,600);
    	frame.setVisible(false);
    	frame.setLocationRelativeTo (null);
    	frame.setResizable(false);
    	frame.getContentPane().setBackground(new Color(14, 25, 70));

		//Inventory Add Attribute
    	frame.add(sp);
    	frame.add(back);
    	frame.add(invent);
    	frame.add(restore);
    	frame.add(red);
    	frame.add(legend);
    	frame.add(green);

		//Inventory SetBounds
		TblHeader.setBackground(new Color(195, 212, 227));
		sp.setBounds(80,100,800,250);
		back.setBounds(10,20,80,30);
		back.setBackground(new Color(162, 223, 203));
		back.setBorder(new EmptyBorder(10,10,10,10));
		invent.setBounds(420,60,200,20);
		invent.setFont (new Font ("Calibri", Font.BOLD, 25));
		invent.setForeground(Color.WHITE);
		restore.setBounds(695,400,180,50);
		restore.setBackground(new Color(252, 232, 105));
		restore.setBorder(new EmptyBorder(10,10,10,10));
		legend.setBounds(850,480,100,20);
		legend.setForeground(Color.WHITE);
		red.setBounds(870,500,100,20);
		red.setForeground(new Color(243, 71, 77));
		green.setBounds(870,520,100,20);
		green.setForeground(new Color(104, 214, 13));

		//Archived Back button
	    back.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
	    		frame.setVisible(false);
		    	showInventoryFrame();
	    	}
	    });
	    
		restore.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
	    		int rowSelected = invtable.getSelectedRow();

	    		if(invtable.getSelectedRow() > -1){
		    		int tblValue = Integer.parseInt(modeltb.getValueAt(rowSelected, 0).toString());
		    		showRestoreItemsFrame(tblValue);
					frame.setVisible(false);
	    		}

	    	}
	    });
    }

	private static String[][] fetchInventoryData() {
        try (Connection connection = database_connection.getConnection()) {
            String query = "SELECT item_no, item_name, category, description, price, quantity, date_added FROM inventory WHERE  isArchived = 1 ";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    // Get the result set metadata to determine the number of columns
                    int columnCount = resultSet.getMetaData().getColumnCount();

                    // Create a list to store the rows
                    java.util.List<String[]> rows = new java.util.ArrayList<>();

                    // Iterate over the result set
                    while (resultSet.next()) {
                        // Create an array to store the current row
                        String[] row = new String[columnCount];

                        // Populate the array with column values
                        for (int i = 1; i <= columnCount; i++) {
                            row[i - 1] = resultSet.getString(i);
                        }

                        // Add the row to the list
                        rows.add(row);
                    }

                    // Convert the list to a 2D array
                    String[][] result = new String[rows.size()][columnCount];
                    result = rows.toArray(result);

                    return result;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database error: " + ex.getMessage());
        }

        return new String[0][0];
    }

	public JFrame getFrame() {
        return frame;
    }

    private static void showInventoryFrame() {
        // Instantiate the test class and display its frame
        JFrame inventoryFrame = new inventory().getFrame();
        inventoryFrame.setVisible(true);
    }

    private static void showRestoreItemsFrame(int item_no) {
        // Instantiate the test class and display its frame
        JFrame restoreItemsFrame = new restore_items(item_no).getFrame();
        restoreItemsFrame.setVisible(true);
    }
}
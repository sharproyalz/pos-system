import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;

import javax.swing.border.*;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.List;

public class inventory {

	private JFrame frame;

    public inventory() {
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
		JButton add = new JButton("Add Item");
		JButton edit = new JButton("Edit Item");
		JButton archived = new JButton("View Archived");
		JButton delete = new JButton("Delete");
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
    	frame.add(add);
    	frame.add(edit);
    	frame.add(archived);
    	frame.add(delete);
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
		add.setBounds(85,400,180,50);
		add.setBackground(new Color(152, 209, 234));
		add.setBorder(new EmptyBorder(10,10,10,10));
		edit.setBounds(288,400,180,50);
		edit.setBackground(new Color(135, 212, 131));
		edit.setBorder(new EmptyBorder(10,10,10,10));
		edit.setEnabled(false);
		delete.setBounds(493,400,180,50);
		delete.setBackground(new Color(243, 71, 77));
		delete.setForeground(new Color(255, 255, 255));
		delete.setBorder(new EmptyBorder(10,10,10,10));
		delete.setEnabled(false);
		archived.setBounds(695,400,180,50);
		archived.setBackground(new Color(252, 232, 105));
		archived.setBorder(new EmptyBorder(10,10,10,10));
		legend.setBounds(850,480,100,20);
		legend.setForeground(Color.WHITE);
		red.setBounds(870,500,100,20);
		red.setForeground(new Color(243, 71, 77));
		green.setBounds(870,520,100,20);
		green.setForeground(new Color(104, 214, 13));
		
		DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable invtable, Object value,
														   boolean isSelected, boolean hasFocus,
														   int rows, int col) {
				Component c = super.getTableCellRendererComponent(invtable, value,
						isSelected, hasFocus, rows, col);
				
				int stocks = Integer.parseInt(value.toString());
				c.setFont(new Font(c.getFont().getFamily(), Font.BOLD, c.getFont().getSize()));
				// c.setForeground(new Color(255, 255, 255));
				
				// Set background color based on the cell value
				if (stocks <= 5) {
					c.setForeground(new Color(243, 71, 77));
				} else {
					// Reset background color for other cells
					c.setForeground(new Color(	104, 214, 13));
				}


				return c;
			}
		};

		// Apply the custom cell renderer to the Age column (column index 2)
		invtable.getColumnModel().getColumn(5).setCellRenderer(cellRenderer);



		invtable.addMouseListener(new MouseAdapter(){
	    	public void mouseClicked(MouseEvent e){
	    		if(invtable.getSelectedRow() > -1){
					edit.setEnabled(true);
					delete.setEnabled(true);
				}
	    	}
	    });
		
		//Inventory Back button
	    back.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
	    		frame.setVisible(false);
		    	showMenuFrame();
	    	}
	    });

		// Add Item button
		add.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
	    		frame.setVisible(false);
	    		showAddNewItemsFrame();
	    	}
	    });

		// Edit Item button
		edit.addActionListener(new ActionListener(){
	     	public void actionPerformed(ActionEvent e){

				 int rowSelected = invtable.getSelectedRow();

				 if(invtable.getSelectedRow() > -1){
					 int tableId = Integer.parseInt(modeltb.getValueAt(rowSelected, 0).toString());
					 String tableItemName = modeltb.getValueAt(rowSelected, 1).toString();
					 String tableCategory = modeltb.getValueAt(rowSelected, 2).toString();
					 String tableDescription = modeltb.getValueAt(rowSelected, 3).toString();
					 int tablePrice = Integer.parseInt(modeltb.getValueAt(rowSelected, 4).toString());
					 int tableQuantity = Integer.parseInt(modeltb.getValueAt(rowSelected, 5).toString());
					 showEditItemsFrame(tableId,tableItemName,tableCategory,tableDescription,tablePrice,tableQuantity);
					 frame.setVisible(false);
				 }
	     	}
		});

		delete.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
	    		int rowSelected = invtable.getSelectedRow();

	    		if(invtable.getSelectedRow() > -1){
		    		int tblValue = Integer.parseInt(modeltb.getValueAt(rowSelected, 0).toString());
		    		showDeleteItemFrame(tblValue);
					frame.setVisible(false);
	    		}

	    	}
	    });

		archived.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
	    		frame.setVisible(false);
	    		showArchivedItemsFrame();

	    	}
	    });
    }

	private static String[][] fetchInventoryData() {
        try (Connection connection = database_connection.getConnection()) {
            String query = "SELECT item_no, item_name, category, description, price, quantity, date_added FROM inventory WHERE  isArchived = 0 ";
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

    private static void showAddNewStocksFrame() {
        // Instantiate the test class and display its frame
        JFrame addNewStocksFrame = new add_new_stocks().getFrame();
        addNewStocksFrame.setVisible(true);
    }

    private static void showAddNewItemsFrame() {
        // Instantiate the test class and display its frame
        JFrame addNewItemsFrame = new add_new_items().getFrame();
        addNewItemsFrame.setVisible(true);
    }

    private static void showEditItemsFrame(int item_no, String item_name, String category, String description, int price, int quantity) {
        // Instantiate the test class and display its frame
        JFrame addEditItemsFrame = new edit_items(item_no, item_name, category, description, price, quantity).getFrame();
        addEditItemsFrame.setVisible(true);	
    }

    private static void showDeleteItemFrame(int item_no) {
        // Instantiate the test class and display its frame
        JFrame deleteItemFrame = new delete_item(item_no).getFrame();
        deleteItemFrame.setVisible(true);
    }

    private static void showMenuFrame() {
        // Instantiate the test class and display its frame
        JFrame showMenuFrame = new menu().getFrame();
        showMenuFrame.setVisible(true);
    }

    private static void showArchivedItemsFrame() {
        // Instantiate the test class and display its frame
        JFrame showMenuFrame = new archived_items().getFrame();
        showMenuFrame.setVisible(true);
    }

}
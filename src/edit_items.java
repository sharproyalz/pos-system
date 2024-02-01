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

public class edit_items {

	private JFrame frame;

    public edit_items(int item_no, String item_name, String category, String description, int price, int quantity) {
    	// Edit item
		frame = new JFrame("Edit Item");
		JLabel lblEditItems = new JLabel("Edit Item");
		JLabel lblEditItemName = new JLabel("Item Name:");
		JLabel lblEditCategory = new JLabel("Category:");
		JLabel lblEditDescription = new JLabel("Description:");
		JLabel lblEditPrice = new JLabel("Price:");
		JLabel lblEditQuantity = new JLabel("Quantity:");
		JLabel lblEditImage = new JLabel("Image:");
		JTextField txtEditItemName = new JTextField(item_name);
		JTextField txtEditCategory = new JTextField(category);
		JTextField txtEditDescription = new JTextField(description);
		JTextField txtEditPrice = new JTextField(String.valueOf(price));
		JTextField txtEditQuantity = new JTextField(String.valueOf(quantity));
		JTextField txtEditImage = new JTextField();
		JButton btnSaveEditItem = new JButton("Save");
		JButton btnCancelEditItem = new JButton("Cancel");

		// Edit items Frame
		frame.setLayout(null);
		frame.setBounds(10,10,400,450);
		frame.setVisible(false);
		frame.setLocationRelativeTo (null);

		// Edit item Add Attribute
		frame.add(lblEditItems);
		frame.add(lblEditItemName);
		frame.add(txtEditItemName);
		frame.add(lblEditCategory);
		frame.add(txtEditCategory);
		frame.add(lblEditDescription);
		frame.add(txtEditDescription);
		frame.add(lblEditPrice);
		frame.add(txtEditPrice);
		frame.add(lblEditQuantity);
		frame.add(txtEditQuantity);
		frame.add(lblEditImage);
		frame.add(txtEditImage);
		frame.add(btnSaveEditItem);
		frame.add(btnCancelEditItem);

		// Add new items setBounds
		lblEditItems.setBounds(150,45,200,20);
		lblEditItemName.setBounds(50,80,100,20);
		txtEditItemName.setBounds(150,80,150,20);
		lblEditCategory.setBounds(50,110,100,20);
		txtEditCategory.setBounds(150,110,150,20);
		lblEditDescription.setBounds(50,140,100,20);
		txtEditDescription.setBounds(150,140,150,20);
		lblEditPrice.setBounds(50,170,100,20);
		txtEditPrice.setBounds(150,170,150,20);
		lblEditQuantity.setBounds(50,200,100,20);
		txtEditQuantity.setBounds(150,200,150,20);
		lblEditImage.setBounds(50,230,100,20);
		txtEditImage.setBounds(150,230,150,20);
		btnSaveEditItem.setBounds(50,310,120,30);
		btnCancelEditItem.setBounds(180,310,120,30);

		btnSaveEditItem.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
				String item_name = txtEditItemName.getText();
				String category = txtEditCategory.getText();
				String description = txtEditDescription.getText();
				int price = Integer.parseInt(txtEditPrice.getText());
				int quantity = Integer.parseInt(txtEditQuantity.getText());

	    		confirm_edit(item_no, item_name, category, description, price, quantity);
	    		frame.setVisible(false);	
				showInventoryFrame();
	    	}
	    });

		btnCancelEditItem.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
	    		frame.setVisible(false);
				showInventoryFrame();
	    	}
	    });
    }

	private void confirm_edit(int item_no, String item_name, String category, String description, int price, int quantity) {
		try (Connection connection = database_connection.getConnection()) {
            String editQuery = "UPDATE `inventory` SET `item_name` = ?, `category` = ?, `description` = ?, `price` = ?, `quantity` = ? WHERE `inventory`.`item_no` = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(editQuery)) {
            	preparedStatement.setString(1, item_name);
            	preparedStatement.setString(2, category);
            	preparedStatement.setString(3, description);
            	preparedStatement.setInt(4, price);
            	preparedStatement.setInt(5, quantity);
            	preparedStatement.setInt(6, item_no);

                preparedStatement.executeUpdate();
                JOptionPane.showMessageDialog(null, "Item edited successfully!");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Database error: " + ex.getMessage());
        }
	}

	public JFrame getFrame() {
        return frame;
    }

	private static void showInventoryFrame() {
        // Instantiate the test class and display its frame
        JFrame inventoryFrame = new inventory().getFrame();
        inventoryFrame.setVisible(true);
    }
}
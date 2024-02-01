import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import java.awt.Color;
import javax.swing.border.*;
import java.awt.geom.*;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.table.JTableHeader;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class add_new_items {

	private JFrame frame;
	public byte[] imageBytes;

    public add_new_items() {
		// Add new item
		frame = new JFrame("Add New Item");
		JLabel lblAddNewItems = new JLabel("Add New Items");
		JLabel lblNewItemName = new JLabel("Item Name:");
		JLabel lblNewCategory = new JLabel("Category:");
		JLabel lblNewDescription = new JLabel("Description:");
		JLabel lblNewPrice = new JLabel("Price:");
		JLabel lblNewQuantity = new JLabel("Quantity:");
		JLabel lblNewDateAdded = new JLabel("Date added:");
		JLabel lblNewImage = new JLabel("Image:");
		JTextField txtNewItemName = new JTextField();
		JTextField txtNewCategory = new JTextField();
		JTextField txtNewDescription = new JTextField();
		JTextField txtNewPrice = new JTextField();
		JTextField txtNewQuantity = new JTextField();
		JTextField txtNewDateAdded = new JTextField();
		JButton btnUpload = new JButton("Upload");
		JButton btnSaveNewItem = new JButton("Save");
		JButton btnCancelNewItem = new JButton("Cancel");

	    // Add new items Frame
		frame.setLayout(null);
		frame.setBounds(10,10,400,450);
		frame.setVisible(false);
		frame.setLocationRelativeTo (null);

		// Add new items Add Attribute
		frame.add(lblAddNewItems);
		frame.add(lblNewItemName);
		frame.add(txtNewItemName);
		frame.add(lblNewCategory);
		frame.add(txtNewCategory);
		frame.add(lblNewDescription);
		frame.add(txtNewDescription);
		frame.add(lblNewPrice);
		frame.add(txtNewPrice);
		frame.add(lblNewQuantity);
		frame.add(txtNewQuantity);
		frame.add(lblNewDateAdded);
		frame.add(txtNewDateAdded);
		frame.add(lblNewImage);
		frame.add(btnUpload);
		frame.add(btnSaveNewItem);
		frame.add(btnCancelNewItem);

		// Add new items setBounds
		lblAddNewItems.setBounds(150,45,200,20);
		lblNewItemName.setBounds(50,80,100,20);
		txtNewItemName.setBounds(150,80,150,20);
		lblNewCategory.setBounds(50,110,100,20);
		txtNewCategory.setBounds(150,110,150,20);
		lblNewDescription.setBounds(50,140,100,20);
		txtNewDescription.setBounds(150,140,150,20);
		lblNewPrice.setBounds(50,170,100,20);
		txtNewPrice.setBounds(150,170,150,20);
		lblNewQuantity.setBounds(50,200,100,20);
		txtNewQuantity.setBounds(150,200,150,20);
		lblNewImage.setBounds(50,230,100,20);
		btnUpload.setBounds(150,230,150,20);
		btnSaveNewItem.setBounds(50,310,120,30);
		btnCancelNewItem.setBounds(180,310,120,30);

		btnSaveNewItem.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
				String item_name = txtNewItemName.getText();
				String category = txtNewCategory.getText();
				String description = txtNewDescription.getText();
				int price = Integer.parseInt(txtNewPrice.getText());
				int quantity = Integer.parseInt(txtNewQuantity.getText());

				System.out.print(imageBytes);
				add_item(item_name,category,description,price,quantity,imageBytes);

				frame.setVisible(false);
				showInventoryFrame();
	    	}
		});

		btnCancelNewItem.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
				frame.setVisible(false);
				showInventoryFrame();
	    	}
		});

		btnUpload.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JFileChooser fileChooser = new JFileChooser();
                    int result = fileChooser.showOpenDialog(null);

                    if (result == JFileChooser.APPROVE_OPTION) {
                        File selectedFile = fileChooser.getSelectedFile();
                        try {
                            imageBytes = convertFileToByteArray(selectedFile);

                            // Save the image to the database
                            // saveImageToDatabase(imageBytes);

                            JOptionPane.showMessageDialog(null, "Image uploaded successfully!");
                        } catch (IOException ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(null, "Error uploading image.");
                        }
                    }
                }
            });
    }

	private static byte[] convertFileToByteArray(File file) throws IOException {
        FileInputStream fis = new FileInputStream(file);
        byte[] buffer = new byte[(int) file.length()];
        fis.read(buffer);
        fis.close();
        return buffer;
    }

	private void add_item(String item_name, String category, String description, int price, int quantity, byte[] imageBytes) {
		try (Connection connection = database_connection.getConnection()) {
            String query = "INSERT INTO inventory (item_name, category, description, price, quantity, date_added, image) VALUES (?, ?, ?, ?, ?, NOW(), ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, item_name);
                preparedStatement.setString(2, category);
                preparedStatement.setString(3, description);
                preparedStatement.setInt(4, price);
				preparedStatement.setInt(5, quantity);

				ByteArrayInputStream inputStream = new ByteArrayInputStream(imageBytes);
				preparedStatement.setBlob(6, inputStream);

				JOptionPane.showMessageDialog(frame, "Item added succesfully.");
                preparedStatement.executeUpdate();
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
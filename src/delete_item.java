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

public class delete_item {

	private JFrame frame;

    public delete_item(int item_no) {
    	// Delete item
		frame = new JFrame("Edit Item");
		JLabel lblDeleteItems = new JLabel(String.valueOf("Are you sure you want to delete this item?"));
		JButton btnDeleteItem = new JButton("Delete");
		JButton btnCancelDeleteItem = new JButton("Cancel");

    	// Delete item Frame
		frame.setLayout(null);
		frame.setBounds(10,10,400,250);
		frame.setVisible(false);
		frame.setLocationRelativeTo (null);

		// Delete item Add Attribute
		frame.add(lblDeleteItems);
		frame.add(btnDeleteItem);
		frame.add(btnCancelDeleteItem);

		// Delete item setBounds
		lblDeleteItems.setBounds(65,40,300,20);
		btnDeleteItem.setBounds(50,90,120,30);
		btnCancelDeleteItem.setBounds(180,90,120,30);

		btnDeleteItem.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
	    		confirm_delete(item_no);
	    		frame.setVisible(false);	
				showInventoryFrame();
	    	}
	    });

		btnCancelDeleteItem.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
	    		frame.setVisible(false);
				showInventoryFrame();
	    	}
	    });
    }

	private void confirm_delete(int item_no) {
		try (Connection connection = database_connection.getConnection()) {
            String deleteQuery = "UPDATE `inventory` SET `isArchived` = '1' WHERE `inventory`.`item_no` = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
            	preparedStatement.setInt(1, item_no);

                preparedStatement.executeUpdate();
                JOptionPane.showMessageDialog(null, "Item deleted successfully!");
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
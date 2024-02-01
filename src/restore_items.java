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

public class restore_items {

	private JFrame frame;

    public restore_items(int item_no) {
    	// Restore item
		frame = new JFrame("Edit Item");
		JLabel lblRestoreItems = new JLabel(String.valueOf("Are you sure you want to restore this item?"));
		JButton btnRestoreItem = new JButton("Restore");
		JButton btnCancelRestoreItem = new JButton("Cancel");

    	// Restore item Frame
		frame.setLayout(null);
		frame.setBounds(10,10,400,250);
		frame.setVisible(false);
		frame.setLocationRelativeTo (null);

		// Restore item Add Attribute
		frame.add(lblRestoreItems);
		frame.add(btnRestoreItem);
		frame.add(btnCancelRestoreItem);

		// Restore item setBounds
		lblRestoreItems.setBounds(65,40,300,20);
		btnRestoreItem.setBounds(50,90,120,30);
		btnCancelRestoreItem.setBounds(180,90,120,30);

		btnRestoreItem.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
	    		confirm_restore(item_no);
	    		frame.setVisible(false);	
				showArchivedItemsFrame();
	    	}
	    });

		btnCancelRestoreItem.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
	    		frame.setVisible(false);
				showArchivedItemsFrame();
	    	}
	    });
    }

	private void confirm_restore(int item_no) {
		try (Connection connection = database_connection.getConnection()) {
            String restoreQuery = "UPDATE `inventory` SET `isArchived` = '0' WHERE `inventory`.`item_no` = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(restoreQuery)) {
            	preparedStatement.setInt(1, item_no);

                preparedStatement.executeUpdate();
                JOptionPane.showMessageDialog(null, "Item restored successfully!");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Database error: " + ex.getMessage());
        }
	}

	public JFrame getFrame() {
        return frame;
    }

    private static void showArchivedItemsFrame() {
        // Instantiate the test class and display its frame
        JFrame archivedItemsFrame = new archived_items().getFrame();
        archivedItemsFrame.setVisible(true);
    }
}
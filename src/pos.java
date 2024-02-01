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

public class pos {
	private JFrame frame;

	public static double subtotal = 0.0;
	public static double totalamount = 0.0;
	private static List<String> selectedItems = new ArrayList<>();
	private static double getPriceForItem(String itemName) {
        switch (itemName) {
            case "Item 1":
                return 19.99;
            case "Item 2":
                return 29.99;
            case "Item 3":
                return 39.99;
            default:
                return 0.0;  // Default value, change accordingly
        }
    }

    public pos() {
    	//POS Frame
		frame = new JFrame("POS");
      	frame.setLayout(null);
        frame.setLocationRelativeTo (null);
       	frame.setVisible(false);
     	frame.setBounds(100, 100, 1200, 600);
     	frame.setResizable(false);
     	frame.getContentPane().setBackground(new Color(14, 25, 70));

		// paymentFrame
    	JFrame paymentFrame = new JFrame("Payment");
        paymentFrame.setLayout(null);
        paymentFrame.setBounds(100, 100, 400, 300);
        paymentFrame.getContentPane().setBackground(new Color(14, 25, 70));

        // Subtotal laber and Textfield
        JTextField subtotalTextField = new JTextField("Subtotal: 0.00");
        subtotalTextField.setBounds(10, 10, 250, 30);
        subtotalTextField.setForeground(Color.white);
        subtotalTextField.setEnabled(false);
        subtotalTextField.setFont(new Font("Calibri", Font.BOLD, 18));

        // Textarea receipt
        JTextArea itemTextArea = new JTextArea("Selected Items:\n");
        itemTextArea.setEnabled(false);
        itemTextArea.setFont(new Font("Calibri", Font.PLAIN, 18));
        JScrollPane scrollPane = new JScrollPane(itemTextArea);
        scrollPane.setBounds(10, 50, 500, 500);

		//Itemno & txtfield:
        JLabel itemNoLabel = new JLabel("Item No:");
        itemNoLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        itemNoLabel.setBounds(530, 50, 100, 30);
        itemNoLabel.setForeground(Color.white);
 		JTextField itemNoTextField = new JTextField();
        itemNoTextField.setFont(new Font("Calibri", Font.BOLD, 18));
        itemNoTextField.setBounds(530, 170, 250, 30);

		// Itemname & txtfield:
        JLabel itemNameLabel = new JLabel("Item Name:");
        itemNameLabel.setFont(new Font("Calibri", Font.BOLD, 18));
        itemNameLabel.setForeground(Color.white);
        itemNameLabel.setBounds(530, 210, 100, 30);
        JTextField itemNameTextField = new JTextField();
        itemNameTextField.setFont(new Font("Calibri", Font.PLAIN, 18));
        itemNameTextField.setBounds(640, 210, 250, 30);

		// Category & txtfield:
        JLabel categoryLabel = new JLabel("Category:");
        categoryLabel.setFont(new Font("Calibri", Font.BOLD, 18));
        categoryLabel.setForeground(Color.white);
        categoryLabel.setBounds(530, 250, 100, 30);
      	JTextField categoryTextField = new JTextField();
        categoryTextField.setFont(new Font("Calibri", Font.PLAIN, 18));
        categoryTextField.setBounds(640, 250, 250, 30);

		// Description & txtfield:
        JLabel descriptionLabel = new JLabel("Description:");
        descriptionLabel.setFont(new Font("Calibri", Font.BOLD, 18));
        descriptionLabel.setForeground(Color.white);
        descriptionLabel.setBounds(530, 290, 110, 30);
        JTextField descriptionTextField = new JTextField();
        descriptionTextField.setFont(new Font("Calibri", Font.PLAIN, 18));
        descriptionTextField.setBounds(640, 290, 250, 30);

       	// Price & txtfield:
        JLabel priceLabel = new JLabel("Price:");
        priceLabel.setFont(new Font("Calibri", Font.BOLD, 18));
        priceLabel.setBounds(530, 330, 80, 30);
        priceLabel.setForeground(Color.white);
        JTextField priceTextField = new JTextField();
        priceTextField.setFont(new Font("Calibri", Font.PLAIN, 18));
        priceTextField.setBounds(640, 330, 120, 30);

		// Quantity & txtfield:
        JLabel quantityLabel = new JLabel("Quantity:");
        quantityLabel.setFont(new Font("Calibri", Font.BOLD, 18));
        quantityLabel.setBounds(770, 330, 100, 30);
        quantityLabel.setForeground(Color.white);
        JTextField quantityTextField = new JTextField();
        quantityTextField.setFont(new Font("Calibri", Font.PLAIN, 18));
        quantityTextField.setBounds(880, 330, 120, 30);

		// total & txtfield:
        JLabel totalLabel = new JLabel("Total:");
        totalLabel.setFont(new Font("Calibri", Font.BOLD, 18));
        totalLabel.setBounds(530, 370, 80, 30);
        totalLabel.setForeground(Color.white);
        JTextField totalTextField = new JTextField();
        totalTextField.setEnabled(false);
        totalTextField.setFont(new Font("Calibri", Font.PLAIN, 18));
        totalTextField.setBounds(640, 370, 120, 30);

        // add button
        JButton addButton = new JButton("Add");
        addButton.setBackground(new Color(162, 223, 203));
        addButton.setFont(new Font("Calibri", Font.PLAIN, 18));
        addButton.setBounds(530, 430, 100, 40);


        // proceed button
        JButton proceedButton = new JButton("Proceed");
        proceedButton.setFont(new Font("Calibri", Font.PLAIN, 18));
        proceedButton.setBounds(640, 430, 120, 40);
     	proceedButton.setBackground(new Color(252, 232, 105));

        // clear button
        JButton clearButton = new JButton("Clear");
        clearButton.setFont(new Font("Calibri", Font.PLAIN, 18));
        clearButton.setBounds(770, 430, 100, 40);

        // Menu for items
        String[] items = {"Item 1", "Item 2", "Item 3"};
        JComboBox itemMenu = new JComboBox<>(items);
        itemMenu.setFont(new Font("Arial", Font.PLAIN, 18));
        itemMenu.setBounds(640, 50, 250, 30);

		// title Labels
        JLabel titleLabel = new JLabel("Payment");
        titleLabel.setFont(new Font("Calibri", Font.BOLD, 24));
        titleLabel.setBounds(150, 10, 100, 30);

        //totalAmount TextFields and label
        JTextField totalAmountTextField = new JTextField();
        totalAmountTextField.setFont(new Font("Arial", Font.PLAIN, 18));
        totalAmountTextField.setBounds(200, 60, 150, 30);
        totalAmountTextField.setEditable(false);
        totalAmountTextField.setText(String.format("%.2f", subtotal));
        JLabel totalAmountLabel = new JLabel("Total Amount:");
        totalAmountLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        totalAmountLabel.setBounds(50, 60, 150, 30);

		// cashGiven TextFields and label
        JTextField cashGivenTextField = new JTextField();
        cashGivenTextField.setFont(new Font("Arial", Font.PLAIN, 18));
        cashGivenTextField.setBounds(200, 100, 150, 30);
        JLabel cashGivenLabel = new JLabel("Cash Given:");
        cashGivenLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        cashGivenLabel.setBounds(50, 100, 150, 30);

		// changeTextField and label
        JTextField changeTextField = new JTextField();
        changeTextField.setFont(new Font("Arial", Font.PLAIN, 18));
        changeTextField.setBounds(200, 140, 150, 30);
        changeTextField.setEditable(false);
        JLabel changeLabel = new JLabel("Change:");
        changeLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        changeLabel.setBounds(50, 140, 150, 30);

        //printReceipt Buttons
        JButton printReceiptButton = new JButton("Print Receipt");
        printReceiptButton.setFont(new Font("Arial", Font.PLAIN, 18));
        printReceiptButton.setBounds(50, 200, 150, 40);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setFont(new Font("Arial", Font.PLAIN, 18));
        cancelButton.setBounds(220, 200, 100, 40);

         itemMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

           		String selectedItem = (String) itemMenu.getSelectedItem();


	            if ("Item 1".equals(selectedItem)) {
	            itemNoTextField.setText("001");
	            itemNameTextField.setText("Item 1");
	            categoryTextField.setText("Category 1");
	            descriptionTextField.setText("Description for Item 1");
	            priceTextField.setText("19.99");

	        } else if ("Item 2".equals(selectedItem)) {
	            itemNoTextField.setText("002");
	            itemNameTextField.setText("Item 2");
	            categoryTextField.setText("Category 2");
	            descriptionTextField.setText("Description for Item 2");
	            priceTextField.setText("29.99");

	        } else if ("Item 3".equals(selectedItem)) {
	            itemNoTextField.setText("003");
	            itemNameTextField.setText("Item 3");
	            categoryTextField.setText("Category 3");
	            descriptionTextField.setText("Description for Item 3");
	            priceTextField.setText("39.99");
      	  	}
          }
        });

        addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedItem = (String) itemMenu.getSelectedItem();
				String quantityText = quantityTextField.getText();

				if (quantityText.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please enter a quantity.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}

				try {
					int quantity = Integer.parseInt(quantityText);

					// Update the receipt
					String receiptLine = selectedItem + " x" + quantity + "\n";
					itemTextArea.append(receiptLine);

					// Update the subtotal
					double price = getPriceForItem(selectedItem);
					subtotal += price * quantity;
					subtotalTextField.setText("Subtotal:" + String.format("%.2f", subtotal));

					totalamount = subtotal;
					totalTextField.setText(String.format("%.2f",totalamount));

				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Invalid quantity. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
        proceedButton.addActionListener(new ActionListener() {
   		 	public void actionPerformed(ActionEvent e) {
			if (subtotal == 0.0) {
				JOptionPane.showMessageDialog(null, "Please add items before proceeding.", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			else{
				frame.setVisible(false);
				paymentFrame.setVisible(true);
			}
			try {
						double cashGiven = Double.parseDouble(cashGivenTextField.getText());
						double totalAmount = subtotal;
						double change = cashGiven - totalAmount;

						changeTextField.setText(String.format("%.2f", change));

					} catch (NumberFormatException ex) {
						// Handle invalid input (non-numeric values)
						changeTextField.setText("");
					}
				}

			});


        clearButton.addActionListener(new ActionListener() {
   			public void actionPerformed(ActionEvent e) {
				itemTextArea.setText("Selected Items:\n");
				subtotal = 0.0;
				subtotalTextField.setText("Subtotal: 0.00");
				itemMenu.setSelectedIndex(0);
				itemNoTextField.setText("");
				itemNameTextField.setText("");
				categoryTextField.setText("");
				descriptionTextField.setText("");
				priceTextField.setText("");
				quantityTextField.setText("");
			}
		});

		frame.add(subtotalTextField);
		frame.add(scrollPane);
		frame.add(itemNoLabel);
		frame.add(itemMenu);
		frame.add(itemNoTextField);
		frame.add(itemNameTextField);
		frame.add(itemNameLabel);
		frame.add(categoryTextField);
		frame.add(categoryLabel);
		frame.add(priceLabel);
		frame.add(priceTextField);
		frame.add(quantityLabel);
		frame.add(quantityTextField);
		frame.add(addButton);
		frame.add(proceedButton);
		frame.add(clearButton);
		frame.add(descriptionLabel);
		frame.add(descriptionTextField);
		frame.add(totalLabel);
		frame.add(totalTextField);

		paymentFrame.add(titleLabel);
		paymentFrame.add(totalAmountLabel);
		paymentFrame.add(cashGivenLabel);
		paymentFrame.add(changeLabel);
		paymentFrame.add(totalAmountTextField);
		paymentFrame.add(cashGivenTextField);
		paymentFrame.add(changeTextField);
		paymentFrame.add(printReceiptButton);
		paymentFrame.add(cancelButton);
		paymentFrame.setVisible(false);

    }

	public JFrame getFrame() {
        return frame;
    }
}
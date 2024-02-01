import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.Color;
import javax.swing.border.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class login {
	private static JFrame f;

	//Global Variable Cashier
	private static String usernamecash = "Cashier";
	private static String passwordcash = "Cashier";

	public static void main(String[] args) {
		//Login Frame
    	f = new JFrame("Login");
    	JLabel logo = new JLabel("HOBBIE NATION");
    	JLabel user = new JLabel("Username:");
    	JLabel pass = new JLabel("Password:");
    	JTextField usertf = new JTextField();
    	JPasswordField passtf = new JPasswordField();
		JButton login = new JButton("Login");

		//Login Frame
		f.setLayout(null);
    	f.setBounds(10,10,500,400);
    	f.setVisible(true);
    	f.setLocationRelativeTo (null);
    	f.setResizable(false);
    	f.getContentPane().setBackground(new Color(14, 25, 70));
    	//f.add(new JLabel(new ImageIcon("images/bg1.jpg")));

		//Login Add Attribute
    	f.add(login);
    	f.add(user);
    	f.add(pass);
    	f.add(usertf);
    	f.add(passtf);
    	f.add(logo);

		//Login SetBounds
		login.setBounds(130,270,250,50);
		login.setEnabled(true);
		login.setBackground(new Color(162, 223, 203));
		login.setFont (new Font ("Constantia", Font.BOLD, 16));
		login.setForeground(Color.BLACK);
		login.setBorder(new EmptyBorder(10,10,10,10));
		logo.setBounds(80,50,350,100);
		logo.setFont (new Font ("Constantia", Font.BOLD, 40));
		logo.setForeground(Color.WHITE);
		user.setBounds(100,160,100,30);
		user.setForeground(Color.WHITE);
		user.setFont (new Font ("Constantia", Font.BOLD, 16));
		pass.setBounds(100,210,100,30);
		pass.setForeground(Color.WHITE);
		pass.setFont (new Font ("Constantia", Font.BOLD, 16));
		passtf.setBounds(200,210,200,30);
		usertf.setBounds(200,160,200,30);

		//Login function
		login.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
				String usernameinput = usertf.getText();
				String passwordinput = passtf.getText();

	    		// Admin
				if(authenticate(usernameinput,passwordinput)){
				    JOptionPane.showMessageDialog(login,"Login Successfully!", "Login", JOptionPane.INFORMATION_MESSAGE);
						f.dispose();
					    showMenuFrame();
				}
		    	/*//Cashier
	    		else if((usernamecash.equals(usernameinput)) && (passwordcash.equals(usernameinput))){
	    			JOptionPane.showMessageDialog(login,"Login Successfully!", "Login", JOptionPane.INFORMATION_MESSAGE);
		    			f.dispose();
					    new menu();
	    		}*/
	    		else{
	    			JOptionPane.showMessageDialog(login,"Incorrect username or password. Please try again!", "Incorrect Login", JOptionPane.ERROR_MESSAGE);
					usertf.setText("");
					passtf.setText("");
	    		}
	    	}
	    });
	}

	private static boolean authenticate(String username, String password) {
	    try (Connection connection = database_connection.getConnection()) {
            String query = "SELECT * FROM users WHERE username = ? AND password = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    return resultSet.next(); // Check if the query returned any results
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            //System.out.println("ERROR");
        }

        return false;
	}


	private static void showMenuFrame() {
        // Instantiate the test class and display its frame
        JFrame menuFrame = new menu().getFrame();
        menuFrame.setVisible(true);
    }

}
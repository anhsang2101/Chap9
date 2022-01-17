package ex3;


import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;

public class ex3 extends JFrame {
	JFrame f = new JFrame("Account");
	JLabel lbUser = new JLabel("Username");
	JTextField tfUser = new JTextField(10);
	JLabel lbPass = new JLabel("Password");
	JPasswordField tfPass = new JPasswordField(10);
	JButton btnSignUp = new JButton("Sign Up");
	JLabel lbRole = new JLabel("Role");
	JComboBox Role = new JComboBox();
	public ex3() {
		
		f.setLocation(300, 300);
		f.getContentPane().setLayout(new GridLayout(4,1));
		f.getContentPane().add(lbUser);
		f.getContentPane().add(tfUser);
		f.getContentPane().add(lbPass);
		f.getContentPane().add(tfPass);
		f.getContentPane().add(lbRole);
		Role.setModel(new DefaultComboBoxModel(new String[] {"Admin", "Teacher", "Student"}));
		f.getContentPane().add(Role);
		f.getContentPane().add(btnSignUp);
		
		btnSignUp.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ConnectDB c = new ConnectDB();
				c.connect();
				int record = c.ExcuteUpdate("Insert into Account values('"+tfUser.getText()+"','"+tfPass.getText()+"','"+Role.getSelectedItem().toString()+"')");
		    	if(record>0) JOptionPane.showMessageDialog(null, "Success");
			}
		});
		f.setSize(200,200);
		f.pack();
	    f.setVisible(true);
	}
	public static void main(String[] args) {
		new ex3();
	}
}
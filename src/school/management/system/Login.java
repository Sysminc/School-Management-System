package school.management.system;

import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Login extends JFrame implements ActionListener{

    Font descFont;
    Font titleFont;
    
    JButton login, cancel;
    JTextField tfUsername, tfPassword;
    
    Login() {

        try {
            descFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/font/static/Cabin-Regular.ttf")).deriveFont(14f);
            titleFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/font/static/Cabin-Bold.ttf")).deriveFont(16f);
        } catch (FontFormatException | IOException ex) {
            System.err.println("Terjadi kesalahan saat memuat font: " + ex.getMessage());
        }

        getContentPane().setBackground(Color.BLUE);
        setLayout(null);
        
        ImageIcon i1 = new ImageIcon("src/icon/private.png");
        Image i2 = i1.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(350, 20, 200, 200);
        add(image);

        JLabel lblUsername = new JLabel("Username");
        lblUsername.setBounds(40, 40, 100, 20);
        lblUsername.setFont(titleFont);
        lblUsername.setForeground(Color.WHITE);
        add(lblUsername);

        tfUsername = new JTextField();
        tfUsername.setBounds(150, 40, 180, 25);
        tfUsername.setFont(descFont);
        add(tfUsername);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setBounds(40, 100, 100, 20);
        lblPassword.setFont(titleFont);
        lblPassword.setForeground(Color.WHITE);
        add(lblPassword);

        tfPassword = new JPasswordField();
        tfPassword.setBounds(150, 100, 180, 25);
        tfPassword.setFont(descFont);
        add(tfPassword);
        
        login = new JButton("Login");
        login.setBounds(40, 180, 120, 30);
        login.setBackground(Color.white);
        login.setForeground(Color.black);
        login.setFont(titleFont);
        login.addActionListener(this);
        add(login);

        cancel = new JButton("Cancel");
        cancel.setBounds(210, 180, 120, 30);
        cancel.setBackground(Color.white);
        cancel.setForeground(Color.black);
        cancel.setFont(titleFont);
        cancel.addActionListener(this);
        add(cancel);

        setSize(600, 300);
        setLocation(400, 250);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        if (ae.getSource() == login) {
            String username = tfUsername.getText();
            String password = tfPassword.getText();
            
            String queryExe = "select * from user where username = '"+username+"' and password = '"+password+"'";
            
            try {
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery(queryExe);
                
                if (rs.next()) {
                    setVisible(false);
                    new DashBoard();
                } else {
                    JOptionPane.showMessageDialog(null, "User and Password don't match");
                    setVisible(false);
                }
                c.s.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        } else if (ae.getSource() == cancel) {
            setVisible(false);
        }
    }

    public static void main(String[] args) {

        new Login();
    }
}

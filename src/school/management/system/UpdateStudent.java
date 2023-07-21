package school.management.system;

import com.toedter.calendar.JDateChooser;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.sql.*;

public class UpdateStudent extends JFrame implements ActionListener{
    
    JTextField tfprovinsi, tfkota, tfphone, tfemail, tfnama, tffname;
    JDateChooser tftanggalLahir;
    JLabel labelNPM;
    JButton submit, cancel;
    JComboBox cbprodi;
    Choice crollno;
    
    Font titleMain;
    Font titleSecond;
    Font textInput;
    
    UpdateStudent() {
        
        try {
            titleMain = Font.createFont(Font.TRUETYPE_FONT, new File("src/font/static/Cabin-Bold.ttf")).deriveFont(30f);
            titleSecond = Font.createFont(Font.TRUETYPE_FONT, new File("src/font/static/Cabin-Italic.ttf")).deriveFont(20f);
            textInput = Font.createFont(Font.TRUETYPE_FONT, new File("src/font/static/Cabin-Regular.ttf")).deriveFont(14f);
        } catch (FontFormatException | IOException ex) {
            System.err.println("Terjadi kesalahan saat memuat font: " + ex.getMessage());
        }
        
        setSize(1000, 700);
        setLocation(175, 25);
        
        setLayout(null);
        
        JLabel heading = new JLabel("Update Mahasiswa");
        heading.setBounds(50, 10, 500, 50);
        heading.setFont(titleMain);
        add(heading);
        
        JLabel lblrollnumber = new JLabel("Pilih NPM");
        lblrollnumber.setBounds(50, 80, 100, 30);
        lblrollnumber.setFont(titleSecond);
        add(lblrollnumber);
        
        crollno = new Choice();
        crollno.setBounds(200, 80, 150, 30);
        add(crollno);
        
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from mahasiswa");
            while(rs.next()) {
                crollno.add(rs.getString("NPM"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        JLabel lblnama = new JLabel("Nama");
        lblnama.setBounds(50, 150, 100, 30);
        lblnama.setFont(titleSecond);
        add(lblnama);
        
        tfnama = new JTextField();
        tfnama.setBounds(200, 150, 150, 30);
        tfnama.setFont(titleSecond);
        add(tfnama);
        
        JLabel lblfname = new JLabel("Nama Wali");
        lblfname.setBounds(500, 150, 200, 30);
        lblfname.setFont(titleSecond);
        add(lblfname);
        
        tffname = new JTextField();
        tffname.setBounds(700, 150, 150, 30);
        tffname.setFont(titleSecond);
        add(tffname);
        
        JLabel lblNPM = new JLabel("NPM");
        lblNPM.setBounds(50, 200, 200, 30);
        lblNPM.setFont(titleSecond);
        add(lblNPM);
        
        labelNPM = new JLabel();
        labelNPM.setBounds(200, 200, 200, 30);
        labelNPM.setFont(titleSecond);
        add(labelNPM);
        
        JLabel lbltanggal = new JLabel("Tanggal Lahir");
        lbltanggal.setBounds(500, 200, 200, 30);
        lbltanggal.setFont(titleSecond);
        add(lbltanggal);
        
        tftanggalLahir = new JDateChooser();
        tftanggalLahir.setBounds(700, 200, 150, 30);
        tftanggalLahir.setFont(textInput);
        add(tftanggalLahir);
        
        JLabel lblprovinsi = new JLabel("Provinsi");
        lblprovinsi.setBounds(50, 250, 200, 30);
        lblprovinsi.setFont(titleSecond);
        add(lblprovinsi);
        
        tfprovinsi = new JTextField();
        tfprovinsi.setBounds(200, 250, 150, 30);
        tfprovinsi.setFont(textInput);
        add(tfprovinsi);
        
        JLabel lblkota = new JLabel("Kota");
        lblkota.setBounds(500, 250, 200, 30);
        lblkota.setFont(titleSecond);
        add(lblkota);
        
        tfkota = new JTextField();
        tfkota.setBounds(700, 250, 150, 30);
        tfkota.setFont(textInput);
        add(tfkota);
        
        JLabel lblphone = new JLabel("Nomer HP");
        lblphone.setBounds(500, 300, 200, 30);
        lblphone.setFont(titleSecond);
        add(lblphone);
        
        tfphone = new JTextField();
        tfphone.setBounds(700, 300, 150, 30);
        add(tfphone);
        
        JLabel lblemail = new JLabel("Email Id");
        lblemail.setBounds(50, 300, 200, 30);
        lblemail.setFont(titleSecond);
        add(lblemail);
        
        tfemail = new JTextField();
        tfemail.setBounds(200, 300, 150, 30);
        tfemail.setFont(textInput);
        add(tfemail);
        
        JLabel lblprodi = new JLabel("PRODI");
        lblprodi.setBounds(50, 350, 200, 30);
        lblprodi.setFont(titleSecond);
        add(lblprodi);
        
        String prodi[] = {"Teknik Informatika", "Sistem Informasi"};
        cbprodi = new JComboBox(prodi);
        cbprodi.setBounds(200, 350, 150, 30);
        cbprodi.setFont(textInput);
        add(cbprodi);
        
        try {
            Conn c = new Conn();
            String query = "select * from mahasiswa where NPM='"+crollno.getSelectedItem()+"'";
            ResultSet rs = c.s.executeQuery(query);
            while(rs.next()) {
                tfnama.setText(rs.getString("NAMA"));
                tffname.setText(rs.getString("WALI"));
                ((JTextField) tftanggalLahir.getDateEditor().getUiComponent()).setText(Optional.ofNullable(rs.getString("TANGGAL_LAHIR")).orElse(""));
                tfprovinsi.setText(rs.getString("PROVINSI"));
                tfkota.setText(rs.getString("KOTA"));
                tfphone.setText(rs.getString("NO_HP"));
                tfemail.setText(rs.getString("EMAIL"));
                labelNPM.setText(rs.getString("NPM"));
                cbprodi.setSelectedItem(rs.getString("PRODI"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        crollno.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent ie) {
                try {
                    Conn c = new Conn();
                    String query = "select * from mahasiswa where NPM='"+crollno.getSelectedItem()+"'";
                    ResultSet rs = c.s.executeQuery(query);
                    while(rs.next()) {
                        tfnama.setText(rs.getString("NAMA"));
                        tffname.setText(rs.getString("WALI"));
                        ((JTextField) tftanggalLahir.getDateEditor().getUiComponent()).setText(Optional.ofNullable(rs.getString("TANGGAL_LAHIR")).orElse(""));
                        tfprovinsi.setText(rs.getString("PROVINSI"));
                        tfkota.setText(rs.getString("KOTA"));
                        tfphone.setText(rs.getString("NO_HP"));
                        tfemail.setText(rs.getString("EMAIL"));
                        labelNPM.setText(rs.getString("NPM"));
                        cbprodi.setSelectedItem(rs.getString("PRODI"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        
        submit = new JButton("Update");
        submit.setBounds(160, 500, 140, 30);
        submit.setBackground(Color.GREEN);
        submit.setForeground(Color.white);
        submit.setFont(titleSecond);
        submit.addActionListener(this);
        add(submit);

        cancel = new JButton("Cancel");
        cancel.setBounds(680, 500, 140, 30);
        cancel.setBackground(Color.RED);
        cancel.setForeground(Color.white);
        cancel.setFont(titleSecond);
        cancel.addActionListener(this);
        add(cancel);
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submit) {
            String nama = tfnama.getText();
            String wali = tffname.getText();
            String NPM = labelNPM. getText();
            String noHP = tfphone.getText(); //length 12
            String email = tfemail.getText();
            String provinsi = tfprovinsi.getText();
            String kota = tfkota.getText();
            String prodi = (String) cbprodi.getSelectedItem();
            String ttl = ((JTextField) tftanggalLahir.getDateEditor().getUiComponent()).getText();

            
            try {
                String query = "update mahasiswa set NAMA='"+nama+"', WALI='"+wali+"', TANGGAL_LAHIR='"+ttl+"', PROVINSI='"+provinsi+"',KOTA='"+kota+"', NO_HP='"+noHP+"', EMAIL='"+email+"', PRODI='"+prodi+"' where NPM='"+NPM+"'";
                Conn con = new Conn();
                con.s.executeUpdate(query);
                
                JOptionPane.showMessageDialog(null, "Mahasiswa Updated Suksess");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            setVisible(false);
            new DashBoard();
        }
    }
    
    public static void main(String[] args) {
        new UpdateStudent();
    }
}
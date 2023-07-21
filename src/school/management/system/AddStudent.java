package school.management.system;

import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.time.Year;
import net.proteanit.sql.DbUtils;

public class AddStudent extends JFrame implements ActionListener {

    Font titleMain;
    Font titleSecond;
    Font textInput;

    //String randomNpm = getNPM();

    JLabel lbnama, lbNPM, lbNoHp, lbprovinsi, lblkota, lbprodi, lbambilNPM, lborangtua, lbtanggalLahir, lbemail, lbnilai;
    JTextField tfnama, tfNoHp, tfprovinsi, tfkota, tforangtua, tfemail, tfnilai, tfnpm;
    JComboBox cbprodi;
    JDateChooser tftanggalLahir;
    JButton sumbit, cancel;

    AddStudent() {

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

        JLabel heading = new JLabel("Tambah Mahasiswa Baru");
        heading.setBounds(320, 30, 500, 50);
        heading.setFont(titleMain);
        add(heading);

        //KIRI
        lbnama = new JLabel("Nama");
        lbnama.setBounds(100, 150, 180, 30);
        lbnama.setFont(titleSecond);
        add(lbnama);

        tfnama = new JTextField();
        tfnama.setBounds(250, 150, 180, 30);
        tfnama.setFont(textInput);
        add(tfnama);

        lbNPM = new JLabel("NPM");
        lbNPM.setBounds(100, 200, 180, 30);
        lbNPM.setFont(titleSecond);
        add(lbNPM);

        tfnpm = new JTextField();
        tfnpm.setBounds(250, 200, 180, 30);
        tfnpm.setFont(titleSecond);
        add(tfnpm);
        
//        lbambilNPM = new JText(randomNpm);
//        lbambilNPM.setBounds(250, 200, 180, 30);
//        lbambilNPM.setFont(titleSecond);
//        add(lbambilNPM);

        lbNoHp = new JLabel("Nomer HP");
        lbNoHp.setBounds(100, 250, 180, 30);
        lbNoHp.setFont(titleSecond);
        add(lbNoHp);

        tfNoHp = new JTextField();
        tfNoHp.setBounds(250, 250, 180, 30);
        tfNoHp.setFont(textInput);
        add(tfNoHp);

        lbprovinsi = new JLabel("Provinsi");
        lbprovinsi.setBounds(100, 300, 180, 30);
        lbprovinsi.setFont(titleSecond);
        add(lbprovinsi);

        tfprovinsi = new JTextField();
        tfprovinsi.setBounds(250, 300, 180, 30);
        tfprovinsi.setFont(textInput);
        add(tfprovinsi);

        lbprodi = new JLabel("PRODI S1");
        lbprodi.setBounds(100, 350, 180, 30);
        lbprodi.setFont(titleSecond);
        add(lbprodi);

        String prodi[] = {"Teknik Informatika", "Sistem Informasi"};
        cbprodi = new JComboBox(prodi);
        cbprodi.setBounds(250, 350, 180, 30);
        cbprodi.setFont(textInput);
        add(cbprodi);

        //KANAN
        lborangtua = new JLabel("Nama Wali");
        lborangtua.setBounds(500, 150, 180, 30);
        lborangtua.setFont(titleSecond);
        add(lborangtua);

        tforangtua = new JTextField();
        tforangtua.setBounds(650, 150, 180, 30);
        tforangtua.setFont(textInput);
        add(tforangtua);

        lbtanggalLahir = new JLabel("Tanggal Lahir");
        lbtanggalLahir.setBounds(500, 200, 180, 30);
        lbtanggalLahir.setFont(titleSecond);
        add(lbtanggalLahir);

        tftanggalLahir = new JDateChooser();
        tftanggalLahir.setBounds(650, 200, 180, 30);
        tftanggalLahir.setFont(textInput);
        add(tftanggalLahir);

        lbemail = new JLabel("Email");
        lbemail.setBounds(500, 250, 180, 30);
        lbemail.setFont(titleSecond);
        add(lbemail);

        tfemail = new JTextField();
        tfemail.setBounds(650, 250, 180, 30);
        tfemail.setFont(textInput);
        add(tfemail);

        lblkota = new JLabel("Kota");
        lblkota.setBounds(500, 300, 180, 30);
        lblkota.setFont(titleSecond);
        add(lblkota);

        tfkota = new JTextField();
        tfkota.setBounds(650, 300, 180, 30);
        tfkota.setFont(textInput);
        add(tfkota);

        lbnilai = new JLabel("Nilai Rapor");
        lbnilai.setBounds(500, 350, 180, 30);
        lbnilai.setFont(titleSecond);
        add(lbnilai);

        tfnilai = new JTextField();
        tfnilai.setBounds(650, 350, 180, 30);
        tfnilai.setFont(textInput);
        add(tfnilai);

        //BUTTON
        sumbit = new JButton("Kirim");
        sumbit.setBounds(100, 500, 140, 30);
        sumbit.setBackground(Color.GREEN);
        sumbit.setForeground(Color.white);
        sumbit.setFont(titleSecond);
        sumbit.addActionListener(this);
        add(sumbit);

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
        if (ae.getSource() == sumbit) {
            String nama = tfnama.getText();
            String namaWali = tforangtua.getText();
            String NPM = tfnpm.getText(); 
            String tanggalLahir = ((JTextField) tftanggalLahir.getDateEditor().getUiComponent()).getText();
            String noHP = tfNoHp.getText(); //length 12
            String email = tfemail.getText();
            String provinsi = tfprovinsi.getText();
            String kota = tfkota.getText();
            String prodi = (String) cbprodi.getSelectedItem();
            String nilai = tfnilai.getText();

            try {

                String query = "INSERT INTO mahasiswa VALUES ('" + nama + "','" + namaWali + "','" + NPM + "','" + tanggalLahir + "','" + noHP + "','" + email + "','" + provinsi + "','" + kota + "','"+prodi+"','"+nilai+"')";
                String selectQuery = "SELECT * FROM mahasiswa";

                Conn con = new Conn();
                con.s.executeUpdate(query);
                               
                JOptionPane.showMessageDialog(null, "Mahasiswa di tambahkan");
                
            } catch(Exception e) {
                e.printStackTrace();
            }

        } else {
            setVisible(false);
            new DashBoard();
            
        }
    }

    public static void main(String[] args) {
        new AddStudent();
    }
    
//    public static String getNPM() {
//        int year = Year.now().getValue();
//        long randomDigits = (long) (Math.random() * 90000000L) + 10000000L;
//        String randomNpm = String.valueOf(year) + String.valueOf(randomDigits);
//
//        return randomNpm;
//    }

}

package school.management.system;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;
import java.awt.event.*;
import java.io.*;

public class DashBoard extends JFrame implements ActionListener {

    Font titleSecond, titleMain, titleText, titleMon;

    Choice NPM;
    JTable table;
    JButton search, print, update, add, exit, delete, importXls;

    DashBoard() {

        try {
            titleMain = Font.createFont(Font.TRUETYPE_FONT, new File("src/font/static/Cabin-Bold.ttf")).deriveFont(18f);
            titleSecond = Font.createFont(Font.TRUETYPE_FONT, new File("src/font/static/Cabin-Italic.ttf")).deriveFont(16f);
            titleText = Font.createFont(Font.TRUETYPE_FONT, new File("src/font/static/Cabin-SemiBold.ttf")).deriveFont(14f);
            titleMon = Font.createFont(Font.TRUETYPE_FONT, new File("src/font/static/Cabin-SemiBold.ttf")).deriveFont(36f);
        } catch (FontFormatException | IOException ex) {
            System.err.println("Terjadi kesalahan saat memuat font: " + ex.getMessage());
        }

        try {
            final Image backgroundImage = javax.imageio.ImageIO.read(new File("src/icon/dashboard.jpg"));
            setContentPane(new JPanel(new BorderLayout()) {
                @Override public void paintComponent(Graphics g) {
                    g.drawImage(backgroundImage, 0, 0, null);
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        setSize(1540, 850);
        setLocation(-10, 0);

        setLayout(null);

        JLabel heading = new JLabel("Cari dangan NPM");
        heading.setBounds(20, 25, 150, 20);
        heading.setFont(titleSecond);
        heading.setForeground(Color.WHITE);
        add(heading);

        NPM = new Choice();
        NPM.setBounds(180, 20, 150, 20);
        NPM.setFont(titleSecond);
        add(NPM);
        
        JLabel title = new JLabel("SCHOOL MANAGEMENT SYSTEM");
        title.setBounds(400, 5, 700, 50);
        title.setFont(titleMon);
        title.setForeground(Color.white);
        add(title);
        
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from mahasiswa");
            while (rs.next()) {
                NPM.add(rs.getString("NPM"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        table = new JTable();
        table.setFont(titleText);
        table.getTableHeader().setFont(titleText);

        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from mahasiswa");
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }

        JScrollPane jsp = new JScrollPane(table);
        jsp.setBounds(80, 150, 1200, 500);
        jsp.getViewport().setBackground(Color.BLUE);
        add(jsp);

        ImageIcon searchIcon = new ImageIcon("src/icon/search.png");
        Image scaledIcon = searchIcon.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        ImageIcon scaledSearchIcon = new ImageIcon(scaledIcon);
        search = new JButton("Search");
        search.setBounds(20, 80, 150, 50);
        search.setFont(titleMain);
        search.setBackground(Color.white);
        search.addActionListener(this);
        search.setIcon(scaledSearchIcon);  // Menambahkan ikon
        search.setHorizontalTextPosition(SwingConstants.RIGHT);  // Mengatur posisi teks ke kiri ikon
        add(search);

        ImageIcon searchIcon2 = new ImageIcon("src/icon/printer.png");
        Image scaledIcon2 = searchIcon2.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        ImageIcon scaledSearchIcon2 = new ImageIcon(scaledIcon2);
        print = new JButton("Print");
        print.setBounds(180, 80, 150, 50);
        print.setFont(titleMain);
        print.setBackground(Color.WHITE);
        print.addActionListener(this);
        print.setIcon(scaledSearchIcon2);  // Menambahkan ikon
        print.setHorizontalTextPosition(SwingConstants.RIGHT);  // Mengatur posisi teks ke kiri ikon
        add(print);

        ImageIcon searchIcon3 = new ImageIcon("src/icon/add.png");
        Image scaledIcon3 = searchIcon3.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        ImageIcon scaledSearchIcon3 = new ImageIcon(scaledIcon3);
        add = new JButton("Add");
        add.setBounds(340, 80, 150, 50);
        add.setFont(titleMain);
        add.setBackground(Color.white);
        add.addActionListener(this);
        add.setIcon(scaledSearchIcon3);  // Menambahkan ikon
        add.setHorizontalTextPosition(SwingConstants.RIGHT);  // Mengatur posisi teks ke kiri ikon
        add(add);

        ImageIcon searchIcon4 = new ImageIcon("src/icon/update.png");
        Image scaledIcon4 = searchIcon4.getImage().getScaledInstance(25, 25,Image.SCALE_DEFAULT);
        ImageIcon scaledSearchIcon4 = new ImageIcon(scaledIcon4);
        update = new JButton("Update");
        update.setBounds(500, 80, 150, 50);
        update.setFont(titleMain);
        update.setBackground(Color.white);
        update.addActionListener(this);
        update.setIcon(scaledSearchIcon4);  // Menambahkan ikon
        update.setHorizontalTextPosition(SwingConstants.RIGHT);  // Mengatur posisi teks ke kiri ikon
        add(update);
        
        ImageIcon searchIcon5 = new ImageIcon("src/icon/delete.png");
        Image scaledIcon5 = searchIcon5.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        ImageIcon scaledSearchIcon5 = new ImageIcon(scaledIcon5);
        delete = new JButton("Delete");
        delete.setBounds(660, 80, 150, 50);
        delete.setFont(titleMain);
        delete.setBackground(Color.white);
        delete.addActionListener(this);
        delete.setIcon(scaledSearchIcon5);  // Menambahkan ikon
        delete.setHorizontalTextPosition(SwingConstants.RIGHT);  // Mengatur posisi teks ke kiri ikon
        add(delete);

        ImageIcon searchIcon6 = new ImageIcon("src/icon/import.png");
        Image scaledIcon6 = searchIcon6.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        ImageIcon scaledSearchIcon6 = new ImageIcon(scaledIcon6);
        importXls = new JButton("Import");
        importXls.setBounds(820, 80, 150, 50);
        importXls.setFont(titleMain);
        importXls.setBackground(Color.white);
        importXls.addActionListener(this);
        importXls.setIcon(scaledSearchIcon6);  // Menambahkan ikon
        importXls.setHorizontalTextPosition(SwingConstants.RIGHT);  // Mengatur posisi teks ke kiri ikon
        add(importXls);
        
        ImageIcon searchIcon7 = new ImageIcon("src/icon/exit.png");
        Image scaledIcon7 = searchIcon7.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        ImageIcon scaledSearchIcon7 = new ImageIcon(scaledIcon7);
        exit = new JButton("Exit");
        exit.setBounds(1200, 80, 150, 50);
        exit.setFont(titleMain);
        exit.setBackground(Color.white);
        exit.addActionListener(this);
        exit.setIcon(scaledSearchIcon7);  // Menambahkan ikon
        exit.setHorizontalTextPosition(SwingConstants.RIGHT);  // Mengatur posisi teks ke kiri ikon
        add(exit);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == search) {
            String query = "select * from mahasiswa where NPM = '" + NPM.getSelectedItem() + "'";
            try {
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery(query);
                table.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == print) {
            try {
                table.print();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == add) {
            new AddStudent();
            setVisible(false);
        } else if (ae.getSource() == delete) {            
            String deleteQuery = "DELETE FROM mahasiswa WHERE NPM = '" + NPM.getSelectedItem() + "'";
            String selectQuery = "SELECT * FROM mahasiswa";
            try {
                Conn c = new Conn();
                c.s.executeUpdate(deleteQuery);
                ResultSet rs = c.s.executeQuery(selectQuery);
                table.setModel(DbUtils.resultSetToTableModel(rs));
                while (rs.next()) {
                    NPM.add(rs.getString("NPM"));
                }
                JOptionPane.showMessageDialog(null, "Mahasiswa berhasil dihapus");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == update) {
            new UpdateStudent();
            setVisible(false);
        } else if (ae.getSource() == importXls) {
            new ImpXls();
        } else {
            int confirm = JOptionPane.showConfirmDialog(null, "Apakah Anda yakin ingin keluar?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) { // Kode untuk tombol default setelah konfirmasi
                setVisible(false);
            }
        }
    }

    public static void main(String[] args) {
        
        new DashBoard();
    }
}

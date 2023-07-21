package school.management.system;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Splash extends JFrame implements Runnable {

    Thread t;
    Font titleMain, titleSecond;

    Splash() {

        try {
            titleMain = Font.createFont(Font.TRUETYPE_FONT, new File("src/font/Hanging Lights Demo.ttf")).deriveFont(120f);
            titleSecond = Font.createFont(Font.TRUETYPE_FONT, new File("src/font/static/Cabin-Medium.ttf")).deriveFont(24f);
        } catch (Exception e) {
            System.err.println("Font Tidak Ditemukan");
        }

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/background.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1000, 700, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        add(image);

        ImageIcon titleIcon = new ImageIcon("src/icon/3.png");
        Image scaledIcon = titleIcon.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon scaledTitleIcon = new ImageIcon(scaledIcon);
        JLabel title = new JLabel("KELOMPOK");
        title.setBounds(200, 0, 600, 120);
        title.setFont(titleMain);
        title.setForeground(Color.white);
        title.setIcon(scaledTitleIcon); // Menambahkan ikon
        title.setHorizontalTextPosition(SwingConstants.LEFT); // Mengatur posisi teks ke kiri ikon
        image.add(title);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panel.setBounds(10, 150, 500, 300);
        panel.setBackground(Color.blue);
        panel.setBorder(BorderFactory.createLineBorder(Color.black)); // Menambahkan border

        JLabel nama = new JLabel("1. Ahmad Novianto 202043501297");
        nama.setFont(titleSecond);
        nama.setForeground(Color.white);
        panel.add(nama);

        JLabel nama1 = new JLabel("2. Fitriyana Nurul Cholifah 202043501942");
        nama1.setFont(titleSecond);
        nama1.setForeground(Color.white);
        panel.add(nama1);

        JLabel nama2 = new JLabel("3. Gian Patria 202043501857");
        nama2.setFont(titleSecond);
        nama2.setForeground(Color.white);
        panel.add(nama2);

        JLabel nama3 = new JLabel("4. Sesa Delani Putri 202043502362");
        nama3.setFont(titleSecond);
        nama3.setForeground(Color.white);
        panel.add(nama3);

        JLabel nama4 = new JLabel("5. Simon Keli 202043501931");
        nama4.setFont(titleSecond);
        nama4.setForeground(Color.white);
        panel.add(nama4);

        image.add(panel);

        t = new Thread(this);
        t.start();

        setVisible(true);

        int j = 1;
        for (int i = 0; i <= 800; i += 4, j += 1) {
            setLocation(200, 800 / (i + j));
            setSize(i + j, 700);

            try {
                Thread.sleep(10);
            } catch (Exception e) {

            }
        }
    }

    public void run() {
        try {
            Thread.sleep(7000);
            setVisible(false);

            //Lanjut Login
            new Login();
        } catch (Exception e) {

        }
    }

    public static void main(String[] args) {
        new Splash();
    }
}

package school.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ImpXls extends JFrame {

    private JLabel lblExcelFile;
    private JTextField txtExcelFile;
    private JButton btnBrowse;
    private JButton btnImport;

    public ImpXls() {
        setTitle("Import Excel to MySQL");
        setLayout(new FlowLayout());

        lblExcelFile = new JLabel("Excel File:");
        txtExcelFile = new JTextField(20);
        btnBrowse = new JButton("Browse");
        btnImport = new JButton("Import");

        btnBrowse.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(ImpXls.this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    txtExcelFile.setText(file.getAbsolutePath());
                }
            }
        });

        btnImport.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String excelFilePath = txtExcelFile.getText();
                if (excelFilePath.isEmpty()) {
                    JOptionPane.showMessageDialog(ImpXls.this, "Please select an Excel file.");
                    return;
                }

                try {
                    List<Mahasiswa> mahasiswas = readMahasiswasFromExcel(excelFilePath);
                    importMahasiswasToMySQL(mahasiswas);
                    JOptionPane.showMessageDialog(ImpXls.this, "Data imported successfully.");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(ImpXls.this, "Error importing data: " + ex.getMessage());
                }
            }
        });

        add(lblExcelFile);
        add(txtExcelFile);
        add(btnBrowse);
        add(btnImport);
        
        setVisible(true);

        pack();
        setLocationRelativeTo(null);
    }

    private List<Mahasiswa> readMahasiswasFromExcel(String excelFilePath) throws Exception {
        List<Mahasiswa> mahasiswas = new ArrayList<>();

        try (Workbook workbook = new XSSFWorkbook(excelFilePath)) {
            Sheet sheet = workbook.getSheetAt(0);
            DataFormatter dataFormatter = new DataFormatter();

            for (Row row : sheet) {
                String nama = dataFormatter.formatCellValue(row.getCell(0));
                String wali = dataFormatter.formatCellValue(row.getCell(1));
                String npm = dataFormatter.formatCellValue(row.getCell(2));
                String tanggalLahir = dataFormatter.formatCellValue(row.getCell(3));
                String noHp = dataFormatter.formatCellValue(row.getCell(4));
                String email = dataFormatter.formatCellValue(row.getCell(5));
                String provinsi = dataFormatter.formatCellValue(row.getCell(6));
                String kota = dataFormatter.formatCellValue(row.getCell(7));
                String prodi = dataFormatter.formatCellValue(row.getCell(8));
                String nilai = dataFormatter.formatCellValue(row.getCell(9));

                Mahasiswa mahasiswa = new Mahasiswa(nama, wali, npm, tanggalLahir, noHp, email, provinsi, kota, prodi, nilai);
                mahasiswas.add(mahasiswa);
            }
        }

        return mahasiswas;
    }

    private void importMahasiswasToMySQL(List<Mahasiswa> mahasiswas) throws SQLException {
        Conn conn = new Conn();
        String sql = "INSERT INTO mahasiswa (NAMA, WALI, NPM, TANGGAL_LAHIR, NO_HP, EMAIL, PROVINSI, KOTA, PRODI, NILAI) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            for (Mahasiswa mahasiswa : mahasiswas) {
                statement.setString(1, mahasiswa.getNama());
                statement.setString(2, mahasiswa.getWali());
                statement.setString(3, mahasiswa.getNpm());
                statement.setString(4, mahasiswa.getTanggalLahir());
                statement.setString(5, mahasiswa.getNoHp());
                statement.setString(6, mahasiswa.getEmail());
                statement.setString(7, mahasiswa.getProvinsi());
                statement.setString(8, mahasiswa.getKota());
                statement.setString(9, mahasiswa.getProdi());
                statement.setString(10, mahasiswa.getNilai());
                statement.executeUpdate();
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ImpXls();
            }
        });
    }

    private class Mahasiswa {

        private String nama;
        private String wali;
        private String npm;
        private String tanggalLahir;
        private String noHp;
        private String email;
        private String provinsi;
        private String kota;
        private String prodi;
        private String nilai;

        public Mahasiswa(String nama, String wali, String npm, String tanggalLahir, String noHp, String email, String provinsi, String kota, String prodi, String nilai) {
            this.nama = nama;
            this.wali = wali;
            this.npm = npm;
            this.tanggalLahir = tanggalLahir;
            this.noHp = noHp;
            this.email = email;
            this.provinsi = provinsi;
            this.kota = kota;
            this.prodi = prodi;
            this.nilai = nilai;
        }

        public String getNama() {
            return nama;
        }

        public String getWali() {
            return wali;
        }

        public String getNpm() {
            return npm;
        }

        public String getTanggalLahir() {
            return tanggalLahir;
        }

        public String getNoHp() {
            return noHp;
        }

        public String getEmail() {
            return email;
        }

        public String getProvinsi() {
            return provinsi;
        }

        public String getKota() {
            return kota;
        }

        public String getProdi() {
            return prodi;
        }

        public String getNilai() {
            return nilai;
        }
    }
}

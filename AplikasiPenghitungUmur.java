import javax.swing.*;
import com.toedter.calendar.JDateChooser;
import java.awt.*;
import java.awt.event.*;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import javax.swing.Timer;
import com.formdev.flatlaf.themes.FlatMacLightLaf;

public class AplikasiPenghitungUmur extends JFrame {
    private JDateChooser dateChooser;
    private JTextField tfTahun, tfBulan, tfHari, tfNextBirthday;
    private JButton btnHitung;
    private JLabel lblResult;

    public AplikasiPenghitungUmur() {
        // Setup Tema FlatLaf
        FlatMacLightLaf.setup();

        setTitle("🕒 Aplikasi Penghitung Umur ");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(450, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // ======= Header =======
        JLabel lblTitle = new JLabel("Aplikasi Penghitung Umur", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Poppins", Font.BOLD, 20));
        lblTitle.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        lblTitle.setForeground(new Color(30, 30, 60));
        add(lblTitle, BorderLayout.NORTH);

        // ======= Panel Tengah =======
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10);
        c.fill = GridBagConstraints.HORIZONTAL;

        c.gridx = 0; c.gridy = 0;
        panel.add(new JLabel("Tanggal Lahir:"), c);

        dateChooser = new JDateChooser();
        dateChooser.setDateFormatString("dd-MM-yyyy");
        c.gridx = 1;
        panel.add(dateChooser, c);

        btnHitung = new JButton("Hitung Umur");
        btnHitung.setFont(new Font("Poppins", Font.BOLD, 14));
        btnHitung.setBackground(new Color(70, 130, 180));
        btnHitung.setForeground(Color.WHITE);
        btnHitung.setFocusPainted(false);
        btnHitung.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 2;
        panel.add(btnHitung, c);

        // ======= Hasil =======
        c.gridwidth = 1;
        c.gridy = 2; c.gridx = 0;
        panel.add(new JLabel("Tahun:"), c);
        tfTahun = new JTextField(10);
        tfTahun.setEditable(false);
        c.gridx = 1;
        panel.add(tfTahun, c);

        c.gridy = 3; c.gridx = 0;
        panel.add(new JLabel("Bulan:"), c);
        tfBulan = new JTextField(10);
        tfBulan.setEditable(false);
        c.gridx = 1;
        panel.add(tfBulan, c);

        c.gridy = 4; c.gridx = 0;
        panel.add(new JLabel("Hari:"), c);
        tfHari = new JTextField(10);
        tfHari.setEditable(false);
        c.gridx = 1;
        panel.add(tfHari, c);

        c.gridy = 5; c.gridx = 0;
        panel.add(new JLabel("Ulang Tahun Berikutnya:"), c);
        tfNextBirthday = new JTextField(20);
        tfNextBirthday.setEditable(false);
        c.gridx = 1;
        panel.add(tfNextBirthday, c);

        add(panel, BorderLayout.CENTER);

        // Label hasil (animasi)
        lblResult = new JLabel("", SwingConstants.CENTER);
        lblResult.setFont(new Font("Poppins", Font.ITALIC, 14));
        lblResult.setForeground(new Color(100, 100, 100));
        lblResult.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 10));
        add(lblResult, BorderLayout.SOUTH);

        // Event Listener
        btnHitung.addActionListener(e -> hitungUmur());
    }

    private void hitungUmur() {
        Date tanggal = dateChooser.getDate();
        if (tanggal == null) {
            JOptionPane.showMessageDialog(this, "Silakan pilih tanggal lahir terlebih dahulu!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        LocalDate lahir = tanggal.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate sekarang = LocalDate.now();

        if (lahir.isAfter(sekarang)) {
            JOptionPane.showMessageDialog(this, "Tanggal lahir tidak boleh di masa depan!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Period p = Period.between(lahir, sekarang);
        tfTahun.setText(p.getYears() + " Tahun");
        tfBulan.setText(p.getMonths() + " Bulan");
        tfHari.setText(p.getDays() + " Hari");

        LocalDate nextBirthday = lahir.withYear(sekarang.getYear());
        if (!nextBirthday.isAfter(sekarang)) {
            nextBirthday = nextBirthday.plusYears(1);
        }
        long hariMenujuUltah = ChronoUnit.DAYS.between(sekarang, nextBirthday);
        tfNextBirthday.setText("Dalam " + hariMenujuUltah + " hari lagi (" + nextBirthday + ")");

        // Animasi muncul teks hasil
        animateResult("🎉 Umur berhasil dihitung! Anda berumur " + p.getYears() + " tahun 🎂");
    }

    private void animateResult(String text) {
        lblResult.setText("");
        Timer timer = new Timer(30, null);
        final int[] index = {0};
        timer.addActionListener(e -> {
            if (index[0] < text.length()) {
                lblResult.setText(text.substring(0, index[0] + 1));
                index[0]++;
            } else {
                ((Timer) e.getSource()).stop();
            }
        });
        timer.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AplikasiPenghitungUmurModern().setVisible(true));
    }
}

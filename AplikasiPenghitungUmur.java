import com.toedter.calendar.JDateChooser;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import javax.swing.JOptionPane;

public class AplikasiPenghitungUmur extends javax.swing.JFrame {

    public AplikasiPenghitungUmur() {
        initComponents();
        setLocationRelativeTo(null); // tengah layar
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        dateChooser = new JDateChooser();
        btnHitung = new javax.swing.JButton();
        tfTahun = new javax.swing.JTextField();
        tfBulan = new javax.swing.JTextField();
        tfHari = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Aplikasi Penghitung Umur");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18));
        jLabel1.setText("Aplikasi Penghitung Umur");

        jLabel2.setText("Tanggal Lahir");

        jLabel3.setText("Tahun");

        jLabel4.setText("Bulan");

        jLabel5.setText("Hari");

        btnHitung.setText("Hitung Umur");
        btnHitung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHitungActionPerformed(evt);
            }
        });

        tfTahun.setEditable(false);
        tfBulan.setEditable(false);
        tfHari.setEditable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(dateChooser, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(tfTahun)
                            .addComponent(tfBulan)
                            .addComponent(tfHari)
                            .addComponent(btnHitung))))
                .addGap(30, 30, 30))
        );

        layout.setVerticalGroup(
            layout.createSequentialGroup()
            .addGap(20)
            .addComponent(jLabel1)
            .addGap(20)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(jLabel2)
                .addComponent(dateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(15)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel3)
                .addComponent(tfTahun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(15)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel4)
                .addComponent(tfBulan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(15)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel5)
                .addComponent(tfHari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(20)
            .addComponent(btnHitung)
            .addGap(20)
        );

        pack();
    }// </editor-fold>

    private void btnHitungActionPerformed(java.awt.event.ActionEvent evt) {
        Date tanggal = dateChooser.getDate();

        if (tanggal == null) {
            JOptionPane.showMessageDialog(this, "Silakan pilih tanggal lahir!");
            return;
        }

        LocalDate lahir = tanggal.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        LocalDate sekarang = LocalDate.now();

        if (lahir.isAfter(sekarang)) {
            JOptionPane.showMessageDialog(this, "Tanggal lahir tidak valid!");
            return;
        }

        Period umur = Period.between(lahir, sekarang);

        tfTahun.setText(umur.getYears() + "");
        tfBulan.setText(umur.getMonths() + "");
        tfHari.setText(umur.getDays() + "");
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new AplikasiPenghitungUmur().setVisible(true);
        });
    }

    // Variables declaration
    private javax.swing.JButton btnHitung;
    private JDateChooser dateChooser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField tfBulan;
    private javax.swing.JTextField tfHari;
    private javax.swing.JTextField tfTahun;
    // End of variables declaration
}

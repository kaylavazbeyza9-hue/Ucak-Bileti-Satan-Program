import javax.swing.*;
import java.io.*;

public class UyeOlEkrani {

    public UyeOlEkrani() {

        JFrame pencere = new JFrame("Kayıt Ol");
        pencere.setSize(500, 400);
        pencere.setLayout(null);
        pencere.setLocationRelativeTo(null);
        
        ImageIcon icon = new ImageIcon("src/Ucak.png");
        JLabel arkaPlan = new JLabel(icon);
        arkaPlan.setBounds(0, 0, 500, 400);
        arkaPlan.setLayout(null);
        pencere.add(arkaPlan);

        JLabel kullaniciLabel = new JLabel("Kullanıcı Adı:");
        kullaniciLabel.setBounds(150, 200, 100, 25);
        arkaPlan.add(kullaniciLabel);

        JTextField kullaniciAlani = new JTextField();
        kullaniciAlani.setBounds(250, 200, 90, 25);
        arkaPlan.add(kullaniciAlani);

        JLabel sifreLabel = new JLabel("Şifre:");
        sifreLabel.setBounds(200, 240, 100, 25);
        arkaPlan.add(sifreLabel);

        JPasswordField sifreAlani = new JPasswordField();
        sifreAlani.setBounds(250, 240, 90, 25);
        arkaPlan.add(sifreAlani);

        JButton kaydetButonu = new JButton("Kaydet");
        kaydetButonu.setBounds(140, 270, 100, 25);
        arkaPlan.add(kaydetButonu);

        kaydetButonu.addActionListener(e -> {
            String kullaniciAdi = kullaniciAlani.getText();
            String sifre = new String(sifreAlani.getPassword());

            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter("kullanici.txt", true));
                bw.write(kullaniciAdi + "," + sifre);
                bw.newLine();
                bw.close();

                JOptionPane.showMessageDialog(null, "Kayıt başarıyla kaydedildi!");
                pencere.dispose();

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Dosyaya yazılamadı!");
            }
        });

        pencere.setVisible(true);
    }
}

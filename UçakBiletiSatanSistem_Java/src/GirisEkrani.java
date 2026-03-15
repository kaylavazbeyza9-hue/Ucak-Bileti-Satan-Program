import javax.swing.*;
import java.io.*;

public class GirisEkrani {

    public static void main(String[] args) {

        JFrame pencere = new JFrame("Giriş Ekranı");
        pencere.setSize(500, 400);
        pencere.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pencere.setLayout(null);
        pencere.setLocationRelativeTo(null);
        
        ImageIcon icon = new ImageIcon("src/Ucak.png");
        JLabel arkaPlan = new JLabel(icon);
        arkaPlan.setBounds(0, 0, 500, 400);
        arkaPlan.setLayout(null);
        pencere.add(arkaPlan);

        JLabel baslik = new JLabel("UÇAK BİLETİ SİSTEMİ");
        baslik.setBounds(160, 165, 300, 30);
        arkaPlan.add(baslik);

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

        JButton girisButonu = new JButton("Giriş Yap");
        girisButonu.setBounds(250, 270, 100, 25);
        arkaPlan.add(girisButonu);

        JButton uyeOlButonu = new JButton("Kayıt Ol");
        uyeOlButonu.setBounds(140, 270, 100, 25);
        arkaPlan.add(uyeOlButonu);

        girisButonu.addActionListener(e -> {
            String girilenKullanici = kullaniciAlani.getText();
            String girilenSifre = new String(sifreAlani.getPassword());

            boolean bulundu = false;

            try {
                BufferedReader br = new BufferedReader(new FileReader("kullanici.txt"));
                String satir;

                while ((satir = br.readLine()) != null) {

                    String[] parca = satir.split(",");
                    if (parca.length != 2) continue;

                    String dosyaKullanici = parca[0];
                    String dosyaSifre = parca[1];

                    if (dosyaKullanici.equals(girilenKullanici) &&
                        dosyaSifre.equals(girilenSifre)) {
                        bulundu = true;
                        break;
                    }
                }
                br.close();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Dosya okunamadı!");
            }

            if (bulundu) {
                JOptionPane.showMessageDialog(null, "Giriş başarılı!");
                pencere.dispose();
                new AnaEkran(girilenKullanici);
            } else {
                JOptionPane.showMessageDialog(null, "Kullanıcı adı veya şifre hatalı!");
            }
        });

        uyeOlButonu.addActionListener(e -> new UyeOlEkrani());

        pencere.setVisible(true);
    }
}

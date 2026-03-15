import javax.swing.*;
import java.io.*;

public class MilIslemleri {
    public static int milHesapla(String dosyaAdi, String isim, String soyisim) {
        int toplamMil = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(dosyaAdi))) {
            String satir;

            while ((satir = br.readLine()) != null) {
                if (satir.trim().isEmpty()) continue;

                String[] p = satir.split(",");
                if (p.length < 10) continue;

                String dosyaIsim = p[0].trim();
                String dosyaSoyisim = p[1].trim();

                if (dosyaIsim.equalsIgnoreCase(isim)
                        && dosyaSoyisim.equalsIgnoreCase(soyisim)) {
                    toplamMil += 2500; 
                }
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Mil hesaplanırken hata oluştu:\n" + e.getMessage(),
                    "Hata",
                    JOptionPane.ERROR_MESSAGE
            );
        }

        return toplamMil;
    }
    public static double indirimOraniHesapla(String dosyaAdi, String isim, String soyisim) {
        int mil = milHesapla(dosyaAdi, isim, soyisim);
        if (mil >= 10000) {
            return 0.10;
        }
        return 0.0;
    }
    public static void milBilgiYazdir(String dosyaAdi, String isim, String soyisim, double biletFiyati) {
        int mil = milHesapla(dosyaAdi, isim, soyisim);
        double indirimOrani = indirimOraniHesapla(dosyaAdi, isim, soyisim);
        double indirimliFiyat = biletFiyati * (1 - indirimOrani);

        String mesaj;
        if (indirimOrani > 0) {
            mesaj = " Tebrikler!\n\n"
                    + "Toplam miliniz: " + mil + "\n"
                    + "Biletinize %10 indirim uygulandı!\n"
                    + "Normal Fiyat: " + biletFiyati + " TL\n"
                    + "İndirimli Fiyat: " + indirimliFiyat + " TL";
        } else {
            mesaj = " Toplam miliniz: " + mil + "\n"
                    + "İndirim için kalan mil: " + (10000 - mil) + "\n"
                    + "Bilet Fiyatı: " + biletFiyati + " TL";
        }

        JOptionPane.showMessageDialog(null, mesaj, "Mil Bilgisi", JOptionPane.INFORMATION_MESSAGE);
    }
}

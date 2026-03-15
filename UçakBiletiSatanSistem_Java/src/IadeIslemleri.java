import java.io.*;
import java.util.ArrayList;

public class IadeIslemleri {
    public static void biletSil(String dosyaAdi, Bilet bilet) {

        ArrayList<String> yeniListe = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(dosyaAdi))) {
            String satir;

            while ((satir = br.readLine()) != null) {
                String[] p = satir.split(",");

                if (p.length < 10) {
                    yeniListe.add(satir);
                    continue;
                }
                boolean ayniBilet =
                	    p[0].trim().equalsIgnoreCase(bilet.getYolcu().getİsim().trim()) &&
                	    p[1].trim().equalsIgnoreCase(bilet.getYolcu().getSoyisim().trim()) &&
                	    p[3].trim().equalsIgnoreCase(bilet.getUcus().getNereden().trim()) &&
                	    p[4].trim().equalsIgnoreCase(bilet.getUcus().getNereye().trim()) &&
                	    p[5].trim().equals(bilet.getUcus().getTarih()) &&
                	    p[6].trim().equals(bilet.getUcus().getSaat()) &&
                	    p[7].trim().equals(bilet.getKoltuk().getKoltukNo());
            
                if (!ayniBilet) {
                    yeniListe.add(satir);
                }
            }

        } catch (IOException e) {
            System.out.println("Bilet dosyası okunamadı: " + e.getMessage());
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(dosyaAdi))) {
            for (String s : yeniListe) {
                bw.write(s);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Bilet dosyası yazılamadı: " + e.getMessage());
        }
    }
    public static void koltukBosalt(String dosyaAdi, Bilet bilet) {

        ArrayList<String> yeniListe = new ArrayList<>();
        String ucus =
                bilet.getUcus().getNereden() + "-" +
                bilet.getUcus().getNereye() + "-" +
                bilet.getUcus().getTarih() + "-" +
                bilet.getUcus().getSaat();

        try (BufferedReader br = new BufferedReader(new FileReader(dosyaAdi))) {
            String satir;

            while ((satir = br.readLine()) != null) {
                String[] p = satir.split(",");

                if (p.length < 3) {
                    yeniListe.add(satir);
                    continue;
                }

                String dosyaUcus = p[0];
                String koltukNo = p[1];
                boolean dolu = Boolean.parseBoolean(p[2]);

                if (dosyaUcus.equals(ucus)
                        && koltukNo.equals(bilet.getKoltuk().getKoltukNo())) {//iade edilen bilet
                    dolu = false;
                }

                yeniListe.add(dosyaUcus + "," + koltukNo + "," + dolu);
            }

        } catch (IOException e) {
            System.out.println("Koltuk dosyası okunamadı: " + e.getMessage());
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(dosyaAdi))) {
            for (String s : yeniListe) {
                bw.write(s);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Koltuk dosyası yazılamadı: " + e.getMessage());
        }
    }
}
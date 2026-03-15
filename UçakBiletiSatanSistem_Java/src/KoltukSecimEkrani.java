import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class KoltukSecimEkrani {
	private Koltuk secilenKoltuk;

    public KoltukSecimEkrani(Ucus ucus) {

        JFrame pencere = new JFrame("Koltuk Seçimi");
        pencere.setSize(1500, 900);
        pencere.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pencere.setLayout(null);
        pencere.setLocationRelativeTo(null);
       
        ImageIcon icon = new ImageIcon("src/AnaEkran.png");
        JLabel arkaPlan = new JLabel(icon);
        arkaPlan.setBounds(0, 0, 1500, 900);
        arkaPlan.setLayout(null);
        pencere.add(arkaPlan);

        JLabel kullaniciLabel = new JLabel("Koltuk Seçiniz:");
        kullaniciLabel.setBounds(300, 250, 100, 25);
        arkaPlan.add(kullaniciLabel);
        
        JButton ileriButon = new JButton("İleri");
        ileriButon.setBounds(600,750,100,30);
        arkaPlan.add(ileriButon);
       
        JPanel koltukPanel = new JPanel();
        koltukPanel.setBounds(300, 300, 400, 400);
        koltukPanel.setLayout(new GridLayout(6, 2, 5, 5));
        arkaPlan.add(koltukPanel);
        
        String ucusBilgisi = ucus.getNereden() + "-" + ucus.getNereye() + "-" + ucus.getTarih() + "-" + ucus.getSaat();
        
        Koltuk temp = new Koltuk("0","Ekonomi");
        ArrayList<Koltuk> doluKoltuklar = temp.koltuklariOku(ucusBilgisi);
        
        ArrayList<Koltuk> koltuklar = new ArrayList<>();
        for(int i=1; i<=12; i++) {
            String tipi = (i <= 4) ? "Business" : "Ekonomi";
            Koltuk k = new Koltuk(i+"", tipi);

            for(Koltuk dk : doluKoltuklar) {
                if(dk.getKoltukNo().equals(k.getKoltukNo()) && dk.isDolu()) { 
                    k.setDolu(true);
                    break;
                }
            }
            koltuklar.add(k);
        }

        for(Koltuk k : koltuklar) {
            JButton btn = new JButton(k.getKoltukNo());

            if(k.isDolu()) {
                btn.setBackground(new Color(220,120,120));     
            } else if(k.getTipi().equals("Business")) {
                btn.setBackground(new Color(160,190,210));
            } else {
                btn.setBackground(new Color(170,215,180));
            }

            btn.addActionListener(e -> {
                if(k.isDolu()) {
                	 JOptionPane.showMessageDialog(pencere, "Bu koltuk dolu! Lütfen başka koltuk seçin.");
                     return;
                 }
                k.setDolu(true);
                btn.setBackground(new Color(220,120,120));
                k.koltukKaydet(ucusBilgisi);
                secilenKoltuk = k;
                JOptionPane.showMessageDialog(pencere, "Koltuk " + k.getKoltukNo() + " (" + k.getTipi() + ") rezerve edildi");
                });
            koltukPanel.add(btn);
            }
        ileriButon.addActionListener(e ->{
        	if(secilenKoltuk != null) {
        		new YolcuBilgileriEkrani(ucus,secilenKoltuk);
        		pencere.dispose();
        	}else {
        		JOptionPane.showMessageDialog(pencere, "Lütfen önce bir koltuk seçin");
        	}
        });
        pencere.setVisible(true);
    }
}

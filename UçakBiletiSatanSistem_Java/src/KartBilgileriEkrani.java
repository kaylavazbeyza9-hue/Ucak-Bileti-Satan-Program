import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class KartBilgileriEkrani {
	public KartBilgileriEkrani(Yolcu yolcu, Koltuk secilenKoltuk,Ucus ucus) {
		JFrame pencere = new JFrame("Kart Bilgileri");
		pencere.setSize(1500,900);
		pencere.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pencere.setLayout(null);
		pencere.setLocationRelativeTo(null);
		

        ImageIcon icon = new ImageIcon("src/AnaEkran.png");
        JLabel arkaPlan = new JLabel(icon);
        arkaPlan.setBounds(0, 0, 1500, 900);
        arkaPlan.setLayout(null);
        pencere.add(arkaPlan);
        
		JLabel kartNoLabel = new JLabel("Kart Numarasaı:");
		kartNoLabel.setBounds(100,300,300,50);
		arkaPlan.add(kartNoLabel);
		
		JTextField kartNoField = new JTextField();
		kartNoField.setBounds(300,300,300,50);
		arkaPlan.add(kartNoField);
		
		JLabel sonktLabel = new JLabel("Son Kulanma Tarihi:");
		sonktLabel.setBounds(100,400,300,50);
		arkaPlan.add(sonktLabel);
		
		JTextField sonktField = new JTextField();
		sonktField.setBounds(300,400,300,50);
		arkaPlan.add(sonktField);
		
		JLabel cvvLabel = new JLabel("CVV:");
		cvvLabel.setBounds(100,500,300,50);
		arkaPlan.add(cvvLabel);
		
		JPasswordField cvvField = new JPasswordField();
		cvvField.setBounds(300,500,300,50);
		arkaPlan.add(cvvField);
		
		JButton iadeButton = new JButton("Bileti İade Et");
		iadeButton.setBounds(650, 600, 200, 50);
		arkaPlan.add(iadeButton);

		JLabel lb = new JLabel("Bilet Önizleme", SwingConstants.CENTER);
		lb.setBounds(350, 150, 800, 40);
		lb.setFont(new Font("Arial", Font.BOLD, 24)); 
		lb.setForeground(Color.black); 
		arkaPlan.add(lb);

		JTextField biletBilgisi = new JTextField();
		biletBilgisi.setBounds(350, 200, 1000,50);
		biletBilgisi.setFont(new Font("Arial", Font.BOLD, 14));
		biletBilgisi.setEditable(false);
		arkaPlan.add(biletBilgisi);
		
		double onizlemeFiyat;

		double mesafe = ucus.getMesafe();

		if (secilenKoltuk.getTipi().equals("Ekonomi")) {
		    onizlemeFiyat = mesafe * 2;
		} else {
		    onizlemeFiyat = mesafe * 4; 
		}
		MilIslemleri.milBilgiYazdir("biletler.txt", yolcu.getİsim(), yolcu.getSoyisim(), onizlemeFiyat);
		double indirimOrani = MilIslemleri.indirimOraniHesapla(
		        "biletler.txt",
		        yolcu.getİsim(),
		        yolcu.getSoyisim()
		);
		double indirimliFiyat = onizlemeFiyat * (1 - indirimOrani);
		biletBilgisi.setText(
			    "Yolcu: " + yolcu.getİsim() + " " + yolcu.getSoyisim() +
			    "\nUçuş: " + ucus.getNereden() + " → " + ucus.getNereye() +
			    "\nTarih: " + ucus.getTarih() +
			    "\nSaat: " + ucus.getSaat() +
			    "\nMesafe: " + mesafe + " km" +
			    "\nKoltuk: " + secilenKoltuk.getKoltukNo() + " (" + secilenKoltuk.getTipi() + ")" +
			    "\nFiyat: " + onizlemeFiyat + " TL"
			);
        if (indirimOrani > 0) {
            JTextField indirimliFiyatField = new JTextField();
            indirimliFiyatField.setBounds(700, 260, 300, 35);
            indirimliFiyatField.setFont(new Font("Arial", Font.BOLD, 14));
            indirimliFiyatField.setEditable(false);
            indirimliFiyatField.setText(" İndirimli Fiyat: " + indirimliFiyat + " TL ("+ String.format("%.0f", indirimOrani * 100) + "% indirim)");
            arkaPlan.add(indirimliFiyatField);
        }

		JButton odemeButton = new JButton("Ödeme yap");
		odemeButton.setBounds(300,600,300,50);
		arkaPlan.add(odemeButton);
		
		odemeButton.addActionListener(e -> {
		    String kartNo = kartNoField.getText();
		    String cvv = new String(cvvField.getPassword());

		    if(kartNo.isEmpty() || cvv.isEmpty()) {
		        JOptionPane.showMessageDialog(pencere, "Lütfen kart bilgilerini doldurun!");
		        return;
		    }
		    JOptionPane.showMessageDialog(pencere, "Ödeme tamamlandı!\nBiletiniz onaylandı.");
		    
		    String secilenTur = secilenKoltuk.getTipi();
		    Bilet bilet;
		    
		    if(secilenTur.equals("Ekonomi")) {
		        bilet = new EkonomikBilet(yolcu, ucus, secilenKoltuk);
		    } else {
		        bilet = new BusinessBilet(yolcu, ucus, secilenKoltuk);
		    }
		    bilet.biletiKaydet();
		    });
		iadeButton.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(java.awt.event.ActionEvent e) {
		        Bilet bilet;
		        if (secilenKoltuk.getTipi().equals("Ekonomi")) {
		            bilet = new EkonomikBilet(yolcu, ucus, secilenKoltuk);
		        } else {
		            bilet = new BusinessBilet(yolcu, ucus, secilenKoltuk);
		        }
		        IadeIslemleri.biletSil("biletler.txt", bilet);
		        IadeIslemleri.koltukBosalt("koltuklar.txt", bilet);
		        secilenKoltuk.setDolu(false);

		        JOptionPane.showMessageDialog(pencere, "Bilet iade edildi. Koltuk boşaltıldı.");
		    }
		});
		pencere.setVisible(true);
		}
	}
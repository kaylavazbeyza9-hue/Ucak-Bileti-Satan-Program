import javax.swing.*;

public class YolcuBilgileriEkrani {

    public YolcuBilgileriEkrani(Ucus ucus,Koltuk secilenKoltuk) {

        JFrame pencere = new JFrame("Yolcu Bilgileri");
        pencere.setSize(1500, 900);
        pencere.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pencere.setLayout(null);
        pencere.setLocationRelativeTo(null);
        
        ImageIcon icon = new ImageIcon("src/AnaEkran.png");
        JLabel arkaPlan = new JLabel(icon);
        arkaPlan.setBounds(0, 0, 1500, 900);
        arkaPlan.setLayout(null);
        pencere.add(arkaPlan);

        JLabel isimLabel = new JLabel("İsim:");
        isimLabel.setBounds(200, 300, 300, 50);
        arkaPlan.add(isimLabel);

        JTextField isimField = new JTextField();
        isimField.setBounds(300, 300, 300, 50);
        arkaPlan.add(isimField);

        JLabel soyisimLabel = new JLabel("Soyisim:");
        soyisimLabel.setBounds(200, 400, 300, 50);
        arkaPlan.add(soyisimLabel);

        JTextField soyisimField = new JTextField();
        soyisimField.setBounds(300, 400, 300, 50);
        arkaPlan.add(soyisimField);

        JLabel telefonLabel = new JLabel("Telefon:");
        telefonLabel.setBounds(200, 500, 300, 50);
        arkaPlan.add(telefonLabel);

        JTextField telefonField = new JTextField();
        telefonField.setBounds(300, 500, 300, 50);
        arkaPlan.add(telefonField);

        JButton ileriButon = new JButton("İleri");
        ileriButon.setBounds(300, 600, 300, 50);
        arkaPlan.add(ileriButon);
        
        ileriButon.addActionListener(e -> {
        	String isim = isimField.getText();
            String soyisim = soyisimField.getText();
            String telefon = telefonField.getText();

            if(!isim.isEmpty() && !soyisim.isEmpty() && !telefon.isEmpty()) {
                Yolcu yolcu = new Yolcu(isim, soyisim, telefon);
                new KartBilgileriEkrani(yolcu,secilenKoltuk,ucus);
                pencere.dispose();
            } else {
                JOptionPane.showMessageDialog(pencere, "Lütfen tüm alanları doldurun!");
            }
        });
        pencere.setVisible(true);
    }
}

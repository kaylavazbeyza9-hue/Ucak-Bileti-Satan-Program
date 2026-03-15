import java.util.ArrayList;
import javax.swing.*;
import java.awt.Font;
import com.toedter.calendar.JDateChooser;


public class AnaEkran {

    public AnaEkran(String kullaniciAdi) {

        JFrame pencere = new JFrame("Uçak Bileti Sistemi");
        pencere.setSize(1500, 900);
        pencere.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pencere.setLayout(null);
        pencere.setLocationRelativeTo(null);
        
        Ucus ucuslar = new Ucus("", "", "", "", 0);
        ArrayList<Ucus> tumUcuslar = ucuslar.ucuslariOku();


        ImageIcon icon = new ImageIcon("src/AnaEkran.png");
        JLabel arkaPlan = new JLabel(icon);
        arkaPlan.setBounds(0, 0, 1500, 900);
        arkaPlan.setLayout(null);
        pencere.add(arkaPlan);


        JLabel baslik = new JLabel("Uçak Bileti Ara", SwingConstants.CENTER);
        baslik.setFont(new Font("Arial", Font.BOLD, 28));
        baslik.setBounds(0, 30, 1500, 40);
        arkaPlan.add(baslik);
        
        JComboBox<Ucus> ucusBox = new JComboBox<>();
        ucusBox.setBounds(350, 550, 400, 35);
        arkaPlan.add(ucusBox);


        JLabel neredenLabel = new JLabel("Nereden");
        neredenLabel.setBounds(350, 180, 100, 25);
        arkaPlan.add(neredenLabel);

        JComboBox<String> neredenBox = new JComboBox<>(
                new String[]{"İstanbul", "Ankara", "İzmir", "Antalya"}
        );
        neredenBox.setBounds(350, 210, 200, 30);
        arkaPlan.add(neredenBox);

        JLabel nereyeLabel = new JLabel("Nereye");
        nereyeLabel.setBounds(600, 180, 100, 25);
        arkaPlan.add(nereyeLabel);
        
        JButton ileriButon = new JButton("İleri");
        ileriButon.setBounds(850, 700,200, 30);
        arkaPlan.add(ileriButon);

        ileriButon.addActionListener(e -> {
            Ucus secilenUcus = (Ucus) ucusBox.getSelectedItem();
            if(secilenUcus != null) {
                new KoltukSecimEkrani(secilenUcus);
                pencere.dispose(); 
            } else {
                JOptionPane.showMessageDialog(pencere, "Lütfen önce bir uçuş seçin!");
            }
        });

        JComboBox<String> nereyeBox = new JComboBox<>(
                new String[]{"İstanbul", "Ankara", "İzmir", "Antalya"}
        );
        nereyeBox.setBounds(600, 210, 200, 30);
        arkaPlan.add(nereyeBox);
        
        JLabel gidisLabel = new JLabel("Gidiş Tarihi");
        gidisLabel.setBounds(600, 300, 100, 25);
        arkaPlan.add(gidisLabel);
        
        JDateChooser gidisTarih = new JDateChooser();
        gidisTarih.setBounds(600, 330, 200, 30);
        gidisTarih.setDateFormatString("yyyy-MM-dd");
        gidisTarih.setMinSelectableDate(new java.util.Date());
        arkaPlan.add(gidisTarih);

        JButton araButon = new JButton("Uçuş Ara");
        araButon.setBounds(850,330,200,30);
        arkaPlan.add(araButon);
    
        araButon.addActionListener(e -> {
        	ucusBox.removeAllItems();

        	String nereden = neredenBox.getSelectedItem().toString();
            String nereye = nereyeBox.getSelectedItem().toString();
            String tarih = new java.text.SimpleDateFormat("yyyy-MM-dd").format(gidisTarih.getDate());


            for (int i = 0; i < tumUcuslar.size(); i++) {

            	Ucus ucus = tumUcuslar.get(i);

            	if (ucus.getNereden().equals(nereden) && ucus.getNereye().equals(nereye) && ucus.getTarih().equals(tarih)) {
            		ucusBox.addItem(ucus);
            		}
            	}
            });
        pencere.setVisible(true);
        }
}

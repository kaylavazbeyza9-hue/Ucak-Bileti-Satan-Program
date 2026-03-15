import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public abstract class Bilet implements Biletİnterface {
	private Yolcu yolcu;
	private Ucus ucus;
	private Koltuk koltuk;
	private String biletTuru;
	
	public Bilet(Yolcu yolcu,Ucus ucus,Koltuk koltuk, String biletTuru) {
		this.yolcu = yolcu;
		this.ucus = ucus;
		this.koltuk = koltuk;
		this.biletTuru = biletTuru;
	}
	protected Yolcu getYolcu() {
		return yolcu;
	}
	protected Ucus getUcus() {
		return ucus;
	}
	protected Koltuk getKoltuk() {
		return koltuk;
	}
	protected String getBiletTuru() {
		return biletTuru;
	}
	public abstract double fiyatHesapla();
	public void biletIade() {
	}
	public void biletiKaydet() {
	    try(BufferedWriter bw = new BufferedWriter(new FileWriter("Biletler.txt", true))) {
	        bw.write(yolcu.getİsim() + "," + yolcu.getSoyisim() + "," + yolcu.getTelefonNo() + "," +
	                 ucus.getNereden() + "," + ucus.getNereye() + "," + ucus.getTarih() + "," +
	                 ucus.getSaat() + "," + koltuk.getKoltukNo() + "," + koltuk.getTipi() + "," +
	                 fiyatHesapla());
	        bw.newLine();
	    } catch(IOException e) {
	        System.out.println("Bilet dosyasına yazılamadı");
	        e.printStackTrace();
	    }
	}
}
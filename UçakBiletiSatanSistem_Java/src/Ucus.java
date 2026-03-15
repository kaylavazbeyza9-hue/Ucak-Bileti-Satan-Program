import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Ucus{
	private String nereden;
	private String nereye;
	private String tarih;
	private String saat;
	private int mesafe;
	
	public Ucus(String nereden,String nereye,String tarih,String saat, int mesafe) {
		this.nereden = nereden;
		this.nereye = nereye;
		this.tarih = tarih;
		this.saat = saat;
		this.mesafe = mesafe;
	}
	public String getNereden() {
		return nereden;
	}
	public void setNereden(String nereden) {
		this.nereden = nereden;
	}
	public String getNereye() {
		return nereye;
	}
	public void setNereye(String nereye) {
		this.nereye = nereye;
	}
	public String getTarih() {
		return tarih;
	}
	public void setTarih(String tarih) {
		this.tarih = tarih;
	}
	public String getSaat() {
		return saat;
	}
	public void setSaat(String saat) {
		this.saat = saat;
	}
	public int getMesafe() {
		return mesafe;
	}
	public void setMesafe(int mesafe) {
		this.mesafe = mesafe;
	}
	public String toString() {
		return nereden + "=>" + nereye + "," + tarih + "," + saat;
	}
	public void ucusEkle() {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("Ucuslar.txt", true))) {
			bw.write(this.nereden + "," + this.nereye + "," + this.tarih + "," + this.saat + "," + this.mesafe);
			bw.newLine();
			} catch (IOException e) {
				System.out.println("Uçuş dosyasına yazılamadı: " + e.getMessage());
				}
		}
	public ArrayList<Ucus> ucuslariOku() {
		ArrayList<Ucus> liste = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader("Ucuslar.txt"))) {
			String satir;
			while ((satir = br.readLine()) != null) {
				String[] u = satir.split(",");
	            String nereden = u[0];
	            String nereye = u[1];
	            String tarih = u[2];
	            String saat = u[3];
	            int mesafe = Integer.parseInt(u[4]);
	            liste.add(new Ucus(nereden, nereye, tarih, saat, mesafe));
	            }
			} catch (IOException e) {
				System.out.println("Uçuş dosyası okunamadı: " + e.getMessage());
				}
		return liste;
		}
	}

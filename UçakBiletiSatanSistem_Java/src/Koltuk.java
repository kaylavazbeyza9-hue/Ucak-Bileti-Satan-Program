import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Koltuk{
	private String koltukNo;
	private boolean dolu;
	private String tipi;
	
	public Koltuk(String koltukNo,String tipi) {
		this.koltukNo = koltukNo;
		this.dolu = false;
		this.tipi = tipi;
	}
	public String getTipi() {
		return tipi;
	}
	public void setTipi(String tipi) {
		this.tipi = tipi;
	}
	public String getKoltukNo() {
		return koltukNo;
	}
	public void setKoltukNo(String koltukNo) {
		this.koltukNo = koltukNo;
	}
	public boolean isDolu() {
		return dolu;
	}
	public void setDolu(boolean dolu) {
		this.dolu = dolu;
	}
	@Override
	public String toString() {
		return koltukNo + (dolu ? "(Dolu)" : "(Boş)");
	}
	public void koltukKaydet(String ucusBilgisi) {
	    try (BufferedWriter bw = new BufferedWriter(new FileWriter("koltuklar.txt", true))) {
	    	bw.write(ucusBilgisi + "," + koltukNo + "," + dolu);
	    	bw.newLine();
	    	} catch (IOException e) {
	    		System.out.println("Koltuk dosyasına yazılamadı: " + e.getMessage());
	    		}
	    }
	public ArrayList<Koltuk> koltuklariOku(String ucusBilgisi) {
	    ArrayList<Koltuk> liste = new ArrayList<>();
	    try (BufferedReader br = new BufferedReader(new FileReader("koltuklar.txt"))) {
	        String satir;
	        while ((satir = br.readLine()) != null) {
	            if(satir.trim().isEmpty()) continue;
	            String[] K = satir.split(","); 
	            String ucus = K[0];
	            String no = K[1];
	            boolean dolu = Boolean.parseBoolean(K[2]);
	            if (ucus.equals(ucusBilgisi)) {
	            	String tipi = (Integer.parseInt(no) <= 4) ? "Business" : "Ekonomi";
	            	Koltuk k = new Koltuk(no, tipi);
	                k.setDolu(dolu);
	                liste.add(k);
	            }
	        }
	    } catch (IOException e) {
	        System.out.println("Koltuk dosyası okunamadı: " + e.getMessage());
	    }
	    return liste;
	}
}
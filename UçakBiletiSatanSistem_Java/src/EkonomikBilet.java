public class EkonomikBilet extends Bilet implements Biletİnterface{
	public EkonomikBilet(Yolcu yolcu,Ucus ucus,Koltuk Koltuk) {
		super(yolcu,ucus,Koltuk,"Ekonomik");
	}
	@Override
	public double fiyatHesapla() {
		return getUcus().getMesafe()*2;
	}
	@Override
	public void biletIade(String dosyaAdi) {
		IadeIslemleri.biletSil("biletler.txt", this);
		IadeIslemleri.koltukBosalt("koltuklar.txt", this);
	}
}

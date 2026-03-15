public class BusinessBilet extends Bilet implements Biletİnterface {
	public BusinessBilet(Yolcu yolcu,Ucus ucus,Koltuk koltuk) {
		super(yolcu,ucus,koltuk,"Business");
	}
	@Override
	public double fiyatHesapla() {
		return getUcus().getMesafe()*4;
	}
	@Override
	public void biletIade(String dosyaAdi) {
		IadeIslemleri.biletSil("biletler.txt", this);
		IadeIslemleri.koltukBosalt("koltuklar.txt", this);
		}
	}


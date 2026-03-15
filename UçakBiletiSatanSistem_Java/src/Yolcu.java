public class Yolcu{
	private String isim;
	private String soyisim;
	private String telefonNo;
	
	public Yolcu(String isim,String soyisim,String telefonNo) {
		this.isim = isim;
		this.soyisim = soyisim;
		this.telefonNo = telefonNo;
	}
	public String getİsim() {
		return isim;
	}
	public void setİsim(String isim) {
		this.isim = isim;
	}
	public String getSoyisim() {
		return soyisim;
	}
	public void setSoyisim(String soyisim) {
		this.soyisim = soyisim;
	}
	public String getTelefonNo(){
	return telefonNo;
	}
	public void setTelefonNo(String telefonNo){
		this.telefonNo = telefonNo;
	}
	@Override
	public String toString() {
		return isim +""+ soyisim +""+ telefonNo;
	}
}

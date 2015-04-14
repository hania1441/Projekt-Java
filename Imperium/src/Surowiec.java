import java.util.GregorianCalendar;

public class Surowiec extends Byt {
	private int identyfikator;
	private String nazwa;
	private int obj;
	private int waga;
	private Osada osadaProd;
	private Osada osadaKup;
	private int cena;
	boolean czySpoz;

	public Surowiec(int x, int y, int identyfikator, String nazwa, int obj,
			int waga, Osada osadaProd, Osada osadaKup, int cena) {
		super(x, y);
		this.identyfikator = identyfikator;
		this.nazwa = nazwa;
		this.obj = obj;
		this.waga = waga;
		this.osadaKup = osadaKup;
		this.osadaProd = osadaProd;
		this.cena = cena;
		this.czySpoz = false;
	}

	public int getIdentyfikator() {
		return identyfikator;
	}

	public String getNazwa() {
		return nazwa;
	}

	public int getObj() {
		return obj;
	}

	public int getWaga() {
		return waga;
	}

	public Osada getOsadaProd() {
		return osadaProd;
	}

	public Osada getOsadaKup() {
		return osadaKup;
	}

	public int getCena() {
		return cena;
	}

	public boolean getCzySpozyw() {
		return czySpoz;
	}

	public void setIdentyfikator(int id) {
		this.identyfikator = id;
	}

	public void setNazwa(String naz) {
		this.nazwa = naz;
	}

	public void setObj(int obj) {
		this.obj = obj;
	}

	public void setWaga(int waga) {
		this.waga = waga;
	}

	public void setOsadaProd(Osada os) {
		this.osadaProd = os;
	}

	public void setOsadaKup(Osada os) {
		this.osadaKup = os;
	}

	public void setCena(int cena) {
		this.cena = cena;
	}
}

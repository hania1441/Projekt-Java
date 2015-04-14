import java.util.GregorianCalendar;

public class ArtykulSpozywczy extends Surowiec {
	private GregorianCalendar dataWaznosci;

	public ArtykulSpozywczy(int x, int y, int identyfikator, String nazwa,
			int obj, int waga, Osada osadaProd, Osada osadaKup,
			GregorianCalendar dataWaznosci, int cena) {
		super(x, y, identyfikator, nazwa, obj, waga, osadaProd, osadaKup, cena);
		this.dataWaznosci = dataWaznosci;
		this.czySpoz = true;
	}

	public GregorianCalendar getDataWaznosci() {
		return dataWaznosci;
	}

	public void setDataWa¿nosci(GregorianCalendar dataWaznosci) {
		this.dataWaznosci = dataWaznosci;
	}

}

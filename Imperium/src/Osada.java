import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Osada extends Byt {
	private String nazwa;
	private int liczbaMieszkancow;
	private volatile int stanSkarbca;
	private ArrayList<String> SurowceProdukowane;
	private ArrayList<String> SurowceKupowane;
	private ArrayList<Osada> listaOsad;
	private int maxPojMagazynu;
	private int aktPojMagazynu;
	private boolean sprDate;
	private volatile ArrayList<Surowiec> aktStanMagazynu;
	private volatile ArrayList<ArtykulSpozywczy> aktStanArtSpoz;
	private volatile ArrayList<ArtykulSpozywczy> doDaty;
	private Droga droga;
	private ArrayList<Surowiec> surowiecTemp;
	private Random losowanie;
	private boolean artSpoz;
	private boolean zajeta;
	private String ArtSpoz[] = { "maka", "maslo", "olej", "cukier", "mleko",
			"wino" };

	public Osada(int x, int y, String nazwa, int liczbaMieszkancow,
			int stanSkarbca, int maxPojMagazynu, boolean artSpoz,
			ArrayList<Osada> listaOsad) {
		super(x, y);
		this.SurowceProdukowane = new ArrayList<>();
		this.SurowceKupowane = new ArrayList<>();
		this.aktStanMagazynu = new ArrayList<>();
		this.aktStanArtSpoz = new ArrayList<>();
		this.doDaty = new ArrayList<>();
		this.surowiecTemp = new ArrayList<>();
		this.liczbaMieszkancow = liczbaMieszkancow;
		this.maxPojMagazynu = maxPojMagazynu;
		this.nazwa = nazwa;
		this.aktPojMagazynu = 0;
		this.stanSkarbca = stanSkarbca;
		this.listaOsad = listaOsad;
		this.artSpoz = artSpoz;
		this.zajeta = false;
		this.sprDate = false;
		losowanie = new Random();

		listaOsad.add(this);

	}

	public String getNazwa() {
		return nazwa;
	}

	public int getLiczbaMieszkancow() {
		return liczbaMieszkancow;
	}

	public float getStanSkarbca() {
		return stanSkarbca;
	}

	public ArrayList<String> getSurowceProdukowane() {
		return SurowceProdukowane;
	}

	public ArrayList<String> getSurowceKupowane() {
		return SurowceKupowane;
	}

	public boolean getArtSpoz() {
		return artSpoz;
	}

	public void setNazwaSurowcaProd(ArrayList<String> SurowceProdukowane) {
		this.SurowceProdukowane = SurowceProdukowane;
	}

	public void setNazwaSurowcaKupow(ArrayList<String> SurowceKupowane) {
		this.SurowceKupowane = SurowceKupowane;
	}

	public int getMaxPojMagazynu() {
		return maxPojMagazynu;
	}

	public int getAktPojMagazynu() {
		return aktPojMagazynu;
	}

	public ArrayList<Surowiec> getAktStanMagazynu() {
		return aktStanMagazynu;
	}

	public ArrayList<ArtykulSpozywczy> getAktStanArtykulSpozywczego() {
		return aktStanArtSpoz;
	}

	public Droga getDroga() {
		return droga;
	}

	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}

	public void setLiczbaMieszkancow(int liczbaMieszkancow) {
		this.liczbaMieszkancow = liczbaMieszkancow;
	}

	public void setStanSkarbca(int stanSkarbca) {
		this.stanSkarbca = stanSkarbca;
	}

	public void setMaxPojMagazynu(int maxPojMagazynu) {
		this.maxPojMagazynu = maxPojMagazynu;
	}

	public void setAktPojMagazynu(int aktPojMagazynu) {
		this.aktPojMagazynu = aktPojMagazynu;
	}

	public void setAktStanMagazynu(ArrayList<Surowiec> aktStanMagazynu) {
		this.aktStanMagazynu = aktStanMagazynu;
	}

	public void setDroga(Droga droga) {
		this.droga = droga;
	}

	public void produkuj(DoLosowania los) {
		Surowiec s;
		ArtykulSpozywczy a;

		int m = los.getIdSurowca();

		GregorianCalendar dataWaznosci = null;

		for (int i = 0; i < 6; i++) {
			if (this.getSurowceProdukowane().get(0).equals(ArtSpoz[i])) {
				int y = losowanie.nextInt(27) + 1;
				int p = losowanie.nextInt(1);
				dataWaznosci = new GregorianCalendar(2014, p, y);
				break;
			}
		}
		int obj = losowanie.nextInt(9) + 10;
		int i = losowanie.nextInt(10);
		while (i == listaOsad.indexOf(this)) {
			i = losowanie.nextInt(10);
		}
		Osada kup = listaOsad.get(i);
		if (this.aktPojMagazynu + obj <= (7 * this.maxPojMagazynu) / 10) {
			los.setIdSurowca(los.getIdSurowca() + 1);

			if (dataWaznosci == null) {

				s = new Surowiec(this.getX(), this.getY(), m,
						this.SurowceProdukowane.get(losowanie.nextInt(2)), obj,
						losowanie.nextInt(10), this, kup, losowanie.nextInt(20));
				this.aktStanMagazynu.add(s);
				this.aktPojMagazynu += s.getObj();
			} else {

				a = new ArtykulSpozywczy(this.getX(), this.getY(), m,
						this.SurowceProdukowane.get(losowanie.nextInt(2)), obj,
						losowanie.nextInt(10), this, kup, dataWaznosci,
						losowanie.nextInt(20));
				this.aktStanArtSpoz.add(a);
				this.aktPojMagazynu += a.getObj();
			}

		}

	}

	public void kup(Handlarz h, ArrayList<Surowiec> surowiecTemp)
			throws InterruptedException {

		h.setOpuscOsade(false);
		surowiecTemp.addAll(h.getWoz().getSurowcePrzewozone());
		for (Surowiec s : surowiecTemp) {

			if (s.getOsadaKup().equals(this)) {
				if ((this.aktPojMagazynu + s.getObj() <= this.maxPojMagazynu)
						&& (this.stanSkarbca >= s.getCena())) {
					if (s.getCzySpozyw()) {
						this.aktStanArtSpoz.add((ArtykulSpozywczy) s);
					} else {

						this.aktStanMagazynu.add(s);
					}
					this.aktPojMagazynu += s.getObj();
					this.stanSkarbca -= s.getCena();
					h.getWoz().ZdejmijZWozu(s);
				} else {
					h.setCzeka(true);
					return;
				}
			}
		}
		surowiecTemp.clear();

		return;

	}

	public void sprzedaj(Handlarz h) {
		while (this.zajeta) {
			try {
				h.sleep(10);
			} catch (InterruptedException ex) {
				Logger.getLogger(Osada.class.getName()).log(Level.SEVERE, null,
						ex);
			}
		}
		this.zajeta = true;
		if (h.getWoz().getAktPoj() + 20 > h.getWoz().getMaxPoj()) {
			h.setOpuscOsade(true);
			this.zajeta = false;
			return;
		}
		int i = 0;
		if ((this.getArtSpoz() == true) && (!this.aktStanArtSpoz.isEmpty())) {

			while ((h.getWoz().getAktPoj() + 20 <= h.getWoz().getMaxPoj())
					&& (i < aktStanArtSpoz.size() - 1)) {

				while (sprDate) {
					try {
						sleep(10);
					} catch (InterruptedException ex) {
						Logger.getLogger(Osada.class.getName()).log(
								Level.SEVERE, null, ex);
					}
				}

				if (this.aktStanArtSpoz.get(i).getOsadaProd().equals(this)) {

					h.getWoz().dodajDoWozu(this.aktStanArtSpoz.get(i));
					this.aktPojMagazynu -= this.aktStanArtSpoz.get(i).getObj();
					this.stanSkarbca += this.aktStanArtSpoz.get(i).getCena();
					this.aktStanArtSpoz.remove(i);
				} else {
					i++;
				}

			}
		}

		if ((this.getArtSpoz() == false) && (!this.aktStanMagazynu.isEmpty())) {

			while ((h.getWoz().getAktPoj() + 20 <= h.getWoz().getMaxPoj())
					&& (i < aktStanMagazynu.size() - 1)) {

				if (this.aktStanMagazynu.get(i).getOsadaProd().equals(this)) {
					h.getWoz().dodajDoWozu(aktStanMagazynu.get(i));
					this.aktPojMagazynu -= this.aktStanMagazynu.get(i).getObj();
					this.stanSkarbca += this.aktStanMagazynu.get(i).getCena();
					this.aktStanMagazynu.remove(i);
				} else {
					i++;
				}
			}
		}

		h.setOpuscOsade(true);
		this.zajeta = false;

	}

	public void sprawdzDateWaznosci() throws InterruptedException {
		this.sprDate = true;
		GregorianCalendar dataBiezaca = new GregorianCalendar();
		doDaty.addAll(aktStanArtSpoz);
		Iterator<ArtykulSpozywczy> it = doDaty.iterator();

		while (it.hasNext()) {
			ArtykulSpozywczy s = it.next();
			GregorianCalendar dataWaznosci = s.getDataWaznosci();
			if (dataWaznosci.before(dataBiezaca)) {

				this.aktPojMagazynu -= s.getObj();
				this.aktStanArtSpoz.remove(s);
				this.stanSkarbca += s.getCena();

			}
		}
		doDaty.clear();
		this.sprDate = false;
	}

}

import java.util.ArrayList;

public class Woz extends ObiektRuchomy {
	private float maxPoj, aktPoj;
	private ArrayList<Surowiec> zbAktPrzewozSurow;
	private ArrayList<Woz> listaWozow;
	private ArrayList<Osada> listaOsad;
	private ArrayList<Skrzyzowanie> listaSkrzyzowan;
	private ArrayList<Handlarz> listaHandlarzy;
	private ArrayList<Droga> listaDrog;

	public Woz(int x, int y, float maxPoj, int szybkosc, Mapa myTimer) {
		super(x, y, szybkosc, myTimer);
		this.listaHandlarzy = myTimer.getListaHandlarzy();
		this.listaOsad = myTimer.getListaOsad();
		this.listaSkrzyzowan = myTimer.getListaSkrzyzowan();
		this.listaDrog = myTimer.getListaDrog();
		this.maxPoj = maxPoj;
		this.aktPoj = 0;
		this.zbAktPrzewozSurow = new ArrayList<>();
		this.listaWozow = myTimer.getListaWozow();
		listaWozow.add(this);
	}

	public float getMaxPoj() {
		return maxPoj;
	}

	public void setMaxPoj(float maxPoj) {
		this.maxPoj = maxPoj;
	}

	public float getAktPoj() {
		return aktPoj;
	}

	public void setAktPoj(float aktPoj) {
		this.aktPoj = aktPoj;
	}

	public ArrayList<Surowiec> getSurowcePrzewozone() {
		return zbAktPrzewozSurow;
	}

	public void dodajDoWozu(Surowiec prod) {
		if ((aktPoj + prod.getObj()) <= maxPoj) {
			this.setSzybkosc(this.getSzybkosc() + 1);
			zbAktPrzewozSurow.add(prod);
			aktPoj += prod.getObj();
		}
	}

	public void ZdejmijZWozu(Surowiec prod) {
		this.setSzybkosc(this.getSzybkosc() - 1);
		zbAktPrzewozSurow.remove(prod);
		aktPoj -= prod.getObj();
	}

	public void zepsujSie() {
		for (Handlarz h : listaHandlarzy) {
			if (h.getWoz().equals(this)) {
				h.setStop(true);
			}
		}
	}
}

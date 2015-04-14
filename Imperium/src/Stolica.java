import java.io.IOException;
import java.util.ArrayList;

public class Stolica extends Osada {
	private int id = 1;
	private ArrayList<LegionZolnierzy> listaLegionow;
	private ArrayList<LegionZolnierzy> listaLegDoWyslania;
	private ArrayList<Skrzyzowanie> listaSkrzyzowan;
	private ArrayList<Droga> listaDrog;
	private ArrayList<Osada> listaOsad;

	public Stolica(int x, int y, String nazwa, int liczbaMieszkancow,
			int stanSkarbca, int maxPojMagazynu, boolean artSpoz,
			ArrayList<Osada> listaOsad,
			ArrayList<LegionZolnierzy> listaLegionow,

			ArrayList<Skrzyzowanie> listaSkrzyzowan, ArrayList<Droga> listaDrog) {
		super(x, y, nazwa, liczbaMieszkancow, stanSkarbca, maxPojMagazynu,
				artSpoz, listaOsad);
		this.listaDrog = listaDrog;
		this.listaLegionow = listaLegionow;
		this.listaSkrzyzowan = listaSkrzyzowan;
		this.listaOsad = listaOsad;
		this.listaLegDoWyslania = new ArrayList<>();

	}

	public void run() {

	}

	public void wyslijLegionZolnierzy(Osada osadaCel) throws IOException,
			InterruptedException {

		listaLegDoWyslania.addAll(listaLegionow);
		if (!listaLegDoWyslania.isEmpty()) {
			for (LegionZolnierzy i : listaLegDoWyslania) {
				if ((i.getX() == listaOsad.get(0).getX() + 30)
						&& (i.getY() == listaOsad.get(0).getY())) {

					i.setOsadaCel(osadaCel);
					i.start();
					break;
				}
			}

		}
	}

}

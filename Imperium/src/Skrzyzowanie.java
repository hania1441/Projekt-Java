import java.util.ArrayList;

public class Skrzyzowanie extends Byt {

	private boolean wolne;
	private Droga dol, gora, prawo, lewo;
	private Droga[] wskazowka;

	/**
	 *
	 * @param dol
	 * @param gora
	 * @param prawo
	 * @param lewo
	 * @param zajete
	 */
	public Skrzyzowanie(int x, int y, Droga dol, Droga gora, Droga lewo,
			Droga prawo, Droga[] wskazowka,
			ArrayList<Skrzyzowanie> listaSkrzyzowan) {

		super(x, y);
		this.wolne = true;
		this.dol = dol;
		this.gora = gora;
		this.lewo = lewo;
		this.prawo = prawo;
		this.wskazowka = new Droga[10];
		for (int i = 0; i < 10; i++) {
			this.wskazowka[i] = wskazowka[i];
		}
		listaSkrzyzowan.add(this);
	}

	public synchronized boolean getWolne() throws InterruptedException {
		while (!wolne) {
			wait();

		}

		wolne = false;
		return wolne;
	}

	public synchronized void setWolne() {
		wolne = true;
		notify();
	}

	public Droga getGora() {
		return gora;
	}

	public Droga getDol() {
		return dol;
	}

	public Droga getLewo() {
		return lewo;
	}

	public Droga getPrawo() {
		return prawo;
	}

	public Droga getWskazowka(int i) {
		return this.wskazowka[i];
	}
}

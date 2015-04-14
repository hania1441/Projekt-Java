import java.util.ArrayList;

public class Droga extends Byt {
	private int xKoncowe;
	private int yKoncowe;
	private Skrzyzowanie skrzyzowanie;

	private boolean pozioma;
	private int kierunek;// 1-prwo, gora, -1-lewodol

	public Droga(int xPoczatkowe, int yPoczatkowe, int xKoncowe, int yKoncowe,
			boolean pozioma, int kierunek, ArrayList<Droga> ListaDrog) {
		super(xPoczatkowe, yPoczatkowe);
		this.xKoncowe = xKoncowe;
		this.yKoncowe = yKoncowe;
		this.pozioma = pozioma;
		this.kierunek = kierunek;

		ListaDrog.add(this);

	}

	public int getXGora() {
		return xKoncowe;
	}

	public int getYGora() {
		return yKoncowe;
	}

	public boolean getPozioma() {
		return pozioma;
	}

	public int getKierunek() {
		return kierunek;
	}

	public Skrzyzowanie getSkrzyzowanie() {
		return skrzyzowanie;
	}

	public void setSkrzyzowanie(Skrzyzowanie s) {
		this.skrzyzowanie = s;
	}
}

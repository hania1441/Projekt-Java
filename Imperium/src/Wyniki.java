import java.io.Serializable;

public class Wyniki implements Serializable, Comparable<Wyniki> {
	private transient int idWyniku;
	private String imieGracza;
	private int lbPokonanychPlemion;
	private int czasRozgrywki;

	public void setLiczbaPokonanychPlemion(int lb) {
		this.lbPokonanychPlemion = lb;
	}

	public int getLiczbaPokonanychPlemion() {
		return lbPokonanychPlemion;
	}

	public void setCzasRozgrywki(int czas) {
		this.czasRozgrywki = czas;
	}

	public int getCzasRozgrywki() {
		return czasRozgrywki;
	}

	public void setIdWyniku(int id) {
		this.idWyniku = id;
	}

	public int getIdWyniku() {
		return idWyniku;
	}

	public void setImie(String imie) {
		this.imieGracza = imie;
	}

	public String getImie() {
		return imieGracza;
	}

	@Override
	public int compareTo(Wyniki w) {

		return w.czasRozgrywki - czasRozgrywki;

	}
}

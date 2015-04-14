import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class Handlarz extends ObiektRuchomy {
	private String imie;
	private String nazwisko;
	private Woz woz;
	private boolean czeka;
	private ArrayList<ObiektRuchomy> listaObRuch;
	private ArrayList<Handlarz> listaHandlarzy;
	private ArrayList<Osada> listaOsad;
	private ArrayList<LegionZolnierzy> listaLegionow;
	private ArrayList<PlemieBarbarzyncow> listaPlemion;
	private ArrayList<Skrzyzowanie> listaSkrzyzowan;
	private ArrayList<Droga> listaDrog;
	private int nzmienione;
	private ArrayList<Surowiec> surowiecTemp;
	private boolean opuscOsade;
	private boolean zmien;

	public Handlarz(String imie, String nazwisko, Woz woz, Mapa myTimer)
			throws IOException, URISyntaxException {
		super(woz.getX(), woz.getY(), woz.getSzybkosc(), myTimer);
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.woz = woz;
		this.listaHandlarzy = myTimer.getListaHandlarzy();
		this.setListaDrog(myTimer.getListaDrog());
		this.listaSkrzyzowan = myTimer.getListaSkrzyzowan();
		this.listaOsad = myTimer.getListaOsad();
		this.listaObRuch = new ArrayList<>();
		this.listaLegionow = new ArrayList<>();
		this.listaPlemion = myTimer.getListaPlemionBarbarzyncow();
		this.surowiecTemp = new ArrayList<>();
		ImageFile = new File("grafika/handlarz.png");

		img = ImageIO.read(ImageFile);
		this.czeka = false;
		this.zmien = false;
		listaHandlarzy.add(this);
		start();
	}

	public void run() {
		for (Osada os : listaOsad) {
			if ((this.getX() <= os.getX() + 40)
					&& (this.getX() >= os.getX() - 40)
					&& (this.getY() <= os.getY() + 40)
					&& (this.getY() >= os.getY() - 40)) {
				this.setOsadaCel(os);

			}

		}
		this.getOsadaCel().sprzedaj(this);

		if (opuscOsade) {

			if (!this.woz.getSurowcePrzewozone().isEmpty()) {
				this.setOsadaCel(this.getWoz().getSurowcePrzewozone().get(0)
						.getOsadaKup());
				try {
					this.ruszajSie(this.getOsadaCel(), listaOsad,
							listaSkrzyzowan);
				} catch (InterruptedException ex) {
					Logger.getLogger(Handlarz.class.getName()).log(
							Level.SEVERE, null, ex);
				}
			}
		}
		while (true) {
			try {
				this.getOsadaCel().kup(this, surowiecTemp);
				this.getOsadaCel().sprzedaj(this);

				if (opuscOsade) {

					if (!this.woz.getSurowcePrzewozone().isEmpty()) {
						this.setOsadaCel(this.getWoz().getSurowcePrzewozone()
								.get(0).getOsadaKup());

						this.ruszajSie(this.getOsadaCel(), listaOsad,
								listaSkrzyzowan);
					}
				}
			} catch (InterruptedException ex) {
				Logger.getLogger(Handlarz.class.getName()).log(Level.SEVERE,
						null, ex);
			}
		}
	}

	public void zmienOsadeCel(String nazwaOs) {

		for (Osada os : listaOsad) {
			if (os.getNazwa().equals(nazwaOs)) {
				this.setOsadaCel(os);
				zmien = true;
				nzmienione = listaOsad.indexOf(os);

			}
		}

	}

	public boolean getOpuscOsade() {
		return opuscOsade;
	}

	public void setOpuscOsade(boolean opuscOsade) {
		this.opuscOsade = opuscOsade;
	}

	public int getNZmnienione() {
		return nzmienione;
	}

	public void setNZmienione(int nzmienione) {
		this.nzmienione = nzmienione;
	}

	public boolean getCzeka() {
		return czeka;
	}

	public void setCzeka(boolean czeka) {
		this.czeka = czeka;
	}

	public String getImie() {
		return imie;
	}

	public String getNazwisko() {
		return nazwisko;
	}

	public Woz getWoz() {
		return woz;
	}

	public void setImie(String imie) {
		this.imie = imie;
	}

	public void setNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}

	public void setWoz(Woz woz) {
		this.woz = woz;
	}

	public void rysujSie(Graphics g) {
		g.drawImage(img, this.getX() - 5, this.getY() - 8, null);
	}

	public void idzPoDrodze(Droga droga, ArrayList<Skrzyzowanie> listaSkrzyzowan)
			throws InterruptedException {
		int xCel = -1, yCel = -1;

		if (droga.getKierunek() == -1) {
			for (Skrzyzowanie k : listaSkrzyzowan) {
				if ((droga.getX() >= k.getX() - 15)
						&& (droga.getX() <= k.getX() + 15)
						&& (droga.getY() >= k.getY() - 15)
						&& (droga.getY() <= k.getY() + 15)) {
					xCel = k.getX();
					yCel = k.getY();

				}

			}
		}
		if (droga.getKierunek() == 1) {
			for (Skrzyzowanie k : listaSkrzyzowan) {
				if ((droga.getXGora() >= k.getX() - 15)
						&& (droga.getXGora() <= k.getX() + 15)
						&& (droga.getYGora() >= k.getY() - 15)
						&& (droga.getYGora() <= k.getY() + 15)) {
					xCel = k.getX();
					yCel = k.getY();

				}
			}
		}
		if ((xCel == -1) && (yCel == -1)) {
			if (droga.getKierunek() == -1) {
				for (Iterator<Osada> it = listaOsad.iterator(); it.hasNext();) {
					Osada j = it.next();
					if ((droga.getX() >= j.getX() - 30)
							&& (droga.getX() <= j.getX() + 30)
							&& (droga.getY() >= j.getY() - 30)
							&& (droga.getY() <= j.getY() + 30)) {
						xCel = j.getX();
						yCel = j.getY();

					}
				}
			}
			if (droga.getKierunek() == 1) {
				for (Iterator<Osada> it = listaOsad.iterator(); it.hasNext();) {
					Osada j = it.next();
					if ((droga.getXGora() >= j.getX() - 30)
							&& (droga.getXGora() <= j.getX() + 30)
							&& (droga.getYGora() >= j.getY() - 30)
							&& (droga.getYGora() <= j.getY() + 30)) {
						xCel = j.getX();
						yCel = j.getY();

					}
				}
			}
		}

		if (droga.getPozioma() == true) {
			this.setY(droga.getY());
			if (droga.getKierunek() == 1) {
				while (this.getX() <= xCel - 11) {
					listaObRuch.addAll(listaHandlarzy);
					listaObRuch.addAll(listaLegionow);
					listaObRuch.addAll(listaPlemion);
					Iterator<ObiektRuchomy> it = listaObRuch.iterator();
					while (it.hasNext()) {
						ObiektRuchomy h = it.next();
						if ((this.getX() + 8 == h.getX())
								&& (this.getY() == h.getY())) {
							while (this.getX() + 8 == h.getX()) {
								try {
									Thread.sleep(10);
								} catch (InterruptedException ex) {

								}
							}
							break;
						}
					}

					if (this.getStop() == false) {
						this.setX(this.getX() + droga.getKierunek());
						this.getWoz().setX(this.getX());
						this.getWoz().setY(this.getY());
						listaObRuch.clear();

						try {
							Thread.sleep(this.getSzybkosc());
						} catch (InterruptedException ex) {

						}
					} else {
						Thread.sleep(10);
					}
				}
			} else {
				while (this.getX() != xCel + 14) {
					listaObRuch.addAll(listaHandlarzy);
					listaObRuch.addAll(listaLegionow);
					listaObRuch.addAll(listaPlemion);
					Iterator<ObiektRuchomy> it = listaObRuch.iterator();
					while (it.hasNext()) {
						ObiektRuchomy h = it.next();
						if ((this.getX() + (8 * droga.getKierunek()) == h
								.getX()) && (this.getY() == h.getY())) {
							while (this.getX() + (8 * droga.getKierunek()) == h
									.getX()) {
								try {
									Thread.sleep(10);
								} catch (InterruptedException ex) {

								}
							}
							break;
						}
					}

					if (this.getStop() == false) {
						this.setX(this.getX() + droga.getKierunek());
						this.getWoz().setX(this.getX());
						this.getWoz().setY(this.getY());
						listaObRuch.clear();

						try {
							Thread.sleep(this.getSzybkosc());
						} catch (InterruptedException ex) {

						}
					} else {
						Thread.sleep(10);
					}
				}
			}

		}
		if (droga.getPozioma() == false) {
			this.setX(droga.getX());
			if (droga.getKierunek() == 1) {
				while (this.getY() != yCel - 14) {
					listaObRuch.addAll(listaHandlarzy);
					listaObRuch.addAll(listaLegionow);
					listaObRuch.addAll(listaPlemion);
					Iterator<ObiektRuchomy> it = listaObRuch.iterator();
					while (it.hasNext()) {
						ObiektRuchomy h = it.next();
						if ((this.getY() + (15 * droga.getKierunek()) == h
								.getY()) && (this.getX() == h.getX())) {
							while (this.getY() + (15 * droga.getKierunek()) == h
									.getY()) {
								try {
									Thread.sleep(10);
								} catch (InterruptedException ex) {

								}
							}

							break;
						}
					}

					if (this.getStop() == false) {
						this.setY(this.getY() + droga.getKierunek());
						this.getWoz().setX(this.getX());
						this.getWoz().setY(this.getY());
						listaObRuch.clear();

						try {
							Thread.sleep(this.getSzybkosc());
						} catch (InterruptedException ex) {

						}
					} else {
						Thread.sleep(10);
					}
				}
			} else {
				while (this.getY() != yCel + 14) {
					listaObRuch.addAll(listaHandlarzy);
					listaObRuch.addAll(listaLegionow);
					listaObRuch.addAll(listaPlemion);
					Iterator<ObiektRuchomy> it = listaObRuch.iterator();
					while (it.hasNext()) {
						ObiektRuchomy h = it.next();
						if ((this.getY() + (15 * droga.getKierunek()) == h
								.getY()) && (this.getX() == h.getX())) {
							while (this.getY() + (15 * droga.getKierunek()) == h
									.getY()) {
								try {
									Thread.sleep(10);
								} catch (InterruptedException ex) {

								}
							}
							break;
						}
					}

					if (this.getStop() == false) {
						this.setY(this.getY() + droga.getKierunek());
						this.getWoz().setX(this.getX());
						this.getWoz().setY(this.getY());
						listaObRuch.clear();

						try {
							Thread.sleep(this.getSzybkosc());
						} catch (InterruptedException ex) {

						}
					} else {
						Thread.sleep(10);
					}
				}
			}
		}

		for (Skrzyzowanie k : listaSkrzyzowan) {
			if ((this.getX() <= k.getX() + 16)
					&& (this.getX() >= k.getX() - 16)
					&& (this.getY() <= k.getY() + 19)
					&& (this.getY() >= k.getY() - 19)) {
				k.getWolne();
				if (droga.getPozioma()) {
					while (this.getX() != k.getX()) {
						if (this.getStop() == false) {
							this.setX(this.getX() + droga.getKierunek());
							this.getWoz().setX(this.getX());
							this.getWoz().setY(this.getY());
							try {
								Thread.sleep(this.getSzybkosc());
							} catch (InterruptedException ex) {

							}
						} else {
							Thread.sleep(10);
						}
					}
				} else {
					while (this.getY() != k.getY()) {
						if (this.getStop() == false) {
							this.setY(this.getY() + droga.getKierunek());
							this.getWoz().setX(this.getX());
							this.getWoz().setY(this.getY());
							try {
								Thread.sleep(this.getSzybkosc());
							} catch (InterruptedException ex) {

							}
						} else {
							Thread.sleep(10);
						}
					}
				}

			}
		}
	}

	public void ruszajSie(Osada osada, ArrayList<Osada> listaOsad,
			ArrayList<Skrzyzowanie> listaSkrzyzowan)
			throws InterruptedException {

		Droga droga;
		Droga poprzednia = null;
		boolean m;
		int n = 0;
		for (Osada i : listaOsad) {

			if ((osada.getX() == i.getX()) && (osada.getY() == i.getY())) {

				break;
			}
			n++;
		}

		for (Osada i : listaOsad) {

			if ((i.getX() <= this.getX() + 60)
					&& (i.getX() >= this.getX() - 60)
					&& (i.getY() <= this.getY() + 65)
					&& (i.getY() >= this.getY() - 65)) {

				if (i.getDroga().getPozioma()) {
					this.setY(i.getDroga().getY());
					this.getWoz().setX(this.getX());
					this.getWoz().setY(this.getY());
				} else {
					this.setX(i.getDroga().getX());
					this.getWoz().setX(this.getX());
					this.getWoz().setY(this.getY());
				}

				this.idzPoDrodze(i.getDroga(), listaSkrzyzowan);
				poprzednia = i.getDroga();
			}
		}
		while ((this.getX() < osada.getX() - 20)
				|| (this.getX() > osada.getX() + 20)
				|| (this.getY() < osada.getY() - 20)
				|| (this.getY() > osada.getY() + 20)) {
			m = false;

			for (Skrzyzowanie k : listaSkrzyzowan) {

				if ((this.getX() <= k.getX() + 10)
						&& (this.getX() >= k.getX() - 10)
						&& (this.getY() <= k.getY() + 10)
						&& (this.getY() >= k.getY() - 10)) {
					m = true;
					if (this.zmien == true) {

						n = nzmienione;
						this.zmien = false;
					}
					droga = k.getWskazowka(n);
					if (droga.equals(poprzednia)) {

						if ((k.getDol() != null) && (k.getDol() != poprzednia)) {
							droga = k.getDol();
						}
						if ((k.getGora() != null)
								&& (k.getGora() != poprzednia)) {
							droga = k.getGora();
						}
						if ((k.getLewo() != null)
								&& (k.getLewo() != poprzednia)) {
							droga = k.getLewo();
						}
						if ((k.getPrawo() != null)
								&& (k.getPrawo() != poprzednia)) {
							droga = k.getPrawo();
						}
					}
					poprzednia = droga;
					if (droga.equals(k.getGora())) {
						listaObRuch.addAll(listaHandlarzy);
						listaObRuch.addAll(listaLegionow);
						listaObRuch.addAll(listaPlemion);
						Iterator<ObiektRuchomy> it = listaObRuch.iterator();
						while (it.hasNext()) {
							ObiektRuchomy h = it.next();
							while ((this.getY() - 25 <= h.getY())
									&& (this.getY() > h.getY())
									&& (droga.getX() == h.getX())) {
								try {
									Thread.sleep(10);
								} catch (InterruptedException ex) {
								}
							}
						}

						listaObRuch.clear();
						for (int i = 10; i > 0; i--) {

							this.setY(this.getY() - 1);
							this.getWoz().setX(this.getX());
							this.getWoz().setY(this.getY());
							try {
								Thread.sleep(this.getSzybkosc());
							} catch (InterruptedException ex) {

							}

						}
					}

					if (droga.equals(k.getLewo())) {
						listaObRuch.addAll(listaHandlarzy);
						listaObRuch.addAll(listaLegionow);
						listaObRuch.addAll(listaPlemion);
						Iterator<ObiektRuchomy> it = listaObRuch.iterator();
						while (it.hasNext()) {
							ObiektRuchomy h = it.next();
							while ((this.getX() - 18 <= h.getX())
									&& (this.getX() > h.getX())
									&& (droga.getY() == h.getY())) {
								try {
									Thread.sleep(10);
								} catch (InterruptedException ex) {
								}
							}
						}

						listaObRuch.clear();
						for (int i = 10; i > 0; i--) {
							this.setX(this.getX() - 1);
							this.getWoz().setX(this.getX());
							this.getWoz().setY(this.getY());
							try {
								Thread.sleep(this.getSzybkosc());
							} catch (InterruptedException ex) {

							}
						}
					}
					if (droga.equals(k.getPrawo())) {
						listaObRuch.addAll(listaHandlarzy);
						listaObRuch.addAll(listaLegionow);
						listaObRuch.addAll(listaPlemion);
						Iterator<ObiektRuchomy> it = listaObRuch.iterator();
						while (it.hasNext()) {
							ObiektRuchomy h = it.next();
							while ((this.getX() + 18 >= h.getX())
									&& (this.getX() < h.getX())
									&& (droga.getY() == h.getY())) {
								try {
									Thread.sleep(10);
								} catch (InterruptedException ex) {
								}
							}
						}

						listaObRuch.clear();
						for (int i = 10; i > 0; i--) {
							this.setX(this.getX() + 1);
							this.getWoz().setX(this.getX());
							this.getWoz().setY(this.getY());

							try {
								Thread.sleep(this.getSzybkosc());
							} catch (InterruptedException ex) {

							}
						}
					}
					if (droga.equals(k.getDol())) {
						listaObRuch.addAll(listaHandlarzy);
						listaObRuch.addAll(listaLegionow);
						listaObRuch.addAll(listaPlemion);
						Iterator<ObiektRuchomy> it = listaObRuch.iterator();
						while (it.hasNext()) {
							ObiektRuchomy h = it.next();
							while ((this.getY() + 25 >= h.getY())
									&& (this.getY() < h.getY())
									&& (droga.getX() == h.getX())) {
								try {
									Thread.sleep(10);
								} catch (InterruptedException ex) {
								}
							}
						}

						listaObRuch.clear();
						for (int i = 10; i > 0; i--) {
							this.setY(this.getY() + 1);
							this.getWoz().setX(this.getX());
							this.getWoz().setY(this.getY());
							try {
								Thread.sleep(this.getSzybkosc());
							} catch (InterruptedException ex) {

							}
						}
					}
					k.setWolne();
					this.idzPoDrodze(droga, listaSkrzyzowan);
				}
			}

		}
		if (poprzednia.getPozioma()) {
			this.setY(this.getY() - 30);
			this.getWoz().setX(this.getX());
			this.getWoz().setY(this.getY());
		} else {

			this.setX(this.getX() - 1);
			this.getWoz().setX(this.getX());
			this.getWoz().setY(this.getY());

		}

	}

	public ArrayList<Droga> getListaDrog() {
		return listaDrog;
	}

	public void setListaDrog(ArrayList<Droga> listaDrog) {
		this.listaDrog = listaDrog;
	}
}

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

public class ObiektRuchomy extends Byt {
	private int szybkosc;
	BufferedImage img;
	File ImageFile;
	private ArrayList<Osada> listaOsad;
	private ArrayList<Skrzyzowanie> listaSkrzyzowan;
	private ArrayList<Droga> listaDrog;
	private ArrayList<ObiektRuchomy> listaObRuch;
	private boolean stop;
	private Mapa mapa;
	private Osada osadaCel;

	public ObiektRuchomy(int x, int y, int szybkosc, Mapa myTimer) {
		super(x, y);
		this.stop = false;
		this.mapa = myTimer;
		this.szybkosc = szybkosc;
		this.listaOsad = myTimer.getListaOsad();
		this.listaSkrzyzowan = myTimer.getListaSkrzyzowan();
		this.listaDrog = myTimer.getListaDrog();
		this.listaObRuch = new ArrayList<>();

	}

	public Mapa getMapa() {
		return mapa;
	}

	public void setStop(boolean stop) {
		this.stop = stop;
	}

	public boolean getStop() {
		return stop;
	}

	public void zatrzymajSie() {
		this.stop = true;
	}

	public void idz() {
		this.stop = false;
	}

	public ArrayList<ObiektRuchomy> getListaObRuch() {
		return listaObRuch;
	}

	public int getSzybkosc() {
		return szybkosc;
	}

	public void setSzybkosc(int szybkosc) {
		this.szybkosc = szybkosc;
	}

	public Osada getOsadaCel() {
		return osadaCel;
	}

	public void setOsadaCel(Osada osadaCel) {
		this.osadaCel = osadaCel;
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
					listaObRuch.addAll(mapa.getListaHandlarzy());
					listaObRuch.addAll(mapa.getListaLegionow());
					listaObRuch.addAll(mapa.getListaPlemionBarbarzyncow());
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

					if (this.stop == false) {
						this.setX(this.getX() + droga.getKierunek());

						listaObRuch.clear();

						try {
							Thread.sleep(this.szybkosc);
						} catch (InterruptedException ex) {

						}
					} else {
						Thread.sleep(10);
					}
				}
			} else {
				while (this.getX() != xCel + 14) {
					listaObRuch.addAll(mapa.getListaHandlarzy());
					listaObRuch.addAll(mapa.getListaLegionow());
					listaObRuch.addAll(mapa.getListaPlemionBarbarzyncow());
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

					if (this.stop == false) {
						this.setX(this.getX() + droga.getKierunek());

						listaObRuch.clear();

						try {
							Thread.sleep(this.szybkosc);
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
					listaObRuch.addAll(mapa.getListaHandlarzy());
					listaObRuch.addAll(mapa.getListaLegionow());
					listaObRuch.addAll(mapa.getListaPlemionBarbarzyncow());
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

					if (this.stop == false) {
						this.setY(this.getY() + droga.getKierunek());

						listaObRuch.clear();

						try {
							Thread.sleep(this.szybkosc);
						} catch (InterruptedException ex) {

						}
					} else {
						Thread.sleep(10);
					}
				}
			} else {
				while (this.getY() != yCel + 14) {
					listaObRuch.addAll(mapa.getListaHandlarzy());
					listaObRuch.addAll(mapa.getListaLegionow());
					listaObRuch.addAll(mapa.getListaPlemionBarbarzyncow());
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

					if (this.stop == false) {
						this.setY(this.getY() + droga.getKierunek());
						listaObRuch.clear();

						try {
							Thread.sleep(this.szybkosc);
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
						if (this.stop == false) {
							this.setX(this.getX() + droga.getKierunek());
							try {
								Thread.sleep(this.szybkosc);
							} catch (InterruptedException ex) {

							}
						} else {
							Thread.sleep(10);
						}
					}
				} else {
					while (this.getY() != k.getY()) {
						if (this.stop == false) {
							this.setY(this.getY() + droga.getKierunek());
							try {
								Thread.sleep(this.szybkosc);
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
				} else {
					this.setX(i.getDroga().getX());
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
						listaObRuch.addAll(mapa.getListaHandlarzy());
						listaObRuch.addAll(mapa.getListaLegionow());
						listaObRuch.addAll(mapa.getListaPlemionBarbarzyncow());
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
							try {
								Thread.sleep(this.szybkosc);
							} catch (InterruptedException ex) {

							}

						}
					}

					if (droga.equals(k.getLewo())) {
						listaObRuch.addAll(mapa.getListaHandlarzy());
						listaObRuch.addAll(mapa.getListaLegionow());
						listaObRuch.addAll(mapa.getListaPlemionBarbarzyncow());
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

							try {
								Thread.sleep(this.szybkosc);
							} catch (InterruptedException ex) {

							}
						}
					}
					if (droga.equals(k.getPrawo())) {
						listaObRuch.addAll(mapa.getListaHandlarzy());
						listaObRuch.addAll(mapa.getListaLegionow());
						listaObRuch.addAll(mapa.getListaPlemionBarbarzyncow());
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

							try {
								Thread.sleep(this.szybkosc);
							} catch (InterruptedException ex) {

							}
						}
					}
					if (droga.equals(k.getDol())) {
						listaObRuch.addAll(mapa.getListaHandlarzy());
						listaObRuch.addAll(mapa.getListaLegionow());
						listaObRuch.addAll(mapa.getListaPlemionBarbarzyncow());
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

							try {
								Thread.sleep(this.szybkosc);
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
		} else {

			this.setX(this.getX() + 30);

		}

	}
}

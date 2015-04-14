import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class PlemieBarbarzyncow extends ObiektRuchomy {
	private String nazwa;
	private int liczbaOsob;
	private String bron;
	private Osada osadaCel;
	private volatile ArrayList<Handlarz> listaHandlarzy;
	private ArrayList<LegionZolnierzy> legiony;
	private ArrayList<LegionZolnierzy> listaLegionow;
	private ArrayList<PlemieBarbarzyncow> listaPlemion;
	private ArrayList<Osada> listaOsad;
	private ArrayList<Skrzyzowanie> listaSkrzyzowan;
	private ArrayList<Droga> listaDrog;
	private Random losowanie;
	private ArrayList<Handlarz> listaHandlarzyDoZabicia;
	private ArrayList<Handlarz> listaHandlarzy2;

	public PlemieBarbarzyncow(int x, int y, String nazwa, int liczbaOsob,
			String bron, int szybkosc, Mapa myTimer) throws IOException,
			InterruptedException {
		super(x, y, szybkosc, myTimer);
		this.bron = bron;
		this.liczbaOsob = liczbaOsob;
		this.nazwa = nazwa;
		this.legiony = new ArrayList<>();
		this.listaHandlarzy = myTimer.getListaHandlarzy();
		this.listaLegionow = myTimer.getListaLegionow();
		this.listaPlemion = myTimer.getListaPlemionBarbarzyncow();
		this.listaOsad = myTimer.getListaOsad();
		this.listaDrog = myTimer.getListaDrog();
		this.listaSkrzyzowan = myTimer.getListaSkrzyzowan();
		this.listaHandlarzyDoZabicia = new ArrayList<>();
		this.listaHandlarzy2 = new ArrayList<>();
		losowanie = new Random();
		ImageFile = new File("grafika/barbarian.png");

		img = ImageIO.read(ImageFile);
		listaPlemion.add(this);
		this.start();

	}

	public void run() {
		try {
			osadaCel = wybierzOsadeDoAtaku();
			idzDoSkrzyz();
			osadaCel = wybierzOsadeDoAtaku();
			while (true) {
				ruszajSie(osadaCel, listaOsad, listaSkrzyzowan);
				oproznijSkarbiecIMagazyn(osadaCel);

				osadaCel = losujOsade(osadaCel);
			}

		} catch (InterruptedException ex) {
			Logger.getLogger(PlemieBarbarzyncow.class.getName()).log(
					Level.SEVERE, null, ex);
		}
	}

	public String getNazwa() {
		return nazwa;
	}

	public int getLiczbaOsob() {
		return liczbaOsob;
	}

	public String getBron() {
		return bron;
	}

	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}

	public void setLiczbaOsob(int liczbaOsob) {
		this.liczbaOsob = liczbaOsob;
	}

	public void setBron(String bron) {
		this.bron = bron;
	}

	public void idzDoSkrzyz() throws InterruptedException {

		float odl, m;
		Skrzyzowanie skrzyzowanie = null;
		m = ((listaSkrzyzowan.get(0).getX() - this.getX()) * (listaSkrzyzowan
				.get(0).getX() - this.getX()))
				+ ((listaSkrzyzowan.get(0).getY() - this.getY()) * (listaSkrzyzowan
						.get(0).getY() - this.getY()));
		for (Skrzyzowanie i : listaSkrzyzowan) {
			odl = ((i.getX() - this.getX()) * (i.getX() - this.getX()))
					+ ((i.getY() - this.getY()) * (i.getY() - this.getY()));

			if (odl <= m) {
				m = odl;
				skrzyzowanie = i;
			}
		}
		if ((skrzyzowanie.getX() > this.getX()) && (this.getY() <= 40)) {
			while (this.getX() != skrzyzowanie.getX()) {
				this.setX(this.getX() + 1);
				Thread.sleep(this.getSzybkosc());
			}
			while (this.getY() != skrzyzowanie.getY()) {
				this.setY(this.getY() + 1);
				Thread.sleep(this.getSzybkosc());
			}
			return;
		}
		if ((skrzyzowanie.getX() < this.getX()) && (this.getY() <= 40)) {
			while (this.getX() != skrzyzowanie.getX()) {
				this.setX(this.getX() - 1);
				Thread.sleep(this.getSzybkosc());
			}
			while (this.getY() != skrzyzowanie.getY()) {
				this.setY(this.getY() + 1);
				Thread.sleep(this.getSzybkosc());
			}
			return;
		}
		if ((skrzyzowanie.getX() < this.getX())
				&& (skrzyzowanie.getY() < this.getY())) {
			while (this.getY() != skrzyzowanie.getY()) {
				this.setY(this.getY() - 1);
				Thread.sleep(this.getSzybkosc());
			}
			while (this.getX() != skrzyzowanie.getX()) {
				this.setX(this.getX() - 1);
				Thread.sleep(this.getSzybkosc());
			}
			return;
		}
		if ((skrzyzowanie.getX() > this.getX())
				&& (skrzyzowanie.getY() < this.getY())) {
			while (this.getY() != skrzyzowanie.getY()) {
				this.setY(this.getY() - 1);
				Thread.sleep(this.getSzybkosc());
			}
			while (this.getX() != skrzyzowanie.getX()) {
				this.setX(this.getX() + 1);
				Thread.sleep(this.getSzybkosc());
			}

		}
		if ((skrzyzowanie.getX() < this.getX())
				&& (skrzyzowanie.getY() > this.getY())) {
			while (this.getY() != skrzyzowanie.getY()) {
				this.setY(this.getY() + 1);
				Thread.sleep(this.getSzybkosc());
			}
			while (this.getX() != skrzyzowanie.getX()) {
				this.setX(this.getX() - 1);
				Thread.sleep(this.getSzybkosc());
			}

		}
		if ((skrzyzowanie.getX() > this.getX())
				&& (skrzyzowanie.getY() > this.getY())) {
			while (this.getY() != skrzyzowanie.getY()) {
				this.setY(this.getY() + 1);
				Thread.sleep(this.getSzybkosc());
			}
			while (this.getX() != skrzyzowanie.getX()) {
				this.setX(this.getX() + 1);
				Thread.sleep(this.getSzybkosc());
			}

		}

	}

	public Osada losujOsade(Osada poprzednia) {

		Osada osada = poprzednia;

		while (osada.equals(poprzednia)) {

			osada = listaOsad.get(losowanie.nextInt(10));
		}
		return osada;
	}

	public void zabijHandlarza() throws InterruptedException {

		listaHandlarzy2.addAll(listaHandlarzy);
		if (!listaHandlarzy.isEmpty()) {
			for (Handlarz h : listaHandlarzy2) {
				if ((this.getX() >= h.getX() - 16)
						&& (this.getX() <= h.getX() + 16)
						&& (this.getY() <= h.getY() + 16)
						&& (this.getY() >= h.getY() - 16)) {

					for (Skrzyzowanie sk : this.getMapa().getListaSkrzyzowan()) {
						if ((h.getX() <= sk.getX() + 16)
								&& (h.getX() >= sk.getX() - 16)
								&& (h.getY() <= sk.getY() + 19)
								&& (h.getY() >= sk.getY() - 19)) {

							sk.setWolne();
							break;
						}
					}

					listaHandlarzy.remove(h);
				}

			}
		}
	}

	public void zabijLegionISiebie() throws InterruptedException {
		legiony.addAll(listaLegionow);
		for (LegionZolnierzy z : legiony) {
			if ((this.getX() <= z.getX() + 25)
					&& (this.getY() <= z.getY() + 25)
					&& (this.getX() >= z.getX() - 25)
					&& (this.getY() >= z.getY() - 25)) {
				for (Osada os : listaOsad) {
					if ((os.getX() <= z.getX() + 40)
							&& (os.getX() >= z.getX() - 40)
							&& (os.getY() <= z.getY() + 40)
							&& (os.getY() >= z.getY() - 40)) {
						if (os.getLiczbaMieszkancow() <= 20) {
							os.setLiczbaMieszkancow(0);
						} else {
							os.setLiczbaMieszkancow(os.getLiczbaMieszkancow()
									- losowanie.nextInt(os
											.getLiczbaMieszkancow() / 6));
						}

						break;
					}
				}

				this.getMapa().setLbPokonanychPlemion();

				for (Skrzyzowanie sk : this.getMapa().getListaSkrzyzowan()) {
					if (((z.getX() <= sk.getX() + 16)
							&& (z.getX() >= sk.getX() - 16)
							&& (z.getY() <= sk.getY() + 19) && (z.getY() >= sk
							.getY() - 19))
							|| ((this.getX() <= sk.getX() + 16)
									&& (this.getX() >= sk.getX() - 16)
									&& (this.getY() <= sk.getY() + 19) && (this
									.getY() >= sk.getY() - 19))) {

						sk.setWolne();
						break;
					}

				}

				listaLegionow.remove(z);
				listaPlemion.remove(this);
				break;
			}
		}
		legiony.clear();

	}

	public void oproznijSkarbiecIMagazyn(Osada osada) {
		while (osada.getLiczbaMieszkancow() > 0) {
			if (!osada.getAktStanMagazynu().isEmpty()) {
				osada.getAktStanMagazynu().clear();
			}
			osada.setAktPojMagazynu(0);
			osada.setStanSkarbca(0);
			if (osada.getLiczbaMieszkancow() <= 20) {
				osada.setLiczbaMieszkancow(0);
			} else {
				osada.setLiczbaMieszkancow(osada.getLiczbaMieszkancow()
						- (losowanie.nextInt(osada.getLiczbaMieszkancow() / 6)));
			}
		}
	}

	public Osada wybierzOsadeDoAtaku() {
		float odl, m;
		Osada osada = null;
		m = ((listaOsad.get(0).getX() - this.getX()) * (listaOsad.get(0).getX() - this
				.getX()))
				+ ((listaOsad.get(0).getY() - this.getY()) * (listaOsad.get(0)
						.getY() - this.getY()));
		for (Osada i : listaOsad) {
			odl = ((i.getX() - this.getX()) * (i.getX() - this.getX()))
					+ ((i.getY() - this.getY()) * (i.getY() - this.getY()));

			if (odl <= m) {
				m = odl;
				osada = i;
			}
		}
		return osada;
	}

	public Osada getOsadaCel() {
		return osadaCel;
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
					this.getListaObRuch().addAll(listaHandlarzy);
					this.getListaObRuch().addAll(listaLegionow);
					this.getListaObRuch().addAll(listaPlemion);
					Iterator<ObiektRuchomy> it = this.getListaObRuch()
							.iterator();
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
					this.zabijHandlarza();
					this.zabijLegionISiebie();
					if (this.getStop() == false) {

						this.setX(this.getX() + droga.getKierunek());
						this.getListaObRuch().clear();

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
					this.getListaObRuch().addAll(listaHandlarzy);
					this.getListaObRuch().addAll(listaLegionow);
					this.getListaObRuch().addAll(listaPlemion);
					Iterator<ObiektRuchomy> it = this.getListaObRuch()
							.iterator();
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
					this.zabijHandlarza();
					this.zabijLegionISiebie();
					if (this.getStop() == false) {
						this.setX(this.getX() + droga.getKierunek());

						this.getListaObRuch().clear();

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
					this.getListaObRuch().addAll(listaHandlarzy);
					this.getListaObRuch().addAll(listaLegionow);
					this.getListaObRuch().addAll(listaPlemion);
					Iterator<ObiektRuchomy> it = this.getListaObRuch()
							.iterator();
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
					this.zabijHandlarza();
					this.zabijLegionISiebie();
					if (this.getStop() == false) {

						this.setY(this.getY() + droga.getKierunek());

						this.getListaObRuch().clear();

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
					this.getListaObRuch().addAll(listaHandlarzy);
					this.getListaObRuch().addAll(listaLegionow);
					this.getListaObRuch().addAll(listaPlemion);
					Iterator<ObiektRuchomy> it = this.getListaObRuch()
							.iterator();
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
					this.zabijHandlarza();
					this.zabijLegionISiebie();
					if (this.getStop() == false) {
						this.setY(this.getY() + droga.getKierunek());
						this.getListaObRuch().clear();

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
							this.zabijHandlarza();
							this.zabijLegionISiebie();
							this.setX(this.getX() + droga.getKierunek());
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
							this.zabijHandlarza();
							this.zabijLegionISiebie();
							this.setY(this.getY() + droga.getKierunek());
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
					this.zabijHandlarza();
					this.zabijLegionISiebie();
				} else {
					this.setX(i.getDroga().getX());
					this.zabijHandlarza();
					this.zabijLegionISiebie();
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
						this.getListaObRuch().addAll(listaHandlarzy);
						this.getListaObRuch().addAll(listaLegionow);
						this.getListaObRuch().addAll(listaPlemion);
						Iterator<ObiektRuchomy> it = this.getListaObRuch()
								.iterator();
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
						this.getListaObRuch().clear();
						for (int i = 10; i > 0; i--) {
							this.zabijHandlarza();
							this.zabijLegionISiebie();
							this.setY(this.getY() - 1);
							try {
								Thread.sleep(this.getSzybkosc());
							} catch (InterruptedException ex) {

							}

						}
					}

					if (droga.equals(k.getLewo())) {
						this.getListaObRuch().addAll(listaHandlarzy);
						this.getListaObRuch().addAll(listaLegionow);
						this.getListaObRuch().addAll(listaPlemion);
						Iterator<ObiektRuchomy> it = this.getListaObRuch()
								.iterator();
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
						this.getListaObRuch().clear();
						for (int i = 10; i > 0; i--) {
							this.zabijHandlarza();
							this.zabijLegionISiebie();
							this.setX(this.getX() - 1);

							try {
								Thread.sleep(this.getSzybkosc());
							} catch (InterruptedException ex) {

							}
						}
					}
					if (droga.equals(k.getPrawo())) {
						this.getListaObRuch().addAll(listaHandlarzy);
						this.getListaObRuch().addAll(listaLegionow);
						this.getListaObRuch().addAll(listaPlemion);
						Iterator<ObiektRuchomy> it = this.getListaObRuch()
								.iterator();
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
						this.getListaObRuch().clear();
						for (int i = 10; i > 0; i--) {
							this.zabijHandlarza();
							this.zabijLegionISiebie();
							this.setX(this.getX() + 1);

							try {
								Thread.sleep(this.getSzybkosc());
							} catch (InterruptedException ex) {

							}
						}
					}
					if (droga.equals(k.getDol())) {
						this.getListaObRuch().addAll(listaHandlarzy);
						this.getListaObRuch().addAll(listaLegionow);
						this.getListaObRuch().addAll(listaPlemion);
						Iterator<ObiektRuchomy> it = this.getListaObRuch()
								.iterator();
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
						this.getListaObRuch().clear();
						for (int i = 10; i > 0; i--) {
							this.zabijHandlarza();
							this.zabijLegionISiebie();
							this.setY(this.getY() + 1);

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
		} else {

			this.setX(this.getX() + 30);

		}

	}

	public void rysujSie(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g.drawImage(img, this.getX() - 5, this.getY() - 8, null);
	}
}

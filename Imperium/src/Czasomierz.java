import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Iterator;
import java.util.Random;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;

public class Czasomierz extends TimerTask {
	private Mapa mapa;
	private Random losBarbarz;
	private DoLosowania los;
	private PlemieBarbarzyncow plemie;
	private Stolica stolica;
	private Timer t;
	private int sekunda, barb, popsucie;

	public Czasomierz(Mapa mapa) {
		this.mapa = mapa;
		losBarbarz = new Random();
		los = new DoLosowania();
		stolica = mapa.getStolica();
		sekunda = 0;
		barb = 10;
		popsucie = 20;
		t = new Timer(1000, czas);
		t.start();
		t.addActionListener(najazdBarbarzyncow);
		t.addActionListener(zepsujWoz);

	}

	private final ActionListener czas = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {

			sekunda++;
		}
	};
	private final ActionListener zepsujWoz = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (getCzas() > popsucie) {
				if (!mapa.getListaHandlarzy().isEmpty()) {
					int d = (int) (Math.random() * mapa.getListaHandlarzy()
							.size());
					mapa.getListaHandlarzy().get(d).getWoz().zepsujSie();

				}
				popsucie += 15 + (int) (Math.random() * 25);
			}
		}
	};
	private final ActionListener najazdBarbarzyncow = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (getCzas() > barb) {

				int i = losBarbarz.nextInt(8);
				int j = losBarbarz.nextInt(13);
				try {

					plemie = new PlemieBarbarzyncow(los.getX(i), los.getY(i),
							los.getNazwaPlemienia(los.getId()),
							losBarbarz.nextInt(200) + 1, los.getBron(j),
							los.getSzybkosc(i), mapa);
				} catch (IOException ex) {
					Logger.getLogger(Czasomierz.class.getName()).log(
							Level.SEVERE, null, ex);
				} catch (InterruptedException ex) {
					Logger.getLogger(Czasomierz.class.getName()).log(
							Level.SEVERE, null, ex);
				}
				if (los.getId() == 18) {
					los.setId(0);
				} else {
					los.setId(los.getId() + 1);
				}
				try {
					stolica.wyslijLegionZolnierzy(plemie.wybierzOsadeDoAtaku());
				} catch (IOException | InterruptedException ex) {
					Logger.getLogger(Mapa.class.getName()).log(Level.SEVERE,
							null, ex);
				}

				barb += 20 + (int) (Math.random() * 25);
			}
		}
	};

	public int getCzas() {
		return sekunda;
	}

	@Override
	public void run() {

		boolean koniec = true;
		boolean prod = true;
		for (Osada osada : mapa.getListaOsad()) {
			if (osada.getLiczbaMieszkancow() != 0) {
				koniec = false;
				break;
			}
		}
		if (koniec) {
			mapa.getListaPlemionBarbarzyncow().clear();
			mapa.getListaHandlarzy().clear();
			mapa.getListaLegionow().clear();
			mapa.getListaWozow().clear();

			 WynikOkno w = new WynikOkno(sekunda,
			 mapa.getLbPokonanychPlemion());
			t.stop();
			this.cancel();
		}

		for (Osada osada : mapa.getListaOsad()) {

			Iterator<Handlarz> it = mapa.getListaHandlarzy().iterator();
			while (it.hasNext()) {
				Handlarz h = it.next();
				if ((h.getX() <= osada.getX() + 20)
						&& (h.getX() >= osada.getX() - 20)
						&& (h.getY() <= osada.getY() + 20)
						&& (h.getY() >= osada.getY() - 20) && (h.getCzeka())) {

					prod = false;
					break;
				}
			}

			if (prod) {
				osada.produkuj(los);
			}

			try {
				osada.sprawdzDateWaznosci();
			} catch (InterruptedException ex) {
				Logger.getLogger(Mapa.class.getName()).log(Level.SEVERE, null,
						ex);
			}

		}

	}
}

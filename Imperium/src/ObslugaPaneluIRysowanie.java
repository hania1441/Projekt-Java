import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class ObslugaPaneluIRysowanie extends JPanel implements MouseListener,
		MouseMotionListener {
	BufferedImage img;

	private static volatile ArrayList<Handlarz> listaHandlarzy;
	private static volatile ArrayList<LegionZolnierzy> listaLegionow;
	private static volatile ArrayList<PlemieBarbarzyncow> listaPlemionBarbarzyncow;
	private static volatile ArrayList<Osada> listaOsad;
	private ArrayList<String> coPrzewozi;
	private ArrayList<String> coPrzewozi1;
	private ArrayList<String> coPrzewozi2;
	private ArrayList<Surowiec> surowce;
	private ArrayList<String> wmagazynie;
	private ArrayList<String> wmagazynie1;
	private ArrayList<String> wmagazynie2;
	private ArrayList<String> kup1;
	private ArrayList<String> kup2;
	private ArrayList<ArtykulSpozywczy> artSpoz;
	private static Mapa mapa;
	private int xKlik, yKlik;

	public int getxKlik() {
		return xKlik;
	}

	public int getyKlik() {
		return yKlik;
	}

	public void setBackground(BufferedImage img) throws IOException {
		File ImageFile = new File("grafika/tlo.jpg");

		img = ImageIO.read(ImageFile);
		this.img = img;
	}

	@SuppressWarnings("empty-statement")
	public ObslugaPaneluIRysowanie() throws IOException {
		addMouseListener(this);
		addMouseMotionListener(this);
		this.setSize(1050, 705);
		this.setBackground(img);
		mapa = new Mapa();
		coPrzewozi1 = new ArrayList<>();
		coPrzewozi2 = new ArrayList<>();
		coPrzewozi = new ArrayList<>();
		this.listaHandlarzy = new ArrayList<>();
		this.listaLegionow = new ArrayList<>();
		this.listaPlemionBarbarzyncow = new ArrayList<>();
		this.listaOsad = new ArrayList<>();

		this.artSpoz = new ArrayList<>();
		this.surowce = new ArrayList<>();
		this.wmagazynie = new ArrayList<>();
		this.wmagazynie1 = new ArrayList<>();
		this.wmagazynie2 = new ArrayList<>();

		this.kup1 = new ArrayList<>();
		this.kup2 = new ArrayList<>();

	}

	public int coKliknieto(MouseEvent e, JLabel l0, JLabel l1, JLabel l2,
			JLabel l3, JLabel l4, JLabel l5, JLabel l6, JLabel l7, JLabel l8,
			JLabel l9, JLabel l10) {

		listaHandlarzy.addAll(mapa.getListaHandlarzy());
		listaLegionow.addAll(mapa.getListaLegionow());
		listaPlemionBarbarzyncow.addAll(mapa.getListaPlemionBarbarzyncow());
		listaOsad.addAll(mapa.getListaOsad());
		if (!listaHandlarzy.isEmpty()) {
			for (Handlarz i : listaHandlarzy) {
				if ((e.getX() <= i.getX() + 20) && (e.getX() >= i.getX() - 20)
						&& (e.getY() <= i.getY() + 25)
						&& (e.getY() >= i.getY() - 25)) {

					surowce.addAll(i.getWoz().getSurowcePrzewozone());

					for (Surowiec s : surowce) {
						coPrzewozi.add(s.getNazwa() + s.getIdentyfikator());
					}
					l0.setText("HANDLARZ");
					l1.setText("Wspó³rzêdne:  x= " + i.getX() + "   y= "
							+ i.getY());
					l2.setText("Imiê: " + i.getImie());
					l3.setText("Nazwisko: " + i.getNazwisko());
					l4.setText("Wóz: Aktualna Poj. " + i.getWoz().getAktPoj());
					l5.setText("Wóz: Max Poj." + i.getWoz().getMaxPoj());
					if (coPrzewozi.isEmpty()) {
						l6.setText("Przewozi: ");
					} else {
						if (coPrzewozi.size() > 5) {

							for (int j = 0; j < 6; j++) {
								coPrzewozi1.add(coPrzewozi.get(j));
							}
							for (int j = 6; j < coPrzewozi.size(); j++) {
								coPrzewozi2.add(coPrzewozi.get(j));
							}
							l6.setText("Przewozi: " + coPrzewozi1);
							l7.setText("" + coPrzewozi2);
						} else {
							l6.setText("Przewozi: " + coPrzewozi);
							l7.setText("");
						}
					}
					l8.setText("Szybkoœæ: " + (100 - i.getWoz().getSzybkosc()));
					l9.setText("Osada docelowa: " + i.getOsadaCel().getNazwa());
					l10.setText("");
					surowce.clear();
					coPrzewozi.clear();
					coPrzewozi1.clear();
					coPrzewozi2.clear();
					listaHandlarzy.clear();
					listaLegionow.clear();
					listaPlemionBarbarzyncow.clear();
					listaOsad.clear();
					return 0;
				}
			}
		}
		if (!listaPlemionBarbarzyncow.isEmpty()) {
			for (PlemieBarbarzyncow i : listaPlemionBarbarzyncow) {
				if ((e.getX() <= i.getX() + 20) && (e.getX() >= i.getX() - 20)
						&& (e.getY() <= i.getY() + 25)
						&& (e.getY() >= i.getY() - 25)) {
					l0.setText("PLEMIÊ BARBARZYÑCÓW");
					l1.setText("Wspó³rzêdne:  x= " + i.getX() + "   y= "
							+ i.getY());
					l2.setText("Nazwa: " + i.getNazwa());
					l3.setText("Liczba osób: " + i.getLiczbaOsob());
					l4.setText("Bron: " + i.getBron());
					l5.setText("Szybkoœæ: " + (100 - i.getSzybkosc()));
					l6.setText("Osada docelowa: " + i.getOsadaCel().getNazwa());
					l7.setText("");
					l8.setText("");
					l9.setText("");
					l10.setText("");
					listaHandlarzy.clear();
					listaLegionow.clear();
					listaPlemionBarbarzyncow.clear();
					listaOsad.clear();
					return 0;
				}
			}
		}

		if (!listaLegionow.isEmpty()) {
			for (LegionZolnierzy i : listaLegionow) {
				if ((e.getX() <= i.getX() + 20) && (e.getX() >= i.getX() - 20)
						&& (e.getY() <= i.getY() + 25)
						&& (e.getY() >= i.getY() - 25)) {
					l0.setText("LEGION ¯O£NIERZY");
					l1.setText("Wspó³rzêdne:  x= " + i.getX() + "   y= "
							+ i.getY());
					l2.setText("Identyfikator: " + i.getIdentyfikator());
					l3.setText("Bron: " + i.getBron());
					l4.setText("Szybkoœæ: " + (100 - i.getSzybkosc()));
					if (i.getOsadaCel() == null) {
						l5.setText("");
					} else {
						l5.setText("Osada docelowa: "
								+ i.getOsadaCel().getNazwa());
					}
					l6.setText("");
					l7.setText("");
					l8.setText("");
					l9.setText("");
					l10.setText("");
					listaHandlarzy.clear();
					listaLegionow.clear();
					listaPlemionBarbarzyncow.clear();
					listaOsad.clear();
					return 0;
				}
			}
		}
		if (!listaOsad.isEmpty()) {
			for (Osada i : listaOsad) {

				if ((e.getX() <= i.getX() + 80) && (e.getX() >= i.getX() - 30)
						&& (e.getY() <= i.getY() + 35)
						&& (e.getY() >= i.getY() - 35)) {

					surowce.addAll(i.getAktStanMagazynu());
					artSpoz.addAll(i.getAktStanArtykulSpozywczego());

					for (Surowiec s : surowce) {
						wmagazynie.add(s.getNazwa() + s.getIdentyfikator());
					}
					for (ArtykulSpozywczy a : artSpoz) {
						wmagazynie.add(a.getNazwa() + a.getIdentyfikator());
					}
					l0.setText("OSADA");
					l1.setText("Wspó³rzêdne:  x= " + i.getX() + "   y= "
							+ i.getY());
					l2.setText("Nazwa: " + i.getNazwa());
					l3.setText("Stan skarbca: " + i.getStanSkarbca());
					l4.setText("Liczba mieszkañców: "
							+ i.getLiczbaMieszkancow());
					l5.setText("Max.Poj. Magazynu: " + i.getMaxPojMagazynu()
							+ "       Akt.Poj.Magzynu: "
							+ i.getAktPojMagazynu());
					if (wmagazynie.isEmpty()) {
						l6.setText("Akt.Stan Magazynu: Pusty");
					} else {
						if (wmagazynie.size() > 6) {
							for (int j = 0; j < 7; j++) {
								wmagazynie1.add(wmagazynie.get(j));
							}
							for (int j = 7; j < wmagazynie.size(); j++) {
								wmagazynie2.add(wmagazynie.get(j));
							}
							l6.setText("Akt.Stan Magazynu: " + wmagazynie1);
							l7.setText("" + wmagazynie2);
						} else {
							l6.setText("Akt.Stan Magazynu: " + wmagazynie1);
							l7.setText("");
						}
					}

					l8.setText("Surowce produkowane: "
							+ i.getSurowceProdukowane());
					if (i.getSurowceKupowane().size() > 7) {
						for (int j = 0; j < 7; j++) {
							kup1.add(i.getSurowceKupowane().get(j));
						}
						for (int j = 7; j < i.getSurowceKupowane().size(); j++) {
							kup2.add(i.getSurowceKupowane().get(j));
						}
						l9.setText("Surowce kupowane: " + kup1);
						l10.setText("" + kup2);
					} else {
						l9.setText("Surowce kupowane: "
								+ i.getSurowceKupowane());
						l10.setText("");
					}
					surowce.clear();
					wmagazynie.clear();
					wmagazynie1.clear();
					wmagazynie2.clear();
					kup1.clear();
					kup2.clear();
					listaHandlarzy.clear();
					listaLegionow.clear();
					listaPlemionBarbarzyncow.clear();
					listaOsad.clear();
					return 0;
				}
			}
		}

		return 1;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.drawImage(img, 0, 0, 1366, 705, this);
		listaHandlarzy.addAll(mapa.getListaHandlarzy());
		listaLegionow.addAll(mapa.getListaLegionow());
		listaPlemionBarbarzyncow.addAll(mapa.getListaPlemionBarbarzyncow());
		if (!mapa.getListaHandlarzy().isEmpty()) {
			for (Handlarz i : listaHandlarzy) {
				i.rysujSie(g);
			}
		}
		if (!mapa.getListaPlemionBarbarzyncow().isEmpty()) {
			for (PlemieBarbarzyncow i : listaPlemionBarbarzyncow) {
				i.rysujSie(g);
			}
		}

		if (!mapa.getListaLegionow().isEmpty()) {
			for (LegionZolnierzy i : listaLegionow) {
				i.rysujSie(g);
			}
		}
		listaHandlarzy.clear();
		listaLegionow.clear();
		listaPlemionBarbarzyncow.clear();
	}

	public Mapa getMapa() {
		return mapa;
	}

	public void drawCircle(int x, int y) {
		Graphics g = this.getGraphics();
		// /g.drawOval(x, y, x, y);
		g.setColor(Color.BLACK);
		g.fillOval(x, y, 20, 20);
	}

	public void mouseClicked(MouseEvent e) {
		JLabel label0, label1, label2, label3, label4, label5, label6, label7, label8, label9, label10;

		xKlik = e.getX();
		yKlik = e.getY();
		listaHandlarzy.addAll(mapa.getListaHandlarzy());
		if (!listaHandlarzy.isEmpty()) {
			for (Handlarz i : listaHandlarzy) {
				if ((e.getX() <= i.getX() + 10) && (e.getX() >= i.getX() - 10)
						&& (e.getY() <= i.getY() + 15)
						&& (e.getY() >= i.getY() - 15)) {

					if (i.getStop() == false) {
						i.setStop(true);
					} else {
						if (SwingUtilities.isLeftMouseButton(e)) {
							i.setStop(false);
						}
					}

				}
			}
		}
		listaHandlarzy.clear();
		if (SwingUtilities.isRightMouseButton(e)) {
			label0 = new JLabel();
			label1 = new JLabel();
			label2 = new JLabel();
			label3 = new JLabel();
			label4 = new JLabel();
			label5 = new JLabel();
			label6 = new JLabel();
			label7 = new JLabel();
			label8 = new JLabel();
			label9 = new JLabel();
			label10 = new JLabel();
			label0.setPreferredSize(new Dimension(600, 12));
			label1.setPreferredSize(new Dimension(600, 12));
			label2.setPreferredSize(new Dimension(600, 12));
			label3.setPreferredSize(new Dimension(600, 12));
			label4.setPreferredSize(new Dimension(600, 12));
			label5.setPreferredSize(new Dimension(600, 12));
			label6.setPreferredSize(new Dimension(600, 12));
			label7.setPreferredSize(new Dimension(600, 12));
			label8.setPreferredSize(new Dimension(600, 12));
			label9.setPreferredSize(new Dimension(600, 12));
			label10.setPreferredSize(new Dimension(600, 12));
			if (coKliknieto(e, label0, label1, label2, label3, label4, label5,
					label6, label7, label8, label9, label10) == 0) {
				drawCircle(e.getX(), e.getY());
				repaint();
				JFrame MyFrame = new JFrame();
				MyFrame.setSize(600, 250);
				MyFrame.setLocation(350, 225);
				MyFrame.getContentPane().setBackground(Color.WHITE);
				MyFrame.setLayout(new FlowLayout(FlowLayout.LEFT));
				MyFrame.setResizable(false);
				MyFrame.getContentPane().add(label0);
				MyFrame.getContentPane().add(label1);
				MyFrame.getContentPane().add(label2);
				MyFrame.getContentPane().add(label3);
				MyFrame.getContentPane().add(label4);
				MyFrame.getContentPane().add(label5);
				MyFrame.getContentPane().add(label6);
				MyFrame.getContentPane().add(label7);
				MyFrame.getContentPane().add(label8);
				MyFrame.getContentPane().add(label9);
				MyFrame.getContentPane().add(label10);
				MyFrame.setVisible(true);
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mouseDragged(MouseEvent e) {

	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}

}

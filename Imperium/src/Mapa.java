import java.io.IOException;
import java.util.ArrayList;

public class Mapa {
	private static ArrayList<Droga> listaDrog;
	private static ArrayList<Osada> listaOsad;
	private static ArrayList<Skrzyzowanie> listaSkrzyzowan;
	private static ArrayList<Woz> listaWozow;
	private volatile static ArrayList<Handlarz> listaHandlarzy;
	private static ArrayList<LegionZolnierzy> listaLegionow;
	private static ArrayList<PlemieBarbarzyncow> listaPlemionBarbarzyncow;
	private Osada m1, m2, m4, m5, m6, m7, m8, m9, m3;
	private Stolica stolica;
	private Droga a0, a1, a2, a3, a4, a9, a10, a11, a12, a13, a14, a15, a16,
			a17, a18, a19, a20, a22, a23, a24, a25, a26, a27, a28, a29, a31,
			a32, a33, a34, a35, a36, a37, a38, a39, a40, a41, a42, a43, a44,
			a45, a47, a48, a49, a50, a51, a52, a53, a54, a0b, a1b, a2b, a3b,
			a4b, a9b, a10b, a11b, a12b, a13b, a14b, a15b, a16b, a17b, a18b,
			a19b, a20b, a22b, a23b, a24b, a25b, a26b, a27b, a28b, a29b, a31b,
			a32b, a33b, a34b, a35b, a36b, a37b, a38b, a39b, a40b, a41b, a42b,
			a43b, a44b, a45b, a47b, a48b, a49b, a50b;

	private Skrzyzowanie s1, s2, s3, s4, s5, s6, s7, s8, s10, s11, s12, s13,
			s14, s15, s16, s17, s18, s19, s21, s22, s23, s24, s25, z1, z2, z3,
			z4, z5, z6, z7, z8;

	private volatile Integer LbPokonanychPlemion;

	public Mapa() throws IOException {
		listaOsad = new ArrayList<>();
		listaDrog = new ArrayList<>();
		listaSkrzyzowan = new ArrayList<>();
		listaWozow = new ArrayList<>();
		listaHandlarzy = new ArrayList<>();
		listaLegionow = new ArrayList<>();
		listaPlemionBarbarzyncow = new ArrayList<>();
		LbPokonanychPlemion = 0;

		String T[] = { "maka", "bluzka", "buty", "cegla", "meble", "maslo",
				"drewno", "bawelna", "olej", "bizuteria", "cukier", "spodnie",
				"torebka", "szklo", "drzwi", "mleko", "wegiel", "jedwab",
				"wino", "papier" };

		stolica = new Stolica(417, 380, "Syrakuzy", 1900, 3000, 400, true,
				listaOsad, listaLegionow, listaSkrzyzowan, listaDrog);
		m1 = new Osada(464, 105, "Ateny", 1100, 1600, 300, false, listaOsad);
		m2 = new Osada(716, 109, "Rzym", 1700, 1400, 230, false, listaOsad);
		m3 = new Osada(115, 123, "Kartagina", 1000, 1600, 400, false, listaOsad);
		m4 = new Osada(158, 487, "Lizbona", 1700, 2600, 350, false, listaOsad);
		m5 = new Osada(890, 483, "Warszawa", 1600, 2000, 300, true, listaOsad);
		m6 = new Osada(730, 540, "Kreta", 1050, 1900, 200, false, listaOsad);
		m7 = new Osada(475, 600, "Pary¿", 1700, 2500, 250, false, listaOsad);
		m8 = new Osada(841, 352, "Macedonia", 1600, 1200, 300, true, listaOsad);
		m9 = new Osada(841, 207, "Berlin", 1050, 2100, 250, false, listaOsad);

		for (int i = 0; i < 10; i++) {
			listaOsad.get(i).getSurowceProdukowane().add(T[i]);
			listaOsad.get(i).getSurowceProdukowane().add(T[i + 10]);
		}
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 20; j++) {
				if ((j != i) && (j != i + 10)) {
					listaOsad.get(i).getSurowceKupowane().add(T[j]);
				}
			}
		}
		a0 = new Droga(63, 57, 314, 57, true, -1, listaDrog);
		a1 = new Droga(320, 57, 464, 57, true, -1, listaDrog);
		a2 = new Droga(a1.getXGora(), a1.getYGora(), 665, 57, true, -1,
				listaDrog);
		a3 = new Droga(a2.getXGora(), a2.getYGora(), m9.getX(), 57, true, -1,
				listaDrog);
		a4 = new Droga(a3.getXGora(), a3.getYGora(), 994, 57, true, -1,
				listaDrog);
		a9 = new Droga(a4.getXGora() - 11, 425, a4.getXGora() - 11, 688, false,
				1, listaDrog);
		a10 = new Droga(831, 677, a4.getXGora(), 677, true, -1, listaDrog);
		a11 = new Droga(657, 677, a10.getX(), a10.getY(), true, -1, listaDrog);
		a12 = new Droga(m7.getX(), 677, a11.getX(), a11.getY(), true, -1,
				listaDrog);
		a20 = new Droga(a0.getX(), 677, a12.getX(), a12.getY(), true, -1,
				listaDrog);
		a13 = new Droga(63, 636, a20.getX(), a20.getY() + 11, false, 1,
				listaDrog);
		a14 = new Droga(63, 413, a13.getX(), a13.getY(), false, 1, listaDrog);
		a15 = new Droga(63, 57, a14.getX(), a14.getY(), false, 1, listaDrog);
		a16 = new Droga(a0.getXGora(), a0.getYGora() + 21, a0.getXGora(), 224,
				false, 1, listaDrog);
		a17 = new Droga(m1.getX(), a2.getY(), m1.getX(), m1.getY(), false, 1,
				listaDrog);
		a18 = new Droga(a4.getXGora() - 11, a4.getYGora(), a4.getXGora() - 11,
				302, false, 1, listaDrog);
		a19 = new Droga(m7.getX(), m7.getY(), m7.getX(), a12.getY() + 11,
				false, 1, listaDrog);
		a22 = new Droga(a10.getX(), m5.getY(), a10.getX(), a10.getY() + 11,
				false, 1, listaDrog);
		a23 = new Droga(a11.getX(), m6.getY(), a11.getX(), a11.getY() + 11,
				false, 1, listaDrog);
		a24 = new Droga(a13.getX(), a13.getY(), m4.getX() + 11, a13.getY(),
				true, -1, listaDrog);
		a25 = new Droga(a14.getX(), a14.getY(), 325, a14.getY(), true, -1,
				listaDrog);
		a26 = new Droga(a3.getX() - 8, a3.getY(), a3.getX() - 8, 224, false, 1,
				listaDrog);
		a27 = new Droga(m4.getX(), m4.getY(), m4.getX(), a24.getYGora() + 11,
				false, 1, listaDrog);
		a28 = new Droga(a23.getX(), m6.getY(), m6.getX(), m6.getY(), true, -1,
				listaDrog);
		a29 = new Droga(a22.getX(), a22.getY(), m5.getX(), m5.getY(), true, -1,
				listaDrog);
		a31 = new Droga(a28.getX(), 425, a28.getX(), a28.getY(), false, 1,
				listaDrog);
		a32 = new Droga(a22.getX(), 425, a18.getX() + 11, 425, true, -1,
				listaDrog);
		a33 = new Droga(a32.getX(), a32.getY(), a29.getX(), a29.getY(), false,
				1, listaDrog);
		a34 = new Droga(m2.getX(), a32.getY(), a32.getX(), a32.getY(), true,
				-1, listaDrog);
		a35 = new Droga(a31.getX(), a34.getY(), a34.getX(), a34.getY(), true,
				-1, listaDrog);
		a36 = new Droga(a26.getXGora(), a26.getYGora(), a26.getXGora(), 300,
				false, 1, listaDrog);
		a37 = new Droga(a31.getXGora(), a36.getYGora(), a31.getX(), a31.getY(),
				false, 1, listaDrog);
		a38 = new Droga(stolica.getX(), a37.getY(), a37.getX() + 11,
				a37.getY(), true, -1, listaDrog);
		a39 = new Droga(a38.getX(), a38.getY(), stolica.getX(), stolica.getY(),
				false, 1, listaDrog);
		a40 = new Droga(a16.getXGora(), a16.getYGora(), a16.getXGora(), 347,
				false, 1, listaDrog);
		a41 = new Droga(a40.getXGora(), a40.getYGora(), a40.getXGora(),
				a25.getYGora(), false, 1, listaDrog);
		a42 = new Droga(a41.getXGora(), a41.getYGora(), a41.getXGora(),
				a28.getY(), false, 1, listaDrog);
		a43 = new Droga(m3.getX(), a41.getY(), a41.getX() + 11, a41.getY(),
				true, -1, listaDrog);
		a44 = new Droga(a43.getX(), m3.getY(), m3.getX(), a43.getY() + 11,
				false, 1, listaDrog);
		a47 = new Droga(983, 302, 983, m8.getY(), false, 1, listaDrog);
		a48 = new Droga(983, m8.getY(), 983, a32.getYGora(), false, 1,
				listaDrog);
		a45 = new Droga(m8.getX(), m8.getY(), 994, m8.getY(), true, -1,
				listaDrog);
		a49 = new Droga(m9.getX(), 57, m9.getX(), m9.getY(), false, 1,
				listaDrog);
		a50 = new Droga(m2.getX(), m2.getY(), m2.getX(), 302, false, 1,
				listaDrog);
		a51 = new Droga(a50.getXGora(), a50.getYGora(), 994, a50.getYGora(),
				true, -1, listaDrog);
		a52 = new Droga(a50.getXGora(), a50.getYGora(), a50.getXGora(),
				a32.getY() + 11, false, 1, listaDrog);
		a53 = new Droga(a40.getX(), a40.getY(), a36.getX() + 11, a40.getY(),
				true, 1, listaDrog);
		a54 = new Droga(a42.getXGora(), a42.getYGora(), a28.getX(),
				a42.getYGora(), true, -1, listaDrog);

		a0b = new Droga(63, 68, 314, 68, true, 1, listaDrog);
		a1b = new Droga(a0.getXGora(), a0.getYGora() + 11, 464,
				a0.getYGora() + 11, true, 1, listaDrog);
		a2b = new Droga(a1.getXGora(), a1.getYGora() + 11, 665, 68, true, 1,
				listaDrog);
		a3b = new Droga(a2.getXGora(), a2.getYGora() + 11, m9.getX(), 68, true,
				1, listaDrog);
		a4b = new Droga(a3.getXGora(), a3.getYGora() + 11, 994, 68, true, 1,
				listaDrog);
		a9b = new Droga(a9.getXGora() + 11, 425, a9.getXGora() + 11, 688,
				false, -1, listaDrog);
		a10b = new Droga(831, 688, a4.getXGora(), 688, true, 1, listaDrog);
		a11b = new Droga(657, 688, a10.getX(), a10.getY() + 11, true, 1,
				listaDrog);
		a12b = new Droga(m7.getX(), 688, a11.getX(), a11.getY() + 11, true, 1,
				listaDrog);
		a20b = new Droga(a0.getX(), 688, a12.getX(), a12.getY() + 11, true, 1,
				listaDrog);
		a13b = new Droga(74, 636, a20.getX() + 11, a20.getY() + 11, false, -1,
				listaDrog);
		a14b = new Droga(74, 413, a13.getX() + 11, a13.getY(), false, -1,
				listaDrog);
		a15b = new Droga(74, 57, a14.getX() + 11, a14.getY(), false, -1,
				listaDrog);
		a16b = new Droga(a0.getXGora() + 11, a0.getYGora() + 21,
				a0.getXGora() + 11, 224, false, -1, listaDrog);
		a17b = new Droga(m1.getX() + 11, a2.getY(), m1.getX() + 11, m1.getY(),
				false, -1, listaDrog);
		a18b = new Droga(a4.getXGora(), a4.getYGora(), a4.getXGora(), 302,
				false, -1, listaDrog);
		a19b = new Droga(m7.getX() + 11, m7.getY(), m7.getX() + 11,
				a12.getY() + 11, false, -1, listaDrog);
		a22b = new Droga(a10.getX() + 11, m5.getY(), a10.getX() + 11,
				a10.getY() + 11, false, -1, listaDrog);
		a23b = new Droga(a11.getX() + 11, m6.getY(), a11.getX() + 11,
				a11.getY() + 11, false, -1, listaDrog);
		a24b = new Droga(a13.getX(), a13.getY() + 11, m4.getX() + 11,
				a13.getY() + 11, true, 1, listaDrog);
		a25b = new Droga(a14.getX(), a14.getY() + 11, 325, a14.getY() + 11,
				true, 1, listaDrog);
		a26b = new Droga(a3.getX() + 3, a3.getY(), a3.getX() + 3, 224, false,
				-1, listaDrog);
		a27b = new Droga(m4.getX() + 11, m4.getY(), m4.getX() + 11,
				a24.getYGora() + 11, false, -1, listaDrog);
		a28b = new Droga(a23.getX(), m6.getY() + 11, m6.getX(), m6.getY() + 11,
				true, 1, listaDrog);
		a29b = new Droga(a22.getX(), a22.getY() + 11, m5.getX(),
				m5.getY() + 11, true, 1, listaDrog);
		a31b = new Droga(a28.getX() + 11, 425, a28.getX() + 11, a28.getY(),
				false, -1, listaDrog);
		a32b = new Droga(a22.getX(), 436, a18.getX() + 11, 436, true, 1,
				listaDrog);
		a33b = new Droga(a32.getX() + 11, a32.getY(), a29.getX() + 11,
				a29.getY(), false, -1, listaDrog);
		a34b = new Droga(m2.getX(), a32.getY() + 11, a32.getX(),
				a32.getY() + 11, true, 1, listaDrog);
		a35b = new Droga(a31.getX(), a34.getY() + 11, a34.getX(),
				a34.getY() + 11, true, 1, listaDrog);
		a36b = new Droga(a26.getXGora() + 11, a26.getYGora(),
				a26.getXGora() + 11, 300, false, -1, listaDrog);
		a37b = new Droga(a31.getXGora() + 11, a36.getYGora(), a31.getX() + 11,
				a31.getY(), false, -1, listaDrog);
		a38b = new Droga(stolica.getX(), a37.getY() + 11, a37.getX() + 11,
				a37.getY() + 11, true, 1, listaDrog);
		a39b = new Droga(a38.getX() + 11, a38.getY(), stolica.getX() + 11,
				stolica.getY(), false, -1, listaDrog);
		a40b = new Droga(a16.getXGora() + 11, a16.getYGora(),
				a16.getXGora() + 11, 347, false, -1, listaDrog);
		a41b = new Droga(a40.getXGora() + 11, a40.getYGora(),
				a40.getXGora() + 11, a25.getYGora(), false, -1, listaDrog);
		a42b = new Droga(a41.getXGora() + 11, a41.getYGora(),
				a41.getXGora() + 11, a28.getY(), false, -1, listaDrog);
		a43b = new Droga(m3.getX(), a41.getY() + 11, a41.getX() + 11,
				a41.getY() + 11, true, 1, listaDrog);
		a44b = new Droga(a43.getX() + 11, m3.getY(), m3.getX() + 11,
				a43.getY() + 11, false, -1, listaDrog);
		a47b = new Droga(994, 302, 994, m8.getY(), false, -1, listaDrog);
		a48b = new Droga(994, m8.getY(), 994, a32.getYGora(), false, -1,
				listaDrog);
		a45b = new Droga(m8.getX(), m8.getY() + 11, 994, m8.getY() + 11, true,
				1, listaDrog);
		a49b = new Droga(m9.getX() + 11, 57, m9.getX() + 11, m9.getY(), false,
				-1, listaDrog);
		a50b = new Droga(m2.getX() + 11, m2.getY(), m2.getX() + 11, 302, false,
				-1, listaDrog);

		Droga[] wsk1 = { a1b, a1b, a1b, a16, a0, a1b, a1b, a0, a1b, a1b };
		Droga[] wskz1 = { a0b, a0b, a0b, a0b, a15, a0b, a0b, a15, a0b, a0b };
		Droga[] wsk15 = { a25b, a25b, a25b, a25b, a14, a25b, a25b, a14, a15b,
				a15b };
		Droga[] wsk2 = { a2b, a17, a2b, a1, a1, a2b, a2b, a2b, a2b, a2b };

		Droga[] wsk3 = { a26, a2, a3b, a2, a2, a26, a26, a26, a3b, a3b };
		Droga[] wsk4 = { a3, a3, a4b, a3, a3, a4b, a3, a3, a4b, a49 };
		Droga[] wskz2 = { a4, a4, a18, a4, a4, a18, a4, a4, a18, a4 };
		Droga[] wsk5 = { a51, a18b, a51, a18b, a47, a47, a47, a47, a47, a18b };
		Droga[] wsk6 = { a48, a47b, a47b, a47b, a48, a48, a48, a48, a45, a47b };
		Droga[] wsk7 = { a32, a48b, a48b, a48b, a9, a9, a9, a9, a48b, a48b };
		Droga[] wsk8 = { a12b, a12b, a12b, a20, a20, a12b, a12b, a19b, a12b,
				a12b };
		Droga[] wsk10 = { a52, a52, a50b, a52, a52, a52, a52, a52, a52, a52 };
		Droga[] wsk11 = { a36, a26b, a26b, a26b, a36, a36, a36, a36, a36, a26b };
		Droga[] wsk12 = { a53, a16b, a16b, a40, a40, a53, a53, a53, a53, a16b };
		Droga[] wsk13 = { a40b, a40b, a40b, a43, a41, a40b, a40b, a40b, a40b,
				a40b };
		Droga[] wsk14 = { a41b, a41b, a41b, a41b, a25, a41b, a41b, a25, a41b,
				a41b };

		Droga[] wsk16 = { a38, a36b, a37, a36b, a37, a37, a37, a37, a37, a36b };
		Droga[] wsk17 = { a13, a14b, a14b, a14b, a24b, a13, a13, a13, a13, a14b };
		Droga[] wsk18 = { a23b, a23b, a23b, a23b, a12, a11b, a23b, a12, a11b,
				a11b };
		Droga[] wsk19 = { a11, a11, a22b, a11, a11, a22b, a11, a11, a10b, a10b };

		Droga[] wsk21 = { a31b, a31b, a31b, a54, a23, a23, a28b, a23, a31b,
				a31b };
		Droga[] wsk22 = { a37b, a37b, a35b, a31, a31, a31, a31, a31, a35b, a35b };
		Droga[] wsk23 = { a35, a35, a34b, a35, a35, a34b, a35, a35, a34b, a34b };
		Droga[] wsk24 = { a34, a34, a32b, a34, a34, a33, a34, a34, a32b, a32b };
		Droga[] wsk25 = { a33b, a33b, a33b, a33b, a22, a29b, a22, a22, a33b,
				a33b };

		Droga[] wskz3 = { a9b, a9b, a9b, a9b, a10, a10, a10, a10, a9b, a9b };
		Droga[] wskz4 = { a20b, a20b, a20b, a13b, a13b, a20b, a20b, a20b, a20b,
				a20b };
		Droga[] wskz5 = { a43b, a43b, a43b, a44b, a43b, a43b, a43b, a43b, a43b,
				a43b };
		Droga[] wskz6 = { a24, a24, a24, a24, a27b, a24, a24, a24, a24, a24 };
		Droga[] wskz7 = { a39, a38b, a38b, a38b, a38b, a38b, a38b, a38b, a38b,
				a38b };
		Droga[] wskz8 = { a42b, a42b, a42b, a42b, a42b, a42b, a42b, a42b, a42b,
				a42b };
		s1 = new Skrzyzowanie(320, 63, a16, null, a0, a1b, wsk1,
				listaSkrzyzowan);
		s2 = new Skrzyzowanie(470, 63, a17, null, a1, a2b, wsk2,
				listaSkrzyzowan);
		s3 = new Skrzyzowanie(663, 63, a26, null, a2, a3b, wsk3,
				listaSkrzyzowan);

		s4 = new Skrzyzowanie(847, 63, a49, null, a3, a4b, wsk4,
				listaSkrzyzowan);
		s5 = new Skrzyzowanie(989, 308, a47, a18b, a51, null, wsk5,
				listaSkrzyzowan);
		s6 = new Skrzyzowanie(989, 358, a48, a47b, a45, null, wsk6,
				listaSkrzyzowan);
		s7 = new Skrzyzowanie(989, 431, a9, a48b, a32, null, wsk7,
				listaSkrzyzowan);
		s8 = new Skrzyzowanie(481, 683, null, a19b, a20, a12b, wsk8,
				listaSkrzyzowan);
		s10 = new Skrzyzowanie(722, 308, a52, a50b, null, a51, wsk10,
				listaSkrzyzowan);
		s11 = new Skrzyzowanie(663, 230, a36, a26b, a53, null, wsk11,
				listaSkrzyzowan);
		s12 = new Skrzyzowanie(320, 230, a40, a16b, null, a53, wsk12,
				listaSkrzyzowan);
		s13 = new Skrzyzowanie(320, 353, a41, a40b, a43, null, wsk13,
				listaSkrzyzowan);
		s14 = new Skrzyzowanie(320, 419, a42, a41b, a25, null, wsk14,
				listaSkrzyzowan);
		s15 = new Skrzyzowanie(69, 419, a14, a15b, null, a25b, wsk15,
				listaSkrzyzowan);
		s16 = new Skrzyzowanie(663, 306, a37, a36b, a38, null, wsk16,
				listaSkrzyzowan);
		s17 = new Skrzyzowanie(69, 642, a13, a14b, null, a24b, wsk17,
				listaSkrzyzowan);
		s18 = new Skrzyzowanie(663, 683, null, a23b, a12, a11b, wsk18,
				listaSkrzyzowan);
		s19 = new Skrzyzowanie(837, 683, null, a22b, a11, a10b, wsk19,
				listaSkrzyzowan);

		s21 = new Skrzyzowanie(663, 546, a23, a31b, a54, a28b, wsk21,
				listaSkrzyzowan);
		s22 = new Skrzyzowanie(663, 431, a31, a37b, null, a35b, wsk22,
				listaSkrzyzowan);
		s23 = new Skrzyzowanie(722, 431, null, a52, a35, a34b, wsk23,
				listaSkrzyzowan);
		s24 = new Skrzyzowanie(837, 431, a33, null, a34, a32b, wsk24,
				listaSkrzyzowan);
		s25 = new Skrzyzowanie(837, 489, a22, a33b, null, a29b, wsk25,
				listaSkrzyzowan);
		z1 = new Skrzyzowanie(a15.getX() + 6, a15.getY() + 6, a15, null, null,
				a0b, wskz1, listaSkrzyzowan);
		z2 = new Skrzyzowanie(a18.getX() + 6, a18.getY() + 6, a18, null, a4,
				null, wskz2, listaSkrzyzowan);
		z3 = new Skrzyzowanie(a9b.getXGora() - 5, a9b.getYGora() - 5, null,
				a9b, a10, null, wskz3, listaSkrzyzowan);
		z4 = new Skrzyzowanie(a20b.getX() + 6, a20b.getY() - 5, null, a13b,
				null, a20b, wskz4, listaSkrzyzowan);
		z5 = new Skrzyzowanie(a44b.getXGora() - 5, a44b.getYGora() - 5, null,
				a44b, null, a43b, wskz5, listaSkrzyzowan);
		z6 = new Skrzyzowanie(a24.getXGora() - 5, a24.getYGora() + 6, null,
				a27b, a24, null, wskz6, listaSkrzyzowan);
		z7 = new Skrzyzowanie(a39.getX() + 6, a39.getY() + 6, a39, null, null,
				a38b, wskz7, listaSkrzyzowan);
		z8 = new Skrzyzowanie(a54.getX() + 6, a54.getY() - 5, null, a42b, null,
				a54, wskz8, listaSkrzyzowan);
		listaOsad.get(0).setDroga(a39b);
		listaOsad.get(1).setDroga(a17b);
		listaOsad.get(2).setDroga(a50);
		listaOsad.get(3).setDroga(a44);
		listaOsad.get(4).setDroga(a27);
		listaOsad.get(5).setDroga(a29);
		listaOsad.get(6).setDroga(a28);
		listaOsad.get(7).setDroga(a19);
		listaOsad.get(8).setDroga(a45b);
		listaOsad.get(9).setDroga(a49b);

	}

	public ArrayList<Droga> getListaDrog() {
		return listaDrog;
	}

	public ArrayList<Osada> getListaOsad() {
		return listaOsad;
	}

	public ArrayList<Skrzyzowanie> getListaSkrzyzowan() {
		return listaSkrzyzowan;
	}

	public ArrayList<Handlarz> getListaHandlarzy() {
		return listaHandlarzy;
	}

	public ArrayList<LegionZolnierzy> getListaLegionow() {
		return listaLegionow;
	}

	public ArrayList<PlemieBarbarzyncow> getListaPlemionBarbarzyncow() {
		return listaPlemionBarbarzyncow;
	}

	public ArrayList<Woz> getListaWozow() {
		return listaWozow;
	}

	public Stolica getStolica() {
		return stolica;
	}

	public void setLbPokonanychPlemion() {
		this.LbPokonanychPlemion++;
	}

	public Integer getLbPokonanychPlemion() {
		return LbPokonanychPlemion;
	}
}

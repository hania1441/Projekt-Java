public class DoLosowania {
	private int id = 0;
	private int idSurowca = 0;
	private String Imie[] = { "Pawe�", "Wojciech", "Piotr", "Kamil", "Dawid",
			"Hieronim", "Tomasz", "Mariusz", "Grzogorz", "Marek", "Filip",
			"Wiktor", "Micha�", "Miko�aj", "Damian", "Dominik", "Karol",
			"J�zef", "Jan", "Jerzy", "Zdzis�aw", "Wies�aw", "Fabian", "Olaf" };
	private String Nazwisko[] = { "Nawrot", "Kowalski", "Nowak", "W�jcik",
			"Kami�ski", "Wojciechowski", "Zieli�ski", "Wi�niewski",
			"Lewandowski", "Jankowski", "Wieczorek", "Koz�owski", "Mazur",
			"Krawczyk", "Kwiatkowski", "Kaczmarek", "Nowicki", "Baran", "Duda",
			"Tomaszewski", "G�rski", "Wr�blewski", "Sadowski", "Kubiak" };
	private String Bron[] = { "b1", "b2", "b3", "c2", "c7", "c3", "c4", "f11",
			"f15", "b12", "b5", "b66", "b78" };
	private String NazwaPlemienia[] = { "Hehfeldi", "Miloxi", "Phesnuzi",
			"Thadesi", "Glopeani", "Lendizi", "Prissani", "Uelunzani", "Bruzi",
			" 	Ungare", "Vuislane", "Sleenzane", "Lunsizi", "Milzane",
			"Lupiglaa", "Opolini", "Golensizi", "Besunzane", "Dadodesani" };
	private int X[] = { 250, 250, 1030, 1030, 40, 40, 380, 900 };
	private int Y[] = { 35, 35, 235, 610, 610, 700, 700, 700 };
	private int Szybkosc[] = { 25, 20, 18, 23, 26, 23, 21, 19 };

	public String getImie(int i) {
		return Imie[i];
	}

	public String getNazwisko(int i) {
		return Nazwisko[i];
	}

	public String getBron(int i) {
		return Bron[i];
	}

	public int getX(int i) {
		return X[i];
	}

	public int getY(int i) {
		return Y[i];
	}

	public String getNazwaPlemienia(int i) {
		return NazwaPlemienia[i];
	}

	public int getSzybkosc(int i) {
		return Szybkosc[i];
	}

	public int getId() {
		return id;
	}

	public int getIdSurowca() {
		return idSurowca;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setIdSurowca(int idSur) {
		this.idSurowca = idSur;
	}
}

import java.util.logging.Level;
import java.util.logging.Logger;

public class Odswiezanie extends Thread {
	private ObslugaPaneluIRysowanie mapa;

	public Odswiezanie(ObslugaPaneluIRysowanie mapa) {
		this.mapa = mapa;
		start();

	}

	public void run() {
		while (true) {
			try {
				Thread.sleep(30);
			} catch (InterruptedException ex) {
				Logger.getLogger(Odswiezanie.class.getName()).log(Level.SEVERE,
						null, ex);
			}
			mapa.repaint();

		}
	}
}

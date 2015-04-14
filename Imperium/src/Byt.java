import java.awt.Graphics;
import java.io.IOException;

public class Byt extends Thread {
	private int x;
	private int y;

	public Byt(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	void rysujSie(Graphics g) throws IOException {

	}
}

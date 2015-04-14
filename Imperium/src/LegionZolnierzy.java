import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class LegionZolnierzy extends ObiektRuchomy{
	 private int identyfikator;
	    private String bron;
	    private ArrayList<LegionZolnierzy> listaLegionow;
	    private ArrayList<Osada> listaOsad;
	    private ArrayList<Skrzyzowanie> listaSkrzyzowan;
	   

	    public LegionZolnierzy(int x, int y, int identyfikator, String bron, int szybkosc,
	            Mapa dane) throws IOException {
	        super(x, y, szybkosc, dane);
	        this.listaLegionow = dane.getListaLegionow();
	        this.listaOsad = dane.getListaOsad();
	        this.listaSkrzyzowan = dane.getListaSkrzyzowan();
	        this.identyfikator = identyfikator;
	        this.bron = bron;

	        ImageFile
	                = new File("grafika/zolnierz.png");

	        img = ImageIO.read(ImageFile);
	        listaLegionow.add(this);

	    }

	    public void run() {
	        try {

	            ruszajSie(this.getOsadaCel(), listaOsad, listaSkrzyzowan);

	        } catch (InterruptedException ex) {
	            Logger.getLogger(LegionZolnierzy.class.getName()).log(Level.SEVERE, null, ex);
	        }

	    }

	    public int getIdentyfikator() {
	        return identyfikator;
	    }

	    public String getBron() {
	        return bron;
	    }

	    public void setIdentyfikator(int identyfikator) {
	        this.identyfikator = identyfikator;
	    }

	    public void setBron(String bron) {
	        this.bron = bron;
	    }

	    public void rysujSie(Graphics g) {
	        g.drawImage(img, this.getX() - 5, this.getY() - 8, null);
	    }
}

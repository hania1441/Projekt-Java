import java.awt.Color;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Imperium extends javax.swing.JFrame{

	
	private static Mapa main;
    private static ObslugaPaneluIRysowanie mapa;
    private static Mapa dane;
    private static Odswiezanie odswiez;
    private static DoLosowania los;
    private static Czasomierz czasomierz;
    private static Timer timer;
    private static Random losowanie;
    private int id = 0;
    private static ArrayList<Handlarz> listHan;

    public Imperium() throws IOException {
        initComponents();
        mapa = new ObslugaPaneluIRysowanie();
        getContentPane().setBackground(Color.WHITE);
        getContentPane().add(mapa);
        pack();
        main = mapa.getMapa();
        timer = new Timer();
        dane = new Mapa();
        czasomierz = new Czasomierz(dane);
        timer.schedule(czasomierz, 0, 3000);
        setOdswiez(new Odswiezanie(mapa));

    }

                             
    private void initComponents() {

        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton2.setText("Nowy Handlarz");
        jButton2.setMaximumSize(new java.awt.Dimension(195, 23));
        jButton2.setMinimumSize(new java.awt.Dimension(195, 23));
        jButton2.setPreferredSize(new java.awt.Dimension(195, 23));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Usuñ Handlarza");
        jButton3.setMaximumSize(new java.awt.Dimension(195, 23));
        jButton3.setMinimumSize(new java.awt.Dimension(195, 23));
        jButton3.setPreferredSize(new java.awt.Dimension(195, 23));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Nowy Legion");
        jButton4.setMaximumSize(new java.awt.Dimension(195, 23));
        jButton4.setMinimumSize(new java.awt.Dimension(195, 23));
        jButton4.setPreferredSize(new java.awt.Dimension(195, 23));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Zmieñ osadê docelow¹ Handlarza ");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("Napraw wóz");
        jButton6.setMaximumSize(new java.awt.Dimension(195, 23));
        jButton6.setMinimumSize(new java.awt.Dimension(195, 23));
        jButton6.setPreferredSize(new java.awt.Dimension(195, 23));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("Wyprodukuj nowy Surowiec");
        jButton7.setMaximumSize(new java.awt.Dimension(195, 23));
        jButton7.setMinimumSize(new java.awt.Dimension(195, 23));
        jButton7.setPreferredSize(new java.awt.Dimension(195, 23));
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jTextField1.setText("Wpisz osadê do zmiany");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(1091, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGap(38, 38, 38))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                                .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addContainerGap()))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(260, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>                        

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        int a = losowanie.nextInt(13);
        int b = losowanie.nextInt(5);
        int c = losowanie.nextInt(9);
        int d = losowanie.nextInt(24);
        Woz w3 = new Woz(main.getListaOsad().get(c).getX(), main.getListaOsad().get(c).getY(),
                losowanie.nextInt(100) + 100, los.getSzybkosc(b), main);
        try {
            Handlarz h1 = new Handlarz(los.getImie(d), los.getNazwisko(d), w3, main);     
        } catch (IOException ex) {
            Logger.getLogger(Imperium.class.getName()).log(Level.SEVERE, null, ex);
        } catch (URISyntaxException ex) {
            Logger.getLogger(Imperium.class.getName()).log(Level.SEVERE, null, ex);
        }
    }                                        

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        try {
            int i = losowanie.nextInt(13);
            int j = losowanie.nextInt(8);

            LegionZolnierzy l = new LegionZolnierzy(main.getListaOsad().get(0).getX() + 30,
                    main.getListaOsad().get(0).getY(),
                    id, los.getBron(i),
                    los.getSzybkosc(j), main);
            id++;
        } catch (IOException ex) {
            Logger.getLogger(Imperium.class.getName()).log(Level.SEVERE, null, ex);
        }
    }                                        

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {                                         

        int i = mapa.getxKlik();
        int j = mapa.getyKlik();

        listHan.addAll(main.getListaHandlarzy());
        for (Handlarz hi : listHan) {
            if ((i <= hi.getX() + 20) && (i >= hi.getX() - 20) && (j <= hi.getY() + 25)
                    && (j >= hi.getY() - 25)) {

                hi.setX(2000);
                hi.setY(2000);

                main.getListaHandlarzy().remove(hi);

                mapa.repaint();
                break;
            }
        }
        listHan.clear();
    }                                        

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        String nazwa;
        nazwa = jTextField1.getText();
        int i = mapa.getxKlik();
        int j = mapa.getyKlik();

        listHan.addAll(main.getListaHandlarzy());
        for (Handlarz hi : listHan) {
            if ((i <= hi.getX() + 20) && (i >= hi.getX() - 20) && (j <= hi.getY() + 25)
                    && (j >= hi.getY() - 25)) {
                hi.zmienOsadeCel(nazwa);
                break;
            }
        }
    }                                        

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        int i = mapa.getxKlik();
        int j = mapa.getyKlik();
        listHan.addAll(main.getListaHandlarzy());
        if (!listHan.isEmpty()) {
            for (Handlarz h : listHan) {
                if ((i <= h.getX() + 10) && (i >= h.getX() - 10) && (j <= h.getY() + 15)
                        && (j >= h.getY() - 15)) {

                    if (h.getStop() == true) {
                        h.setStop(false);
                    }

                }

            }

        }
        listHan.clear();
    }                                        

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        int i = mapa.getxKlik();
        int j = mapa.getyKlik();
        for (Osada os : main.getListaOsad()) {
            if ((i <= os.getX() + 50) && (i >= os.getX() - 50) && (j <= os.getY() + 55)
                    && (j >= os.getY() - 55)) {
                os.produkuj(los);
                break;
            }
        }
    }                                        

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {                                            

// TODO add your handling code here:
    }                                           

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws IOException, URISyntaxException, InterruptedException {
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Imperium.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Imperium.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Imperium.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Imperium.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        new Imperium().setVisible(true);
        los = new DoLosowania();
        losowanie = new Random();
        listHan = new ArrayList<>();

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

            }

        });
    }

    public static Odswiezanie getOdswiez() {
		return odswiez;
	}

	public static void setOdswiez(Odswiezanie odswiez) {
		Imperium.odswiez = odswiez;
	}

	// Variables declaration - do not modify                     
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration            

}

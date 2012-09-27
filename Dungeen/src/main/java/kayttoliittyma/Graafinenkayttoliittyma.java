package kayttoliittyma;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import logiikka.Alusta;

public class Graafinenkayttoliittyma implements Kayttoliittyma {

    private JFrame frame;
    private Alusta alusta;
    private boolean alustettu;
    private boolean vuoro;
    private char merkki;

    public Graafinenkayttoliittyma() {
        alustettu = false;
        vuoro = false;
        
    }

    public void luo(Alusta alusta) {
        frame = new JFrame("Otsikko");
        frame.setPreferredSize(new Dimension(400, 400));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        GridLayout layout = new GridLayout(alusta.getKoko(), alusta.getKoko());
        frame.getContentPane().setLayout(layout);
        frame.pack();
        frame.setVisible(true);
        alustettu = true;
    }

    private void luoKomponentit(Container container) {
        container.removeAll();
        Ruutu panel;
        for (int i = 0; i < alusta.getKoko(); i++) {
            for (int j = 0; j < alusta.getKoko(); j++) {
                panel = new Ruutu(alusta.lueMerkki(j, i), j, i);
                panel.setBorder(BorderFactory.createLineBorder(Color.black));
                panel.setBackground(Color.white);
                panel.addMouseListener(new MouseListener() {

                    public void mouseClicked(MouseEvent me) {
                        Ruutu kohde = (Ruutu) me.getComponent();
                        if (vuoro && alusta.onkoTyhja(kohde.x, kohde.y)) {
                            alusta.lisaaMerkkiLaudalle(kohde.x, kohde.y, merkki);
                            vuoro = false;
                        }
                    }

                    public void mousePressed(MouseEvent me) {
                    }

                    public void mouseReleased(MouseEvent me) {
                    }

                    public void mouseEntered(MouseEvent me) {
                    }

                    public void mouseExited(MouseEvent me) {
                    }
                });
                container.add(panel);
            }
        }
    }

    public JFrame getFrame() {
        return frame;
    }

    public void tulosta_alusta(Alusta alusta) {
        this.alusta = alusta;
        if (!alustettu) {
            this.luo(alusta);
        }
        luoKomponentit(frame.getContentPane());
        frame.pack();
    }

    public Alusta kysy_siirto(Alusta alusta, char merkki) {
        vuoro = true;
        this.merkki = merkki;
        while (vuoro) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException ex) {
                System.out.println("Kysy_siirto venaamises vikaa!");
            }
        }
        return alusta;
    }
}

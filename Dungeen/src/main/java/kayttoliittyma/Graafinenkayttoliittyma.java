package kayttoliittyma;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import logiikka.Alusta;

public class Graafinenkayttoliittyma implements Kayttoliittyma {

    private JFrame frame;
    private Alusta alusta;
    private boolean alustettu;

    public Graafinenkayttoliittyma() {
        alustettu = false;
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
                panel = new Ruutu(alusta.lueMerkki(j, i));
                panel.setBorder(BorderFactory.createLineBorder(Color.black));
                panel.setBackground(Color.white);
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
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

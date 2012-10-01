package kayttoliittyma;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import logiikka.Alusta;

public class Graafinenkayttoliittyma implements Kayttoliittyma {

    private JFrame frame;
    private JFrame ilmoitus;
    private Alusta alusta;
    private boolean alustettu;
    private boolean vuoro;
    private char merkki;

    public Graafinenkayttoliittyma() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
        }
        alustettu = false;
        vuoro = false;

    }

    public void luo(Alusta alusta) {
        frame = new JFrame("Ristinolla");
        GridLayout layout = new GridLayout(alusta.getKoko(), alusta.getKoko());
        frame.getContentPane().setLayout(layout);
        frame.setPreferredSize(new Dimension(400, 400));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        teeIlmoitusLaatikko();

        alustettu = true;
    }

    private void teeIlmoitusLaatikko() throws HeadlessException, SecurityException {
        ilmoitus = new JFrame();
        ilmoitus.setAlwaysOnTop(true);
        ilmoitus.setMinimumSize(new Dimension(100, 100));
        ilmoitus.setLocationRelativeTo(frame);
        ilmoitus.setUndecorated(true);
        ilmoitus.setVisible(false);
        ImageIcon icon = new ImageIcon("loading.gif");
        JLabel label = new JLabel(icon, JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.BOTTOM);
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setOpaque(true);
        ilmoitus.add(label);
    }

    private void luoKomponentit(Container container) {
        container.removeAll();
        Ruutu panel;
        for (int i = 0; i < alusta.getKoko(); i++) {
            for (int j = 0; j < alusta.getKoko(); j++) {
                panel = new Ruutu(alusta.lueMerkki(j, i), j, i);
                panel.setBorder(BorderFactory.createLineBorder(Color.black));
                panel.addMouseListener(new MouseListener() {

                    public void mouseClicked(MouseEvent me) {
                        Ruutu kohde = (Ruutu) me.getComponent();
                        if (vuoro && alusta.onkoTyhja(kohde.x, kohde.y)) {
                            alusta.lisaaMerkkiLaudalle(kohde.x, kohde.y, merkki);
                            vuoro = false;
                            ilmoitus.setVisible(true);
                            ilmoitus.setLocationRelativeTo(frame);
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

    public void tulostaAlusta(Alusta alusta) {
        this.alusta = alusta;
        if (!alustettu) {
            this.luo(alusta);
        }
        luoKomponentit(frame.getContentPane());
        frame.pack();
    }

    public void tulostaVoitto(char merkki) {
        ilmoitus.setVisible(false);
        if (merkki == 't') {
            JOptionPane.showMessageDialog(frame, "Tasapeli!");
            return;
        }
        JOptionPane.showMessageDialog(frame, "Pelin voitti " + merkki + "!");
    }

    public Alusta kysySiirto(Alusta alusta, char merkki) {
        vuoro = true;
        ilmoitus.setVisible(false);
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

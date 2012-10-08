package kayttoliittyma;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
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

    /**
     * Luo laudan pohjan.
     */
    public void luo() {
        frame = new JFrame("Ristinolla");
        frame.setPreferredSize(new Dimension(400, 400));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        teeLatauslaatikko();
    }

    /**
     * Kysyy käyttäjältä asetukset ja palauttaa ne taulukoituna
     * @return Taulukkona lukuja, mitkä kuvaavat pelialustan koon, voittorivin pituuden, 1. pelaajan ja 2. pelaajan.
     */
    public int[] luoAsetukset() {
        int[] palautus = new int[4];

        String[] koot = {"3", "4", "5", "6", "7", "8", "9", "10"};
        String[] pelaaja1 = {"Ihmispelaaja", "Tekoäly"};
        String[] pelaaja2 = {"Tekoäly", "Ihmispelaaja"};

        JPanel asetukset = new JPanel();

        asetukset.setLayout(new GridLayout(4, 2));

        asetukset.add(new JLabel("Laudan koko: "));
        JComboBox asetuksetKoot = new JComboBox(koot);

        asetukset.add(asetuksetKoot);

        asetukset.add(new JLabel("Voittorivin pituus: "));
        final JComboBox asetuksetVoitot = new JComboBox(new String[]{"3"});

        asetuksetKoot.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                JComboBox box = (JComboBox) ae.getSource();
                int arvo = Integer.parseInt((String) box.getSelectedItem());
                int in = 0;

                String[] uudet = new String[arvo - 2];

                for (int i = 3; i <= arvo; i++) {
                    uudet[in] = "" + i;
                    in++;
                }

                asetuksetVoitot.removeAllItems();
                ComboBoxModel model = new DefaultComboBoxModel(uudet);
                asetuksetVoitot.setModel(model);
            }
        });

        asetukset.add(asetuksetVoitot);

        asetukset.add(new JLabel("1. Pelaaja:"));
        JComboBox asetuksetPelaaja1 = new JComboBox(pelaaja1);
        asetukset.add(asetuksetPelaaja1);

        asetukset.add(new JLabel("2. Pelaaja:"));
        JComboBox asetuksetPelaaja2 = new JComboBox(pelaaja2);
        asetukset.add(asetuksetPelaaja2);

        if (JOptionPane.showConfirmDialog(frame, asetukset, "Asetukset", JOptionPane.PLAIN_MESSAGE) == -1) {
            System.exit(0);
        }

        palautus[0] = Integer.parseInt((String) asetuksetKoot.getSelectedItem());
        palautus[1] = Integer.parseInt((String) asetuksetVoitot.getSelectedItem());

        if (((String) asetuksetPelaaja1.getSelectedItem()).equals("Ihmispelaaja")) {
            palautus[2] = 0;
        } else {
            palautus[2] = 1;
        }
        if (((String) asetuksetPelaaja2.getSelectedItem()).equals("Ihmispelaaja")) {
            palautus[3] = 0;
        } else {
            palautus[3] = 1;
        }
        return palautus;
    }

    /**
     * Pieni ikkuna jossa latausgiffi auttamassa käyttäjää tiedostamalla, milloin tekoäly on tehnyt siirron.
     * @throws HeadlessException
     * @throws SecurityException 
     */
    private void teeLatauslaatikko() throws HeadlessException, SecurityException {
        ilmoitus = new JFrame();
        ilmoitus.setAlwaysOnTop(true);

        ilmoitus.setMinimumSize(new Dimension(100, 100));
        ilmoitus.setLocationRelativeTo(frame);
        ilmoitus.setUndecorated(true);
        ilmoitus.setVisible(false);
        ImageIcon icon = new ImageIcon("loading.gif");
        JLabel label = new JLabel(icon, JLabel.CENTER);
        label.setOpaque(true);
        label.setVerticalTextPosition(JLabel.BOTTOM);
        label.setHorizontalTextPosition(JLabel.CENTER);

        ilmoitus.add(label);
    }

    /**
     * Tyhjentää laudan ja rakentaa laudalle uuden pelitilanteen alustan tilanteen mukaan
     * @param container apuparametri
     */
    private void luoKomponentit(Container container) {
        container.removeAll();
        GridLayout layout = new GridLayout(alusta.getKoko(), alusta.getKoko());
        container.setLayout(layout);
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

    /**
     * asettaa alustan ja pyytää luoKomponentit metodia luomaan tilanteen ruudulle
     * @param alusta Nykyinen pelitilanne
     */
    public void tulostaAlusta(Alusta alusta) {
        this.alusta = alusta;
        luoKomponentit(frame.getContentPane());
        frame.pack();
    }

    /**
     * tulostaa voittoilmoituksen tilanteen mukaan.
     * @param merkki Voittaja, t jos tilanne päättyi tasapeliksi
     */
    public void tulostaVoitto(char merkki) {
        ilmoitus.setVisible(false);
        if (merkki == 't') {
            JOptionPane.showMessageDialog(frame, "Tasapeli!");
            return;
        }
        JOptionPane.showMessageDialog(frame, "Pelin voitti " + merkki + "!");
    }

    /**
     * Antaa käyttäjälle luvan tehdä siirron. Ryhtyy kuuntelemaan 50ms välein, milloin hiirellä on tiettyyn ruutuun klikattu
     * @param alusta Nykyinen pelitilanne
     * @param merkki pelaajan merkki
     * @return Siirron alustana
     */
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

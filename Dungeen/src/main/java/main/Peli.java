package main;

import logiikka.Alusta;
import logiikka.Pelaaja;
import logiikka.Ratkaisija;
import tekoaly.Arvioija;

/**
 * Pelin pyörittäjäluokka. Kutsuu pelaajilta siirtoja ja pitää tallessa nykyistä pelitilannetta
 * @author n9clip
 */
public class Peli {

    Pelaaja pelaaja1;
    Pelaaja pelaaja2;
    Alusta alusta;
    Ratkaisija ratkaisija;

    /**
     * @param alusta Aloitettava pelilauta
     * @param ratkaisija Ratkaisija pelitilanteen päättymisen tarkastamista varten.
     * @param pelaaja1 1. Pelaaja
     * @param pelaaja2 2. Pelaaja
     */
    public Peli(Alusta alusta, Ratkaisija ratkaisija, Pelaaja pelaaja1, Pelaaja pelaaja2) {
        this.alusta = alusta;
        this.ratkaisija = ratkaisija;
        this.pelaaja1 = pelaaja1;
        this.pelaaja2 = pelaaja2;
    }

    /**
     * Käynnistää pelin ja kysyy vuorotellen pelaajilta siirtoja kunnes peli on päättynyt, jolloin voittoilmoitus annetaan toiselle pelaajalle.
     */
    public void aloita() {
        alusta.tyhjaaLauta();
        int ratkasu = 0;


        do {
            alusta = pelaaja1.seuraavaSiirto(alusta, 'x');
            ratkasu = ratkaisija.etsiVoitto(alusta);
            if (ratkasu == 0 && !alusta.onkoLautaTaynna()) {
                alusta = pelaaja2.seuraavaSiirto(alusta, 'o');
            }
            ratkasu = ratkaisija.etsiVoitto(alusta);
        } while (ratkasu == 0 && !alusta.onkoLautaTaynna());

        Arvioija arv = new Arvioija();
        arv.arvioiAlusta(alusta);

        if (ratkasu > 0) {
            pelaaja1.ilmoitaVoitto('X');
        } else if (ratkasu < 0) {
            pelaaja2.ilmoitaVoitto('O');
        } else {
            pelaaja1.ilmoitaVoitto('t');
        }
        System.exit(0);

    }
}

package tekoaly;

import kayttoliittyma.Kayttoliittyma;
import logiikka.Alusta;
import logiikka.Pelaaja;

/**
 * Tekoäly luokka joka hallinnoi tekoälyyn liittyviä luokki
 */
public class Tekoaly implements Pelaaja {

    private MinMax minmax;
    private Puukasaaja kasaaja;
    private Kayttoliittyma ui;

    /**
     * @param minmax MinMax luoka
     * @param kasaaja Pelipuun kasaajaluokka
     * @param ui Käyttöliittymä tulostusta varten.
     */
    public Tekoaly(MinMax minmax, Puukasaaja kasaaja, Kayttoliittyma ui) {
        this.minmax = minmax;
        this.kasaaja = kasaaja;
        this.ui = ui;
    }

    /**
     * Kysytään seuraava siirto
     * Ensin kasataan puu nykyisestä tilanteesta, minkä jälkeen puu annetaan minmaxille, mikä laskee parhaimman siirron
     * @param alusta Nykyinen pelitilanne
     * @param merkki Laitettava merkki
     * @return Tehty siirto
     */
    public Alusta seuraavaSiirto(Alusta alusta, char merkki) {

        Puu puu;
        Alusta siirto;

        ui.tulostaAlusta(alusta);
        puu = kasaaja.kasaaPuu(alusta, merkki, 4);
        if (merkki == 'x') {
            siirto = minmax.aloitaMax(puu);
        } else {
            siirto = minmax.aloitaMin(puu);

        }
        ui.tulostaAlusta(siirto);
        return siirto;
    }

    public void ilmoitaVoitto(char merkki) {
        ui.tulostaVoitto(merkki);
    }
}

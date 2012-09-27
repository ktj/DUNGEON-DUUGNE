package tekoaly;

import kayttoliittyma.Kayttoliittyma;
import logiikka.Alusta;
import logiikka.Pelaaja;

public class Tekoaly implements Pelaaja {

    private MinMax minmax;
    private Puukasaaja kasaaja;
    private Kayttoliittyma ui;

    public Tekoaly(MinMax minmax, Puukasaaja kasaaja, Kayttoliittyma ui) {
        this.minmax = minmax;
        this.kasaaja = kasaaja;
        this.ui = ui;
    }

    public Alusta seuraava_siirto(Alusta alusta, char merkki) {

        Puu puu;
        Alusta siirto;

        ui.tulosta_alusta(alusta);
        puu = kasaaja.kasaaPuu(alusta, merkki, 4);
        if (merkki == 'x') {
            siirto = minmax.aloitaMax(puu);
        }
        siirto = minmax.aloitaMin(puu);
        ui.tulosta_alusta(siirto);
        return siirto;
    }
}

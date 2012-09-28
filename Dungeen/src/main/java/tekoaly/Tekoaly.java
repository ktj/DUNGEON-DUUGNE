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

    public Alusta seuraavaSiirto(Alusta alusta, char merkki) {

        Puu puu;
        Alusta siirto;

        ui.tulostaAlusta(alusta);
        puu = kasaaja.kasaaPuu(alusta, merkki, 3);
        if (merkki == 'x') {
            siirto = minmax.aloitaMax(puu);
        } else {
            siirto = minmax.aloitaMin(puu);

        }
        ui.tulostaAlusta(siirto);
        System.out.println(siirto.getArvio());
        return siirto;
    }

    public void ilmoitaVoitto(char merkki) {
        ui.tulostaVoitto(merkki);
    }
}

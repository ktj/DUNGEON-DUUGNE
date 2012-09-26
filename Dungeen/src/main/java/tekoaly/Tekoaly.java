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
        
        System.out.println(Runtime.getRuntime().freeMemory());
        if (Runtime.getRuntime().freeMemory() < 100000000) {
            puu = kasaaja.kasaaPuu(alusta, merkki, 2);
        } else {
            puu = kasaaja.kasaaPuu(alusta, merkki, 4);
        }
        if (merkki == 'x') {
            siirto = minmax.aloitaMax(puu);
        }
        siirto =  minmax.aloitaMin(puu);
        ui.tulosta_alusta(siirto);
        return siirto;
    }
}

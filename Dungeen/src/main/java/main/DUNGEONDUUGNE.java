package main;

import kayttoliittyma.Tekstikayttoliittyma;
import logiikka.Alusta;
import logiikka.Ihmispelaaja;
import logiikka.Pelaaja;
import logiikka.Ratkaisija;
import tekoaly.MinMax;
import tekoaly.Puu;
import tekoaly.Puukasaaja;
import tekoaly.Tekoaly;

public class DUNGEONDUUGNE {

    public static void main(String[] args) {

        Alusta alusta = new Alusta(10, 5);
        Ratkaisija ratkaisija = new Ratkaisija();
        Tekstikayttoliittyma tekstikäli = new Tekstikayttoliittyma();
        Pelaaja pelaaja1 = new Ihmispelaaja(tekstikäli);
        Pelaaja pelaaja2 = new Tekoaly(new MinMax(ratkaisija), new Puukasaaja());

        Peli peli = new Peli(alusta, ratkaisija, pelaaja2, pelaaja1);
        peli.aloita();


        

//        
//        alusta.lisaaMerkkiLaudalle(0, 0, 'x');
//        alusta.lisaaMerkkiLaudalle(1, 0, 'o');
//        alusta.lisaaMerkkiLaudalle(0, 1, 'x');
//        alusta.lisaaMerkkiLaudalle(1, 1, 'o');

//        pelaaja2.seuraava_siirto(alusta, 'x').tulostaAlusta();
        
        
//        Puukasaaja kasaaja = new Puukasaaja();
//        Puu puu = kasaaja.kasaaPuu(new Alusta(3,3), 'o', 2);
//        tulostaPuu(puu);
//        
//        alusta.tulostaAlusta();
//        
//        MinMax minmax = new MinMax(new Ratkaisija());
//        
//        minmax.aloitaMin(puu, 3).tulostaAlusta();

    }

    private static void tulostaPuu(Puu puu) {
        puu.haeAlusta().tulostaAlusta();
        for (Puu lapsi : puu.haeLapset()) {
            lapsi.haeAlusta().tulostaAlusta();
            tulostaPuu(lapsi);
        }
    }
}

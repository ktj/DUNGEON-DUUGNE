package main;

import käyttöliittymä.Tekstikäyttöliittymä;
import logiikka.Alusta;
import logiikka.Ihmispelaaja;
import logiikka.Pelaaja;
import logiikka.Ratkaisija;
import tekoäly.MinMax;
import tekoäly.Puu;
import tekoäly.Puukasaaja;
import tekoäly.Tekoäly;

public class DUNGEONDUUGNE {

    public static void main(String[] args) {

        Alusta alusta = new Alusta(3,3);
        Ratkaisija ratkaisija = new Ratkaisija();
        Tekstikäyttöliittymä tekstikäli = new Tekstikäyttöliittymä();
        Pelaaja pelaaja1 = new Ihmispelaaja(tekstikäli);
        Pelaaja pelaaja2 = new Tekoäly(new MinMax(ratkaisija), new Puukasaaja());

        Peli peli = new Peli(alusta, ratkaisija, pelaaja2, pelaaja2);
        peli.aloita();


        

//        
//        alusta.lisaaMerkkiLaudalle(0, 0, 'x');
//        alusta.lisaaMerkkiLaudalle(1, 0, 'o');
//        alusta.lisaaMerkkiLaudalle(0, 1, 'x');
//        alusta.lisaaMerkkiLaudalle(1, 1, 'o');

//        pelaaja2.seuraava_siirto(alusta, 'x').tulostaAlusta();
        
        
//        Puukasaaja kasaaja = new Puukasaaja();
//        Puu puu = kasaaja.kasaaPuu(new Alusta(3), 'o', 2);
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

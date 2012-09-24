package main;

import kayttoliittyma.Tekstikayttoliittyma;
import logiikka.Alusta;
import logiikka.Ihmispelaaja;
import logiikka.Pelaaja;
import logiikka.Ratkaisija;
import tekoaly.*;

public class DUNGEONDUUGNE {

    public static void main(String[] args) {

        Alusta alusta = new Alusta(5, 5);
        Ratkaisija ratkaisija = new Ratkaisija();
        Tekstikayttoliittyma tekstikäli = new Tekstikayttoliittyma();
        Pelaaja pelaaja1 = new Ihmispelaaja(tekstikäli);
        Pelaaja pelaaja2 = new Tekoaly(new MinMax(ratkaisija,new Arvioija()), new Puukasaaja(new Arvioija()));

        Peli peli = new Peli(alusta, ratkaisija, pelaaja2, pelaaja2);
        peli.aloita();




//
//        alusta.lisaaMerkkiLaudalle(1, 0, 'x');
//        alusta.lisaaMerkkiLaudalle(2, 0, 'x');
//        alusta.lisaaMerkkiLaudalle(2, 1, 'x');
//        alusta.lisaaMerkkiLaudalle(0, 1, 'o');
//        alusta.lisaaMerkkiLaudalle(1, 1, 'o');
//        alusta.lisaaMerkkiLaudalle(1, 2, 'o');
//        
       
        
//        Arvioija arvioija = new Arvioija();
//        System.out.println(arvioija.arvioiAlusta(alusta));
//
//        alusta.lisaaMerkkiLaudalle(2, 2, 'o');
//        System.out.println(arvioija.arvioiAlusta(alusta));
        
//        pelaaja2.seuraava_siirto(alusta, 'x').tulostaAlusta();

//
//        Puukasaaja kasaaja = new Puukasaaja(new Arvioija());
//        Puu puu = kasaaja.kasaaPuu(alusta, 'o', 100);
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
        System.out.println("-------");
        for (Puu lapsi : puu.haeLapset()) {
            lapsi.haeAlusta().tulostaAlusta();
            tulostaPuu(lapsi);
        }
        System.out.println("------");
    }
}

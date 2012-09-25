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
        Pelaaja pelaaja2 = new Tekoaly(new MinMax(ratkaisija, new Arvioija()), new Puukasaaja(new Arvioija()));
        
        Peli peli = new Peli(alusta, ratkaisija, pelaaja2, pelaaja2);
        peli.aloita();




////
        alusta.lisaaMerkkiLaudalle(1, 0, 'x');
        alusta.lisaaMerkkiLaudalle(2, 0, 'x');
//        alusta.lisaaMerkkiLaudalle(2, 1, 'x');
//        alusta.lisaaMerkkiLaudalle(0, 1, 'o');
        alusta.lisaaMerkkiLaudalle(1, 1, 'o');
        alusta.lisaaMerkkiLaudalle(1, 2, 'o');
//        
//
//
////        Arvioija arvioija = new Arvioija();
////        System.out.println(arvioija.arvioiAlusta(alusta));
////
////        alusta.lisaaMerkkiLaudalle(2, 2, 'o');
////        System.out.println(arvioija.arvioiAlusta(alusta));
//
////        pelaaja2.seuraava_siirto(alusta, 'x').tulostaAlusta();
//
//        alusta.tulostaAlusta();
//        Puukasaaja kasaaja = new Puukasaaja(new Arvioija());
//        Puu puu = kasaaja.kasaaPuu(alusta, 'o', 100);
////        tulostaPuu(puu);
////        
////        alusta.tulostaAlusta();
////        
//        MinMax minmax = new MinMax(new Ratkaisija(), new Arvioija());
//        
//        Alusta alusta2 = minmax.aloitaMin(puu);
//        alusta2.tulostaAlusta();
//        
//        puu = kasaaja.kasaaPuu(alusta2, 'x', 100);
//        minmax.aloitaMax(puu).tulostaAlusta();
        
//        Joukko joukko = new Joukko(10);
//        
//        Alusta alusta2 = new Alusta(alusta);
//        alusta2.lisaaMerkkiLaudalle(2, 1, 'x');
//        
//        joukko.add(new Puu(alusta2));
//        
//        for(Puu lapsi : joukko){
//            System.out.println("asd");
//            //lapsi.haeAlusta().tulostaAlusta();
//        }
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

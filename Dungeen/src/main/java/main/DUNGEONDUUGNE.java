package main;

import kayttoliittyma.Graafinenkayttoliittyma;
import logiikka.Alusta;
import logiikka.Ihmispelaaja;
import logiikka.Pelaaja;
import logiikka.Ratkaisija;
import tekoaly.*;

public class DUNGEONDUUGNE {

    public static void main(String[] args) {
        Graafinenkayttoliittyma gui = new Graafinenkayttoliittyma();
        Pelaaja pelaaja1, pelaaja2 = null;


        gui.luo();
        int[] asetukset = gui.luoAsetukset();

        if (asetukset[2] == 0) {
            pelaaja1 = new Ihmispelaaja(gui);
        } else {
            pelaaja1 = new Tekoaly(new MinMax(new Ratkaisija()), new Puukasaaja(new Arvioija()), gui);
        }
        
        if (asetukset[3] == 0) {
            pelaaja2 = new Ihmispelaaja(gui);
        } else {
            pelaaja2 = new Tekoaly(new MinMax(new Ratkaisija()), new Puukasaaja(new Arvioija()), gui);
        }

        Peli peli = new Peli(new Alusta(asetukset[0], asetukset[1]), new Ratkaisija(), pelaaja1, pelaaja2);
        peli.aloita();
    }

    private static void tulostaPuu(Puu puu) {
        puu.haeAlusta().tulostaAlusta();
        System.out.println("-------");
        for (Puu lapsi : puu.haeLapset()) {
            System.out.println("***");
            lapsi.haeAlusta().tulostaAlusta();
            System.out.println(lapsi.haeAlusta().getArvio());
            System.out.println("***");
            tulostaPuu(lapsi);
        }
        System.out.println("------");
    }
}

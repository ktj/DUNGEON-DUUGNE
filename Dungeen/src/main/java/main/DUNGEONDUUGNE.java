package main;

import kayttoliittyma.Graafinenkayttoliittyma;
import kayttoliittyma.Tekstikayttoliittyma;
import logiikka.Alusta;
import logiikka.Ihmispelaaja;
import logiikka.Pelaaja;
import logiikka.Ratkaisija;
import tekoaly.*;

public class DUNGEONDUUGNE {

    public static void main(String[] args) {

        Alusta alusta = new Alusta(10, 5);
        Ratkaisija ratkaisija = new Ratkaisija();
        Graafinenkayttoliittyma gui = new Graafinenkayttoliittyma();
        Tekstikayttoliittyma tekstikäli = new Tekstikayttoliittyma();
        Pelaaja pelaaja1 = new Ihmispelaaja(gui);
        Pelaaja pelaaja2 = new Tekoaly(new MinMax(ratkaisija), new Puukasaaja(new Arvioija()), gui);

        Peli peli = new Peli(alusta, ratkaisija, pelaaja2, pelaaja1);
        peli.aloita();




        alusta.lisaaMerkkiLaudalle(1, 1, 'o');
        alusta.lisaaMerkkiLaudalle(2, 2, 'o');
        alusta.lisaaMerkkiLaudalle(3, 3, 'o');
        alusta.lisaaMerkkiLaudalle(0, 0, 'o');
        alusta.lisaaMerkkiLaudalle(4, 4, 'x');
        alusta.lisaaMerkkiLaudalle(3, 5, 'x');
        alusta.lisaaMerkkiLaudalle(3, 6, 'x');
        alusta.lisaaMerkkiLaudalle(3, 7, 'x');
        alusta.lisaaMerkkiLaudalle(4, 5, 'x');
        alusta.lisaaMerkkiLaudalle(5, 6, 'x');
//        alusta.lisaaMerkkiLaudalle(5, 5, 'o');
//        alusta.lisaaMerkkiLaudalle(6, 7, 'o');
//        System.out.println(alusta.onkoTyhja(5, 5));
//        System.out.println(alusta.lueMerkki(5, 5));
//        System.out.println("asd ("+alusta.lueMerkki(-1, 4) + ")");
//        System.out.println(alusta.tarkistaKoordinaatit(-1, 4));
        
        
        alusta.tulostaAlusta();


//        //alusta.lisaaMerkkiLaudalle(3, 4, 'o');
//        //alusta.lisaaMerkkiLaudalle(5, 9, 'o');
//        Puukasaaja puukasaaja = new Puukasaaja(new Arvioija());
//        Puu puu;
//        tulostaPuu(puukasaaja.kasaaPuu(alusta, 'x', 1));






//        alusta.lisaaMerkkiLaudalle(0, 0, 'x');
//        alusta.lisaaMerkkiLaudalle(0, 1, 'o');
//        alusta.tulostaAlusta();


////
//        alusta.lisaaMerkkiLaudalle(5, 5, 'x');
//        alusta.lisaaMerkkiLaudalle(6, 6, 'x');
//        alusta.lisaaMerkkiLaudalle(7, 7, 'x');
//        alusta.lisaaMerkkiLaudalle(8, 8, 'x');
//        alusta.lisaaMerkkiLaudalle(4, 4, 'o');
//        alusta.lisaaMerkkiLaudalle(3, 5, 'o');
//        alusta.lisaaMerkkiLaudalle(2, 6, 'o');

//        gui.luo(alusta);
//        alusta.tulostaAlusta();
//      



//
//
        Arvioija arvioija = new Arvioija();
        arvioija.arvioiAlusta(alusta);
        System.out.println(alusta.getArvio());
//
//        alusta.lisaaMerkkiLaudalle(3, 8, 'o');
//
//        arvioija.arvioiAlusta(alusta);
//        alusta.tulostaAlusta();
//        System.out.println(alusta.getArvio());
//        


//        Puukasaaja kasaaja = new Puukasaaja(new Arvioija());
//        Puu puu = kasaaja.kasaaPuu(alusta, 'o', 1);
//        tulostaPuu(puu);



//        MinMax minmax = new MinMax(new Ratkaisija(), new Arvioija());

//        Alusta alusta2 = minmax.aloitaMin(puu);
//        alusta2.tulostaAlusta();
//
//        alusta.lisaaMerkkiLaudalle(2, 2, 'x');
//        System.out.println(arvioija.arvioiAlusta(alusta));


//        alusta.lisaaMerkkiLaudalle(9, 9, 'x');
//        alusta.tulostaAlusta();
//        System.out.println(arvioija.arvioiAlusta(alusta));
////
//////        pelaaja2.seuraava_siirto(alusta, 'x').tulostaAlusta();
////
////        alusta.tulostaAlusta();



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
            System.out.println("***");
            lapsi.haeAlusta().tulostaAlusta();
            System.out.println(lapsi.haeAlusta().getArvio());
            System.out.println("***");
            tulostaPuu(lapsi);
        }
        System.out.println("------");
    }
}

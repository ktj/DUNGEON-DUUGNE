package main;

import logiikka.Alusta;
import logiikka.Pelaaja;
import logiikka.Ratkaisija;

public class Peli {

    Pelaaja pelaaja1;
    Pelaaja pelaaja2;
    Alusta alusta;
    Ratkaisija ratkaisija;

    public Peli(Alusta alusta, Ratkaisija ratkaisija, Pelaaja pelaaja1, Pelaaja pelaaja2) {
        this.alusta = alusta;
        this.ratkaisija = ratkaisija;
        this.pelaaja1 = pelaaja1;
        this.pelaaja2 = pelaaja2;
    }

    public void aloita() {
        alusta.tyhjaaLauta();
        while (ratkaisija.etsiVoitto(alusta) == 0 && !alusta.onkoLautaTaynna()) {
            alusta = pelaaja1.seuraava_siirto(alusta, 'x');
            if (ratkaisija.etsiVoitto(alusta) == 0 && !alusta.onkoLautaTaynna()) {
                alusta = pelaaja2.seuraava_siirto(alusta, 'o');
            }
        }

        alusta.tulostaAlusta();
        System.out.println("");
        if (ratkaisija.etsiVoitto(alusta) > 0) {
            System.out.println("Pelin voitti x!");
        } else if (ratkaisija.etsiVoitto(alusta) < 0) {
            System.out.println("Pelin voitti o!");
        } else {
            System.out.println("Tasapeli!");
        }

    }
}

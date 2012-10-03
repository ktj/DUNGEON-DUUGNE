package main;

import logiikka.Alusta;
import logiikka.Pelaaja;
import logiikka.Ratkaisija;
import tekoaly.Arvioija;

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
        int ratkasu = 0;
        do {
            alusta = pelaaja1.seuraavaSiirto(alusta, 'x');
            ratkasu = ratkaisija.etsiVoitto(alusta);
            if (ratkasu == 0 && !alusta.onkoLautaTaynna()) {
                alusta = pelaaja2.seuraavaSiirto(alusta, 'o');
            }
            ratkasu = ratkaisija.etsiVoitto(alusta);
        } while (ratkasu == 0 && !alusta.onkoLautaTaynna());
        
        Arvioija arv = new Arvioija();
        arv.arvioiAlusta(alusta);
        System.out.println("Pelin loppu! " + alusta.getArvio());
        
        if (ratkasu > 0) {
            pelaaja1.ilmoitaVoitto('X');
        } else if (ratkasu < 0) {
            pelaaja2.ilmoitaVoitto('O');
        } else {
            pelaaja1.ilmoitaVoitto('t');
        }

    }
}

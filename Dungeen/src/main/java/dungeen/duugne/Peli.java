package dungeen.duugne;

import dungeen.peli.Alusta;
import dungeen.peli.Pelaaja;
import dungeen.peli.Ratkaisija;

public class Peli {

    final int RIVINPITUUS = 3;
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
        while (ratkaisija.etsiVoitto(alusta, RIVINPITUUS)==0) {
            pelaaja1.seuraava_siirto(alusta, 'x');
            if (ratkaisija.etsiVoitto(alusta, RIVINPITUUS)==0) {
                pelaaja2.seuraava_siirto(alusta, 'o');
            }
        }
        
    }
}

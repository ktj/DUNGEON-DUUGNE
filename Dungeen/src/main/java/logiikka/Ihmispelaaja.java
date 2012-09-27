package logiikka;

import kayttoliittyma.Kayttoliittyma;

public class Ihmispelaaja implements Pelaaja {

    Kayttoliittyma UI;

    public Ihmispelaaja(Kayttoliittyma UI) {
        this.UI = UI;
    }

    @Override
    public Alusta seuraavaSiirto(Alusta alusta, char merkki) {
        UI.tulostaAlusta(alusta);
        Alusta pal = UI.kysySiirto(alusta, merkki);
        UI.tulostaAlusta(pal);
        return pal;
    }

    public void ilmoitaVoitto(char merkki) {
        UI.tulostaVoitto(merkki);
    }
}

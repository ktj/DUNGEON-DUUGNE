package logiikka;

import kayttoliittyma.Kayttoliittyma;

public class Ihmispelaaja implements Pelaaja {

    Kayttoliittyma UI;

    public Ihmispelaaja(Kayttoliittyma UI) {
        this.UI = UI;
    }

    @Override
    public Alusta seuraava_siirto(Alusta alusta, char merkki) {
        UI.tulosta_alusta(alusta);
        Alusta pal = UI.kysy_siirto(alusta, merkki);
        UI.tulosta_alusta(pal);
        return pal;
    }
}

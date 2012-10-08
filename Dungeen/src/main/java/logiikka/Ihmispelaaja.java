package logiikka;

import kayttoliittyma.Kayttoliittyma;

/**
 * Ihmispelaaja, eli pelaaja, jonka siirrot kysytään käyttäjältä.
 */
public class Ihmispelaaja implements Pelaaja {

    Kayttoliittyma UI;

    /**
     * Ihmispelaaja
     * @param UI käytettävä käyttöliittymä
     */
    public Ihmispelaaja(Kayttoliittyma UI) {
        this.UI = UI;
    }

    /**
     * Kysytään käyttäjältä seuraava siirto tulostamalla nykyinen tillanne ja odottamalla siirto.
     * @param alusta Nykyinen pelitilanna
     * @param merkki Pelaajan merkki
     * @return Tehty siirto
     */
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

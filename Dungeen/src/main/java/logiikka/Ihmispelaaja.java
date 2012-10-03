package logiikka;

import kayttoliittyma.Kayttoliittyma;
import tekoaly.Arvioija;

public class Ihmispelaaja implements Pelaaja {

    Kayttoliittyma UI;

    public Ihmispelaaja(Kayttoliittyma UI) {
        this.UI = UI;
    }

    @Override
    public Alusta seuraavaSiirto(Alusta alusta, char merkki) {
        UI.tulostaAlusta(alusta);
        Arvioija arv = new Arvioija();
        arv.arvioiAlusta(alusta);
        System.out.println("pelaaja arvio_ " + alusta.getArvio());
        Alusta pal = UI.kysySiirto(alusta, merkki);
        UI.tulostaAlusta(pal);
        return pal;
    }

    public void ilmoitaVoitto(char merkki) {
        UI.tulostaVoitto(merkki);
    }
}

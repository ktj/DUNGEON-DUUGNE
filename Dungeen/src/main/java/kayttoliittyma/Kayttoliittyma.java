package kayttoliittyma;

import logiikka.Alusta;

public interface Kayttoliittyma {

    public void tulostaAlusta(Alusta alusta);
    public void tulostaVoitto(char merkki);
    public Alusta kysySiirto(Alusta alusta, char merkki);
}

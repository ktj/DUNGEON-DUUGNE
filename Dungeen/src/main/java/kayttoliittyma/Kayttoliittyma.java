package kayttoliittyma;

import logiikka.Alusta;

public interface Kayttoliittyma {

    public void tulosta_alusta(Alusta alusta);

    public Alusta kysy_siirto(Alusta alusta, char merkki);
}

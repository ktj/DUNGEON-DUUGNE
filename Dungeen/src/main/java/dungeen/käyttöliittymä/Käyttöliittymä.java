package dungeen.käyttöliittymä;

import dungeen.peli.Alusta;

public interface Käyttöliittymä {

    public void tulosta_alusta(Alusta alusta);

    public Alusta kysy_siirto(Alusta alusta, char merkki);
}

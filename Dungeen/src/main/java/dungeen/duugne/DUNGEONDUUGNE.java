package dungeen.duugne;

import dungeen.käyttöliittymä.Tekstikäyttöliittymä;
import dungeen.peli.Alusta;
import dungeen.peli.Ihmispelaaja;
import dungeen.peli.Ratkaisija;

public class DUNGEONDUUGNE {

    public static void main(String[] args) {
        
        Alusta alusta = new Alusta(3);
        Ratkaisija ratkaisija = new Ratkaisija();
        Tekstikäyttöliittymä tekstikäli = new Tekstikäyttöliittymä();
        Ihmispelaaja pelaaja1 = new Ihmispelaaja(tekstikäli);
        Ihmispelaaja pelaaja2 = new Ihmispelaaja(tekstikäli);
        
        Peli peli = new Peli(alusta, ratkaisija, pelaaja1, pelaaja2);
        peli.aloita();
    }
}

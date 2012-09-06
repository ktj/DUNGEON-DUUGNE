package dungeon.duugne;

import dungeon.käyttöliittymä.Tekstikäyttöliittymä;
import dungeon.peli.Alusta;
import dungeon.peli.Ihmispelaaja;
import dungeon.peli.Ratkaisija;

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

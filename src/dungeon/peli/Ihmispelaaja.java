package dungeon.peli;

import dungeon.käyttöliittymä.Käyttöliittymä;

public class Ihmispelaaja implements Pelaaja {

    Käyttöliittymä UI;
    
    public Ihmispelaaja(Käyttöliittymä UI){
        this.UI = UI;
    }
        
    @Override
    public Alusta seuraava_siirto(Alusta alusta, char merkki) {
        UI.tulosta_alusta(alusta);
        return UI.kysy_siirto(alusta, merkki);
    }
    
}

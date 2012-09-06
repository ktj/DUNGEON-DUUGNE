package dungeon.duugne;

import dungeon.peli.Alusta;
import dungeon.peli.Pelaaja;
import dungeon.peli.Ratkaisija;

public class Peli {
    
    final int RIVINPITUUS = 3;
    
    Pelaaja pelaaja1;
    Pelaaja pelaaja2;
    Alusta alusta;
    Ratkaisija ratkaisija;
    
    public Peli(Alusta alusta, Ratkaisija ratkaisija, Pelaaja pelaaja1, Pelaaja pelaaja2){
        this.alusta = alusta;
        this.ratkaisija = ratkaisija;
        this.pelaaja1 = pelaaja1;
        this.pelaaja2 = pelaaja2;
    }
    
    public void aloita(){
        alusta.tyhjaaLauta();
        while(ratkaisija.etsiVoitto(alusta, RIVINPITUUS)) {
            pelaaja1.seuraava_siirto(alusta, 'x');
            pelaaja2.seuraava_siirto(alusta, 'o');
        }
    }
}

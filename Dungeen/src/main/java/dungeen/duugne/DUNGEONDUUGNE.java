package dungeen.duugne;

import dungeen.peli.Alusta;
import tekoäly.Puu;
import tekoäly.Puukasaaja;

public class DUNGEONDUUGNE {

    public static void main(String[] args) {

        Alusta alusta = new Alusta(3);
//        Ratkaisija ratkaisija = new Ratkaisija();
//        Tekstikäyttöliittymä tekstikäli = new Tekstikäyttöliittymä();
//        Ihmispelaaja pelaaja1 = new Ihmispelaaja(tekstikäli);
//        Ihmispelaaja pelaaja2 = new Ihmispelaaja(tekstikäli);
//        
//        Peli peli = new Peli(alusta, ratkaisija, pelaaja1, pelaaja2);
//        peli.aloita();


        Puukasaaja kasaaja = new Puukasaaja();
        
        Puu puu1 = new Puu(alusta);

        
        Puu puu = kasaaja.kasaaPuu(alusta, 'x');
        tulostaPuu(puu);
    }
    
    private static void tulostaPuu(Puu puu){
        puu.haeAlusta().tulostaAlusta();
        for(Puu lapsi: puu.haeLapset()){
            lapsi.haeAlusta().tulostaAlusta();
        }
    }
}

package dungeen.duugne;

import dungeen.peli.Alusta;
import dungeen.peli.Ratkaisija;
import tekoäly.MinMax;
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
        
        alusta.lisaaMerkkiLaudalle(0, 0, 'x');
        alusta.lisaaMerkkiLaudalle(1, 0, 'o');
        alusta.lisaaMerkkiLaudalle(0, 1, 'x');

        alusta.lisaaMerkkiLaudalle(1, 1, 'o');
        
        Puu puu = kasaaja.kasaaPuu(alusta, 'o');
        
        //tulostaPuu(puu);
        
        alusta.tulostaAlusta();
        
        MinMax minmax = new MinMax(new Ratkaisija());
        
        minmax.aloitaMin(puu, 3).tulostaAlusta();
        
    }
    
    private static void tulostaPuu(Puu puu){
        puu.haeAlusta().tulostaAlusta();
        for(Puu lapsi: puu.haeLapset()){
            lapsi.haeAlusta().tulostaAlusta();
            tulostaPuu(lapsi);
        }
    }
}

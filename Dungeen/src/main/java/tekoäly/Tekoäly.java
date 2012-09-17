package tekoäly;

import dungeen.peli.Alusta;
import dungeen.peli.Pelaaja;

public class Tekoäly implements Pelaaja{
    
    private MinMax minmax;
    private Puukasaaja kasaaja;
    
    public Tekoäly(MinMax minmax, Puukasaaja kasaaja){
        this.minmax = minmax;
        this.kasaaja = kasaaja;
    }

    public Alusta seuraava_siirto(Alusta alusta, char merkki) {
        
        Puu puu = kasaaja.kasaaPuu(alusta, merkki, 5);
        
        if(merkki == 'x'){
            return minmax.aloitaMax(puu, 3);
        }
        return minmax.aloitaMin(puu, 3);
    }
    
}

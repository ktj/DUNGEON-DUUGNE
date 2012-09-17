package tekoäly;

import logiikka.Alusta;
import logiikka.Pelaaja;

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
            return minmax.aloitaMax(puu);
        }
        return minmax.aloitaMin(puu);
    }
    
}

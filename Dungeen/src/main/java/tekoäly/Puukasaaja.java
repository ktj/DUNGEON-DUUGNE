package tekoäly;

import dungeen.peli.Alusta;

public class Puukasaaja {


    public Puukasaaja() {
    }
    

    public Puu kasaaPuu(Alusta alusta, char merkki){
        Puu puu = new Puu(alusta);
        return kasaaLapset(puu, merkki);
    }
    
    private Puu kasaaLapset(Puu parent, char merkki) {
        etsiVaihtoehdot(parent, merkki);
        for(Puu lapsi : parent.haeLapset()){
            kasaaLapset(lapsi, merkki);
        } 
        return parent;
    }

    private void etsiVaihtoehdot(Puu parent, char merkki) {
        Alusta alusta = parent.haeAlusta();
        for (int i = 0; i < alusta.maxKoko(); i++) {
            for (int j = 0; j < alusta.maxKoko(); j++) {
                Alusta alustaklooni = new Alusta(alusta);
                if (alustaklooni.onkoTyhja(i, j)) {
                    alustaklooni.lisaaMerkkiLaudalle(i, j, merkki);
                    parent.lisaaLapsi(new Puu(alustaklooni));
                }
            }
        }
    }
}

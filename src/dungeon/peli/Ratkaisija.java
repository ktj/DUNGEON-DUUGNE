/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeon.peli;

/**
 *
 * @author julius
 */
public class Ratkaisija {

    int voittoRivinPituus;
    Alusta alusta;

    public Ratkaisija(int voittoRivinPituus) {
        this.voittoRivinPituus = voittoRivinPituus;
    }

    public boolean etsiVoitto(Alusta alusta){
        this.alusta=alusta;
        for(int i = 0; i<this.alusta.maxKoko();i++){
            for(int j=0;j<this.alusta.maxKoko();j++){
                if(tarkistaRuutu(i, j))
                    return true;
            }
        }
        return false;
    }
    
    public boolean tarkistaRuutu(int x, int y){
        char merkki = alusta.lueMerkki(x, y);
        if((tarkistaVasemmalle(x, y, merkki) + 1 + tarkistaOikealle(x, y, merkki))>=this.voittoRivinPituus)
            return true;
        if((tarkistaAlas(x, y, merkki)+ 1 + tarkistaYlos(x, y, merkki))>=this.voittoRivinPituus)
            return true;
        return false;
    }
    
    boolean tarkistaKoordinaatit(int x, int y) {
        if (x < 0 || y < 0) {
            return false;
        }
        if (x >= this.alusta.maxKoko() || y >= this.alusta.maxKoko()) {
            return false;
        }
        return true;
    }

    int tarkistaVasemmalle(int x, int y, char merkki) {
        if (!tarkistaKoordinaatit((x - 1), y)) {
            return 0;
        }
        if ((this.alusta.lueMerkki(x - 1, y)) == merkki) {
            return 1 + tarkistaVasemmalle(x - 1, y, merkki);
        }
        return 0;
    }

    int tarkistaOikealle(int x, int y, char merkki) {
        if (!tarkistaKoordinaatit(x + 1, y)) {
            return 0;
        }
        if ((this.alusta.lueMerkki(x + 1, y)) == merkki) {
            return 1 + tarkistaOikealle(x + 1, y, merkki);
        }
        return 0;
    }

    int tarkistaAlas(int x, int y, char merkki) {
        if (!tarkistaKoordinaatit(x, y + 1)) {
            return 0;
        }
        if ((this.alusta.lueMerkki(x, y + 1)) == merkki) {
            return 1 + tarkistaAlas(x, y + 1, merkki);
        }
        return 0;
    }

    int tarkistaYlos(int x, int y, char merkki) {
        if (!tarkistaKoordinaatit(x, y - 1)) {
            return 0;
        }
        if ((this.alusta.lueMerkki(x, y - 1)) == merkki) {
            return 1 + tarkistaYlos(x, y - 1, merkki);
        }
        return 0;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tekoäly;

import dungeen.peli.Alusta;

/**
 *
 * @author asdero
 */
public class Arvioija {

    int voittoRivinPituus;
    Alusta alusta;

    public Arvioija() {
    }
    //- o:lla hyvae, + x:lle hyvae

    public int arvioiLauta(Alusta alusta, int voittoRivinPituus) {
        this.alusta = alusta;
        this.voittoRivinPituus = voittoRivinPituus;
        int arvio = 0;
        for (int i = 0; i < this.alusta.maxKoko(); i++) {
            for (int j = 0; j < this.alusta.maxKoko(); j++) {
                if (!this.alusta.onkoTyhja(i, j)) {
                    arvio = arvio + arvioiRuudunYmparys(i, j);
                }
            }
        }
        return arvio;
    }

    int arvioiRuudunYmparys(int x, int y) {
        char merkki = this.alusta.lueMerkki(x, y);
        int arvio = 0;
        arvio = arvioiRuutu(x - 1, y, merkki)
                + arvioiRuutu(x + 1, y, merkki)
                + arvioiRuutu(x, y - 1, merkki)
                + arvioiRuutu(x, y + 1, merkki)
                + arvioiRuutu(x + 1, y + 1, merkki)
                + arvioiRuutu(x - 1, y - 1, merkki)
                + arvioiRuutu(x - 1, y + 1, merkki)
                + arvioiRuutu(x + 1, y - 1, merkki);
        return arvio;
    }

    int arvioiRuutu(int x, int y, char merkki) {
        if (this.alusta.tarkistaKoordinaatit(x, y)) {
            if (this.alusta.lueMerkki(x, y) == 'x' && merkki == 'x') {
                return 2;
            }
            if (this.alusta.lueMerkki(x, y) == 'o' && merkki == 'o') {
                return -2;
            }
            if (this.alusta.onkoTyhja(x, y)) {
                //tyhjä x:n vieressä
                if (merkki == 'x') {
                    return 1;
                //tyhjä o:n vieressä;
                } else {
                    return -1;
                }
            }
        }
        return 0;

    }
}

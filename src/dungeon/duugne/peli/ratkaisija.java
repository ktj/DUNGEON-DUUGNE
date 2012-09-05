/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeon.duugne.peli;

/**
 *
 * @author julius
 */
public class ratkaisija {

    int voittoRivinPituus;
    alusta alusta;

    ratkaisija(int voittoRivinPituus, alusta alusta) {
        this.voittoRivinPituus = voittoRivinPituus;
        this.alusta = alusta;
    }

    boolean tarkistaKoordinaatit(int x, int y) {
        if (x < 0 && y < 0) {
            return false;
        }
        if (x > this.alusta.maxKoko() && y > this.alusta.maxKoko()) {
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

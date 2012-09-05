/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeon.duugne.peli;

/**
 *
 * @author ;GGGG
 */
public class alusta {

    int koko;
    char[][] lauta;

    alusta(int koko) {
        this.koko = koko;
        this.lauta = new char[this.koko][this.koko];
    }

    void tyhjaaLauta() {
        for (int i = 0; i < this.koko; i++) {
            for (int j = 0; j < this.koko; j++) {
                this.lauta[i][j] = ' ';
            }
        }
    }

    boolean onkoTyhja(int x, int y) {
        if (this.lauta[x][y] == ' ') {
            return true;
        }
        return false;
    }

    void lisaaMerkkiLaudalle(int x, int y, char merkki) {
        this.lauta[x][y] = merkki;
    }

    char lueMerkki(int x, int y) {
        return this.lauta[x][y];
    }

    int maxKoko() {
        return this.koko - 1;
    }
}

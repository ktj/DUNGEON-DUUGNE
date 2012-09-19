/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tekoäly;

import logiikka.Alusta;

/**
 *
 * @author qq
 */
public class Arvioija {

    int voittoRivinPituus;
    Alusta alusta;
    final int merkinArvo = 10;
    final int tyhjanArvo = 1;

    public Arvioija() {
    }

    public int arvioiLauta(Alusta alusta) {
        this.alusta = alusta;
        this.voittoRivinPituus = alusta.voittorivinpituus();
        int arvio = 0;
        for (int i = 0; i < this.alusta.maxKoko(); i++) {
            for (int j = 0; j < this.alusta.maxKoko(); j++) {
                if (alusta.onkoTyhja(i, j)) {
                    continue;
                }
                arvio = arvioiSuora(i, j);
                if (arvio > 0) {
                    return arvio;
                }
                if (arvio < 0) {
                    return arvio;
                }

            }
        }
        return arvio;
    }

    private int arvioiSuora(int x, int y) {
        char merkki = alusta.lueMerkki(x, y);
        int arvo = 0;
        arvo = tarkistaVasemmalle(x, y) + merkinArvo + tarkistaOikealle(x, y, merkki);
        if (arvo >= merkinArvo * 4 + tyhjanArvo) {
            if (merkki == 'x') {
                return arvo;
            } else {
                return -arvo;
            }
        }
        arvo = tarkistaYlos(x, y) + merkinArvo + tarkistaAlas(x, y, merkki);
        if (arvo >= merkinArvo * 4 + tyhjanArvo) {
            if (merkki == 'x') {
                return arvo;
            } else {
                return -arvo;
            }
        }
        arvo = tarkistaVasemmalleYlos(x, y) + merkinArvo + tarkistaOikealleAlas(x, y, merkki);
        if (arvo >= merkinArvo * 4 + tyhjanArvo) {
            if (merkki == 'x') {
                return arvo;
            } else {
                return -arvo;
            }
        }
        arvo = tarkistaOikealleYlos(x, y) + merkinArvo + tarkistaVasemmalleAlas(x, y, merkki);
        if (arvo >= merkinArvo * 4 + tyhjanArvo) {
            if (merkki == 'x') {
                return arvo;
            } else {
                return -arvo;
            }
        }
        return 0;
    }

    private int tarkistaVasemmalle(int x, int y) {
        if (!this.alusta.tarkistaKoordinaatit(x - 1, y)) {
            return 0;
        }
        if (this.alusta.onkoTyhja(x - 1, y)) {
            return tyhjanArvo;
        }
        return 0;
    }

    private int tarkistaOikealle(int x, int y, char merkki) {

        if (!this.alusta.tarkistaKoordinaatit(x + 1, y)) {
            return 0;
        }

        if ((this.alusta.lueMerkki(x + 1, y)) == merkki) {
            return merkinArvo + tarkistaOikealle(x + 1, y, merkki);
        }
        if (this.alusta.onkoTyhja(x + 1, y)) {
            return tyhjanArvo;
        }
        return 0;
    }

    private int tarkistaYlos(int x, int y) {
        if (!this.alusta.tarkistaKoordinaatit(x, y - 1)) {
            return 0;
        }
        if (this.alusta.onkoTyhja(x, y - 1)) {
            return tyhjanArvo;
        }
        return 0;
    }

    private int tarkistaAlas(int x, int y, char merkki) {

        if (!this.alusta.tarkistaKoordinaatit(x, y + 1)) {
            return 0;
        }

        if ((this.alusta.lueMerkki(x, y + 1)) == merkki) {
            return merkinArvo + tarkistaAlas(x, y + 1, merkki);
        }
        if (this.alusta.onkoTyhja(x, y + 1)) {
            return tyhjanArvo;
        }
        return 0;
    }

    private int tarkistaVasemmalleYlos(int x, int y) {
        if (!this.alusta.tarkistaKoordinaatit(x - 1, y - 1)) {
            return 0;
        }
        if (this.alusta.onkoTyhja(x - 1, y - 1)) {
            return tyhjanArvo;
        }
        return 0;
    }

    private int tarkistaOikealleAlas(int x, int y, char merkki) {

        if (!this.alusta.tarkistaKoordinaatit(x + 1, y + 1)) {
            return 0;
        }

        if ((this.alusta.lueMerkki(x + 1, y + 1)) == merkki) {
            return merkinArvo + tarkistaOikealleAlas(x + 1, y + 1, merkki);
        }
        if (this.alusta.onkoTyhja(x + 1, y + 1)) {
            return tyhjanArvo;
        }
        return 0;
    }

    private int tarkistaOikealleYlos(int x, int y) {
        if (!this.alusta.tarkistaKoordinaatit(x + 1, y - 1)) {
            return 0;
        }
        if (this.alusta.onkoTyhja(x + 1, y - 1)) {
            return tyhjanArvo;
        }
        return 0;
    }

    private int tarkistaVasemmalleAlas(int x, int y, char merkki) {

        if (!this.alusta.tarkistaKoordinaatit(x - 1, y + 1)) {
            return 0;
        }

        if ((this.alusta.lueMerkki(x - 1, y + 1)) == merkki) {
            return merkinArvo + tarkistaVasemmalleAlas(x - 1, y + 1, merkki);
        }
        if (this.alusta.onkoTyhja(x - 1, y + 1)) {
            return tyhjanArvo;
        }
        return 0;
    }
}

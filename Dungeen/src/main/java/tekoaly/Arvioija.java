/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tekoaly;

import logiikka.Alusta;

/**
 *
 * @author qq
 */
public class Arvioija {

    int voittoRivinPituus;
    Alusta alusta;
    //final int merkinArvo = 10;
    final int tyhjanArvo = 2;

    public Arvioija() {
    }

    public int arvioiAlusta(Alusta alusta) {
        this.alusta = alusta;
        this.voittoRivinPituus = alusta.voittorivinpituus();
        int arvio = 0;
        for (int i = 0; i < this.alusta.maxKoko(); i++) {
            for (int j = 0; j < this.alusta.maxKoko(); j++) {
                if (alusta.onkoTyhja(i, j)) {
                    continue;
                }
                arvio = arvio + arvioiSuora(i, j);


            }
        }
        return arvio;
    }

    private int arvioiSuora(int x, int y) {
        char merkki = alusta.lueMerkki(x, y);
        int arvo;
        int arvio=0;
        arvo = tarkistaVasemmalle(x, y) + tarkistaOikealle(x, y, merkki);
        if (arvo > 10) {
            if (merkki == 'x') {
                arvio=arvio+arvo;
            } else {
                arvio=arvio-arvo;
            }
        }
        arvo = tarkistaYlos(x, y) + tarkistaAlas(x, y, merkki);
        if (arvo > 10) {
            if (merkki == 'x') {
                arvio=arvio+arvo;
            } else {
                arvio=arvio-arvo;
            }
        }
        arvo = tarkistaVasemmalleYlos(x, y) + tarkistaOikealleAlas(x, y, merkki);
        if (arvo > 10) {
            if (merkki == 'x') {
                arvio=arvio+arvo;
            } else {
                arvio=arvio-arvo;
            }
        }
        arvo = tarkistaOikealleYlos(x, y) + tarkistaVasemmalleAlas(x, y, merkki);
        if (arvo > 10) {
            if (merkki == 'x') {
                arvio=arvio+arvo;
            } else {
                arvio=arvio-arvo;
            }
        }
        return arvio;
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
            return 1;
        }
        if (this.alusta.onkoTyhja(x + 1, y)) {
            return tyhjanArvo;
        }
        if ((this.alusta.lueMerkki(x + 1, y)) == merkki) {
            return 10 * tarkistaOikealle(x + 1, y, merkki);
            //return merkinArvo + tarkistaOikealle(x + 1, y, merkki);
        }

        return 1;
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
            return 10 * tarkistaAlas(x, y + 1, merkki);
//            return merkinArvo + tarkistaAlas(x, y + 1, merkki);
        }
        if (this.alusta.onkoTyhja(x, y + 1)) {
            return tyhjanArvo;
        }
        return 1;
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
            return 10 * tarkistaOikealleAlas(x + 1, y + 1, merkki);
//            return merkinArvo + tarkistaOikealleAlas(x + 1, y + 1, merkki);
        }
        if (this.alusta.onkoTyhja(x + 1, y + 1)) {
            return tyhjanArvo;
        }
        return 1;
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
            return 10 * tarkistaVasemmalleAlas(x - 1, y + 1, merkki);
//            return merkinArvo + tarkistaVasemmalleAlas(x - 1, y + 1, merkki);
        }
        if (this.alusta.onkoTyhja(x - 1, y + 1)) {
            return tyhjanArvo;
        }
        return 1;
    }
}

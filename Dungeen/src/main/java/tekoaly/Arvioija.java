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
    final int merkinArvo = 10;
    final int tyhjanArvo = 1;
    int minimiArvo = 10;
    int seina = 0;

    public Arvioija() {
    }

    /**
     *
     * @param alusta Arvioitava alusta
     * @return palauttaa arvion alustasta posiitivinen tarkoittaa hyvää x:lle ja
     * negatiivinen hyvää oo:lle
     */
    public int arvioiAlusta(Alusta alusta) {
        this.alusta = alusta;
        this.voittoRivinPituus = alusta.getVoittorivinpituus();
        int arvio = 0;
        for (int i = 0; i < this.alusta.getKoko(); i++) {
            for (int j = 0; j < this.alusta.getKoko(); j++) {
                if (alusta.onkoTyhja(i, j)) {
                    continue;
                }
                arvio = arvio + arvioiSuora(i, j);

            }
        }
        alusta.setArvio(arvio);
        return arvio;
    }

    /**
     * Arviointi tapahtuu seuraavasti: (x:n suhteen arvio) x = 4 ox=2 oxx=20
     * oxxx=200 xx=22 xxx=202 xxxx2002 jne Arviointi tapahtu vain oikealle,
     * alas, oikealle alas ja vasemmalle alas. Kun jokainen ruutu käydään läpi
     * niin kaikki mahdolliset suorat saadaan arvioitua ja minimoidaan samojen
     * suorien uudelleen arviointi. Metodit tarkista vasemmalle, ylös jne ovat
     * olemassa vain sitä varten, että voidaan tarkistaa tyhjät ruudut suorien
     * päissä.
     *
     * @param x x koordinaatti
     * @param y y koordinaatti
     * @return tietysti pisteestä lähtevien suorien arviot
     */
    private int arvioiSuora(int x, int y) {
        char merkki = alusta.lueMerkki(x, y);
        int arvo;
        int arvio = 0;
        int lisa;
        arvo = tarkistaVasemmalle(x, y) + tarkistaOikealle(x, y, merkki);
        if (arvo > minimiArvo) {
            if (arvo >= (voittoRivinPituus - 1) * 10) {
                return Integer.MAX_VALUE;
            }
            if (arvo % 10 == 2) {
                if (merkki == 'x') {
                    arvio = arvio + 2*arvo;
                } else {
                    arvio = arvio - 2*arvo;
                }
            }
            if (arvo % 10 == 1) {
                if (merkki == 'x') {
                    arvio = arvio + arvo;
                } else {
                    arvio = arvio - arvo;
                }
            }
        }
        arvo = tarkistaYlos(x, y) + tarkistaAlas(x, y, merkki);
        if (arvo > minimiArvo) {
            if (arvo >= (voittoRivinPituus - 1) * 10) {
                return Integer.MAX_VALUE;
            }
            if (arvo % 10 == 2) {
                if (merkki == 'x') {
                    arvio = arvio + 2*arvo;
                } else {
                    arvio = arvio - 2*arvo;
                }
            }
            if (arvo % 10 == 1) {
                if (merkki == 'x') {
                    arvio = arvio + arvo;
                } else {
                    arvio = arvio - arvo;
                }
            }
        }
        arvo = tarkistaVasemmalleYlos(x, y) + tarkistaOikealleAlas(x, y, merkki);
        if (arvo > minimiArvo) {
            if (arvo >= (voittoRivinPituus - 1) * 10) {
                return Integer.MAX_VALUE;
            }
            if (arvo % 10 == 2) {
                if (merkki == 'x') {
                    arvio = arvio + 2*arvo;
                } else {
                    arvio = arvio - 2*arvo;
                }
            }
            if (arvo % 10 == 1) {
                if (merkki == 'x') {
                    arvio = arvio + arvo;
                } else {
                    arvio = arvio - arvo;
                }
            }
        }
        arvo = tarkistaOikealleYlos(x, y) + tarkistaVasemmalleAlas(x, y, merkki);
        if (arvo > minimiArvo) {
            if (arvo >= (voittoRivinPituus - 1) * 10) {
                return Integer.MAX_VALUE;
            }
            if (arvo % 10 == 2) {
                if (merkki == 'x') {
                    arvio = arvio + 2*arvo;
                } else {
                    arvio = arvio - 2*arvo;
                }
            }
            if (arvo % 10 == 1) {
                if (merkki == 'x') {
                    arvio = arvio + arvo;
                } else {
                    arvio = arvio - arvo;
                }
            }
        }
        return arvio;
    }

    private int tarkistaVasemmalle(int x, int y) {
        if (!this.alusta.tarkistaKoordinaatit(x - 1, y)) {
            return seina;
        }
        if (this.alusta.onkoTyhja(x - 1, y)) {
            return tyhjanArvo;
        }
        return seina;
    }

    private int tarkistaOikealle(int x, int y, char merkki) {

        if (!this.alusta.tarkistaKoordinaatit(x + 1, y)) {
            return seina;
        }
        if (this.alusta.onkoTyhja(x + 1, y)) {
            return tyhjanArvo;
        }
        if ((this.alusta.lueMerkki(x + 1, y)) == merkki) {
            return merkinArvo + tarkistaOikealle(x + 1, y, merkki);
        }

        return seina;
    }

    private int tarkistaYlos(int x, int y) {
        if (!this.alusta.tarkistaKoordinaatit(x, y - 1)) {
            return seina;
        }
        if (this.alusta.onkoTyhja(x, y - 1)) {
            return tyhjanArvo;
        }
        return seina;
    }

    private int tarkistaAlas(int x, int y, char merkki) {

        if (!this.alusta.tarkistaKoordinaatit(x, y + 1)) {
            return seina;
        }

        if ((this.alusta.lueMerkki(x, y + 1)) == merkki) {
            return merkinArvo + tarkistaAlas(x, y + 1, merkki);
        }
        if (this.alusta.onkoTyhja(x, y + 1)) {
            return tyhjanArvo;
        }
        return seina;
    }

    private int tarkistaVasemmalleYlos(int x, int y) {
        if (!this.alusta.tarkistaKoordinaatit(x - 1, y - 1)) {
            return seina;
        }
        if (this.alusta.onkoTyhja(x - 1, y - 1)) {
            return tyhjanArvo;
        }
        return seina;
    }

    private int tarkistaOikealleAlas(int x, int y, char merkki) {

        if (!this.alusta.tarkistaKoordinaatit(x + 1, y + 1)) {
            return seina;
        }

        if ((this.alusta.lueMerkki(x + 1, y + 1)) == merkki) {
            return merkinArvo + tarkistaOikealleAlas(x + 1, y + 1, merkki);
        }
        if (this.alusta.onkoTyhja(x + 1, y + 1)) {
            return tyhjanArvo;
        }
        return seina;
    }

    private int tarkistaOikealleYlos(int x, int y) {
        if (!this.alusta.tarkistaKoordinaatit(x + 1, y - 1)) {
            return seina;
        }
        if (this.alusta.onkoTyhja(x + 1, y - 1)) {
            return tyhjanArvo;
        }
        return seina;
    }

    private int tarkistaVasemmalleAlas(int x, int y, char merkki) {

        if (!this.alusta.tarkistaKoordinaatit(x - 1, y + 1)) {
            return seina;
        }

        if ((this.alusta.lueMerkki(x - 1, y + 1)) == merkki) {
            return merkinArvo + tarkistaVasemmalleAlas(x - 1, y + 1, merkki);
        }
        if (this.alusta.onkoTyhja(x - 1, y + 1)) {
            return tyhjanArvo;
        }
        return seina;
    }
}

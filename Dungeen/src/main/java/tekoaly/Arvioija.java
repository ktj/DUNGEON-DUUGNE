/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tekoaly;

import logiikka.Alusta;

/**
 * Arvioija luokka, mikä arvioi nykyisen tilanteen ja antaa sille luvun.
 */
public class Arvioija {

    private int voittoRivinPituus;
    private Alusta alusta;
    private final int merkinArvo = 10;
    private final int tyhjanArvo = 1;
    private int minimiArvo = 10;
    private int seina = 0;
    private int iso_luku = 1000000;

    public Arvioija() {
    }

    /**
     * @param alusta Arvioitava alusta
     * @return palauttaa arvion alustasta posiitivinen tarkoittaa hyvää x:lle ja negatiivinen hyvää oo:lle
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
     * Saa parametrinä arvon, josta tulkitaan suoran lopullinen pisteytys.
     * Suoran pituus on kymmenissä ja lopussa oleva luku 0,1 tai 2 kertoo kuinka
     * monta tyhjää suoran päissä on. Arvo xy tulkitaan siis x+1 on suoran pituus
     * ja y on tyhjien määrä. 32 tarkoittaa silloin neljän suora ja molemmissa päissä
     * tyhjää. Mikäli suora on alle voittorivin pituinen ja molemmat päät on tukittu,
     * niin suora on arvoton.
     * Tällä hetkellä arvot ovat esim 5 suora pelissä seuraavat:
     * Viiden suora tai pidempi(tyhjillä ei merkitystä) = iso_luku
     * Neljän suora, molemmat päät tyhjät = iso_luku/10
     * Neljän suora, yksi tyhjä = iso_luku/100
     * Kolmen suora, molemmat päät tyhjät = iso_luku/1000
     * Pienemmät suorat, molemmat päät tyhjiä = arvo*2
     * Kolme tai pienempi suora, yksi tyhjä = arvo
     * Muuten 0
     * 
     * @param arvo tulkittava arvo
     * @param merkki x tai o eli + tai -
     * @return suoran lopullinen arvo.
     */
    private int pisteytaSuora(int arvo, char merkki) {
        if (arvo > minimiArvo) {
            if (arvo >= (voittoRivinPituus - 1) * merkinArvo) {
                if (merkki == 'x') {
                    return iso_luku;
                } else {
                    return -iso_luku;
                }
            }
            if (arvo >= (voittoRivinPituus - 2) * merkinArvo + tyhjanArvo * 2) {
                if (merkki == 'x') {
                    return iso_luku / 10;
                } else {
                    return -iso_luku / 10;
                }
            }
            if (arvo >= (voittoRivinPituus - 2) * merkinArvo + tyhjanArvo) {
                if (merkki == 'x') {
                    return iso_luku / 100;
                } else {
                    return -iso_luku / 100;
                }
            }
            if (arvo >= (voittoRivinPituus - 3) * merkinArvo + tyhjanArvo * 2) {
                if (arvo % 10 == 2) {
                    if (merkki == 'x') {
                        return iso_luku / 1000;
                    } else {
                        return -iso_luku / 1000;
                    }
                }
            }
            if (arvo % 10 == 2) {
                if (merkki == 'x') {
                    return 2 * arvo;
                } else {
                    return -2 * arvo;
                }
            }
            if (arvo % 10 == 1) {
                if (merkki == 'x') {
                    return arvo;
                } else {
                    return -arvo;
                }
            }
        }
        return 0;
    }

    /**
     * Saa jonkin pisteen ja tarkistaa siitä kaikki siitä lähtevät suorat ja
     * antaa pisteelle sen mukaan arvion.
     * Tarkistettavat suunnat ovat 
     * oikealle (+tyhjän tarkistus vasemmalta puolelta)
     * alas (+tyhjän tarkistus ylös)
     * oikealle alas(+...)
     * vasemmalle alas(+..)
     * @param x x koordinaatti
     * @param y y koordinaatti
     * @return tietysti pisteestä lähtevien suorien arviot yhteensä.
     */
    private int arvioiSuora(int x, int y) {
        char merkki = alusta.lueMerkki(x, y);
        int arvo;
        int arvio = 0;
        //Tarkistaa pisteestä oikealle lähtevän suoran pituuden ja lisää siihen mahdollisen tyhjän arvon suoran vasemmalta puolelta.
        arvo = tarkistaVasemmalle(x, y) + tarkistaOikealle(x, y, merkki);
        //Antaa arvon lopulliseen pisteytykseen ja lisää sen pisteen kokonais arvioon.
        arvio = arvio + pisteytaSuora(arvo, merkki);

        arvo = tarkistaYlos(x, y) + tarkistaAlas(x, y, merkki);
        arvio = arvio + pisteytaSuora(arvo, merkki);

        arvo = tarkistaVasemmalleYlos(x, y) + tarkistaOikealleAlas(x, y, merkki);
        arvio = arvio + pisteytaSuora(arvo, merkki);

        arvo = tarkistaOikealleYlos(x, y) + tarkistaVasemmalleAlas(x, y, merkki);
        arvio = arvio + pisteytaSuora(arvo, merkki);

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

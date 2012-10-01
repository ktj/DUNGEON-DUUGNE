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
    int iso_luku = 1000000;

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

    private int pisteytaSuora(int arvo,char merkki){
        if (arvo > minimiArvo) {
            if (arvo >= (voittoRivinPituus - 1) * merkinArvo) {
                if (merkki == 'x') {
                    return iso_luku;
                } else {
                    return -iso_luku;
                }
            }
            if(arvo >=(voittoRivinPituus - 2)*merkinArvo + tyhjanArvo*2){
                if (merkki == 'x') {
                    return iso_luku/10;
                } else {
                    return -iso_luku/10;
                }
            }
            if(arvo>=(voittoRivinPituus -2)*merkinArvo + tyhjanArvo){
                if (merkki == 'x') {
                    return iso_luku/100;
                } else {
                    return iso_luku/100;
                }
            }
            if(arvo >=(voittoRivinPituus - 3)*merkinArvo + tyhjanArvo*2){
                if (merkki == 'x') {
                    return iso_luku/1000;
                } else {
                    return -iso_luku/1000;
                }
            }
            if (arvo % 10 == 2) {
                if (merkki == 'x') {
                    return 2*arvo;
                } else {
                    return -2*arvo;
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
     * Saa jonkin pisteen ja tarkistaa siitä kaikki siitä lähtevät suorat ja antaa pisteelle sen mukaan arvion.
     * 
     * @param x x koordinaatti
     * @param y y koordinaatti
     * @return tietysti pisteestä lähtevien suorien arviot
     */
    private int arvioiSuora(int x, int y) {
        char merkki = alusta.lueMerkki(x, y);
        int arvo;
        int arvio = 0;
        arvo = tarkistaVasemmalle(x, y) + tarkistaOikealle(x, y, merkki);
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

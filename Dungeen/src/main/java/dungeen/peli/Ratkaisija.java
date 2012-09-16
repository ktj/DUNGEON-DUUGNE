package dungeen.peli;

/**
 *
 * @author julius
 */
public class Ratkaisija {

    int voittoRivinPituus;
    Alusta alusta;

    public Ratkaisija() {
    }

    public int etsiVoitto(Alusta alusta, int voittoRivinPituus) {
        this.alusta = alusta;
        this.voittoRivinPituus = voittoRivinPituus;
        for (int i = 0; i < this.alusta.maxKoko(); i++) {
            for (int j = 0; j < this.alusta.maxKoko(); j++) {
                if (alusta.onkoTyhja(i, j)) {
                    continue;
                }
                if (tarkistaRuutu(i, j)) {
                    if (this.alusta.lueMerkki(i, j) == 'x') {
                        return 1;
                    } else {
                        return -1;
                    }
                }
            }
        }
        return 0;
    }

    boolean tarkistaRuutu(int x, int y) {
        char merkki = alusta.lueMerkki(x, y);
        if ((tarkistaVasemmalle(x, y, merkki) + 1 + tarkistaOikealle(x, y, merkki)) >= this.voittoRivinPituus) {
            return true;
        }
        if ((tarkistaAlas(x, y, merkki) + 1 + tarkistaYlos(x, y, merkki)) >= this.voittoRivinPituus) {
            return true;
        }
        if ((tarkistaVasemmalleYlos(x, y, merkki) + 1 + tarkistaOikealleAlas(x, y, merkki)) >= this.voittoRivinPituus) {
            return true;
        }
        if ((tarkistaVasemmalleAlas(x, y, merkki) + 1 + tarkistaOikealleYlos(x, y, merkki)) >= this.voittoRivinPituus) {
            return true;
        }
        return false;
    }

    

    int tarkistaVasemmalle(int x, int y, char merkki) {
        if (!this.alusta.tarkistaKoordinaatit((x - 1), y)) {
            return 0;
        }
        if ((this.alusta.lueMerkki(x - 1, y)) == merkki) {
            return 1 + tarkistaVasemmalle(x - 1, y, merkki);
        }
        return 0;
    }

    int tarkistaOikealle(int x, int y, char merkki) {
        if (!this.alusta.tarkistaKoordinaatit(x + 1, y)) {
            return 0;
        }
        if ((this.alusta.lueMerkki(x + 1, y)) == merkki) {
            return 1 + tarkistaOikealle(x + 1, y, merkki);
        }
        return 0;
    }

    int tarkistaAlas(int x, int y, char merkki) {
        if (!this.alusta.tarkistaKoordinaatit(x, y + 1)) {
            return 0;
        }
        if ((this.alusta.lueMerkki(x, y + 1)) == merkki) {
            return 1 + tarkistaAlas(x, y + 1, merkki);
        }
        return 0;
    }

    int tarkistaYlos(int x, int y, char merkki) {
        if (!this.alusta.tarkistaKoordinaatit(x, y - 1)) {
            return 0;
        }
        if ((this.alusta.lueMerkki(x, y - 1)) == merkki) {
            return 1 + tarkistaYlos(x, y - 1, merkki);
        }
        return 0;
    }

    int tarkistaVasemmalleYlos(int x, int y, char merkki) {
        if (!this.alusta.tarkistaKoordinaatit(x - 1, y - 1)) {
            return 0;
        }
        if ((this.alusta.lueMerkki(x - 1, y - 1)) == merkki) {
            return 1 + tarkistaVasemmalleYlos(x - 1, y - 1, merkki);
        }
        return 0;
    }

    int tarkistaOikealleAlas(int x, int y, char merkki) {
        if (!this.alusta.tarkistaKoordinaatit(x + 1, y + 1)) {
            return 0;
        }
        if ((this.alusta.lueMerkki(x + 1, y + 1)) == merkki) {
            return 1 + tarkistaOikealleAlas(x + 1, y + 1, merkki);
        }
        return 0;
    }

    int tarkistaOikealleYlos(int x, int y, char merkki) {
        if (!this.alusta.tarkistaKoordinaatit(x + 1, y - 1)) {
            return 0;
        }
        if ((this.alusta.lueMerkki(x + 1, y - 1)) == merkki) {
            return 1 + tarkistaOikealleYlos(x + 1, y - 1, merkki);
        }
        return 0;
    }

    int tarkistaVasemmalleAlas(int x, int y, char merkki) {
        if (!this.alusta.tarkistaKoordinaatit(x - 1, y + 1)) {
            return 0;
        }
        if ((this.alusta.lueMerkki(x - 1, y + 1)) == merkki) {
            return 1 + tarkistaVasemmalleAlas(x - 1, y + 1, merkki);
        }
        return 0;
    }
}
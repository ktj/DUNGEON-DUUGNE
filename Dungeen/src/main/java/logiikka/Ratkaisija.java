package logiikka;

/**
 *
 * @author qq
 */
public class Ratkaisija {

    private int voittoRivinPituus;
    private Alusta alusta;

    public Ratkaisija() {
    }

    public int etsiVoitto(Alusta alusta) {
        this.alusta = alusta;
        this.voittoRivinPituus = alusta.voittorivinpituus();
        for (int i = 0; i < this.alusta.maxKoko(); i++) {
            for (int j = 0; j < this.alusta.maxKoko(); j++) {
                if (alusta.onkoTyhja(i, j)) {
                    continue;
                }
                if (tarkistaRuutu(i, j)) {
                    if (this.alusta.lueMerkki(i, j) == 'x') {
                        return Integer.MAX_VALUE;
                    } else {
                        return Integer.MIN_VALUE;
                    }
                }
            }
        }
        return 0;
    }

    private boolean tarkistaRuutu(int x, int y) {
        char merkki = alusta.lueMerkki(x, y);
        if ((1 + tarkistaOikealle(x, y, merkki)) >= this.voittoRivinPituus) {
            return true;
        }
        if ((1 + tarkistaAlas(x, y, merkki)) >= this.voittoRivinPituus) {
            return true;
        }
        if ((1 + tarkistaOikealleAlas(x, y, merkki)) >= this.voittoRivinPituus) {
            return true;
        }
        if ((1 + tarkistaVasemmalleAlas(x, y, merkki)) >= this.voittoRivinPituus) {
            return true;
        }
        return false;
    }

    private int tarkistaOikealle(int x, int y, char merkki) {
        if (!this.alusta.tarkistaKoordinaatit(x + 1, y)) {
            return 0;
        }
        if ((this.alusta.lueMerkki(x + 1, y)) == merkki) {
            return 1 + tarkistaOikealle(x + 1, y, merkki);
        }
        return 0;
    }

    private int tarkistaAlas(int x, int y, char merkki) {
        if (!this.alusta.tarkistaKoordinaatit(x, y + 1)) {
            return 0;
        }
        if ((this.alusta.lueMerkki(x, y + 1)) == merkki) {
            return 1 + tarkistaAlas(x, y + 1, merkki);
        }
        return 0;
    }

    private int tarkistaOikealleAlas(int x, int y, char merkki) {
        if (!this.alusta.tarkistaKoordinaatit(x + 1, y + 1)) {
            return 0;
        }
        if ((this.alusta.lueMerkki(x + 1, y + 1)) == merkki) {
            return 1 + tarkistaOikealleAlas(x + 1, y + 1, merkki);
        }
        return 0;
    }

    private int tarkistaVasemmalleAlas(int x, int y, char merkki) {
        if (!this.alusta.tarkistaKoordinaatit(x - 1, y + 1)) {
            return 0;
        }
        if ((this.alusta.lueMerkki(x - 1, y + 1)) == merkki) {
            return 1 + tarkistaVasemmalleAlas(x - 1, y + 1, merkki);
        }
        return 0;
    }
}

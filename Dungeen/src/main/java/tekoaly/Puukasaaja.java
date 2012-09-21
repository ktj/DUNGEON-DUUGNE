package tekoaly;

import logiikka.Alusta;

public class Puukasaaja {

    public Puukasaaja() {
    }

    public Puu kasaaPuu(Alusta alusta, char merkki, int maksimitaso) {
        Puu puu = new Puu(alusta);
        if (maksimitaso < 1) {
            return puu;
        }
        return kasaaLapset(puu, merkki, 1, maksimitaso);
    }

    private Puu kasaaLapset(Puu parent, char merkki, int taso, int maksimitaso) {
        etsiVaihtoehdot(parent, merkki);
        int seurtaso = taso + 1;
        for (Puu lapsi : parent) {
            if (taso < maksimitaso) {
                kasaaLapset(lapsi, negaatio(merkki), seurtaso, maksimitaso);
            }
        }
        return parent;
    }

    private char negaatio(char merkki) {
        if (merkki == 'x') {
            return 'o';
        } else {
            return 'x';
        }
    }

    private void etsiVaihtoehdot(Puu parent, char merkki) {
        Alusta alusta = parent.haeAlusta();
        for (int i = 0; i < alusta.maxKoko(); i++) {
            for (int j = 0; j < alusta.maxKoko(); j++) {
                if (karsinta(alusta, i, j)) {
                    Alusta alustaklooni = new Alusta(alusta);
                    alustaklooni.lisaaMerkkiLaudalle(i, j, merkki);
                    parent.lisaaLapsi(new Puu(alustaklooni));
                }
            }
        }
    }

    /*
     * HIDAS PASKA(?)
     */
    private boolean karsinta(Alusta alusta, int x, int y) {
        if (!alusta.onkoTyhja(x, y)) {
            return false;
        }
        for (int k = y - 1; k <= y + 1; k++) {
            for (int l = x - 1; l <= x + 1; l++) {
                if (!alusta.onkoTyhja(l, k) || alusta.onkoLautaTyhja()) {
                    return true;
                }
            }
        }
        return false;
    }
}

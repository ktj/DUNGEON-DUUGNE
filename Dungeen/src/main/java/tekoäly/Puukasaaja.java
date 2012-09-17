package tekoäly;

import dungeen.peli.Alusta;

public class Puukasaaja {

    public Puukasaaja() {
    }

    public Puu kasaaPuu(Alusta alusta, char merkki, int maksimitaso) {
        Puu puu = new Puu(alusta);
        return kasaaLapset(puu, merkki, 1, maksimitaso);
    }

    private Puu kasaaLapset(Puu parent, char merkki, int taso, int maksimitaso) {
        etsiVaihtoehdot(parent, merkki);
        int seurtaso = taso + 1;
        for (Puu lapsi : parent.haeLapset()) {
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
                Alusta alustaklooni = new Alusta(alusta);
                if (alustaklooni.onkoTyhja(i, j)) {
                    alustaklooni.lisaaMerkkiLaudalle(i, j, merkki);
                    parent.lisaaLapsi(new Puu(alustaklooni));
                }
            }
        }
    }
}

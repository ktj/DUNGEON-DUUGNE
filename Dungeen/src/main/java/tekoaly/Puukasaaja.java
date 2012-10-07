package tekoaly;

import logiikka.Alusta;

public class Puukasaaja {

    private Arvioija arvioija;

    public Puukasaaja(Arvioija arvioija) {
        this.arvioija = arvioija;
    }

    /**
     * Kasaa puun parametrinä annestusta alustasta lähtien.
     *
     * @param alusta Juuri
     * @param merkki Ensimmäinen laitettava merkki, eli kumman vuoro
     * @param maksimitaso Kuinka pitkälle mennään
     * @return Valmiin puun kaikista järkevistä siirroista
     */
    public Puu kasaaPuu(Alusta alusta, char merkki, int maksimitaso) {
        arvioija.arvioiAlusta(alusta);
//        System.out.println("kasaapuu arvio: " + alusta.getArvio());
        Puu puu = new Puu(alusta);
        if (maksimitaso < 1) {
            return puu;
        }
        return kasaaLapset(puu, merkki, 1, maksimitaso);
    }

    /**
     * Itse puun kasaus, kutsuu rekursiivisesti itseään puun pohjalle saakka
     *
     * @param parent Vanhempi solmu
     * @param merkki laitettava merkki, vaihtuu vuorottain
     * @param taso Nykyinen taso
     * @param maksimitaso
     * @return Koko puu, lehti tai osapuu riippuen rekursion syvyydestä
     */
    private Puu kasaaLapset(Puu parent, char merkki, int taso, int maksimitaso) {
        etsiVaihtoehdot(parent, merkki);
        int seurtaso = taso + 1;
        if (taso < maksimitaso) {
            for (Puu lapsi : parent.haeLapset()) {
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

    /**
     * Etsii kaikki mahdolliset vaihtoehdot eli lapset puulle ja karsii niitä.
     * Etsii ainoastaa yhden tason lapsia.
     *
     * @param parent Juuri
     * @param merkki laitettava merkki
     */
    private void etsiVaihtoehdot(Puu parent, char merkki) {
        Alusta alusta = parent.haeAlusta();
        if (alusta.onkoLautaTaynna()) {
            return;
        }
        for (int i = 0; i < alusta.getKoko(); i++) {
            for (int j = 0; j < alusta.getKoko(); j++) {
                if (karsinta(alusta, i, j)) {
                    Alusta alustaklooni = new Alusta(alusta);
                    alustaklooni.lisaaMerkkiLaudalle(i, j, merkki);
                    arvioija.arvioiAlusta(alustaklooni);
                    if (onSuotuisampi(parent, alustaklooni, merkki)) {
                        parent.lisaaLapsi(new Puu(alustaklooni));
                    }
                }
            }
        }
    }

    /**
     * Katsoo onko vaihtoehto suotuisampi kuin edellinen siirto.
     *
     * @param parent Edellinen siirto
     * @param alustaklooni verrattava vaihtoehto
     * @param merkki asetettava merkki
     * @return True tai False tilanteen mukaan
     */
    private boolean onSuotuisampi(Puu parent, Alusta alustaklooni, char merkki) {
        if (merkki == 'x') {
            return (parent.haeAlusta().getArvio() < alustaklooni.getArvio()) || parent.haeLapset().isEmpty();
        } else {
            return (parent.haeAlusta().getArvio() > alustaklooni.getArvio()) || parent.haeLapset().isEmpty();
        }
    }

    /**
     * Karsii kaikki ruudut, joiden ympärillä ei ole muita merkkejä
     *
     * @param alusta
     * @param x x-koordinaatti
     * @param y y-koordinaatti
     */
    private boolean karsinta(Alusta alusta, int x, int y) {
        if (!alusta.onkoTyhja(x, y)) {
            return false;
        }
        for (int k = y - 1; k <= y + 1; k++) {
            for (int l = x - 1; l <= x + 1; l++) {
                if (!alusta.onkoTyhjaTaiSeina(l, k) || alusta.onkoLautaTyhja()) {
                    return true;
                }
            }
        }
        return false;
    }
}

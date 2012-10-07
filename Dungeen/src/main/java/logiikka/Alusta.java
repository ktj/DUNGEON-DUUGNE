package logiikka;

import tekoaly.Arvioija;

/**
 *
 * @author ;GGGG
 */
public class Alusta {

    private int koko;
    private int voittorivinpituus;
    private char[][] lauta;
    private int tilaa;
    private int arvio;

    /**
     * Tyhjän alustan konstruktori
     * @param koko
     * @param voittorivinpituus
     */
    public Alusta(int koko, int voittorivinpituus) {
        this.koko = koko;
        this.voittorivinpituus = voittorivinpituus;
        this.lauta = new char[this.koko][this.koko];
        this.tyhjaaLauta();
        this.arvio = 0;
    }

    /**
     * Kopion luominen, ottaa tiedot parametriltä
     * @param alusta
     */
    public Alusta(Alusta alusta) {

        this(alusta.koko, alusta.voittorivinpituus);
        this.arvio = alusta.arvio;
        this.tilaa = alusta.tilaa;
        for (int i = 0; i < this.koko; i++) {
            for (int j = 0; j < this.koko; j++) {
                this.lauta[j][i] = alusta.lauta[j][i];
            }
        }
    }

    /**
     * Tyhjää laudan, asettaa välilyönnin jokaiseen ruutuun ja nollaa käytetyn tilan.
     */
    public void tyhjaaLauta() {
        for (int i = 0; i < this.koko; i++) {
            for (int j = 0; j < this.koko; j++) {
                this.lauta[i][j] = ' ';
            }
        }
        tilaa = koko * koko;
    }

    /**
     * Tarkistaa onko annettu kohta vapaa laitettavaksi. Käyttää merkinlukua apuna.
     * @param x x-koordinaatti
     * @param y y-koordinaatti
     * @return True tai False tilanteen mukaan
     */
    public boolean onkoTyhja(int x, int y) {
        return lueMerkki(x, y) == ' ';
    }
    /**
     * Tarkistaa onko tyhjä tai seinä.
     * @param x x-koordinaatti
     * @param y y-koordinaatti
     * @return True tai False tilanteen mukaan
     */
    public boolean onkoTyhjaTaiSeina(int x, int y) {
        return (lueMerkki(x, y) == ' ' || lueMerkki(x, y) == 'z');
    }

    /**
     * Lisää lautaan merkin, mikäli koordinaatit ovat pätevät
     * @param x x-koordinaatti
     * @param y y-koordinaatti
     * @param merkki laitettava merkki, tyypillisesti 'x' tai 'o'
     */
    public void lisaaMerkkiLaudalle(int x, int y, char merkki) {
        if (tarkistaKoordinaatit(x, y)) {
            this.lauta[x][y] = merkki;
            tilaa--;
        }
    }

    /**
     * Lukee merkin kohdasta mikäli koordinaatit ovat pätevät, muuten palauttaa
     * "seinän".
     * @param x x-koordinaatti
     * @param y y-koordinaatti
     * @return kohdassa olevan merkin
     */
    public char lueMerkki(int x, int y) {
        if (tarkistaKoordinaatit(x, y)) {
            return this.lauta[x][y];
        }
        return 'z';
    }

    public int getKoko() {
        return this.koko;
    }

    public void tulostaAlusta() {
        for (int i = 0; i < this.koko; i++) {
            for (int j = 0; j < this.koko; j++) {
                System.out.print(lueMerkki(j, i));
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Tarkistaa ovatko koordinaatit pätevät eli mahtuvatko laudalle.
     * @param x x-koordinaatti
     * @param y y-koordinaatti
     * @return True tai False tilanteen mukaan
     */
    public boolean tarkistaKoordinaatit(int x, int y) {
        return x < koko && y < koko && x >= 0 && y >= 0;
    }

    public boolean onkoLautaTaynna() {
        return tilaa == 0;
    }

    public int tyhjaaTilaa() {
        return tilaa;
    }

    public boolean onkoLautaTyhja() {
        return tilaa == koko * koko;
    }

    public int getVoittorivinpituus() {
        return voittorivinpituus;
    }

    public int getArvio() {
        return arvio;
    }

    public void setArvio(int arvio) {
        this.arvio = arvio;
    }
}

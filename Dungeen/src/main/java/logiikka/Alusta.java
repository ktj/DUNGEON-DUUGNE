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

    public Alusta(int koko, int voittorivinpituus) {
        this.koko = koko;
        this.voittorivinpituus = voittorivinpituus;
        this.lauta = new char[this.koko][this.koko];
        this.tyhjaaLauta();
        this.arvio = 0;
    }

    public Alusta(Alusta alusta) {
        this(alusta.koko, alusta.voittorivinpituus);
        for (int i = 0; i < this.koko; i++) {
            for (int j = 0; j < this.koko; j++) {
                this.lauta[j][i] = alusta.lauta[j][i];
            }
        }
    }

    public void tyhjaaLauta() {
        for (int i = 0; i < this.koko; i++) {
            for (int j = 0; j < this.koko; j++) {
                this.lauta[i][j] = ' ';
            }
        }
        tilaa = koko * koko;
    }

    public boolean onkoTyhja(int x, int y) {
        return lueMerkki(x, y) == ' ';
    }

    public void lisaaMerkkiLaudalle(int x, int y, char merkki) {
        if (tarkistaKoordinaatit(x, y)) {
            this.lauta[x][y] = merkki;
            tilaa--;
        }
    }

    public char lueMerkki(int x, int y) {
        if (tarkistaKoordinaatit(x, y)) {
            return this.lauta[x][y];
        }
        return ' ';
    }

    public int maxKoko() {
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

    public boolean tarkistaKoordinaatit(int x, int y) {
        return x < koko && y < koko && x >= 0 && y >= 0;
    }

    public boolean onkoLautaTaynna() {
        return tilaa == 0;
    }

    public int tyhjaaTilaa() {
        return tilaa;
    }
    
    public boolean onkoLautaTyhja(){
        return tilaa == koko*koko;
    }

    public int voittorivinpituus() {
        return voittorivinpituus;
    }
    public int getArvio(){
        return arvio;
    }
    public void setArvio(int arvio){
        this.arvio=arvio;
    }
}

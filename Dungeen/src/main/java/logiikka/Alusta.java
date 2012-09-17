package logiikka;

/**
 *
 * @author ;GGGG
 */
public class Alusta {

    private int koko;
    private int voittorivinpituus;
    private char[][] lauta;

    public Alusta(int koko, int voittorivinpituus) {
        this.koko = koko;
        this.voittorivinpituus = voittorivinpituus;
        this.lauta = new char[this.koko][this.koko];
        this.tyhjaaLauta();
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
    }

    public boolean onkoTyhja(int x, int y) {
        if (this.lauta[x][y] == ' ') {
            return true;
        }
        return false;
    }

    public void lisaaMerkkiLaudalle(int x, int y, char merkki) {
        this.lauta[x][y] = merkki;
    }

    public char lueMerkki(int x, int y) {
        return this.lauta[x][y];
    }

    public int maxKoko() {
        return this.koko;
    }

    public void tulostaAlusta() {
        System.out.println();
        for (int i = 0; i < this.koko; i++) {
            for (int j = 0; j < this.koko; j++) {
                System.out.print(lueMerkki(j, i));
            }
            System.out.println();
        }
    }

    public boolean tarkistaKoordinaatit(int x, int y) {
        if (x < 0 || y < 0) {
            return false;
        }
        if (x >= maxKoko() || y >= maxKoko()) {
            return false;
        }
        return true;
    }

    public boolean onkoLautaTaynna() {
        boolean taysi = true;
        for (int i = 0; i < this.koko; i++) {
            for (int j = 0; j < this.koko; j++) {
                if(this.onkoTyhja(i, j))
                    taysi = false;
            }
        }
        return taysi;
    }
    
    public int voittorivinpituus(){
        return voittorivinpituus;
    }
}

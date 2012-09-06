package dungeon.peli;

/**
 *
 * @author ;GGGG
 */
public class Alusta {

    int koko;
    char[][] lauta;

    public Alusta(int koko) {
        this.koko = koko;
        this.lauta = new char[this.koko][this.koko];
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
        return this.koko - 1;
    }
    public void tulostaAlusta(){
        System.out.println();
        for(int i = 0; i< this.koko;i++){
            for(int j = 0 ; j<this.koko;j++){
                System.out.print(lueMerkki(i, j));
            }
            System.out.println();
        }
    }
}
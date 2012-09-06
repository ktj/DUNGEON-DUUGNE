package dungeon.käyttöliittymä;

import dungeon.peli.Alusta;
import java.util.Scanner;

public class Tekstikäyttöliittymä implements Käyttöliittymä {

    Scanner lukija;

    public Tekstikäyttöliittymä() {
        lukija = new Scanner(System.in);
    }

    @Override
    public void tulosta_alusta(Alusta alusta) {
        alusta.tulostaAlusta();
    }

    @Override
    public Alusta kysy_siirto(Alusta alusta, char merkki) {
        int x, y;
        System.out.print("Syötä x-koordinaatti: ");
        x = lukija.nextInt();
        System.out.print("Syötä y-koordinaatti: ");
        y = lukija.nextInt();

        while (!alusta.onkoTyhja(x, y) || x >= alusta.maxKoko() || y >= alusta.maxKoko()) {
            System.out.println("Virheelliset koordinaatit!");
            System.out.print("Syötä x-koordinaatti: ");
            x = lukija.nextInt();
            System.out.print("Syötä y-koordinaatti: ");
            y = lukija.nextInt();
        }
        alusta.lisaaMerkkiLaudalle(x, y, merkki);
        return alusta;
    }
}

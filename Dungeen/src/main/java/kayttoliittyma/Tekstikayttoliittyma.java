package kayttoliittyma;

import logiikka.Alusta;
import java.util.Scanner;

public class Tekstikayttoliittyma implements Kayttoliittyma {

    Scanner lukija;

    public Tekstikayttoliittyma() {
        lukija = new Scanner(System.in);
    }

    @Override
    public void tulosta_alusta(Alusta alusta) {
        System.out.println();
        for (int i = 0; i < alusta.getKoko(); i++) {
            for (int j = 0; j < alusta.getKoko(); j++) {
                System.out.print(alusta.lueMerkki(j, i));
            }
            System.out.println();
        }
    }

    @Override
    public Alusta kysy_siirto(Alusta alusta, char merkki) {
        int x, y;
        System.out.print("Syötä x-koordinaatti: ");
        x = lukija.nextInt();
        System.out.print("Syötä y-koordinaatti: ");
        y = lukija.nextInt();

        while (!alusta.onkoTyhja(x, y) || x >= alusta.getKoko() || y >= alusta.getKoko()) {
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

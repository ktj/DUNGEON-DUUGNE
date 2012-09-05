/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeon.duugne;

import dungeon.duugne.peli.alusta;
import dungeon.duugne.peli.ratkaisija;

/**
 *
 * @author julius
 */
public class DUNGEONDUUGNE {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        alusta lauta = new alusta(5);
        ratkaisija ratkaisija = new ratkaisija(3, lauta);
        lauta.lisaaMerkkiLaudalle(0, 0, 'x');
        lauta.lisaaMerkkiLaudalle(1, 0, 'x');
        lauta.lisaaMerkkiLaudalle(2, 0, 'o');
        lauta.lisaaMerkkiLaudalle(3, 0, 'o');
        lauta.lisaaMerkkiLaudalle(1, 1, 'x');
        lauta.lisaaMerkkiLaudalle(1, 2, 'x');
        lauta.tulostaAlusta();
        if (ratkaisija.tarkistaVoitto(1, 1)) {
            System.out.println("x voitti");
        }
        if (ratkaisija.tarkistaVoitto(1, 2)) {
            System.out.println("x voitti");
        }
    }
}

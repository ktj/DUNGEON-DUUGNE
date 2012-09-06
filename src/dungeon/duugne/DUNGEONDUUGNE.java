/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeon.duugne;

import dungeon.duugne.peli.Alusta;
import dungeon.duugne.peli.Ratkaisija;

/**
 *
 * @author julius
 */
public class DUNGEONDUUGNE {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Alusta lauta = new Alusta(5);
        Ratkaisija ratkaisija = new Ratkaisija(3, lauta);
        lauta.lisaaMerkkiLaudalle(0, 0, 'x');
        lauta.lisaaMerkkiLaudalle(1, 0, 'x');
        lauta.lisaaMerkkiLaudalle(2, 0, 'o');
        lauta.lisaaMerkkiLaudalle(3, 0, 'o');
        lauta.lisaaMerkkiLaudalle(1, 1, 'x');
        lauta.lisaaMerkkiLaudalle(1, 2, 'x');
        lauta.tulostaAlusta();
        if (ratkaisija.tarkistaVoitto(2, 0)) {
            System.out.println("x voitti");
        }
        if (ratkaisija.tarkistaVoitto(3, 0)) {
            System.out.println("x voitti");
        }
    }
}

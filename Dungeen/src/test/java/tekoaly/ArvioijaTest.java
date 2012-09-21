/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tekoaly;

import tekoaly.Arvioija;
import logiikka.Alusta;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author julius
 */
public class ArvioijaTest {
    Alusta alusta;
    Arvioija arvioija;
    int koko = 100;
    int voittorivinpituus = 5;
    public ArvioijaTest() {
        this.alusta = new Alusta(koko, voittorivinpituus);
        this.arvioija=new Arvioija();
    }

    
    
    @Test
    public void testaaArviointia1() {
        char x = 'x';
        char o = 'o';
        int rivi = 1;
        for (int i = 0; i < this.alusta.maxKoko(); i++) {
            if (rivi == 1 || rivi == 2) {
                x = 'x';
                o = 'o';
            }
            if (rivi == 3 || rivi == 4) {
                x = 'o';
                o = 'x';
            }
            for (int j = 0; j < this.alusta.maxKoko(); j++) {
                if (j % 2 == 0) {
                    this.alusta.lisaaMerkkiLaudalle(i, j, x);
                } else {
                    this.alusta.lisaaMerkkiLaudalle(i, j, o);
                }
            }
            rivi++;
            if (rivi == 5) {
                rivi = 1;
            }
        }
        assertEquals(this.arvioija.arvioiAlusta(this.alusta), 0);
    }
    @Test
    public void testaaArviointia2() {
        char x = 'x';
        char o = 'o';
        int rivi = 1;
        for (int i = 0; i < this.alusta.maxKoko(); i++) {
            if (rivi == 1 || rivi == 2) {
                x = 'x';
                o = 'o';
            }
            if (rivi == 3 || rivi == 4) {
                x = 'o';
                o = 'x';
            }
            for (int j = 0; j < this.alusta.maxKoko(); j++) {
                if (j % 2 == 0) {
                    this.alusta.lisaaMerkkiLaudalle(i, j, x);
                } else {
                    this.alusta.lisaaMerkkiLaudalle(i, j, o);
                }
            }
            rivi++;
            if (rivi == 5) {
                rivi = 1;
            }
        }
        this.alusta.lisaaMerkkiLaudalle(50, 51, ' ');
        this.alusta.lisaaMerkkiLaudalle(51, 51, ' ');
        this.alusta.lisaaMerkkiLaudalle(52, 51, ' ');
        this.alusta.lisaaMerkkiLaudalle(53, 51, ' ');
        this.alusta.lisaaMerkkiLaudalle(54, 51, ' ');
        this.alusta.lisaaMerkkiLaudalle(55, 51, ' ');
        this.alusta.lisaaMerkkiLaudalle(50, 50, ' ');
        this.alusta.lisaaMerkkiLaudalle(51, 50, 'o');
        this.alusta.lisaaMerkkiLaudalle(52, 50, 'o');
        this.alusta.lisaaMerkkiLaudalle(53, 50, 'o');
        this.alusta.lisaaMerkkiLaudalle(54, 50, 'o');
        this.alusta.lisaaMerkkiLaudalle(55, 50, ' ');
        this.alusta.lisaaMerkkiLaudalle(51, 49, ' ');
        this.alusta.lisaaMerkkiLaudalle(52, 49, ' ');
        this.alusta.lisaaMerkkiLaudalle(53, 49, ' ');
        this.alusta.lisaaMerkkiLaudalle(54, 49, ' ');
        this.alusta.lisaaMerkkiLaudalle(55, 49, ' ');
        this.alusta.lisaaMerkkiLaudalle(50, 49, ' ');
        this.alusta.tulostaAlusta();
        assertEquals(-42,this.arvioija.arvioiAlusta(this.alusta));
    }
}

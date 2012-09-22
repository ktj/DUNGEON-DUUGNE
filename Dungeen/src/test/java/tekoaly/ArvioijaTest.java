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
        for (int i = 0; i < this.alusta.getKoko(); i++) {
            if (rivi == 1 || rivi == 2) {
                x = 'x';
                o = 'o';
            }
            if (rivi == 3 || rivi == 4) {
                x = 'o';
                o = 'x';
            }
            for (int j = 0; j < this.alusta.getKoko(); j++) {
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
        this.alusta.lisaaMerkkiLaudalle(51, 50, 'o');
        this.alusta.lisaaMerkkiLaudalle(52, 50, 'o');
        this.alusta.lisaaMerkkiLaudalle(53, 50, 'o');
        this.alusta.lisaaMerkkiLaudalle(54, 50, 'o');

        this.alusta.tulostaAlusta();
        assertEquals(-2222,this.arvioija.arvioiAlusta(this.alusta));
    }
    @Test
    public void testaaArviointia3() {
        char x = 'x';
        char o = 'o';
        this.alusta.lisaaMerkkiLaudalle(51, 50, 'o');

        this.alusta.tulostaAlusta();
        assertEquals(0,this.arvioija.arvioiAlusta(this.alusta));
    }
    @Test
    public void testaaArviointia4() {
        char x = 'x';
        char o = 'o';
        this.alusta.lisaaMerkkiLaudalle(49, 50, x);
        this.alusta.lisaaMerkkiLaudalle(50, 50, o);
        this.alusta.lisaaMerkkiLaudalle(51, 50, x);

        this.alusta.tulostaAlusta();
        assertEquals(0,this.arvioija.arvioiAlusta(this.alusta));
    }
}

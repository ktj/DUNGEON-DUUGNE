package tekoäly;

import logiikka.Alusta;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class PuukasaajaTest {

    Puukasaaja kasaaja;
    Alusta alusta;
    int koko;

    public PuukasaajaTest() {
    }

    @Before
    public void setUp() {
        koko = 3;
        kasaaja = new Puukasaaja();
        alusta = new Alusta(koko, koko);
    }

    @Test
    public void testKasaaPuu1() {
        char merkki = 'x';
        int maksimitaso = 1;
        Puu puu = kasaaja.kasaaPuu(alusta, merkki, maksimitaso);
        assertTrue(puu.haeLapset().size() == koko * koko);
    }

    @Test
    public void testKasaaPuu2() {
        char merkki = 'x';
        int maksimitaso = 2;
        Puu puu = kasaaja.kasaaPuu(alusta, merkki, maksimitaso);
        for (Puu lapsi : puu) {
            assertTrue(lapsi.haeLapset().size() == koko * koko - 1);
        }
    }

    @Test
    public void testKasaaPuuLoytyykoYksiX() {
        char merkki = 'x';
        int maksimitaso = 1;
        boolean loytyi = false;
        int x = 0;
        int y = 0;
        Puu puu = kasaaja.kasaaPuu(alusta, merkki, maksimitaso);
        for (Puu lapsi : puu) {
            if (lapsi.haeAlusta().lueMerkki(x, y) == merkki) {
                if (loytyi) {
                    loytyi = false;
                    break;
                }
                loytyi = true;
            }
        }
        assertTrue(loytyi);
    }
}

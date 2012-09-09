package dungeen.peli;

import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author keus
 */
public class RatkaisijaTest {

    Ratkaisija ratkaisija;
    Alusta alusta;
    int alustakoko = 3;
    int pituus = 3;

    public RatkaisijaTest() {
        ratkaisija = new Ratkaisija();
        alusta = new Alusta(alustakoko);
    }

    @Test
    public void testEtsiVoitto0() {
        assertFalse(ratkaisija.etsiVoitto(alusta, pituus));
    }

    @Test
    public void testEtsiVoitto1() {
        int x = 0;
        int y = 0;
        char merkki = 'x';
        alusta.lisaaMerkkiLaudalle(x, y, merkki);
        assertFalse(ratkaisija.etsiVoitto(alusta, pituus));
    }

    @Test
    public void testEtsiVoitto2() {
        int y = 0;
        char merkki = 'x';
        for (int i = 0; i < pituus - 1; i++) {
            alusta.lisaaMerkkiLaudalle(i, y, merkki);
        }
        alusta.tulostaAlusta();
        assertFalse(ratkaisija.etsiVoitto(alusta, pituus));
    }

    @Test
    public void testEtsiVoitto3() {
        int x = 0;
        char merkki = 'x';
        for (int i = 0; i < pituus - 1; i++) {
            alusta.lisaaMerkkiLaudalle(x, i, merkki);
        }
        alusta.tulostaAlusta();
        assertFalse(ratkaisija.etsiVoitto(alusta, pituus));
    }
    
    
}

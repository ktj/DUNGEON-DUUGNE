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
    public void testEtsiVoittoYksliianVahan1() {
        int y = 0;
        char merkki = 'x';
        for (int i = 0; i < pituus - 1; i++) {
            alusta.lisaaMerkkiLaudalle(i, y, merkki);
        }
        alusta.tulostaAlusta();
        assertFalse(ratkaisija.etsiVoitto(alusta, pituus));
    }

    @Test
    public void testEtsiVoittoYksliianVahan2() {
        int x = 0;
        char merkki = 'x';
        for (int i = 0; i < pituus - 1; i++) {
            alusta.lisaaMerkkiLaudalle(x, i, merkki);
        }
        alusta.tulostaAlusta();
        assertFalse(ratkaisija.etsiVoitto(alusta, pituus));
    }

    @Test
    public void testiEtstiVoitto4() {
        int y = 0;
        char merkki = 'x';
        for (int i = 0; i < pituus; i++) {
            alusta.lisaaMerkkiLaudalle(i, y, merkki);
        }
        alusta.tulostaAlusta();
        assertTrue(ratkaisija.etsiVoitto(alusta, pituus));
    }

    @Test
    public void testiEtstiVoitto5() {
        int x = 0;
        char merkki = 'x';
        for (int i = 0; i < pituus; i++) {
            alusta.lisaaMerkkiLaudalle(x, i, merkki);
        }
        alusta.tulostaAlusta();
        assertTrue(ratkaisija.etsiVoitto(alusta, pituus));
    }

    @Test
    public void testEtstiVoittoaReika1() {
        alustakoko = 5;
        alusta = new Alusta(alustakoko);
        pituus = 3;
        char merkki = 'x';
        alusta.lisaaMerkkiLaudalle(1, 1, merkki);
        alusta.lisaaMerkkiLaudalle(2, 1, merkki);
        alusta.lisaaMerkkiLaudalle(4, 1, merkki);
        assertFalse(ratkaisija.etsiVoitto(alusta, pituus));
        alusta.lisaaMerkkiLaudalle(3, 1, merkki);
        assertTrue(ratkaisija.etsiVoitto(alusta, pituus));
    }

    @Test
    public void testEtstiVoittoaReika2() {
        alustakoko = 5;
        alusta = new Alusta(alustakoko);
        pituus = 3;
        char merkki = 'x';
        alusta.lisaaMerkkiLaudalle(1, 1, merkki);
        alusta.lisaaMerkkiLaudalle(1, 2, merkki);
        alusta.lisaaMerkkiLaudalle(1, 4, merkki);
        assertFalse(ratkaisija.etsiVoitto(alusta, pituus));
        alusta.lisaaMerkkiLaudalle(1, 3, merkki);
        assertTrue(ratkaisija.etsiVoitto(alusta, pituus));
    }
}

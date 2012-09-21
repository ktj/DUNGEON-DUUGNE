package logiikka;

import logiikka.Ratkaisija;
import logiikka.Alusta;
import org.junit.*;
import static org.junit.Assert.*;

public class RatkaisijaTest {

    Ratkaisija ratkaisija;
    Alusta alusta;
    int alustakoko = 3;
    int pituus = 3;
    char merkki;

    public RatkaisijaTest() {
        merkki = 'x';
        ratkaisija = new Ratkaisija();
        alusta = new Alusta(alustakoko, pituus);
    }

    @Test
    public void testEtsiVoitto0() {
        assertFalse(ratkaisija.etsiVoitto(alusta) == 50 || ratkaisija.etsiVoitto(alusta) == -50);
    }

    @Test
    public void testEtsiVoitto1() {
        int x = 0;
        int y = 0;
        alusta.lisaaMerkkiLaudalle(x, y, merkki);
        assertFalse(ratkaisija.etsiVoitto(alusta) == 50 || ratkaisija.etsiVoitto(alusta) == -50);
    }

    @Test
    public void testEtsiVoittoYksliianVahan1() {
        int y = 0;
        for (int i = 0; i < pituus - 1; i++) {
            alusta.lisaaMerkkiLaudalle(i, y, merkki);
        }
        alusta.tulostaAlusta();
        assertFalse(ratkaisija.etsiVoitto(alusta) == 50 || ratkaisija.etsiVoitto(alusta) == -50);
    }

    @Test
    public void testEtsiVoittoYksliianVahan2() {
        int x = 0;
        for (int i = 0; i < pituus - 1; i++) {
            alusta.lisaaMerkkiLaudalle(x, i, merkki);
        }
        alusta.tulostaAlusta();
        assertFalse(ratkaisija.etsiVoitto(alusta) == 50 || ratkaisija.etsiVoitto(alusta) == -50);
    }

    @Test
    public void testiEtstiVoitto4() {
        int y = 0;

        for (int i = 0; i < pituus; i++) {
            alusta.lisaaMerkkiLaudalle(i, y, merkki);
        }
        alusta.tulostaAlusta();
        assertTrue(ratkaisija.etsiVoitto(alusta) == 50 || ratkaisija.etsiVoitto(alusta) == -50);
    }

    @Test
    public void testiEtsiVoitto41() {
        for (int j = 0; j < pituus; j++) {
            alusta = new Alusta(alustakoko, alustakoko);
            for (int i = 0; i < pituus; i++) {
                alusta.lisaaMerkkiLaudalle(i, j, merkki);
            }
            assertTrue(ratkaisija.etsiVoitto(alusta) == 50 || ratkaisija.etsiVoitto(alusta) == -50);
        }
    }

    @Test
    public void testiEtstiVoitto5() {
        int x = 0;
        for (int i = 0; i < pituus; i++) {
            alusta.lisaaMerkkiLaudalle(x, i, merkki);
        }
        alusta.tulostaAlusta();
        assertTrue(ratkaisija.etsiVoitto(alusta) == 50 || ratkaisija.etsiVoitto(alusta) == -50);
    }

    @Test
    public void testiEtsiVoitto51() {
        for (int j = 0; j < pituus; j++) {
            alusta = new Alusta(alustakoko, alustakoko);
            for (int i = 0; i < pituus; i++) {
                alusta.lisaaMerkkiLaudalle(j, i, merkki);
            }
            assertTrue(ratkaisija.etsiVoitto(alusta) == 50 || ratkaisija.etsiVoitto(alusta) == -50);
        }
    }

    @Test
    public void testEtstiVoittoaReika1() {
        alustakoko = 5;
        pituus = 3;
        alusta = new Alusta(alustakoko, pituus);
        alusta.lisaaMerkkiLaudalle(1, 1, merkki);
        alusta.lisaaMerkkiLaudalle(2, 1, merkki);
        alusta.lisaaMerkkiLaudalle(4, 1, merkki);
        assertFalse(ratkaisija.etsiVoitto(alusta) == 50 || ratkaisija.etsiVoitto(alusta) == -50);
        alusta.lisaaMerkkiLaudalle(3, 1, merkki);
        assertTrue(ratkaisija.etsiVoitto(alusta) == 50 || ratkaisija.etsiVoitto(alusta) == -50);
    }

    @Test
    public void testEtstiVoittoaReika2() {
        alustakoko = 5;
        pituus = 3;
        alusta = new Alusta(alustakoko, pituus);
        alusta.lisaaMerkkiLaudalle(1, 1, merkki);
        alusta.lisaaMerkkiLaudalle(1, 2, merkki);
        alusta.lisaaMerkkiLaudalle(1, 4, merkki);
        assertFalse(ratkaisija.etsiVoitto(alusta) == 50 || ratkaisija.etsiVoitto(alusta) == -50);
        alusta.lisaaMerkkiLaudalle(1, 3, merkki);
        assertTrue(ratkaisija.etsiVoitto(alusta) == 50 || ratkaisija.etsiVoitto(alusta) == -50);
    }

    @Test
    public void testEtsiVoittoTaysi() {
        alustakoko = 100;
        pituus = 3;
        alusta = new Alusta(alustakoko, 3);

        char x = 'x';
        char o = 'o';
        int rivi = 1;
        for (int i = 0; i < alustakoko; i++) {
            if (rivi == 1 || rivi == 2) {
                x = 'x';
                o = 'o';
            }
            if (rivi == 3 || rivi == 4) {
                x = 'o';
                o = 'x';
            }
            for (int j = 0; j < alustakoko; j++) {
                if (j % 2 == 0) {
                    alusta.lisaaMerkkiLaudalle(i, j, x);
                } else {
                    alusta.lisaaMerkkiLaudalle(i, j, o);
                }
            }
            rivi++;
            if (rivi == 5) {
                rivi = 1;
            }
        }
        alusta.tulostaAlusta();
        assertFalse(ratkaisija.etsiVoitto(alusta) == 50 || ratkaisija.etsiVoitto(alusta) == -50);
    }

    @Test
    public void etsiVoittoAlas() {
        alustakoko = 100;
        pituus = 5;
        alusta = new Alusta(alustakoko, pituus);
        for (int i = 40; i < 45; i++) {
            alusta.lisaaMerkkiLaudalle(40, i, merkki);
        }
        alusta.tulostaAlusta();
        assertTrue(ratkaisija.etsiVoitto(alusta) == 50 || ratkaisija.etsiVoitto(alusta) == -50);
    }
    @Test
    public void etsiVoittoOikealle(){
        alustakoko = 100;
        pituus = 5;
        alusta = new Alusta(alustakoko, pituus);
        for (int i = 40; i < 45; i++) {
            alusta.lisaaMerkkiLaudalle(i, 40, merkki);
        }
        alusta.tulostaAlusta();
        assertTrue(ratkaisija.etsiVoitto(alusta) == 50 || ratkaisija.etsiVoitto(alusta) == -50);
    }
    @Test
    public void etsiVoittoOikealleAlas(){
        alustakoko = 100;
        pituus = 5;
        alusta = new Alusta(alustakoko, pituus);
        alusta.lisaaMerkkiLaudalle(40, 40, merkki);
        alusta.lisaaMerkkiLaudalle(41, 41, merkki);
        alusta.lisaaMerkkiLaudalle(42, 42, merkki);
        alusta.lisaaMerkkiLaudalle(43, 43, merkki);
        alusta.lisaaMerkkiLaudalle(44, 44, merkki);
        alusta.tulostaAlusta();
        assertTrue(ratkaisija.etsiVoitto(alusta) == 50 || ratkaisija.etsiVoitto(alusta) == -50);
    }
    @Test
    public void etsiVoittoVasemmalleAlas(){
        alustakoko = 100;
        pituus = 5;
        alusta = new Alusta(alustakoko, pituus);
        alusta.lisaaMerkkiLaudalle(44, 40, merkki);
        alusta.lisaaMerkkiLaudalle(43, 41, merkki);
        alusta.lisaaMerkkiLaudalle(42, 42, merkki);
        alusta.lisaaMerkkiLaudalle(41, 43, merkki);
        alusta.lisaaMerkkiLaudalle(40, 44, merkki);
        alusta.tulostaAlusta();
        assertTrue(ratkaisija.etsiVoitto(alusta) == 50 || ratkaisija.etsiVoitto(alusta) == -50);
    }
}

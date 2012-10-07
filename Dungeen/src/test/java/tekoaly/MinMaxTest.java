package tekoaly;

import logiikka.Alusta;
import logiikka.Ratkaisija;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class MinMaxTest {

    Alusta alusta;
    MinMax minmax;
    int voittorivinpituus, koko;
    Puu puu;
    Ratkaisija ratkaisijamock;
    MinMax minmaxoikea;
    Puukasaaja kasaaja;

    public MinMaxTest() {
    }

    @Before
    public void setUp() {
        voittorivinpituus = 3;
        koko = 3;
        alusta = new Alusta(koko, voittorivinpituus);
        puu = new Puu(alusta);
        ratkaisijamock = mock(Ratkaisija.class);
        minmax = new MinMax(ratkaisijamock);
        minmaxoikea = new MinMax(new Ratkaisija());
        kasaaja = new Puukasaaja(new Arvioija());
    }

    @Test
    public void testAloitaMax() {
        when(ratkaisijamock.etsiVoitto(eq(alusta))).thenReturn(0);
        minmax.aloitaMax(puu);
        verify(ratkaisijamock).etsiVoitto(eq(alusta));
    }

    @Test
    public void testAloitaMin() {
        when(ratkaisijamock.etsiVoitto(eq(alusta))).thenReturn(0);
        minmax.aloitaMax(puu);
        verify(ratkaisijamock).etsiVoitto(eq(alusta));
    }

    @Test
    public void testAloitaMinVoitto() {
        Alusta alusta10 = new Alusta(10, 5);

        alusta10.lisaaMerkkiLaudalle(1, 1, 'x');
        alusta10.lisaaMerkkiLaudalle(2, 2, 'x');
        alusta10.lisaaMerkkiLaudalle(3, 3, 'x');
        alusta10.lisaaMerkkiLaudalle(4, 4, 'x');
        alusta10.lisaaMerkkiLaudalle(1, 2, 'o');
        alusta10.lisaaMerkkiLaudalle(1, 3, 'o');
        alusta10.lisaaMerkkiLaudalle(1, 4, 'o');
        alusta10.lisaaMerkkiLaudalle(1, 5, 'o');

//        alusta10.tulostaAlusta();
        Puu kasattu = kasaaja.kasaaPuu(alusta10, 'o', 4);

        Alusta tulos = minmaxoikea.aloitaMin(kasattu);
        alusta10.lisaaMerkkiLaudalle(1, 6, 'o');
//        tulos.tulostaAlusta();

        onkoSamat(alusta10, tulos);
    }

    @Test
    public void testAloitaMaxVoitto() {
        Alusta alusta10 = new Alusta(10, 5);

        alusta10.lisaaMerkkiLaudalle(0, 1, 'x');
        alusta10.lisaaMerkkiLaudalle(1, 2, 'x');
        alusta10.lisaaMerkkiLaudalle(2, 3, 'x');
        alusta10.lisaaMerkkiLaudalle(3, 4, 'x');
        alusta10.lisaaMerkkiLaudalle(0, 2, 'o');
        alusta10.lisaaMerkkiLaudalle(0, 3, 'o');
        alusta10.lisaaMerkkiLaudalle(0, 4, 'o');
        alusta10.lisaaMerkkiLaudalle(0, 5, 'o');

//        alusta10.tulostaAlusta();
        Puu kasattu = kasaaja.kasaaPuu(alusta10, 'x', 4);

        Alusta tulos = minmaxoikea.aloitaMax(kasattu);
        alusta10.lisaaMerkkiLaudalle(4, 5, 'x');

        onkoSamat(alusta10, tulos);
    }

    @Test
    public void testAloitaMinEsto() {
        Alusta alusta10 = new Alusta(10, 5);

        alusta10.lisaaMerkkiLaudalle(0, 1, 'x');
        alusta10.lisaaMerkkiLaudalle(1, 2, 'x');
        alusta10.lisaaMerkkiLaudalle(2, 3, 'x');
        alusta10.lisaaMerkkiLaudalle(3, 4, 'x');
        alusta10.lisaaMerkkiLaudalle(0, 2, 'o');
        alusta10.lisaaMerkkiLaudalle(0, 3, 'o');
        alusta10.lisaaMerkkiLaudalle(0, 4, 'o');

//        alusta10.tulostaAlusta();

        Puu kasattu = kasaaja.kasaaPuu(alusta10, 'o', 4);

        Alusta tulos = minmaxoikea.aloitaMin(kasattu);
        alusta10.lisaaMerkkiLaudalle(4, 5, 'o');

//        tulos.tulostaAlusta();

        onkoSamat(alusta10, tulos);
    }

    @Test
    public void testAloitaMaxEsto() {
        Alusta alusta10 = new Alusta(10, 5);

        alusta10.lisaaMerkkiLaudalle(0, 1, 'x');
        alusta10.lisaaMerkkiLaudalle(1, 2, 'x');
        alusta10.lisaaMerkkiLaudalle(2, 3, 'x');
        alusta10.lisaaMerkkiLaudalle(0, 2, 'o');
        alusta10.lisaaMerkkiLaudalle(0, 3, 'o');
        alusta10.lisaaMerkkiLaudalle(0, 4, 'o');
        alusta10.lisaaMerkkiLaudalle(0, 5, 'o');

//        alusta10.tulostaAlusta();
        
        Puu kasattu = kasaaja.kasaaPuu(alusta10, 'x', 2);

        Alusta tulos = minmaxoikea.aloitaMax(kasattu);
        alusta10.lisaaMerkkiLaudalle(0, 6, 'x');
        
//        tulos.tulostaAlusta();

        onkoSamat(alusta10, tulos);
    }

    private void onkoSamat(Alusta odotettu, Alusta tulos) {
        for (int i = 0; i < odotettu.getKoko(); i++) {
            for (int j = 0; j < odotettu.getKoko(); j++) {
                assertTrue(odotettu.lueMerkki(i, j) == tulos.lueMerkki(i, j));
            }
        }
    }
}

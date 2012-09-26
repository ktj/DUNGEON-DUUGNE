package tekoaly;

import kayttoliittyma.Graafinenkayttoliittyma;
import tekoaly.Tekoaly;
import tekoaly.Puukasaaja;
import tekoaly.MinMax;
import tekoaly.Puu;
import logiikka.Alusta;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatcher;
import static org.mockito.Mockito.*;

public class TekoalyTest {

    Puukasaaja kasaaja;
    MinMax minmax;
    Tekoaly tekoaly;
    Graafinenkayttoliittyma gui;
    int koko;

    public TekoalyTest() {
    }

    @Before
    public void setUp() {
        kasaaja = mock(Puukasaaja.class);
        minmax = mock(MinMax.class);
        gui = mock(Graafinenkayttoliittyma.class);
        tekoaly = new Tekoaly(minmax, kasaaja, gui);
        this.koko = 3;
    }

    @Test
    public void testSeuraava_siirto() {

        Alusta alusta = new Alusta(koko, koko);

        char merkki = 'x';

        tekoaly.seuraava_siirto(alusta, merkki);

        verify(kasaaja).kasaaPuu(eq(alusta), eq(merkki), anyInt());
        verify(minmax).aloitaMax(any(Puu.class));

    }

    @Test
    public void testSeuraava_siirto2() {
        Alusta alusta = new Alusta(koko, koko);
        char merkki = 'o';
        tekoaly.seuraava_siirto(alusta, merkki);
        verify(kasaaja).kasaaPuu(eq(alusta), eq(merkki), anyInt());
        verify(minmax).aloitaMin(any(Puu.class));

    }
}

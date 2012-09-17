package tekoäly;

import logiikka.Alusta;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatcher;
import static org.mockito.Mockito.*;

public class TekoälyTest {

    Puukasaaja kasaaja;
    MinMax minmax;
    Tekoäly tekoaly;
    int koko;

    public TekoälyTest() {
    }

    @Before
    public void setUp() {
        kasaaja = mock(Puukasaaja.class);
        minmax = mock(MinMax.class);
        tekoaly = new Tekoäly(minmax, kasaaja);
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

package tekoäly;

import dungeen.peli.Alusta;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.internal.matchers.LessOrEqual;
import org.mockito.internal.matchers.LessThan;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

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

    class OnYlikoko extends ArgumentMatcher<Integer> {

        @Override
        public boolean matches(Object argument) {
            int vertaa = (Integer) argument;
            return (koko >= vertaa);
        }
    }

    @Test
    public void testSeuraava_siirto() {

        Alusta alusta = new Alusta(koko);

        char merkki = 'x';

        tekoaly.seuraava_siirto(alusta, merkki);

        verify(kasaaja).kasaaPuu(eq(alusta), eq(merkki), anyInt());
        verify(minmax).aloitaMax(any(Puu.class), intThat(new OnYlikoko()));

    }

    @Test
    public void testSeuraava_siirto2() {
        Alusta alusta = new Alusta(koko);
        char merkki = 'o';
        tekoaly.seuraava_siirto(alusta, merkki);
        verify(kasaaja).kasaaPuu(eq(alusta), eq(merkki), anyInt());
        verify(minmax).aloitaMin(any(Puu.class), intThat(new OnYlikoko()));

    }
}

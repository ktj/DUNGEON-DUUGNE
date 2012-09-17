package tekoäly;

import java.util.Set;
import java.util.HashSet;
import logiikka.Alusta;
import logiikka.Ratkaisija;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class MinMaxTest {

    MinMax minmax;
    int voittorivinpituus;
    Puu puumock;
    Ratkaisija ratkaisijamock;

    public MinMaxTest() {
    }

    @Before
    public void setUp() {
        puumock = mock(Puu.class);
        ratkaisijamock = mock(Ratkaisija.class);
        voittorivinpituus = 3;
        minmax = new MinMax(ratkaisijamock);
    }

    @Test
    public void testAloitaMax() {
        Alusta alusta = new Alusta(3, voittorivinpituus);
        Set<Puu> set = new HashSet<Puu>();

        when(ratkaisijamock.etsiVoitto(eq(alusta))).thenReturn(0);
        when(puumock.haeAlusta()).thenReturn(alusta);
        when(puumock.haeLapset()).thenReturn(set);

        minmax.aloitaMax(puumock);

        verify(ratkaisijamock).etsiVoitto(eq(alusta));

        verify(puumock).haeAlusta();

    }

    @Test
    public void testAloitaMin() {
        Alusta alusta = new Alusta(3, 3);
        Set<Puu> set = new HashSet<Puu>();

        when(ratkaisijamock.etsiVoitto(eq(alusta))).thenReturn(0);
        when(puumock.haeAlusta()).thenReturn(alusta);
        when(puumock.haeLapset()).thenReturn(set);

        minmax.aloitaMax(puumock);

        verify(ratkaisijamock).etsiVoitto(eq(alusta));

        verify(puumock).haeAlusta();


    }
}

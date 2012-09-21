package tekoaly;

import java.util.Set;
import java.util.HashSet;
import logiikka.Alusta;
import logiikka.Ratkaisija;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class MinMaxTest {

    Alusta alusta;
    MinMax minmax;
    int voittorivinpituus, koko;
    Puu puu;
    Ratkaisija ratkaisijamock;

    public MinMaxTest() {
    }

    @Before
    public void setUp() {
        voittorivinpituus = 3;
        koko = 3;
        alusta = new Alusta(koko, voittorivinpituus);
        puu = new Puu(alusta);
        ratkaisijamock = mock(Ratkaisija.class);
        minmax = new MinMax(ratkaisijamock,new Arvioija());
    }

    @Test
    public void testAloitaMax() {
        Set<Puu> set = new HashSet<Puu>();
        when(ratkaisijamock.etsiVoitto(eq(alusta))).thenReturn(0);
        minmax.aloitaMax(puu);
        verify(ratkaisijamock).etsiVoitto(eq(alusta));
    }

    @Test
    public void testAloitaMin() {
        Set<Puu> set = new HashSet<Puu>();
        when(ratkaisijamock.etsiVoitto(eq(alusta))).thenReturn(0);
        minmax.aloitaMax(puu);
        verify(ratkaisijamock).etsiVoitto(eq(alusta));
    }
}

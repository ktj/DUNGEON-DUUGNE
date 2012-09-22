package tekoaly;

import tekoaly.Puu;
import logiikka.Alusta;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class PuuTest {

    Puu puu;
    Alusta alustamock;
    Puu puumock;

    public PuuTest() {
    }

    @Before
    public void setUp() {
        alustamock = mock(Alusta.class);
        puu = new Puu(alustamock);
        puumock = mock(Puu.class);
    }

    @Test
    public void testLisaaLapsi() {
        puu.lisaaLapsi(puumock);
        for (Puu lapsi : puu) {
            lapsi.haeAlusta();
            verify(puumock).haeAlusta();
        }
    }

    @Test
    public void testMontaLasta() {
        for (int i = 1; i <= 10; i++) {
            puu.lisaaLapsi(new Puu(alustamock));
            assertTrue(puu.haeLapset().size() == i);
        }
    }

    @Test
    public void testHaeAlusta() {
        Alusta alusta2 = puu.haeAlusta();
        alusta2.getKoko();
        verify(alustamock).getKoko();
    }


    @Test
    public void testIterator() {
        assertFalse(puu.iterator().hasNext());
        puu.lisaaLapsi(new Puu(alustamock));
        assertTrue(puu.iterator().hasNext());
    }
}

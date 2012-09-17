package dungeen.peli;

import org.junit.*;
import static org.junit.Assert.*;

public class AlustaTest {
    
    Alusta alusta;
    int koko = 3;
    
    public AlustaTest() {
        alusta = new Alusta(koko);
    }
   
    @Test
    public void testTyhjaaLauta() {
        int x = 0;
        int y = 0;
        char merkki = 'x';
        alusta.lisaaMerkkiLaudalle(x, y, merkki);
        assertSame(alusta.lueMerkki(x, y), merkki);
        alusta.tyhjaaLauta();
        assertNotSame(alusta.lueMerkki(x, y), merkki);
        assertTrue(alusta.onkoTyhja(x, y));
    }

    @Test
    public void testOnkoTyhja() {
        for(int i=0; i<koko; i++){
            for(int j=0; j<koko; j++){
                assertTrue(alusta.onkoTyhja(j, j));
                assertNotNull(alusta.lueMerkki(j, j));
            }
        }
        
    }

    @Test
    public void testLisaaMerkkiLaudalle() {
        int x = 0;
        int y = 0;
        char merkki = 'x';
        int x2 = koko -1;
        int y2 = koko -1;
        char merkki2 = 'o';
        alusta.lisaaMerkkiLaudalle(x, y, merkki);
        alusta.lisaaMerkkiLaudalle(x2, y2, merkki2);
        assertSame(alusta.lueMerkki(x, y), merkki);
        assertSame(alusta.lueMerkki(x2, y2), merkki2);
    }
    
    @Test
    public void testMaxKoko() {
        assertEquals(alusta.maxKoko(), koko);
    }
}

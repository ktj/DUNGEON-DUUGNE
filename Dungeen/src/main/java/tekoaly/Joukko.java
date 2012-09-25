package tekoaly;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Joukko implements Iterable<Puu> {

    private Puu[] puut;
    private int tilaa;
    private int koko;

    public Joukko(int koko) {
        this.puut = new Puu[koko];
        tilaa = koko;
        koko = 0;
    }

    public void add(Puu puu) {
        if (tilaa <= 0) {
            laajenna();
        }
        puut[koko] = puu;
        koko++;
        tilaa--;
    }
    
    public boolean isEmpty(){
        return koko == 0;
    }
    
    public int size(){
        return koko;
    }

    private void laajenna() {
        Puu[] puutkopio = new Puu[puut.length + 2];
        for (int i = 0; i < puut.length; i++) {
            puutkopio[i] = puut[i];
        }
        puut = puutkopio;
    }

    public Iterator<Puu> iterator() {
        return new Iterator<Puu>() {
            
            private int i = 0;

            public boolean hasNext() {
                return i < koko;
            }

            public Puu next() {
                if(hasNext())
                    return puut[i++];
                else
                    throw new NoSuchElementException();
            }

            public void remove() {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        };
    }
}

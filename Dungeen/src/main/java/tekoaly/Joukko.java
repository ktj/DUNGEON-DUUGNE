package tekoaly;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Joukko implements Iterable<Puu> {

    private Puu[] puut;
    private int tilaa;
    private int koko;

    /**
     * Oma tietorakenne, mikä pitää tallessa puun solmujen lapsia.
     * @param koko Lapsien määrä
     */
    public Joukko(int koko) {
        this.puut = new Puu[koko];
        tilaa = koko;
        koko = 0;
    }

    /**
     * lisätään lapsi puuhun. Mikäli tila loppuu, laajennetaan.
     * @param puu Lisättävä lapsi
     */
    public void add(Puu puu) {
        if (tilaa <= 0) {
            laajenna();
        }
        puut[koko] = puu;
        koko++;
        tilaa--;
    }

    public boolean isEmpty() {
        return koko == 0;
    }

    public int size() {
        return koko;
    }

    /**
     * Laajennetaan käytettävää taulua tekemällä uusi 2 paikaa suurempi taulu ja kopioimalla vanha taulu uuteen.
     */
    private void laajenna() {
        Puu[] puutkopio = new Puu[puut.length + 2];
        for (int i = 0; i < puut.length; i++) {
            puutkopio[i] = puut[i];
        }
        puut = puutkopio;
    }

    /**
     * Iteraattori, auttaa puun läpikäymisessä
     * @return Iteraattorin
     */
    public Iterator<Puu> iterator() {
        return new Iterator<Puu>() {
            private int i = 0;

            public boolean hasNext() {
                return i < koko;
            }

            public Puu next() {
                if (hasNext()) {
                    return puut[i++];
                } else {
                    throw new NoSuchElementException();
                }
            }

            public void remove() {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        };
    }
}

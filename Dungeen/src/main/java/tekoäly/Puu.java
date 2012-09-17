package tekoäly;

import logiikka.Alusta;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Puu implements Iterable<Puu> {

    private Set<Puu> lapset;
    private Alusta alusta;

    public Puu(Alusta alusta) {
        lapset = new HashSet<Puu>();
        this.alusta = alusta;
    }

    public boolean lisaaLapsi(Puu n) {
        return lapset.add(n);
    }

    public Alusta haeAlusta() {
        return alusta;
    }

    public Set<Puu> haeLapset() {
        return lapset;
    }

    public Iterator<Puu> iterator() {
        return lapset.iterator();
    }
}

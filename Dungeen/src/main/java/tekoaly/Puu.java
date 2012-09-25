package tekoaly;

import logiikka.Alusta;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Puu implements Iterable<Puu>, Comparable<Puu> {

    private Joukko lapset;
    private Alusta alusta;

    public Puu(Alusta alusta) {
        lapset = new Joukko(alusta.getKoko()*alusta.getKoko());
        this.alusta = alusta;
    }

    public void lisaaLapsi(Puu n) {
        lapset.add(n);
    }

    public Alusta haeAlusta() {
        return alusta;
    }

    public Joukko haeLapset() {
        return lapset;
    }

    public Iterator<Puu> iterator() {
        return lapset.iterator();
    }

    public int compareTo(Puu t) {
        return this.haeAlusta().getArvio() - (t.haeAlusta().getArvio());
    }
}

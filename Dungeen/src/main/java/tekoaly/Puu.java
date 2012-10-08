package tekoaly;

import logiikka.Alusta;
import java.util.Iterator;

/**
 * Pelipuun tietorakenne. Joko juuri, solmu tai lehti tilanteen mukaan.
 */
public class Puu implements Iterable<Puu>, Comparable<Puu> {

    private Joukko lapset;
    private Alusta alusta;

    /**
     * Luo uuden joukon alustan tyhjän tilan mukaan, eli sen mukaan, kuinka paljon lapsia tietyllä alustalla voi olla.
     * @param alusta Nykyinen tilanne
     */
    public Puu(Alusta alusta) {
        lapset = new Joukko(alusta.tyhjaaTilaa());
        this.alusta = alusta;
    }

    /**
     * Lisää puulle lapsen
     * @param lapsi Lisättävä lapsi 
     */
    public void lisaaLapsi(Puu lapsi) {
        lapset.add(lapsi);
    }

    /**
     * Palauttaa solmun arvon, eli alustan
     * @return Palautettava alusta
     */
    public Alusta haeAlusta() {
        return alusta;
    }

    /**
     * Palauttaa solmun lapset
     * @return Solmun lapset Joukkona.
     */
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

package tekoaly;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import logiikka.Alusta;
import logiikka.Ratkaisija;

/**
 * Minmax luokka, joka laskee optimaalisimman reitin voittoon käyttäen arvioita hyväkseen.
 */
public class MinMax {

    private Ratkaisija ratkaisija;

    public MinMax(Ratkaisija ratkaisija) {
        this.ratkaisija = ratkaisija;
    }

    /**
     * Tuple apuluokka, käytetään moniajon kanssa, missä lasketut puun lapset säilötään tauluun tupleina, eli alusta, arvo pareina
     */
    private class Tuple<X, Y> {

        public final X x;
        public final Y y;

        public Tuple(X x, Y y) {
            this.x = x;
            this.y = y;
        }
    }

    /**
     * Aloituskohta, kun halutaan etsiä maksimiarvo.
     * Ei varsinaisesti vielä laske mitään, vaan apuna valittaessa yksiajon ja moniajon väliltä
     * @param puu Pelipuun juuri
     * @return Parhaimman siirron, eli suurimman arvon antavan siirron.
     */
    public Alusta aloitaMax(Puu puu) {
        Alusta alusta = puu.haeAlusta();
        if (onkovalmis(alusta)) {
            return alusta;
        }
//        return moniajoMax(puu);
        return yksiajoMax(puu);
    }

    /**
     * Aloituskohta, kun halutaan etsiä minimiarvo
     * Ei varsinaisesti vielä laske mitään, vaan apuna valittaessa yksiajon ja moniajon väliltä
     * @param puu Pelipuun juuri
     * @return Parhaimman siirron, eli pienimmän arvon antavan siirron
     */
    public Alusta aloitaMin(Puu puu) {
        Alusta alusta = puu.haeAlusta();
        if (onkovalmis(alusta)) {
            return alusta;
        }
//        return moniajoMin(puu);
        return yksiajoMin(puu);
    }

    /**
     * 1. Kierroksen minmaxin minimiarvon laskeminen
     * Moniajo-versio, eli laskee lapsia samanaikaisesti suoritettavan koneen ytimien mukaan
     * Ei käytä alfa beta karsintaa ensimmäisessä kierroksessa
     * @param puu Pelipuun juuri
     * @return Parhaimman siirron, eli pienimmän arvon antavan siirron
     * @throws RuntimeException 
     */
    private Alusta moniajoMin(Puu puu) throws RuntimeException {
        int threads = Runtime.getRuntime().availableProcessors();
        ExecutorService service = Executors.newFixedThreadPool(threads);
        ArrayList<Future<Tuple<Integer, Alusta>>> futures = new ArrayList<Future<Tuple<Integer, Alusta>>>();
        for (final Puu lapsi : puu.haeLapset()) {
            Callable<Tuple<Integer, Alusta>> callable = new Callable<Tuple<Integer, Alusta>>() {

                public Tuple<Integer, Alusta> call() throws Exception {
                    int arvo = maksimi(lapsi, Integer.MIN_VALUE, Integer.MAX_VALUE, 1);
                    return new Tuple<Integer, Alusta>(arvo, lapsi.haeAlusta());
                }
            };

            futures.add(service.submit(callable));
        }
        service.shutdown();

        Tuple<Integer, Alusta> tuple = null;
        Alusta pienin = puu.haeAlusta();
        boolean paivitetty = false;
        int v = Integer.MAX_VALUE;
        int lapsenarvo;

        for (Future<Tuple<Integer, Alusta>> lapsi : futures) {

            try {
                tuple = lapsi.get();
            } catch (Exception ex) {
                throw new RuntimeException("Moniajos vikaa!");
            }
            lapsenarvo = tuple.x;
            if (lapsenarvo < v || !paivitetty) {
                pienin = tuple.y;
                v = lapsenarvo;
                paivitetty = true;
            }
            if (lapsenarvo == Integer.MIN_VALUE) {
                break;
            }
        }
        return pienin;
    }

    /**
     * 1. Kierroksen minmaxin maksimiarvon laskeminen
     * Moniajo-versio, eli laskee lapsia samanaikaisesti suoritettavan koneen ytimien mukaan
     * Ei käytä alfa beta karsintaa ensimmäisessä kierroksessa
     * @param puu
     * @return Parhaimman siirron, eli suurimman arvon antavan siirron
     * @throws RuntimeException 
     */
    private Alusta moniajoMax(Puu puu) throws RuntimeException {
        int threads = Runtime.getRuntime().availableProcessors();
        ExecutorService service = Executors.newFixedThreadPool(threads);

        ArrayList<Future<Tuple<Integer, Alusta>>> futures = new ArrayList<Future<Tuple<Integer, Alusta>>>();
        for (final Puu lapsi : puu.haeLapset()) {
            Callable<Tuple<Integer, Alusta>> callable = new Callable<Tuple<Integer, Alusta>>() {

                public Tuple<Integer, Alusta> call() throws Exception {
                    int arvo = minimi(lapsi, Integer.MIN_VALUE, Integer.MAX_VALUE, 1);
                    return new Tuple<Integer, Alusta>(arvo, lapsi.haeAlusta());
                }
            };

            futures.add(service.submit(callable));
        }
        service.shutdown();

        Tuple<Integer, Alusta> tuple = null;
        Alusta suurin = puu.haeAlusta();
        boolean paivitetty = false;
        int v = Integer.MIN_VALUE;
        int lapsenarvo;

        for (Future<Tuple<Integer, Alusta>> lapsi : futures) {
            try {
                tuple = lapsi.get();
            } catch (Exception ex) {
                throw new RuntimeException("Moniajos vikaa!");
            }
            lapsenarvo = tuple.x;
            if (lapsenarvo > v || !paivitetty) {
                suurin = tuple.y;
                v = lapsenarvo;
                paivitetty = true;
            }
            if (lapsenarvo == Integer.MAX_VALUE) {
                break;
            }
        }

        return suurin;
    }

    /**
     * 1. Kierroksen minmaxin minimiarvon laskeminen
     * Lineaarinen ajo, eli laskee järjestyksessä lapset
     * Käyttää alfa beta karsintaa.
     * @param puu Pelipuun juuri
     * @return Parhaimman siirron, eli pienimmän arvon antavan siirron
     */
    private Alusta yksiajoMin(Puu puu) {
        Alusta pienin = puu.haeAlusta();
        boolean paivitetty = false;
        int v = Integer.MAX_VALUE;
        int lapsenarvo;

        int alfa = Integer.MIN_VALUE;
        int beta = Integer.MAX_VALUE;

        for (Puu lapsi : puu.haeLapset()) {
            lapsenarvo = maksimi(lapsi, alfa, beta, 1);
            if (lapsenarvo < v || !paivitetty) {
                pienin = lapsi.haeAlusta();
                v = lapsenarvo;
                paivitetty = true;
            }
            if (lapsenarvo == Integer.MIN_VALUE) {
                break;
            }
            beta = Math.min(beta, v);
        }
        return pienin;
    }

    /**
     * 1. Kierroksen minmaxin maksimiarvon laskeminen
     * Lineaarinen ajo, eli laskee järjestyksessä lapset
     * Käyttää alfa beta karsintaa.
     * @param puu Pelipuun juuri
     * @return Parhaimman siirron, eli suurimman arvon antavan siirron
     */
    private Alusta yksiajoMax(Puu puu) {
        Alusta suurin = puu.haeAlusta();
        boolean paivitetty = false;
        int v = Integer.MIN_VALUE;
        int lapsenarvo;

        int alfa = Integer.MIN_VALUE;
        int beta = Integer.MAX_VALUE;


        for (Puu lapsi : puu.haeLapset()) {
            lapsenarvo = minimi(lapsi, alfa, beta, 1);
            if (lapsenarvo > v || !paivitetty) {
                suurin = lapsi.haeAlusta();
                v = lapsenarvo;
                paivitetty = true;
            }
            if (lapsenarvo == Integer.MAX_VALUE) {
                break;
            }
            alfa = Math.max(alfa, v);
        }
        return suurin;
    }

    /**
     * Etsii onko alusta täynnä ja/tai siellä on voittorivi
     * @param alusta Tarkistettava alusta
     * @return True jos alusta on täynnä ja/tai siellä on voittorivi
     */
    private boolean onkovalmis(Alusta alusta) {
        int ratkaisuarvo = ratkaisija.etsiVoitto(alusta);
        return (alusta.onkoLautaTaynna() || ratkaisuarvo != 0);
    }

    /**
     * Minmaxin kierros, maksimi-osa
     * Katsoo puun lapset kutsuu minimiä jokaiselle. Näistä lapsista suurin palautetaan.
     * Mikäli suurin mahdollinen arvo, eli suotuisa voittorivi, löydetään keskeytetään suoritus.
     * @param puu Solmu/Lehti mitä tutkitaan
     * @param alfa Maksimin suurin saatu arvo
     * @param beta Minimin pienin saatu arvo
     * @param taso Puun syvyys. Lähempänä juurta olevilla solmuilla/pelitilanteilla on parempi arvo.
     * @return Suurimman lapsen arvio
     */
    private int maksimi(Puu puu, int alfa, int beta, int taso) {
        Alusta alusta = puu.haeAlusta();
        int ratkaisuarvo = ratkaisija.etsiVoitto(alusta);
        if (onkovalmis(alusta)) {
            return ratkaisuarvo / taso;
        }
        int v = Integer.MIN_VALUE;
        for (Puu lapsi : puu.haeLapset()) {
            v = Math.max(v, minimi(lapsi, alfa, beta, taso++));
            if (v >= beta) {
                return v;
            }
            alfa = Math.max(alfa, v);
        }
        if (puu.haeLapset().isEmpty()) {
            v = alusta.getArvio() / taso;
        }
        return v;
    }

    /**
     * Minmaxin kierros, minimi-osa
     * Katsoo puun lapset kutsuu maksimia jokaiselle. Näistä lapsista pienin palautetaan.
     * Mikäli pienin mahdollinen arvo, eli suotuisa voittorivi, löydetään keskeytetään suoritus.
     * @param puu Solmu/Lehti mitä tutkitaan
     * @param alfa Maksimin suurin saatu arvo
     * @param beta Minimin pienin saatu arvo
     * @param taso Puun syvyys. Lähempänä juurta olevilla solmuilla/pelitilanteilla on parempi arvo.
     * @return Pienimmän lapsen arvio
     */
    private int minimi(Puu puu, int alfa, int beta, int taso) {
        Alusta alusta = puu.haeAlusta();
        int ratkaisuarvo = ratkaisija.etsiVoitto(alusta);
        if (alusta.onkoLautaTaynna() || ratkaisuarvo != 0) {
            return ratkaisuarvo / taso;
        }
        int v = Integer.MAX_VALUE;


        for (Puu lapsi : puu.haeLapset()) {
            v = Math.min(v, maksimi(lapsi, alfa, beta, taso++));
            if (v <= alfa) {
                return v;
            }
            beta = Math.min(beta, v);
        }
        if (puu.haeLapset().isEmpty()) {
            v = alusta.getArvio() / taso;
        }
        return v;
    }
}

package tekoaly;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import logiikka.Alusta;
import logiikka.Ratkaisija;

public class MinMax {

    private Ratkaisija ratkaisija;
    private Arvioija arvioija;

    public MinMax(Ratkaisija ratkaisija, Arvioija arvioija) {
        this.ratkaisija = ratkaisija;
        this.arvioija = arvioija;
    }

    private class Tuple<X, Y> {

        public final X x;
        public final Y y;

        public Tuple(X x, Y y) {
            this.x = x;
            this.y = y;
        }
    }

    public Alusta aloitaMax(Puu puu) {
        Alusta alusta = puu.haeAlusta();
        if (onkovalmis(alusta)) {
            return alusta;
        }
//        return moniajoMax(puu);
        return yksiajoMax(puu);
    }

    public Alusta aloitaMin(Puu puu) {
        Alusta alusta = puu.haeAlusta();
        if (onkovalmis(alusta)) {
            return alusta;
        }
//        return moniajoMin(puu);
        return yksiajoMin(puu);
    }

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
            System.out.println("minimi: " + v);
        }
        System.out.println("Valittu:" + v);
        return suurin;
    }

    private boolean onkovalmis(Alusta alusta) {
        int ratkaisuarvo = ratkaisija.etsiVoitto(alusta);
        return (alusta.onkoLautaTaynna() || ratkaisuarvo != 0);
    }

    private int maksimi(Puu puu, int alfa, int beta, int taso) {
        Alusta alusta = puu.haeAlusta();
        int ratkaisuarvo = ratkaisija.etsiVoitto(alusta);
        if (onkovalmis(alusta)) {
            return ratkaisuarvo / taso;
        }
        int v = Integer.MIN_VALUE;
        ;

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

    private int minimi(Puu puu, int alfa, int beta, int taso) {
        Alusta alusta = puu.haeAlusta();
        int ratkaisuarvo = ratkaisija.etsiVoitto(alusta);
        if (alusta.onkoLautaTaynna() || ratkaisuarvo != 0) {
            return ratkaisuarvo  / taso;
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

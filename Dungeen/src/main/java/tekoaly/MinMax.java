package tekoaly;

import logiikka.Alusta;
import logiikka.Ratkaisija;

public class MinMax {

    private Ratkaisija ratkaisija;

    public MinMax(Ratkaisija ratkaisija) {
        this.ratkaisija = ratkaisija;
    }

    public Alusta aloitaMax(Puu puu) {
        Alusta alusta = puu.haeAlusta();
        if (onkovalmis(alusta)) return alusta;
        Alusta suurin = puu.haeAlusta();
        int v = Integer.MIN_VALUE;
        int lapsenarvo;
        for (Puu lapsi : puu) {
            lapsenarvo = minimi(lapsi, Integer.MIN_VALUE, Integer.MAX_VALUE);
            if (lapsenarvo > v) {
                suurin = lapsi.haeAlusta();
                v = lapsenarvo;
            }
        }
        return suurin;
    }

    public Alusta aloitaMin(Puu puu) {
        Alusta alusta = puu.haeAlusta();
        if (onkovalmis(alusta)) return alusta;
        Alusta pienin = puu.haeAlusta();
        int v = Integer.MAX_VALUE;
        int lapsenarvo;
        for (Puu lapsi : puu) {
            lapsenarvo = maksimi(lapsi, Integer.MIN_VALUE, Integer.MAX_VALUE);
            if (lapsenarvo < v) {
                pienin = lapsi.haeAlusta();
                v = lapsenarvo;
            }
        }
        return pienin;
    }

    private boolean onkovalmis(Alusta alusta) {
        int ratkaisuarvo = ratkaisija.etsiVoitto(alusta);
        if (alusta.onkoLautaTaynna() || ratkaisuarvo != 0) {
            return true;
        }
        return false;
    }

    private int maksimi(Puu puu, int alfa, int beta) {
        Alusta alusta = puu.haeAlusta();
        int ratkaisuarvo = ratkaisija.etsiVoitto(alusta);
        if (alusta.onkoLautaTaynna() || ratkaisuarvo != 0) {
            return ratkaisuarvo;
        }
        int v = Integer.MIN_VALUE;
        for (Puu lapsi : puu) {
            v = Math.max(v, minimi(lapsi, alfa, beta));
            if (v >= beta)
                return v;
            alfa = Math.max(alfa, v);
        }
        
        /*
         * voi laittaa tähän vaik jotai if v == Integer.Min_Value, niin kattoo arvioijast
         * eli siis jos ei oo lapsia
         */
        
        return v;
    }

    private int minimi(Puu puu, int alfa, int beta) {
        Alusta alusta = puu.haeAlusta();
        int ratkaisuarvo = ratkaisija.etsiVoitto(alusta);
        if (alusta.onkoLautaTaynna() || ratkaisuarvo != 0) {
            return ratkaisuarvo;
        }
        int v = Integer.MAX_VALUE;
        for (Puu lapsi : puu) {
            v = Math.min(v, maksimi(lapsi, alfa, beta));
            if (v <= alfa)
                return v;
            beta = Math.min(beta, v);
        }
        return v;
    }
    
}

package tekoaly;

import logiikka.Alusta;
import logiikka.Ratkaisija;

public class MinMax {

    private Ratkaisija ratkaisija;
    private Arvioija arvioija;

    public MinMax(Ratkaisija ratkaisija, Arvioija arvioija) {
        this.ratkaisija = ratkaisija;
        this.arvioija = arvioija;
    }

    public Alusta aloitaMax(Puu puu) {
        Alusta alusta = puu.haeAlusta();
        if (onkovalmis(alusta)) {
            return alusta;
        }
        Alusta suurin = puu.haeAlusta();
        int v = Integer.MIN_VALUE;
        int lapsenarvo;
        

        
        for (Puu lapsi : puu.haeLapset()) {
            lapsenarvo = minimi(lapsi, Integer.MIN_VALUE, Integer.MAX_VALUE);
            if (lapsenarvo >= v) {
                suurin = lapsi.haeAlusta();
                v = lapsenarvo;
            }
            if (lapsenarvo == Integer.MAX_VALUE) {
                break;
            }
        }
        return suurin;
    }

    public Alusta aloitaMin(Puu puu) {
        Alusta alusta = puu.haeAlusta();
        if (onkovalmis(alusta)) {
            return alusta;
        }
        Alusta pienin = puu.haeAlusta();
        int v = Integer.MAX_VALUE;
        int lapsenarvo;
        for (Puu lapsi : puu.haeLapset()) {
            lapsenarvo = maksimi(lapsi, Integer.MIN_VALUE, Integer.MAX_VALUE);
            if (lapsenarvo <= v) {
                pienin = lapsi.haeAlusta();
                v = lapsenarvo;
            }
            if (lapsenarvo == Integer.MIN_VALUE) {
                break;
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
        for (Puu lapsi : puu.haeLapset()) {
            v = Math.max(v, minimi(lapsi, alfa, beta));
            if (v >= beta || v == Integer.MAX_VALUE) {
                return v;
            }
            alfa = Math.max(alfa, v);
        }

        if (v == Integer.MIN_VALUE) {
            v = alusta.getArvio();
        }

        return v;
    }

    private int minimi(Puu puu, int alfa, int beta) {
        Alusta alusta = puu.haeAlusta();
        int ratkaisuarvo = ratkaisija.etsiVoitto(alusta);
        if (alusta.onkoLautaTaynna() || ratkaisuarvo != 0) {
            return ratkaisuarvo;
        }
        int v = Integer.MAX_VALUE;
        for (Puu lapsi : puu.haeLapset()) {
            v = Math.min(v, maksimi(lapsi, alfa, beta));
            if (v <= alfa || v == Integer.MIN_VALUE) {
                return v;
            }
            beta = Math.min(beta, v);
        }
        if (v == Integer.MAX_VALUE) {
            v = alusta.getArvio();
        }
        return v;
    }
}

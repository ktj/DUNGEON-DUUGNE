package tekoäly;

import dungeen.peli.Alusta;
import dungeen.peli.Ratkaisija;

public class MinMax {

    private Ratkaisija ratkaisija;
    private int voittoRivinPituus;

    public MinMax(Ratkaisija ratkaisija) {
        this.ratkaisija = ratkaisija;
    }

    public Alusta aloitaMax(Puu puu, int voittoRivinPituus) {
        this.voittoRivinPituus = voittoRivinPituus;
        Alusta alusta = puu.haeAlusta();
        int ratkaisuarvo = ratkaisija.etsiVoitto(alusta, voittoRivinPituus);
        if (alusta.onkoLautaTaynna() || ratkaisuarvo != 0) {
            return alusta;
        }
        Alusta suurin = null;
        int v = Integer.MIN_VALUE;
        int lapsenarvo;
        for (Puu lapsi : puu) {
            lapsenarvo = minimi(lapsi);
            if (lapsenarvo > v) {
                suurin = lapsi.haeAlusta();
                v = lapsenarvo;
            }
        }
        return suurin;
    }

    public Alusta aloitaMin(Puu puu, int voittoRivinPituus) {
        this.voittoRivinPituus = voittoRivinPituus;
        Alusta alusta = puu.haeAlusta();
        int ratkaisuarvo = ratkaisija.etsiVoitto(alusta, voittoRivinPituus);
        if (alusta.onkoLautaTaynna() || ratkaisuarvo != 0) {
            return alusta;
        }
        Alusta pienin = null;
        int v = Integer.MAX_VALUE;
        int lapsenarvo;
        for (Puu lapsi : puu) {
            lapsenarvo = maksimi(lapsi);
            if (lapsenarvo < v) {
                pienin = lapsi.haeAlusta();
                v = lapsenarvo;
            }
        }
        return pienin;
    }

    private int maksimi(Puu puu) {
        Alusta alusta = puu.haeAlusta();
        int ratkaisuarvo = ratkaisija.etsiVoitto(alusta, voittoRivinPituus);
        if (alusta.onkoLautaTaynna() || ratkaisuarvo != 0) {
            return ratkaisuarvo;
        }
        int v = Integer.MIN_VALUE;
        for (Puu lapsi : puu) {
            v = Math.max(v, minimi(lapsi));
        }
        return v;
    }

    private int minimi(Puu puu) {
        Alusta alusta = puu.haeAlusta();
        int ratkaisuarvo = ratkaisija.etsiVoitto(alusta, voittoRivinPituus);
        if (alusta.onkoLautaTaynna() || ratkaisuarvo != 0) {
            return ratkaisuarvo;
        }
        int v = Integer.MAX_VALUE;
        for (Puu lapsi : puu) {
            v = Math.min(v, maksimi(lapsi));
        }
        return v;
    }
}

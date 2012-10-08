package logiikka;

/**
 * Pelaaja-rajapinta. Käytetään, jotta tekoälyjä ja ihmispelaajia voidaan sekoitella keskenään
 */
public interface Pelaaja {

    public Alusta seuraavaSiirto(Alusta alusta, char merkki);
    public void ilmoitaVoitto(char merkki);
}

package logiikka;

public interface Pelaaja {

    public Alusta seuraavaSiirto(Alusta alusta, char merkki);
    public void ilmoitaVoitto(char merkki);
}

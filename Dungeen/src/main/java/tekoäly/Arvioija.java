/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tekoäly;

import logiikka.Alusta;

/**
 *
 * @author asdero
 */
public class Arvioija {

    int voittoRivinPituus;
    Alusta alusta;
    final int merkinArvo=10;
    final int tyhjanArvo=5;

    public Arvioija() {
    }
    
    public int arvioiLauta(Alusta alusta, int voittoRivinPituus) {
        this.alusta = alusta;
        this.voittoRivinPituus = voittoRivinPituus;
        int arvio = 0;
        for(int i=0;i<this.alusta.maxKoko();i++){
            for(int j=0;j<this.alusta.maxKoko();j++){
                if (alusta.onkoTyhja(i, j)) {
                    continue;
                }
                arvio=arvio+arvioiSuora(i,j);
            }
        }
        return arvio;
    }

    private int arvioiSuora(int x, int y) {
        char merkki = alusta.lueMerkki(x, y);
        
        if ((tarkistaVasemmalle(x, y)+merkinArvo + tarkistaOikealle(x, y, merkki)) >= this.voittoRivinPituus) {
            return 100;
        }
        if ((tarkistaYlos(x, y)+merkinArvo + tarkistaAlas(x, y, merkki)) >= this.voittoRivinPituus) {
            return 100;
        }
        if ((tarkistaVasemmalleYlos(x, y)+merkinArvo + tarkistaOikealleAlas(x, y, merkki)) >= this.voittoRivinPituus) {
            return 100;
        }
        if ((tarkistaOikealleYlos(x, y)+merkinArvo + tarkistaVasemmalleAlas(x, y, merkki)) >= this.voittoRivinPituus) {
            return 100;
        }
        return 13;
    }
    private int tarkistaVasemmalle(int x,int y){
        if (!this.alusta.tarkistaKoordinaatit(x - 1, y)) {
            return 0;
        }
        if(this.alusta.onkoTyhja(x-1, y))
            return 5;
        return 0;
    }
    private int tarkistaOikealle(int x, int y, char merkki) {
        if (!this.alusta.tarkistaKoordinaatit(x + 1, y)) {
            return 0;
        }
        if(this.alusta.onkoTyhja(x, y))
            return 5;
        if ((this.alusta.lueMerkki(x + 1, y)) == merkki) {
            return 10 + tarkistaOikealle(x + 1, y, merkki);
        }
        return 0;
    }

    private int tarkistaYlos(int x,int y){
        if (!this.alusta.tarkistaKoordinaatit(x, y-1)) {
            return 0;
        }
        if(this.alusta.onkoTyhja(x, y-1))
            return 5;
        return 0;
    }
    private int tarkistaAlas(int x, int y, char merkki) {
        if (!this.alusta.tarkistaKoordinaatit(x, y + 1)) {
            return 0;
        }
        if(this.alusta.onkoTyhja(x, y))
            return 5;
        if ((this.alusta.lueMerkki(x, y + 1)) == merkki) {
            return 10 + tarkistaAlas(x, y + 1, merkki);
        }
        return 0;
    }

    private int tarkistaVasemmalleYlos(int x,int y){
        if (!this.alusta.tarkistaKoordinaatit(x-1, y-1)) {
            return 0;
        }
        if(this.alusta.onkoTyhja(x-1, y-1))
            return 5;
        return 0;
    }
    private int tarkistaOikealleAlas(int x, int y, char merkki) {
        if (!this.alusta.tarkistaKoordinaatit(x + 1, y + 1)) {
            return 0;
        }
        if(this.alusta.onkoTyhja(x, y))
            return 5;
        if ((this.alusta.lueMerkki(x + 1, y + 1)) == merkki) {
            return 10 + tarkistaOikealleAlas(x + 1, y + 1, merkki);
        }
        return 0;
    }

    private int tarkistaOikealleYlos(int x,int y){
        if (!this.alusta.tarkistaKoordinaatit(x+1, y-1)) {
            return 0;
        }
        if(this.alusta.onkoTyhja(x+1, y-1))
            return 5;
        return 0;
    }
    private int tarkistaVasemmalleAlas(int x, int y, char merkki) {
        if (!this.alusta.tarkistaKoordinaatit(x - 1, y + 1)) {
            return 0;
        }
        if(this.alusta.onkoTyhja(x, y))
            return 5;
        if ((this.alusta.lueMerkki(x - 1, y + 1)) == merkki) {
            return 10 + tarkistaVasemmalleAlas(x - 1, y + 1, merkki);
        }
        return 0;
    }


}


package ohtu.intjoukkosovellus;

import org.apache.commons.lang3.ArrayUtils;
import java.util.Arrays;

public class IntJoukko {

    public final static int KAPASITEETTI = 5;
    private int kasvatusKoko = 5;
    private int[] lukuLista;

    public IntJoukko() {
        this.lukuLista = alustaUusiJoukko(KAPASITEETTI, kasvatusKoko);
    }

    public IntJoukko(int kasvatusKoko) {
        if (kasvatusKoko < 0) {
            throw new IntJoukkoException("Epäkelpo alustusparametri");
        }
        this.lukuLista = alustaUusiJoukko(KAPASITEETTI, kasvatusKoko);
    }

    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0 || kasvatuskoko < 0) {
            throw new IntJoukkoException("Epäkelpo alustusparametri");
        }
        this.lukuLista = alustaUusiJoukko(kapasiteetti, kasvatuskoko);
    }

    public int[] alustaUusiJoukko(int kapasiteetti, int kasvatus){
        int[] joukko = new int[kapasiteetti];
        this.kasvatusKoko = kasvatus;
        return Arrays.stream(joukko).map(luku -> 0).toArray();
    }

    public boolean lisaaJoukkoon(int luku) {
        if (!kuuluuJoukkoon(luku)) {
            lukuLista[ArrayUtils.indexOf(lukuLista, 0)] = luku;
            if (!listaEiOleTaysi()) {
                this.lukuLista = Arrays.copyOf(this.lukuLista, this.lukuLista.length + kasvatusKoko);
            }
            return true;
        }
        return false;
    }

    public boolean kuuluuJoukkoon(int luku) {
        return Arrays.stream(this.lukuLista)
                .filter(alkio -> alkio == luku)
                .findFirst().isPresent();
    }

    public boolean poistaJoukosta(int luku) {
        if (!kuuluuJoukkoon(luku)){
            return false;
        }
        this.lukuLista = filtteroiJaPalautaTaulukko(this.lukuLista, luku);
        return true;
    }

    public int joukonMahtavuus() {
        return filtteroiJaPalautaTaulukko(this.lukuLista, 0).length;
    }

    public boolean listaEiOleTaysi(){
        return Arrays.stream(this.lukuLista)
                .filter(nro -> nro == 0)
                .findFirst()
                .isPresent();
    }

    public int[] poistaTyhjatJaPalautaTaulukko(){
        return filtteroiJaPalautaTaulukko(this.lukuLista, 0);
    }

    @Override
    public String toString() {
        String arr = Arrays.toString(filtteroiJaPalautaTaulukko(this.lukuLista, 0));
        arr = arr.replace("[", "{");
        arr = arr.replace("]", "}");
        return arr;
    }

    public static IntJoukko joukkojenYhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko uusiJoukko = new IntJoukko();
        uusiJoukko.lukuLista = ArrayUtils.addAll(a.lukuLista, b.lukuLista);
        return uusiJoukko;
    }

    public static IntJoukko joukkojenLeikkaus(IntJoukko a, IntJoukko b) {
        int[] leikattu = Arrays.stream(a.lukuLista)
                .filter(luku -> ArrayUtils.contains(b.lukuLista, luku))
                .toArray();
        IntJoukko uusiJoukko = new IntJoukko();
        uusiJoukko.lukuLista = leikattu;
        return uusiJoukko;
    }
    
    public static IntJoukko joukkojenErotus(IntJoukko a, IntJoukko b) {
        IntJoukko erotusJoukko = new IntJoukko();
        erotusJoukko.lukuLista = Arrays.stream(joukkojenYhdiste(a,b).lukuLista)
                .filter(luku -> ArrayUtils.contains(joukkojenLeikkaus(a, b).lukuLista, luku))
                .toArray();
        return erotusJoukko;
    }

    public static int[] filtteroiJaPalautaTaulukko(int[] lista, int luku){
        return Arrays.stream(lista)
                .filter(nro -> nro != luku)
                .toArray();
    }

    public static class IntJoukkoException extends RuntimeException {
        public IntJoukkoException(String s) {
            super(s);
        }
    }
}
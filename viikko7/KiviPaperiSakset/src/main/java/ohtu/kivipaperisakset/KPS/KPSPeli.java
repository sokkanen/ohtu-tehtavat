package ohtu.kivipaperisakset.KPS;

import ohtu.kivipaperisakset.IO.IO;
import ohtu.kivipaperisakset.tekoaly.Tekoaly;
import ohtu.kivipaperisakset.Tuomari;

public class KPSPeli implements KPS {

    private boolean onTekoaly;
    private IO io;
    private Tuomari tuomari;
    private Tekoaly tekoaly;
    private String ekanSiirto;
    private String tokanSiirto;

    public KPSPeli(IO io, Tuomari tuomari, Tekoaly tekoaly){
        this.io = io;
        this.tuomari = tuomari;
        this.tekoaly = tekoaly;
        this.onTekoaly = tekoaly != null ? true : false;
    }

    @Override
    public void pelaa() {
        pelaajaSiirto(1, false);
        pelaajaSiirto(2, onTekoaly);

        while (onkoOkSiirto(ekanSiirto) && onkoOkSiirto(tokanSiirto)) {
            tuomari.kirjaaSiirto(ekanSiirto, tokanSiirto);
            System.out.println(tuomari);
            System.out.println();

            pelaajaSiirto(1, false);
            pelaajaSiirto(2, onTekoaly);
        }

        System.out.println();
        System.out.println("Kiitos!");
        System.out.println(tuomari);
    }

    public void nollaa(){
        this.tuomari.nollaa();
    }

    public void pelaajaSiirto(int pelaaja, boolean konePelaaja){
        if (!konePelaaja){
            String outPut = pelaaja == 1 ? "Ensimmäisen pelaajan siirto" : "Toisen pelaajan siirto";
            System.out.println(outPut);
        }
        if (pelaaja == 1 && !konePelaaja){
            this.ekanSiirto = io.lueRivi();
        } else {
            this.tokanSiirto = konePelaaja == false ? io.lueRivi() : tekoaly.annaSiirto();
        }
        if (konePelaaja){
            System.out.println("Tietokone valitsi: " + tokanSiirto);
            tekoaly.asetaSiirto(ekanSiirto);
        }
    }

    private static boolean onkoOkSiirto(String siirto) {
        return "k".equals(siirto) || "p".equals(siirto) || "s".equals(siirto);
    }
}

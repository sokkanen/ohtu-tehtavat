package ohtu.kivipaperisakset;

import ohtu.kivipaperisakset.IO.IO;
import ohtu.kivipaperisakset.IO.KonsoliIO;
import ohtu.kivipaperisakset.KPS.KPS;
import ohtu.kivipaperisakset.KPS.KPSTehdas;

public class Peli {

    private IO io = new KonsoliIO();
    private Tuomari tuomari = new Tuomari();
    private KPSTehdas tehdas = new KPSTehdas(io, 20, tuomari);

    public void kaynnista(){
        while (true) {
            System.out.println("\nValitse pelataanko"
                    + "\n (a) ihmistä vastaan "
                    + "\n (b) tekoälyä vastaan"
                    + "\n (c) parannettua tekoälyä vastaan"
                    + "\nmuilla valinnoilla lopetataan");
            try {
                pelaa(io.lueRivi());
            } catch (NullPointerException e){
                break;
            }
        }
    }

    public void pelaa(String valinta) throws NullPointerException{
        KPS kps = tehdas.haePeli(valinta);
        kps.nollaa();
        System.out.println("peli loppuu kun pelaaja antaa virheellisen siirron eli jonkun muun kuin k, p tai s");
        kps.pelaa();
    }
}

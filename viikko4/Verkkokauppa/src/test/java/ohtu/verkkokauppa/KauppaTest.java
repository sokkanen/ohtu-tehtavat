package ohtu.verkkokauppa;

import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class KauppaTest {

    Pankki pankki;
    Varasto varasto;
    Viitegeneraattori viite;

    @Before
    public void setUp() {
        pankki = mock(Pankki.class);
        varasto = mock(Varasto.class);
        viite = mock(Viitegeneraattori.class);
    }


    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaan() {
        when(viite.uusi()).thenReturn(42);

        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        Kauppa k = new Kauppa(varasto, pankki, viite);

        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto(anyString(), anyInt(), anyString(), anyString(),anyInt());
    }

    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaanOikeillaParametreilla() {
        when(viite.uusi()).thenReturn(42);

        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        Kauppa k = new Kauppa(varasto, pankki, viite);

        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto("pekka", 42, "12345","33333-44455",5);
    }

    @Test
    public void PankinMetodiaTilisiirtoKutsutaanOikeillaParametreillaKahdellaEriOstoksella() {
        when(viite.uusi()).thenReturn(111);

        when(varasto.saldo(1))
                .thenReturn(10);
        when(varasto.saldo(2))
                .thenReturn(10);

        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "sinappi", 7));

        Kauppa k = new Kauppa(varasto, pankki, viite);

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(2);
        k.tilimaksu("jorma", "12345-666");

        verify(pankki).tilisiirto("jorma", 111, "12345-666","33333-44455",12);
    }

    @Test
    public void PankinMetodiaTilisiirtoKutsutaanOikeillaParametreillaKahdellaSamallaOstoksella() {
        when(viite.uusi()).thenReturn(111);

        when(varasto.saldo(1))
                .thenReturn(10);

        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        Kauppa k = new Kauppa(varasto, pankki, viite);

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(1);
        k.tilimaksu("jorma", "12345-666");

        verify(pankki).tilisiirto("jorma", 111, "12345-666","33333-44455",10);
    }

    @Test
    public void PankinMetodiaTilisiirtoKutsutaanOikeillaParametreillaKahdellaOstoksellaJoistaToinenLoppu() {
        when(viite.uusi()).thenReturn(111);

        when(varasto.saldo(1))
                .thenReturn(10);
        when(varasto.saldo(2))
                .thenReturn(0);

        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "sinappi", 7));

        Kauppa k = new Kauppa(varasto, pankki, viite);

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(2);
        k.tilimaksu("jorma", "12345-666");

        verify(pankki).tilisiirto("jorma", 111, "12345-666","33333-44455",5);
    }

    @Test
    public void AloitettaessaAsiointiEdellinenOstotapahtumaEiVaikutaUuteen() {
        when(viite.uusi())
                .thenReturn(111)
                .thenReturn(112);

        when(varasto.saldo(1))
                .thenReturn(10);
        when(varasto.saldo(2))
                .thenReturn(10)
                .thenReturn(9);

        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "ketsuppi", 8));

        Kauppa k = new Kauppa(varasto, pankki, viite);

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(2);
        k.tilimaksu("jorma", "12345-666");

        verify(pankki).tilisiirto("jorma", 111, "12345-666","33333-44455",13);

        k.aloitaAsiointi();
        k.lisaaKoriin(2);
        k.lisaaKoriin(2);
        k.tilimaksu("jarppa", "112233-321");

        verify(pankki).tilisiirto("jarppa", 112, "112233-321","33333-44455",16);
    }

    @Test
    public void JokaisellaOstotapahtumallaOnUusiViitenumero() {
        when(viite.uusi())
                .thenReturn(1)
                .thenReturn(2)
                .thenReturn(3);
        when(varasto.saldo(1))
                .thenReturn(10)
                .thenReturn(9)
                .thenReturn(8);

        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        Kauppa k = new Kauppa(varasto, pankki, viite);

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("jorma", "12345-666");

        verify(pankki).tilisiirto("jorma", 1, "12345-666","33333-44455",5);
        verify(viite, times(1)).uusi();

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("jarppa", "112233-321");

        verify(pankki).tilisiirto("jarppa", 2, "112233-321","33333-44455",5);
        verify(viite, times(2)).uusi();

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("jarppa", "112233-321");

        verify(pankki).tilisiirto("jarppa", 3, "112233-321","33333-44455",5);
        verify(viite, times(3)).uusi();
    }
}


package domain;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

class WorldCasesTest {

    public static WorldCases casosMundiais;


    @Test
    public void testPortugal() throws IllegalArgumentException, IOException {
        casosMundiais = new WorldCases();

        String x = casosMundiais.relatorio("Portugal");
        System.out.println(x);
    }

    @Test
    public void testGetCountries() throws IOException {
        casosMundiais = new WorldCases();
        List<Country> x = casosMundiais.getCountriesFromString("Italy Usa Spain Saudi Arabia");
        System.out.println(x);
        assertEquals("[Italy, USA, Spain, Saudi Arabia]", x.toString());
    }

    @Test
    public void novosRecup() throws IOException {
        casosMundiais = new WorldCases();
        List<Country> x = casosMundiais.getCountriesFromString("USA Mexico");

        System.out.println(x.get(0).getNomePais() +" "+ x.get(0).getNovosRecup());

        System.out.println(x.get(1).getNomePais() + " " + x.get(1).getNovosRecup());


    }

    @Test
    public void testReport() throws IOException {
        casosMundiais = new WorldCases();
        String x = casosMundiais.compareCountries("portugal, netherlands, mexico");
        System.out.println(x);
    }

    @Test
    public void testReportNoError() throws IOException {
        casosMundiais = new WorldCases();
        String x = casosMundiais.compareCountries("portugal gf   uk, france, spain, italy, usa, germany, brazil");
        System.out.println(x);
    }

    @Test
    public void testWorld() throws IOException {
        casosMundiais = new WorldCases();
        String x = casosMundiais.compareCountries("world");
        System.out.println(x);
    }

    @Test
    public void testMundo() throws IOException {
        casosMundiais = new WorldCases();

        String result = casosMundiais.relatorio();

        System.out.println(result);

    }

    @Test
    public void taxaSucessoInsucesso() throws IOException {
        casosMundiais = new WorldCases();

        int x = casosMundiais.numCasosMundiais();

        System.out.println(x);

        double y = (double) casosMundiais.numTotalCasosAtivos()/x*100.0;
        System.out.println(y);

        double w = (double) casosMundiais.numTotalRecuperados()/x*100;

        System.out.println(w);

        double z = (double) casosMundiais.numTotalMortos()/x*100;

        System.out.println(z);

        System.out.println(z + y + w);

        double a = casosMundiais.numTotalRecuperados();
        double b = casosMundiais.numTotalMortos();

        System.out.println((a/(a+b))*100 + "%");
        System.out.println((b/(a+b))*100 + "%");
    }

    @Test
    public void testAccurate() throws  IOException {
        casosMundiais = new WorldCases();

        System.out.println(casosMundiais.naoAccurate());

    }

    @Test
    public void testGetMaiorPais() throws IOException {
        WorldCases worldCases = new WorldCases();
        System.out.println(worldCases.getMaiorPais());

    }

}
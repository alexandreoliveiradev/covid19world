package domain;

import static org.junit.Assert.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;


class CountryTest {

    @Test
    void setNomePaís() {
        Country country = new Country("nome", 1, 1,
                1, 1, 1, 1, 1, 1, 1,
                1, 1);
        Country country1 = new Country("nome", 1, 1,
                1, 1, 1, 1, 1, 1, 1,
                1, 1);
        Country country2 = new Country("nome", 1, 1,
                1, 1, 1, 1, 1, 1, 1,
                1, 1);
        Country country4 = new Country("nome", 1, 1,
                1, 1, 1, 1, 1, 1, 1,
                1, 1);
        country.setNomePaís("R&eacute;union");
        country1.setNomePaís("IsleofMan");
        country2.setNomePaís("TrinidadandTobago");
        country4.setNomePaís("Cura&ccedil;ao");
        assertEquals("Reunion", country.getNomePais());
        assertEquals("Isle Of Man", country1.getNomePais());
        assertEquals("Trinidad And Tobago", country2.getNomePais());
        assertEquals("Curacao", country4.getNomePais());

    }


    @Test
    void comparatorSingleCountry() throws IOException{

        WorldCases worldCases = new WorldCases();

        List<Country> list = worldCases.getCountriesFromString("Saint Pierre Miquelon");

        Country stome = list.get(0);

        System.out.println(stome.taxaActivos() + stome.taxaRecuperados() + stome.taxaMortalidade());

        String x = stome.compare();

        System.out.println(x);
    }

    @Test
    public void taxaSucessoInsucesso() throws IOException {
        WorldCases worldCases = new WorldCases();

        List<Country> list = worldCases.getCountriesFromString("portugal");

        Country portugal = list.get(0);

        System.out.println(portugal.taxaSucesso() +"%");
        System.out.println(2549.0/3684.0*100.0);
        System.out.println(portugal.getNumRecuperados());

        System.out.println(portugal.getCasosFechados());
        System.out.println(portugal.taxaInsucesso() +"%");
        System.out.println(1135.0/3684.0*100.0);
        System.out.println(portugal.getNumMortes());

    }


    @Test
    void getNumCasos()  throws IOException {
        WorldCases worldCases = new WorldCases();

        Country country = (Country)worldCases.getCountriesFromString("portugal").get(0);

        int x = country.getNovosRecup();

        System.out.println(x);

    }

    @Test
    void getNovosRec()  throws IOException {
        WorldCases worldCases = new WorldCases();
        int x = worldCases.numCasosPais("portugal");
        System.out.println(x);

    }

    @Test
    void getDensidadeCasos(){
        Country portugal  = new Country("Portugal", 1, 1,
                1, 1, 1, 1, 1, 1982, 70,
                1, 23133);
        System.out.println(portugal.getDensidadeCasos());
        System.out.println(portugal.getDensidadeMortos());
        System.out.println(portugal.getDensidadeTestes());
    }

    @Test
    void relatorio() throws IOException {
        WorldCases worldCases = new WorldCases();
        System.out.println(worldCases.relatorio("portugal"));
    }

    @Test
    void relatorioNA() throws IOException {
        WorldCases worldCases = new WorldCases();
        System.out.println(worldCases.relatorio("UK"));
    }


}
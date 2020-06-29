package assemblers;

import domain.Country;
import domain.WorldCases;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GraphAssemblerTest {

    @Test
    void graphCountry() throws IOException {
        WorldCases worldCases = new WorldCases();

        Country portugal = new ArrayList<Country>(worldCases.getCountriesFromString(
                "yemen")).get(0);

        String x = GraphAssembler.graphCountry(portugal);

        System.out.println(x);
    }

    @Test
    void graphCountryNA() throws IOException {
        WorldCases worldCases = new WorldCases();

        Country portugal = new ArrayList<Country>(worldCases.getCountriesFromString(
                "UK")).get(0);

        String x = GraphAssembler.graphCountry(portugal);

        System.out.println(x);
    }

    @Test
    void normalizeDouble(){
        System.out.println(GraphAssembler.normalizeDouble(73.3));
        System.out.println(GraphAssembler.normalizeDouble(13.3));
        System.out.println(GraphAssembler.normalizeDouble(13.3));
    }

}
package domain;

import org.junit.jupiter.api.Test;

import java.io.IOException;


class TopsTest {
    WorldCases worldCases = new WorldCases();

    TopsTest() throws IOException {
    }


    @Test
    void top10MaisCasos() throws IOException {
        System.out.println(Tops.top10("1", worldCases));
    }

    @Test
    void top10MaisMortos() throws IOException {
        System.out.println(Tops.top10("2", worldCases));
    }

    @Test
    void top10TaxaActivos() throws IOException {
        System.out.println(Tops.top10("17", worldCases));
    }


    @Test
    void topDesign() throws IOException {
        String x = Tops.top10("1", worldCases);
        System.out.println(x);
    }

    @Test
    void topDesignMais1() throws IOException {
        System.out.println(Tops.top10("1", worldCases));
        System.out.println(Tops.top10("2", worldCases));
        System.out.println(Tops.top10("3", worldCases));
        System.out.println(Tops.top10("4", worldCases));
        System.out.println(Tops.top10("5", worldCases));
        System.out.println(Tops.top10("6", worldCases));
        System.out.println(Tops.top10("7", worldCases));
        System.out.println(Tops.top10("8", worldCases));
        System.out.println(Tops.top10("9", worldCases));
        System.out.println(Tops.top10("10", worldCases));
        System.out.println(Tops.top10("11", worldCases));
        System.out.println(Tops.top10("12", worldCases));
        System.out.println(Tops.top10("13", worldCases));
        System.out.println(Tops.top10("14", worldCases));
    }

    @Test
    void topDesignVariosTops() throws IOException {
        System.out.println(Tops.tops("1", worldCases));
        System.out.println(Tops.tops("2", worldCases));
        System.out.println(Tops.tops("3", worldCases));
        System.out.println(Tops.tops("4", worldCases));
        System.out.println(Tops.tops("5", worldCases));
        System.out.println(Tops.tops("6", worldCases));
    }

    @Test
    void top10MenosNovosCasosButNotZero() throws IOException {
        String x = Tops.tops("7", worldCases);
        System.out.println(x);
    }
}
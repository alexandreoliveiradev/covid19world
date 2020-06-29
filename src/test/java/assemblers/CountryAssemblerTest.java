package assemblers;

import domain.Country;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;


class CountryAssemblerTest {

    @Test
    void readLineByLine() throws IOException {

        CountryAssembler.readLineByLine();
    }

    @Test
    void paisesPorString() throws IOException {

        String str = CountryAssembler.readLineByLine();

        List<Country> paises = CountryAssembler.paisesPorString(str);

        System.out.println(paises);
    }

    @Test
    void testpaisesPorString() {

        String str = "USA 10000 5000 3000 1222 758530 123 45 04 008 003 500";

        List<Country> paises = CountryAssembler.paisesPorString(str);

        System.out.println(paises);
    }


}
package domain;

import assemblers.CountryAssembler;
import assemblers.DataAssembler;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WorldCases {

    List<Country> listaPaises;

    public WorldCases() throws IOException {
        this.listaPaises = new ArrayList<>();
        String str = CountryAssembler.readLineByLine();
        listaPaises = CountryAssembler.paisesPorString(str);
        DataAssembler dataAssembler = new DataAssembler();
        CountryAssembler.setRecuperados(dataAssembler.getRecovered(), listaPaises);
    }

    public int numCasosPais(String nomePais) {
        for (Country pais : listaPaises) {
            if (pais.ePais(nomePais)) {
                return pais.getNumCasos();
            }
        }
        throw new IllegalArgumentException("Não há um país com esse nome");
    }

    public int numNovosCasos() {
        int total = 0;
        for (Country pais : listaPaises) {
            total += pais.getNovosCasos();
        }
        return total;
    }

    public int numNovosMortos() {
        int total = 0;
        for (Country pais : listaPaises) {
            total += pais.getNovosMortos();
        }
        return total;
    }

    public int numPaisesInfetados() {
        return listaPaises.size();
    }

    public int numCasosMundiais() {
        int total = 0;
        for (Country pais : listaPaises) {
            total += pais.getNumCasos();
        }
        return total;
    }

    public int numTotalMortos() {
        int total = 0;
        for (Country pais : listaPaises) {
            total += pais.getNumMortes();
        }
        return total;
    }

    public int numTotalRecuperados() {
        int total = 0;
        for (Country pais : listaPaises) {
            total += pais.getNumRecuperados();
        }
        return total;
    }

    public int numTotalCasosAtivos() {
        int total = 0;
        for (Country pais : listaPaises) {
            total += pais.getNumCasosAtivos();
        }
        return total;
    }

    public int numCasosFechados() {
        return numTotalRecuperados() + numTotalMortos();
    }

    public double taxaSucessoGlobal() {
        double x = (double) numTotalRecuperados() / numCasosFechados() * 100.0;
        BigDecimal bd = new BigDecimal(x).setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public double taxaInsucessoGlobal() {
        double x = 100 - taxaSucessoGlobal();
        BigDecimal bd = new BigDecimal(x).setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public int numTotalTestes() {
        int total = 0;
        for (Country pais : listaPaises) {
            total += pais.getTotalTest();
        }
        return total;
    }

    public int novosRecuperados() {
        int total = 0;
        for (Country pais : listaPaises) {
            total += pais.getNovosRecup();
        }
        return total;
    }

    public double percentagemLetalTotal() {
        double x = (double) numTotalMortos() / numCasosMundiais() * 100.0;

        BigDecimal bd = new BigDecimal(x).setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();

    }

    public double densidadeCasosMundial() {
        double x = 0;
        for (Country country : listaPaises) {
            x += country.getDensidadeCasos();
        }
        return new BigDecimal(x / listaPaises.size()).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    public double densidadeMortosMundial() {
        double x = (double) numTotalMortos() / numCasosMundiais() * 100.0;
        return new BigDecimal(x).setScale(1, RoundingMode.HALF_UP).doubleValue();
    }

    public double taxaCasosAtivos() {
        double x = (double) numTotalCasosAtivos() / numCasosMundiais() * 100.0;
        return new BigDecimal(x).setScale(1, RoundingMode.HALF_UP).doubleValue();
    }

    public double taxaRecuperados() {
        double x = (double) numTotalRecuperados() / numCasosMundiais() * 100.0;
        return new BigDecimal(x).setScale(1, RoundingMode.HALF_UP).doubleValue();

    }

    public String relatorio(String nomePais) {
        for (Country pais : listaPaises) {
            if (pais.ePais(nomePais)) {
                return pais.relatorio();
            }
        }
        throw new IllegalArgumentException("There is no country with this name.");
    }

    public String relatorio() {

        String report = " World Report at " + Country.horas()
                + "\n " + "In total there were " + numCasosMundiais() + " confirmed cases, distributed over " +
                numPaisesInfetados() + " countries/regions," + "\n " + "in which " + numTotalMortos() +
                " people died, " + numTotalRecuperados() + " recovered and " + numTotalTestes() + " tests have" +
                " been performed \n It has infected " + densidadeCasosMundial() +
                "% of the world's population and killed " + densidadeMortosMundial() + "%.\n At the moment there are "
                + numTotalCasosAtivos() + " active cases, " + mildCases() + "% in mild conditions,\n " +
                taxaSerios() + "% in a critical state (" + numSerios() + " cases), and the mortality rate is "
                + percentagemLetalTotal() + "%.\n Of the closed cases, " + taxaSucessoGlobal() + "% recovered and "
                + taxaInsucessoGlobal() + "% died.\n In the past 24h there was an increase of "
                + numNovosCasos() + " cases,\n " + novosRecuperados() + " people recovered" +
                " and there was a rise of " + numNovosMortos() + " deaths.";
        String naoAccurateStr = naoAccurate();
        if (!naoAccurateStr.equals("")) {
            report = report.concat("\n\n The following countries have innacurate results: " + naoAccurateStr);
        }
        return report;
    }

    public String naoAccurate() {
        String result = "";
        List<Country> countries = new ArrayList();
        for (Country pais : listaPaises) {
            if (pais.getNumCasos() != pais.getNumCasosAtivos() + pais.getNumRecuperados() + pais.getNumMortes())
                countries.add(pais);
        }
        for (Country pais : countries) {
            {
                if (countries.get(countries.size() - 1).equals(pais)) {
                    result = result.concat("and " + pais.toString() + ".");
                } else if (countries.get(countries.size() - 2).equals(pais)) {
                    result = result.concat(pais.toString() + " ");
                } else result = result.concat(pais.toString() + ", ");

            }
        }
        return result;
    }

    public List getCountriesFromString(String nomes) {
        List<Country> countryList = new ArrayList<>();
        String delims = "[ ]+";
        String[] tokens = nomes.replaceAll("[,]", " ").split(delims);
        for (int i = 0; i < tokens.length; i++) {
            for (Country country : listaPaises) {
                if (!tokens[i].equals("") && country.ePais(tokens[i])) {
                    countryList.add(country);
                    break;
                } else if (tokens.length - i >= 2 && country.ePais(tokens[i].concat(" " + tokens[i + 1]))) {
                    countryList.add(country);
                    i = i + 1;
                    break;
                } else if (tokens.length - i >= 3 && country.ePais(tokens[i].concat(" " + tokens[i + 1]).concat(" " + tokens[i + 2]))) {
                    countryList.add(country);
                    i = i + 2;
                    break;
                } else if (tokens.length - i >= 4 && country.ePais(tokens[i].concat(" " + tokens[i + 1]).concat(" " + tokens[i + 2]).
                        concat(" " + tokens[i + 3]))) {
                    countryList.add(country);
                    i = i + 3;
                    break;
                } else if (tokens.length - i >= 5 && country.ePais(tokens[i].concat(" " + tokens[i + 1]).concat(" " + tokens[i + 2]).
                        concat(" " + tokens[i + 3]).concat(" " + tokens[i + 4]))) {
                    countryList.add(country);
                    i = i + 4;
                    break;
                }
            }
        }

        if (!countryList.isEmpty()) {
            return countryList;
        }
        throw new IllegalArgumentException("One or more names don't correspond to a country's name.");
    }

    public String getMaiorPais() {
        String output = "";
        int x = 0;
        for (Country country : listaPaises) {
            if (country.getNomePais().length() >= x) {
                x = country.getNomePais().length();
                output = country.getNomePais();
            }
        }
        return output.concat(" : " + x);
    }

    public String compareCountries(String countriesStr) {
        if (countriesStr.equalsIgnoreCase("world")) {
            return compareWorld();
        }

        List<Country> countries = getCountriesFromString(countriesStr);
        String output = "";
        List<Country> list = new ArrayList<>();
        int x = countries.size();
        int y = 0;
        while (x >= 1) {
            for (int i = y; i < countries.size(); i++) {
                list.add(countries.get(i));
                if ((multiplo(i))) {
                    y = i + 1;
                    break;
                }
            }
            output = output.concat(compareCountriesHelper(list).concat("\n"));
            x = x - list.size();
            list.clear();
        }
        if (!areValidCountries(countriesStr, countries)) {
            output = "\n  ONE (OR MORE) OF THE WORDS YOU WROTE DON'T CORRESPOND TO A COUNTRY'S NAME.\n\n".concat(output);
        }
        return output.replace("-  -", "----").replace("-\n", "--");
    }

    public boolean multiplo(int i) {
        return ((i + 1) % 4 == 0);
    }

    public static String compareCountriesHelper(List<Country> countries) {
        if (countries.isEmpty()) {
            throw new IllegalArgumentException("One or more names don't correspond to a country's name.");
        }
        String x = "";
        String[] countryData = new String[]{};
        for (Country country : countries) {
            String delims = "\n";
            String[] countriesData = country.compare().split(delims);
            if (country.equals(countries.get(0))) {
                x = country.compare();
                countryData = x.split(delims);
            } else for (int i = 0; i < 13; i++) {
                countryData[i] = countryData[i].concat(countriesData[i]);
            }
        }
        for (int y = 0; y < 13; y++) {
            countryData[y] = countryData[y].concat("\n");
            if (y == 0) {
                countryData[0] = " " + countryData[0];
            }
            if (y == 12) {
                countryData[12] = countryData[12].substring(1);
            }
        }
        return Arrays.toString(countryData).replace("[", "").replace("]", "")
                .replace(",", "");
    }

    public double popTested() {
        double x = 0;
        for (Country country : listaPaises) {
            x += country.getDensidadeTestes();
        }
        return new BigDecimal(x / listaPaises.size()).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    public double crescimentoCasos() {
        double x = 0;
        for (Country country : listaPaises) {
            x += country.crescimentoRelativo();
        }
        return new BigDecimal(x / listaPaises.size()).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    public double crescimentoMortos() {
        double x = 0;
        for (Country country : listaPaises) {
            x += country.crescimentoRelativoMortos();
        }
        return new BigDecimal(x / listaPaises.size()).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    public double crescimentoRecuperados() {
        double x = 0;
        for (Country country : listaPaises) {
            x += country.crescimentoNovosRecup();
        }
        return new BigDecimal(x / listaPaises.size()).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    public boolean areValidCountries(String countriesStr, List<Country> countriesList) {
        countriesStr = countriesStr.replaceAll("[, ]", " ");
        String[] countries = countriesStr.split(" ");
        int x = countries.length - numEmptyStrings(countries);
        int y = 0;
        for (Country country : countriesList) {
            String[] words = country.getNomePais().split(" ");
            y += words.length;
        }
        return x == y;
    }

    public String compareWorld() {
        List<String> list = new ArrayList<>();
        list.add("           WORLD");
        list.add("\ntotal cases: " + numCasosMundiais());
        list.add("\nactive: " + numTotalCasosAtivos() + "(" + taxaCasosAtivos() + "%)");
        list.add("\ndeaths: " + numTotalMortos() + "(" + densidadeMortosMundial() + "%)");
        list.add("\nrecovered: " + numTotalRecuperados() + "(" + taxaRecuperados() + "%)");
        list.add("\nICU cases: " + numSerios() + "(" + taxaSerios() + "%)");
        list.add("\ntests: " + numTotalTestes());
        list.add("\npop infected: " + densidadeCasosMundial() + "%");
        list.add("\npop dead: " + densidadeMortosMundial() + "%");
        list.add("\npop tested: " + popTested() + "%");
        list.add("\nnew cases: " + numNovosCasos() + "(+" + crescimentoCasos() + "%)");
        list.add("\nnew deaths: " + numNovosMortos() + "(+" + crescimentoMortos() + "%)");
        list.add("\nnew recovered: " + novosRecuperados() + "(+" + crescimentoRecuperados() + "%)");

        List<String> outputList = new ArrayList<>();
        int maxSize = Country.numCaractMaiorLinha(list);

        for (String string : list) {
            for (int i = string.length(); i < maxSize; i++) {
                string = string.concat(" ");
            }
            string = string.concat("|");
            outputList.add(string);
        }
        String y = "\n";
        for (int j = 0; j < maxSize; j++) {
            y = y.concat("-");
        }
        outputList.add(y.concat("-"));
        return Country.formatCompare(outputList.toString().replace(",", "").replace("[", "")
                .replace("]", ""));
    }

    public double mildCases() {
        double x = 100.0 - taxaSerios();
        BigDecimal bd = new BigDecimal(x).setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public double taxaSerios() {
        double x = (double) numSerios() / numTotalCasosAtivos() * 100.0;
        BigDecimal bd = new BigDecimal(x).setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public int numSerios() {
        int total = 0;
        for (Country pais : listaPaises) {
            total += pais.getCasosSerios();
        }
        return total;
    }

    public int numEmptyStrings(String[] string) {
        int output = 0;
        for (String x : string) {
            if (x.equals("")) {
                output += 1;
            }
        }
        return output;
    }
}

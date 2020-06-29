package domain;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Tops {


    public static String tops(String escolha, WorldCases list) throws IOException {

        String output = "";

        switch (escolha) {
            case "1": //+cases
                output = addTops(Tops.top10("1", list), Tops.top10("6", list));
                output = addTops(output, Tops.top10("18", list));
                output = addTops(output, Tops.top10("19", list));
                break;
            case "2": //+deaths
                output = addTops(Tops.top10("2", list), Tops.top10("10", list));
                output = addTops(output, Tops.top10("12", list));
                break;
            case "3": //recovered
                output = addTops(Tops.top10("3", list), Tops.top10("14", list));
                output = addTops(output, Tops.top10("21", list));
                output = addTops(output, Tops.top10("22", list));
                break;
            case "4": //new
                output = addTops(Tops.top10("8", list), Tops.top10("7", list));
                output = addTops(output, Tops.top10("9", list));
                output = addTops(output, Tops.top10("15", list));
                break;
            case "5": // icu
                output = addTops(Tops.top10("4", list), Tops.top10("13", list));
                break;
            case "6": //tests
                output = addTops(Tops.top10("5", list), Tops.top10("11", list));
                break;
            case "7": //least
                output = addTops(Tops.top10("16", list), Tops.top10("17", list));
                output = output.concat("\n *Countries with zero new cases are not displayed" +
                        "\n since it can't be proved they've provided today's data.\n");
                break;
            default:
                throw new IllegalArgumentException("No top with that number");
        }
        return output;
    }

    public static String addTops(String word, String word2) {

        String output = "";

        String[] top1 = word.split("\n");
        String[] top2 = word2.split("\n");
        for (int i = 0; i < top1.length; i++) {
            if (i == 0) {
                output = output.concat(top1[i] + "  ").concat(top2[i]).concat("\n");
            } else {
                output = output.concat(top1[i]).concat(top2[i]).concat("\n");
            }
        }

        return output.replace("- -", "---");
    }

    public static String top10(String escolha, WorldCases worldCases) {

        List<Country> list = worldCases.listaPaises;
        List<Country> output = new ArrayList<>();
        String top = "";

        List <List> input = new ArrayList<>(Arrays.asList(list, output));

        switch (escolha) {
            case "1":
                top = top10MaisCasos(input);
                break;
            case "2":
                top = top10MaisMortos(input);
                break;
            case "3":
                top = top10MaisRecuperados(input);
                break;
            case "4":
                top = top10MaisSerios(input);
                break;
            case "5":
                top = top10MaisTestes(input);
                break;
            case "6":
                top = top10Densidade(input);
                break;
            case "7":
                top = top10crescimento(input);
                break;
            case "8":
                top = top10CasosHoje(input);
                break;
            case "9":
                top = top10MortosHoje(input);
                break;
            case "10":
                top = top10taxaMort(input);
                break;
            case "11":
                top = top10testesPerc(input);
                break;
            case "12":
                top = top10DeadPop(input);
                break;
            case "13":
                top = top10taxaSerios(input);
                break;
            case "14":
                top = top10taxaRecuperados(input);
                break;
            case "15":
                top = top10NovosMortosPerc(input);
                break;
            case "16":
                top = top10MenosCasos(input);
                break;
            case "17":
                top = top10MenosNovosCasos(input);
                break;
            case "18":
                top = top10MaisCasosAtivos(input);
                break;
            case "19":
                top = top10TaxaCasosAtivos(input);
                break;
            case "20":
                top = top10TaxaCasosAtivos2(input);
                return formatTop2(top);
            case "21":
                top = top10NovosRecuperados(input);
                break;
            case "22":
                top = top10PercentNovosRecuperados(input);
                break;

        }
        return formatTop(top);
    }

    public static String top10NovosRecuperados(List<List> input) {
        List<Country> list = input.get(0);
        List<Country> output = input.get(1);
        list.sort(Comparator.comparing(Country::getNovosRecup).reversed());
        shortArray(output, list);
        String outputStr = doTitle(" Most New Recovered");
        for (int i = 0; i < 10; i++) {
            outputStr = outputStr.concat(doTop(output.get(i), output.get(i).getNovosRecup()) + "\n");
        }
        return outputStr;
    }

    public static String top10PercentNovosRecuperados(List<List> input) {
        List<Country> list = input.get(0);
        List<Country> output = input.get(1);
        list.sort(Comparator.comparing(Country::crescimentoNovosRecup).reversed());
        shortArray(output, list);
        String outputStr = doTitle(" % New Recovered");
        for (int i = 0; i < 10; i++) {
            outputStr = outputStr.concat(doTop(output.get(i), output.get(i).crescimentoNovosRecup()) + "\n");
        }
        return outputStr;
    }

    public static String top10MaisMortos(List<List> input) {
        List<Country> list = input.get(0);
        List<Country> output = input.get(1);
        list.sort(Comparator.comparing(Country::getNumMortes).reversed());
        shortArray(output, list);
        String outputStr = doTitle(" Most Deaths");
        for (int i = 0; i < 10; i++) {
            outputStr = outputStr.concat(doTop(output.get(i), output.get(i).getNumMortes()) + "\n");
        }
        return outputStr;
    }

    public static String top10MaisCasosAtivos(List<List> input) {
        List<Country> list = input.get(0);
        List<Country> output = input.get(1);
        list.sort(Comparator.comparing(Country::getNumCasosAtivos).reversed());
        shortArray(output, list);
        String outputStr = doTitle(" Most Active Cases");
        for (int i = 0; i < 10; i++) {
            outputStr = outputStr.concat(doTop(output.get(i), output.get(i).getNumCasosAtivos()) + "\n");
        }
        return outputStr;
    }

    public static String top10TaxaCasosAtivos2(List<List> input) {
        List<Country> list = input.get(0);
        List<Country> output = input.get(1);
        list.sort(Comparator.comparing(Country::taxaActivos).reversed());
        shortArray1(output, list);
        String outputStr = doTitle(" Most Active Cases");
        for (int i = 0; i < 50; i++) {
            outputStr = outputStr.concat(i+1 +". " +  doTop(output.get(i), output.get(i).taxaActivos()) + "\n");
        }
        return outputStr;
    }

    public static String top10TaxaCasosAtivos(List<List> input) {
        List<Country> list = input.get(0);
        List<Country> output = input.get(1);
        list.sort(Comparator.comparing(Country::taxaActivos).reversed());
        shortArray(output, list);
        String outputStr = doTitle(" % Active Cases");
        for (int i = 0; i < 10; i++) {
            outputStr = outputStr.concat(doTop(output.get(i), output.get(i).taxaActivos()) + "\n");
        }
        return outputStr;
    }

    public static String top10MenosNovosCasos(List<List> input) {
        List<Country> list = input.get(0);
        List<Country> output = input.get(1);
        list.sort(Comparator.comparing(Country::getNovosCasos));
        shortArrayX(output, list);
        String outputStr = doTitle(" Least New Cases*");
        for (int i = 0; i < 10; i++) {
            outputStr = outputStr.concat(doTop(output.get(i), output.get(i).getNovosCasos()) + "\n");
        }
        return outputStr;
    }

    public static String top10MaisCasos(List<List> input) {
        List<Country> list = input.get(0);
        List<Country> output = input.get(1);
        list.sort(Comparator.comparing(Country::getNumCasos).reversed());
        shortArray(output, list);
        String outputStr = doTitle(" Most Cases");
        for (int i = 0; i < 10; i++) {
            outputStr = outputStr.concat(doTop(output.get(i), output.get(i).getNumCasos()) + "\n");
        }
        return outputStr;
    }

    public static String top10MaisRecuperados(List<List> input) {
        List<Country> list = input.get(0);
        List<Country> output = input.get(1);
        list.sort(Comparator.comparing(Country::getNumRecuperados).reversed());
        shortArray(output, list);
        String outputStr = doTitle(" Most Recovered");
        for (int i = 0; i < 10; i++) {
            outputStr = outputStr.concat(doTop(output.get(i), output.get(i).getNumRecuperados()) + "\n");
        }
        return outputStr;
    }

    public static String top10MenosCasos(List<List> input) {
        List<Country> list = input.get(0);
        List<Country> output = input.get(1);
        list.sort(Comparator.comparing(Country::getNumCasos));
        shortArray(output, list);
        String outputStr = doTitle(" Least Cases");
        for (int i = 0; i < 10; i++) {
            outputStr = outputStr.concat(doTop(output.get(i), output.get(i).getNumCasos()) + "\n");
        }
        return outputStr;
    }

    public static String top10MaisSerios(List<List> input) {
        List<Country> list = input.get(0);
        List<Country> output = input.get(1);
        list.sort(Comparator.comparing(Country::getCasosSerios).reversed());
        shortArray(output, list);
        String outputStr = doTitle(" Most in ICU");
        for (int i = 0; i < 10; i++) {
            outputStr = outputStr.concat(doTop(output.get(i), output.get(i).getCasosSerios()) + "\n");
        }
        return outputStr;
    }

    public static String top10MaisTestes(List<List> input) {
        List<Country> list = input.get(0);
        List<Country> output = input.get(1);
        list.sort(Comparator.comparing(Country::getTotalTest).reversed());
        shortArray(output, list);
        String outputStr = doTitle(" Most tests");
        for (int i = 0; i < 10; i++) {
            outputStr = outputStr.concat(doTop(output.get(i), output.get(i).getTotalTest()) + "\n");
        }
        return outputStr;
    }

    public static String top10Densidade(List<List> input) {
        List<Country> list = input.get(0);
        List<Country> output = input.get(1);
        list.sort(Comparator.comparing(Country::getDensidadeCasos).reversed());
        shortArray(output, list);
        String outputStr = doTitle(" % pop. infected");
        for (int i = 0; i < 10; i++) {
            outputStr = outputStr.concat(doTop(output.get(i), output.get(i).getDensidadeCasos()) + "\n");
        }
        return outputStr;
    }

    public static String top10NovosMortosPerc(List<List> input) {
        List<Country> list = input.get(0);
        List<Country> output = input.get(1);
        list.sort(Comparator.comparing(Country::crescimentoRelativoMortos).reversed());
        shortArray(output, list);
        String outputStr = doTitle(" % new deaths");
        for (int i = 0; i < 10; i++) {
            outputStr = outputStr.concat(doTop(output.get(i), output.get(i).crescimentoRelativoMortos()) + "\n");
        }
        return outputStr;
    }

    public static String top10taxaMort(List<List> input) {
        List<Country> list = input.get(0);
        List<Country> output = input.get(1);
        list.sort(Comparator.comparing(Country::taxaMortalidade).reversed());
        shortArray(output, list);
        String outputStr = doTitle(" % infected -> died");
        for (int i = 0; i < 10; i++) {
            outputStr = outputStr.concat(doTop(output.get(i), output.get(i).taxaMortalidade()) + "\n");
        }
        return outputStr;
    }

    public static String top10taxaSerios(List<List> input) {
        List<Country> list = input.get(0);
        List<Country> output = input.get(1);
        list.sort(Comparator.comparing(Country::taxaSerios).reversed());
        shortArray(output, list);
        String outputStr = doTitle(" % infected -> ICU");
        for (int i = 0; i < 10; i++) {
            outputStr = outputStr.concat(doTop(output.get(i), output.get(i).taxaSerios()) + "\n");
        }
        return outputStr;
    }

    public static String top10taxaRecuperados(List<List> input) {
        List<Country> list = input.get(0);
        List<Country> output = input.get(1);
        list.sort(Comparator.comparing(Country::taxaRecuperados).reversed());
        shortArray(output, list);
        String outputStr = doTitle(" % recovered");
        for (int i = 0; i < 10; i++) {
            outputStr = outputStr.concat(doTop(output.get(i), output.get(i).taxaRecuperados()) + "\n");
        }
        return outputStr;
    }

    public static String top10DeadPop(List<List> input) {
        List<Country> list = input.get(0);
        List<Country> output = input.get(1);
        list.sort(Comparator.comparing(Country::getDensidadeMortos).reversed());
        shortArray(output, list);
        String outputStr = doTitle(" % pop. died");
        for (int i = 0; i < 10; i++) {
            outputStr = outputStr.concat(doTop(output.get(i), output.get(i).getDensidadeMortos()) + "\n");
        }
        return outputStr;
    }

    public static String top10crescimento(List<List> input) {
        List<Country> list = input.get(0);
        List<Country> output = input.get(1);
        list.sort(Comparator.comparing(Country::crescimentoRelativo).reversed());
        shortArray(output, list);
        String outputStr = doTitle(" % new cases");
        for (int i = 0; i < 10; i++) {
            outputStr = outputStr.concat(doTop(output.get(i), output.get(i).crescimentoRelativo()) + "\n");
        }
        return outputStr;
    }

    public static String top10CasosHoje(List<List> input) {
        List<Country> list = input.get(0);
        List<Country> output = input.get(1);
        list.sort(Comparator.comparing(Country::getNovosCasos).reversed());
        shortArray(output, list);
        String outputStr = doTitle(" new cases today");
        for (int i = 0; i < 10; i++) {
            outputStr = outputStr.concat(doTop(output.get(i), output.get(i).getNovosCasos()) + "\n");
        }
        return outputStr;
    }

    public static String top10MortosHoje(List<List> input) {
        List<Country> list = input.get(0);
        List<Country> output = input.get(1);
        list.sort(Comparator.comparing(Country::getNovosMortos).reversed());
        shortArray(output, list);
        String outputStr = doTitle(" new deaths today");
        for (int i = 0; i < 10; i++) {
            outputStr = outputStr.concat(doTop(output.get(i), output.get(i).getNovosMortos()) + "\n");
        }
        return outputStr;
    }

    public static String top10testesPerc(List<List> input) {
        List<Country> list = input.get(0);
        List<Country> output = input.get(1);
        list.sort(Comparator.comparing(Country::getDensidadeTestes).reversed());
        shortArray(output, list);

        String outputStr = doTitle(" % pop. tested");
        for (int i = 0; i < 10; i++) {
            outputStr = outputStr.concat(doTop(output.get(i), output.get(i).getDensidadeTestes()) + "\n");
        }
        return outputStr;
    }

    public static String doTitle(String titulo) {
        return " " + titulo + "\n";
    }

    public static String doTop(Country pais, int total) {
        return pais.getNomePais() + " " + total;
    }

    public static String doTop(Country pais, double total) {
        return pais.getNomePais() + " " + total + "%";
    }

    public static void shortArray1(List<Country> output, List<Country> list) {
        for (int i = 0; i < 50; i++) {
            output.add(list.get(i));
        }
    }

    public static void shortArray(List<Country> output, List<Country> list) {
        for (int i = 0; i < 10; i++) {
            output.add(list.get(i));
        }
    }

    public static void shortArrayX(List<Country> output, List<Country> list) {
        int x = 0;
        for (int i = 0; i < list.size(); i++) {
            if (!(list.get(i).getNovosCasos() == 0)) {
                for (int j = x; j < 10; j++) {
                    output.add(list.get(i));
                    x += 1;
                    break;
                }
            }
        }
    }

    public static String formatTop(String rawTop) {
        String[] top = rawTop.split("\n");
        List<String> topStr = new ArrayList<>();
        topStr.addAll(Arrays.asList(top));

        int maxSize = Country.numCaractMaiorLinha(topStr);
        String y = ".";

        for (int i = 0; i < 11; i++) {
            int dots = maxSize - topStr.get(i).length();
            for (int j = 0; j < dots; j++) {
                y = y.concat(".");
            }
            if (i == 0) {
                int a = dots / 2;
                String b = "";
                for (int w = 0; w < a; w++) {
                    b = b.concat(" ");
                }
                topStr.set(i, " " + b + topStr.get(i).toUpperCase().concat(b + "\n"));
            } else if (numPalavras(topStr.get(i)) == 2) {
                topStr.set(i, topStr.get(i).replace(" ", y + ".").concat("|\n"));
            } else {
                StringBuilder b = new StringBuilder(topStr.get(i));
                b.replace(topStr.get(i).lastIndexOf(" "), topStr.get(i).lastIndexOf(" ") + 1, y + ".");
                topStr.set(i, b.toString().concat("|\n"));
            }
            y = ".";
        }
        String z = "";
        for (int j = 0; j < maxSize; j++) {
            z = z.concat("-");
        }
        topStr.add(z.concat("--"));

        return topStr.toString().replace(",", "").replace("[", "")
                .replace("]", "");
    }

    public static int numPalavras(String palavra) {
        String[] x = palavra.split(" ");
        return x.length;
    }

    public static String formatTop2(String rawTop) {
        String[] top = rawTop.split("\n");
        List<String> topStr = new ArrayList<>();
        topStr.addAll(Arrays.asList(top));

        int maxSize = Country.numCaractMaiorLinha(topStr);
        String y = ".";

        for (int i = 0; i < 51; i++) {
            int dots = maxSize - topStr.get(i).length();
            for (int j = 0; j < dots; j++) {
                y = y.concat(".");
            }
            if (i == 0) {
                int a = dots / 2;
                String b = "";
                for (int w = 0; w < a; w++) {
                    b = b.concat(" ");
                }
                topStr.set(i, " " + b + topStr.get(i).toUpperCase().concat(b + "\n"));
            } else if (numPalavras(topStr.get(i)) == 2) {
                topStr.set(i, topStr.get(i).replace(" ", y + ".").concat("|\n"));
            } else {
                StringBuilder b = new StringBuilder(topStr.get(i));
                b.replace(topStr.get(i).lastIndexOf(" "), topStr.get(i).lastIndexOf(" ") + 1, y + ".");
                topStr.set(i, b.toString().concat("|\n"));
            }
            y = ".";
        }
        String z = "";
        for (int j = 0; j < maxSize; j++) {
            z = z.concat("-");
        }
        topStr.add(z.concat("--"));

        return topStr.toString().replace(",", "").replace("[", "")
                .replace("]", "");
    }
}


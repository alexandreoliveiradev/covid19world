package domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Country {

    public String nomePais;
    public int numCasos;
    public int numMortes;
    public int numRecuperados;
    public int numCasosAtivos;
    public int novosCasos;
    public int novosMortos;
    public int serios;
    public double densidadeCasos;
    public double densidadeMortos;
    public int totalTest;
    public double densidadeTestes;
    public int novosRecup;

    public Country(String nomePais, int numCasos, int numMortes, int numRecuperados, int numCasosAtivos, int
            novosCasos, int novosMortos, int serios, int casosPMilh, int mortosPMilh, int totalTest, int testPMilh) {
        setNomePaís(nomePais);
        this.numCasos = numCasos;
        this.numMortes = numMortes;
        this.numRecuperados = numRecuperados;
        this.numCasosAtivos = numCasosAtivos;
        this.novosCasos = novosCasos;
        this.novosMortos = novosMortos;
        this.serios = serios;
        this.densidadeCasos = new BigDecimal((double) casosPMilh / 10000).setScale(3, RoundingMode.HALF_UP).doubleValue();
        this.densidadeMortos = new BigDecimal((double) mortosPMilh / 10000).setScale(3, RoundingMode.HALF_UP).doubleValue();
        this.totalTest = totalTest;
        this.densidadeTestes = new BigDecimal((double) testPMilh / 10000).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    public void setNomePaís(String text) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (Character.isUpperCase(c) && i != 0) {
                if (!Character.isUpperCase(text.charAt(i - 1))) {
                    sb.append(' ');
                }
            }
            sb.append(c);
        }
        String nomePais = sb.toString();
        nomePais = nomePais.replace("&ccedil;", "c").replace("&eacute;", "e").
                replace("- ", "-").replace("of ", " Of ").
                replace("and ", " And ").replace("l And", "land");
        this.nomePais = nomePais;
    }

    public void setNovosRecup(int totalRecupOntem) {
         this.novosRecup = numRecuperados-totalRecupOntem;
    }

    public boolean ePais(String nomePais) {
        return this.nomePais.equalsIgnoreCase(nomePais);
    }

    public int getNumCasos() {
        return this.numCasos;
    }

    public String getNomePais() {
        return this.nomePais;
    }

    public int getNumMortes() {
        return numMortes;
    }

    public int getCasosFechados() {
        return getNumCasos() - getNumCasosAtivos();
    }

    public double taxaSucesso() {
        if (getCasosFechados() == 0) {
            return 0.0;
        }
        double x = (double) getNumRecuperados() / getCasosFechados() * 100.0;
        BigDecimal bd = new BigDecimal(x).setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public double taxaInsucesso() {
        if (getCasosFechados() == 0) {
            return 0.0;
        }
        double x = (double) getNumMortes() / getCasosFechados() * 100.0;
        BigDecimal bd = new BigDecimal(x).setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public double taxaActivos() {
        if (numCasos == 0) {
            return 0.0;
        }
        double x = (double) numCasosAtivos / numCasos * 100.0;
        BigDecimal bd = new BigDecimal(x).setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public double crescimentoNovosRecup() {

        if (numRecuperados - novosRecup == 0) {
            return 0.0;
        }
        double x = (double) novosRecup / (numRecuperados - novosRecup) * 100.0;

        BigDecimal bd = new BigDecimal(x).setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();

    }

    public double taxaRecuperados() {
        if (numCasos == 0) {
            return 0.0;
        }
        double x = (double) numRecuperados / numCasos * 100.0;
        BigDecimal bd = new BigDecimal(x).setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public double taxaMortalidade() {
        if (numCasos == 0) {
            return 0.0;
        }
        double x = (double) numMortes / numCasos * 100.0;
        BigDecimal bd = new BigDecimal(x).setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public double taxaSerios() {
        if (numCasosAtivos == 0) {
            return 0.0;
        }
        double x = (double) serios / numCasosAtivos * 100.0;
        BigDecimal bd = new BigDecimal(x).setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public String compare() {
        boolean unaccurate = getNumCasos() != getNumMortes() + getNumRecuperados() + getNumCasosAtivos();
        String x = "";
        if (unaccurate) {
            x = "\nactive: N/A";
        }

        List<String> list = new ArrayList<>();
        list.add(getNomePais().toUpperCase());
        list.add("\ntotal cases: " + getNumCasos());
        if (unaccurate && getNumCasosAtivos() == 0) {
            list.add(x);
        } else {
            list.add("\nactive: " + getNumCasosAtivos() + "(" + taxaActivos() + "%)");
        }
        list.add("\ndeaths: " + getNumMortes() + "(" + taxaMortalidade() + "%)");
        if (unaccurate && getNumRecuperados() == 0) {
            list.add(x.replace("active", "recovered"));
        } else {
            list.add("\nrecovered: " + getNumRecuperados() + "(" + taxaRecuperados() + "%)");
        }
        list.add("\nICU cases: " + getCasosSerios() + "(" + taxaSerios() + "%)");
        list.add("\ntests: " + getTotalTest() + "(" + getDensidadeTestes() + "%)");
        list.add("\npop infected: " + getDensidadeCasos() + "%");
        list.add("\npop dead: " + getDensidadeMortos() + "%");
        if (!unaccurate && getNovosCasos() == 0) {
            list.add("\nnew cases: 0");
        } else list.add("\nnew cases: " + getNovosCasos() + "(+" + crescimentoRelativo() + "%)");
        if (unaccurate && getNovosRecup() == 0) {
            list.add(x.replace("active", "new recovered"));
        } else if (!unaccurate && getNovosRecup() == 0) {
            list.add("\nnew recovered: 0");
        } else list.add("\nnew recovered: " + getNovosRecup() + "(+" + crescimentoNovosRecup() + "%)");
        if (!unaccurate && getNovosMortos() == 0) {
            list.add("\nnew deaths: 0");
        } else list.add("\nnew deaths: " + getNovosMortos() + "(+" + crescimentoRelativoMortos() + "%)");

        List<String> outputList = new ArrayList<>();
        int maxSize = numCaractMaiorLinha(list);

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
        return formatCompare(outputList.toString().replace(",", "").replace("[", "")
                .replace("]", ""));
    }

    public static int numCaractMaiorLinha(List<String> list) {
        int size = 0;
        for (String s : list) {
            if (s.length() > size) {
                size = s.length();
            }
        }
        return size;
    }

    public int getNovosRecup() {
        return novosRecup;
    }

    public int getNumRecuperados() {
        return numRecuperados;
    }

    public int getNovosCasos() {
        return novosCasos;
    }

    public int getNovosMortos() {
        return novosMortos;
    }

    public double crescimentoRelativo() {
        if (numCasos - novosCasos == 0) {
            return 0.0;
        }
        double x = (double) novosCasos / (numCasos - novosCasos) * 100.0;

        BigDecimal bd = new BigDecimal(x).setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public double crescimentoRelativoMortos() {
        if (numMortes - novosMortos == 0) {
            return 0.0;
        }
        double x = (double) novosMortos / (numMortes - novosMortos) * 100.0;

        BigDecimal bd = new BigDecimal(x).setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public int getNumCasosAtivos() {
        return numCasosAtivos;
    }

    public int getCasosSerios() {
        return serios;
    }

    public double getDensidadeCasos() {
        return densidadeCasos;
    }

    public int getTotalTest() {
        return totalTest;
    }

    public double getDensidadeTestes() {
        return densidadeTestes;
    }

    public double getDensidadeMortos() {
        return densidadeMortos;
    }

    public String relatorio() {

        boolean inaccurate = getNumCasos() != getNumMortes() + getNumRecuperados() + getNumCasosAtivos();

        String report = " " + this.toString().toUpperCase() + "'s Report at " + horas()
                + "\n" + " In " + this + " there were " + getNumCasos() + " cases (" + getDensidadeCasos() + "% of " +
                "the pop),\n in which " + getNumMortes() +
                " people died (" + getDensidadeMortos() + "%) and " + getNumRecuperados() + " recovered.\n " + getTotalTest() +
                " tests have been done (" + getDensidadeTestes() + "% of the pop. Some repeated).\n"
                + " Of the closed cases, " + taxaSucesso() + "% recovered and " + taxaInsucesso() +
                "% died.\n At the time there are " + getNumCasosAtivos() + " active cases, " + mildCases() + "% in mild conditions,\n"
                + " " + taxaSerios() + "% in a critical state ("
                + getCasosSerios() + " cases), and the mortality rate is "
                + taxaMortalidade() + "%.\n In the last 24h there was a rise of "
                + getNovosCasos() + " cases (" + crescimentoRelativo() + "%),\n an increase of " +
                getNovosMortos() + " deaths ("+ getDensidadeMortos() + "%) and " + getNovosRecup() + " people recovered ("
                + crescimentoNovosRecup() + "%).\n";

        if (inaccurate) {
            report = report.concat("\n This country has provided innacurate data.\n");
        }

        return report;

    }

    public double mildCases() {
        double x = 100.0 - taxaSerios();
        BigDecimal bd = new BigDecimal(x).setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    @Override
    public String toString() {
        return this.nomePais;
    }

    public static String horas() {
        LocalDateTime agora = LocalDateTime.now();
        return "" + horas(agora.getHour()) + "h" + minutos(agora.getMinute()) + ", on "
                + agora.getDayOfMonth() + "/" + agora.getMonthValue() + "/" + agora.getYear() + ":";
    }

    public static String minutos(int minuto) {
        if (minuto < 10) {
            return "0".concat(Integer.toString(minuto));
        } else return Integer.toString(minuto);
    }

    public static String horas(int hora) {
        if (hora < 10) {
            return "0".concat(Integer.toString(hora));
        } else return Integer.toString(hora);
    }

    public static String formatCompare(String country) {
        String[] countryArr = country.split("\n");
        int maxSize = 0;
        for (int i = 0; i < countryArr.length; i++) {
            if (countryArr[i].length() > maxSize) {
                maxSize = countryArr[i].length();
            }
        }
        if (countryArr[0].length() == maxSize) {
            for (int j = 0; j < countryArr.length; j++) {
                countryArr[j] = countryArr[j].replace("|", " |\n");
            }
            countryArr[0] = " ".concat(countryArr[0]);
        }

        return Arrays.toString(countryArr).replace(",", "").replace("[", "")
                .replace("]", "");
    }
}

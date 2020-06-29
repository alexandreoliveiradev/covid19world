package assemblers;

import domain.Country;
import domain.WorldCases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CountryAssembler {


    public static String readLineByLine() throws IOException {

        DataAssembler rawTable = new DataAssembler();
        return rawTable.getSelectedRawTable1();
    }

    public static List<Country> paisesPorString(String str) {
        List<Country> paises = new ArrayList();

        String delims = "[ ]+";
        String[] tokens = str.replaceAll("[,.+]", "").
                replaceAll("\n", " ").
                replaceAll("\t", " ").split(delims);

        String nomePais = "";
        int numCasos= -1, numMortos= -1, numRecuperados = -1, numCasosAtivos = -1, novosCasos = -1,
        novosMortos= -1, casosSerios= -1, casosPMilh= -1, mortosPMilh= -1, totalTest= -1, testesPMilh= -1;



        for (int i = 0; i < tokens.length; i++) {
            if (i == tokens.length - 1) {
                tokens[i] = tokens[i].replace("\n", "");
            }
            if (naoENumerico(tokens[i])) {
                int y = i;
                nomePais = fazerNomePais(tokens, i, nomePais);
                i = y + nomePais.split(delims).length - 1;

            }
            if (!naoENumerico(tokens[i]) && numCasos == -1) {
                numCasos = Integer.parseInt(tokens[i]);
            } else if (!naoENumerico(tokens[i]) && numCasos != -1 && novosCasos == -1) {
                novosCasos = Integer.parseInt(tokens[i]);
            } else if (!naoENumerico(tokens[i]) && numCasos != -1 && novosCasos != -1 && numMortos == -1) {
                numMortos = Integer.parseInt(tokens[i]);
            } else if (!naoENumerico(tokens[i]) && numCasos != -1 && novosCasos != -1 && numMortos != -1
                    && novosMortos == -1) {
                novosMortos = Integer.parseInt(tokens[i]);
            } else if (!naoENumerico(tokens[i]) && numCasos != -1 && novosCasos != -1 && numMortos != -1
                    && novosMortos != -1 && numRecuperados == -1 ) {
                numRecuperados = Integer.parseInt(tokens[i]);
            } else if (!naoENumerico(tokens[i]) && numCasos != -1 && novosCasos != -1 && numMortos != -1
                    && novosMortos != -1 && numRecuperados != -1 && numCasosAtivos == -1 ) {
                numCasosAtivos = Integer.parseInt(tokens[i]);
            } else if (!naoENumerico(tokens[i]) && numCasos != -1 && novosCasos != -1 && numMortos != -1
                    && novosMortos != -1 && numRecuperados != -1 && numCasosAtivos != -1 && casosSerios == -1) {
                casosSerios = Integer.parseInt(tokens[i]);
            } else if (!naoENumerico(tokens[i]) && numCasos != -1 && novosCasos != -1 && numMortos != -1
                    && novosMortos != -1 && numRecuperados != -1 && numCasosAtivos != -1 && casosSerios != -1 &&
             casosPMilh == -1) {
                casosPMilh = Integer.parseInt(tokens[i]);
            } else if(!naoENumerico(tokens[i]) && numCasos != -1 && novosCasos != -1 && numMortos != -1
                    && novosMortos != -1 && numRecuperados != -1 && numCasosAtivos != -1 && casosSerios != -1 &&
                    casosPMilh != -1 && mortosPMilh == -1) {
                mortosPMilh = Integer.parseInt(tokens[i]);
            } else if(!naoENumerico(tokens[i]) && numCasos != -1 && novosCasos != -1 && numMortos != -1
                    && novosMortos != -1 && numRecuperados != -1 && numCasosAtivos != -1 && casosSerios != -1 &&
                    casosPMilh != -1 && mortosPMilh != -1 && totalTest == -1) {
                totalTest = Integer.parseInt(tokens[i]);
            } else if(!naoENumerico(tokens[i]) && numCasos != -1 && novosCasos != -1 && numMortos != -1
                    && novosMortos != -1 && numRecuperados != -1 && numCasosAtivos != -1 && casosSerios != -1 &&
                    casosPMilh != -1 && mortosPMilh != -1 && totalTest != -1 && testesPMilh == -1) {
                testesPMilh = Integer.parseInt(tokens[i]);
            }

            if (!nomePais.equals("") && numCasos != -1 && numMortos != -1 && numRecuperados != -1 &&
                    numCasosAtivos != -1 && novosCasos != -1 && novosMortos != -1 && casosSerios != -1
            && casosPMilh != -1 && mortosPMilh != -1 && totalTest != -1 && testesPMilh != -1) {
                paises.add(new Country(nomePais, numCasos, numMortos, numRecuperados, numCasosAtivos,
                        novosCasos, novosMortos, casosSerios, casosPMilh, mortosPMilh, totalTest, testesPMilh));

                nomePais = ""; numCasos = -1; numMortos = -1; casosSerios = -1; casosPMilh = -1;
                numRecuperados = -1; numCasosAtivos = -1; novosCasos = -1; novosMortos = -1;
                mortosPMilh = -1; totalTest = -1; testesPMilh = -1;
            }
        }
        return paises;
    }

    public static boolean naoENumerico(String palavra) {
        return !palavra.matches(".*\\d.*");
    }


    public static String fazerNomePais(String[] tokens, int i, String nome) {
        String nomePais = nome;
        int y = i;
        if (i == 0) {
            return tokens[i];
        }
        while (naoENumerico(tokens[y]) && (y != tokens.length - 1)) {
            if (!nomePais.equals("")) {
                nomePais = nomePais.concat(" " + tokens[y]);
                y++;
            } else {
                nomePais = tokens[y];
                y++;
            }
        }
        return nomePais;

    }

    public static void setRecuperados(String str, List<Country> countries) {

        String delims = "[ ]+";
        String[] tokens = str.replaceAll("[,.+]", "").
                replaceAll("\n", " ").
                replaceAll("\t", " ").split(delims);

        String nomePais = "";
        int numCasos= -1, numMortos= -1, numRecuperados = -1, novosCasos = -1, novosMortos= -1;



        for (int i = 0; i < tokens.length; i++) {
            if (i == tokens.length - 1) {
                tokens[i] = tokens[i].replace("\n", "");
            }
            if (naoENumerico(tokens[i])) {
                int y = i;
                nomePais = fazerNomePais(tokens, i, nomePais);
                i = y + nomePais.split(delims).length - 1;

            }
            if (!naoENumerico(tokens[i]) && numCasos == -1) {
                numCasos = Integer.parseInt(tokens[i]);
            } else if (!naoENumerico(tokens[i]) && numCasos != -1 && novosCasos == -1) {
                novosCasos = Integer.parseInt(tokens[i]);
            } else if (!naoENumerico(tokens[i]) && numCasos != -1 && novosCasos != -1 && numMortos == -1) {
                numMortos = Integer.parseInt(tokens[i]);
            } else if (!naoENumerico(tokens[i]) && numCasos != -1 && novosCasos != -1 && numMortos != -1
                    && novosMortos == -1) {
                novosMortos = Integer.parseInt(tokens[i]);
            } else if (!naoENumerico(tokens[i]) && numCasos != -1 && novosCasos != -1 && numMortos != -1
                    && novosMortos != -1 && numRecuperados == -1 ) {
                numRecuperados = Integer.parseInt(tokens[i]);
            }

            if (!nomePais.equals("") && numCasos != -1 && numMortos != -1 && numRecuperados != -1 &&
                    novosCasos != -1 && novosMortos != -1) {

                for (Country country : countries){
                    if (country.ePais(nomePais)){
                        country.setNovosRecup(numRecuperados);
                        break;
                    }
                }

                nomePais = ""; numCasos = -1; numMortos = -1;
                numRecuperados = -1;  novosCasos = -1; novosMortos = -1;

            }
        }
    }


}
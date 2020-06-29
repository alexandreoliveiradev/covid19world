package assemblers;

import domain.Country;
import java.util.Arrays;

public class GraphAssembler {

    public static String graphCountry(Country country){

        boolean inaccurate = country.getNumCasos() != country.getNumMortes() +
                country.getNumRecuperados() + country.getNumCasosAtivos();

        int casosAtivos = normalizeDouble(country.taxaActivos());
        int numMortes =  normalizeDouble(country.taxaMortalidade());
        int numRecuperados = normalizeDouble(country.taxaRecuperados());
        String[] graph = setupGraph(country);

        for (int i = 10; i > 10-casosAtivos ; i-- ){
            graph[i] = graph[i].replace("1", "X");
        }
        for (int j = 11-casosAtivos; j > 0; j--){
            graph[j] = graph[j].replace("1", " ");
        }

        for (int i = 10; i > 10-numMortes; i-- ){
            graph[i] = graph[i].replace("2", "X");
        }
        for (int j = 11-numMortes; j > 0; j--){
            graph[j] = graph[j].replace("2", " ");
        }

        for (int i = 10; i > 10-numRecuperados ; i-- ){
            graph[i] = graph[i].replace("3", "X");
        }
        for (int j = 11-numRecuperados; j > 0; j--){
            graph[j] = graph[j].replace("3", " ");
        }

        String graphStr = Arrays.toString(graph).replace(",", "").replace("[", "").
                replace("]", "");

        if (inaccurate){
            graphStr = graphStr.concat("\n\n This country has provided inaccurate data.");
        }
        return graphStr;
    }

    public static String[] setupGraph(Country country){

        String[] graph = new String[12];

        graph[0] = "   " + country.getNomePais().toUpperCase() + "\n  (X -> 10%)\n";
        String lineExemple = ("|  1    2    3  \n");
        for (int j = 1; j < 11; j++) { graph[j] = lineExemple;}
        graph[11] = "  Act  Dea  Rec";

        return graph;
    }

    public static int normalizeDouble(double numero){
        double num = numero/10;
        int output;
        while(num > 1){
            num -= 1;
        }
        if (num -0.5 >= 0) {
            output = (int)numero/10+1;
        } else output = (int)numero/10;

        return output;
    }
}

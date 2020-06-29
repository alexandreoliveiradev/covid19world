package assemblers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;

public class DataAssembler {


    public String getSelectedRawTable() throws IOException {

        URL url = new URL("https://www.worldometers.info/coronavirus/");

        URLConnection con = url.openConnection();

        con.addRequestProperty("User-Agent", "Opera");
        con.setReadTimeout(5000);
        con.setConnectTimeout(5000);



        InputStream is = con.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        String codeLine;
        StringBuilder table = new StringBuilder();

        while ((codeLine = br.readLine()) != null && !codeLine.equals("<div class=\"tab-pane \" id=\"nav-yesterday\" " +
                "role=\"tabpanel\" aria-labelledby=\"nav-yesterday-tab\">")) {

            getHtml(codeLine, table);
        } return removeUnwantedColumns(table.toString(), 12);
    }

    public String keepOnlyNumber(String text){
        char[] chars = text.toCharArray();
        StringBuilder sb = new StringBuilder();
        if(text.equals("") || text.equals(" ") || text.equals("N/A") || text.equals("  ")) return "0 ";
        for(char c : chars){
            if(Character.isDigit(c))
                sb.append(c);
        } return sb.append(" ").toString();
    }

    public String getRecovered() throws IOException {

        URL url = new URL("https://www.worldometers.info/coronavirus/");

        URLConnection con = url.openConnection();

        con.addRequestProperty("User-Agent", "Opera");
        con.setReadTimeout(5000);
        con.setConnectTimeout(5000);

        InputStream is = con.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        String codeLine;
        StringBuilder table = new StringBuilder();

        while ((codeLine = br.readLine()) != null && !codeLine.equals("<div class=\"tab-pane \" id=\"nav-today\" " +
                "role=\"tabpanel\" aria-labelledby=\"nav-today-tab\">") && !codeLine.equals("<div class=\"tab-pane \" id=\"nav-yesterday2\" " +
                "role=\"tabpanel\" aria-labelledby=\"nav-yesterday2-tab\">")) {

            getHtml(codeLine, table);

        } return removeUnwantedColumns(table.toString(), 6);
    }

    private void getHtml(String codeLine, StringBuilder table) {
        if (codeLine.contains("<td style=\"font-weight: bold; font-size:15px; text-align:left;\"><a class=\"mt_a\" href=\"country/")) {
            int indexStart = codeLine.indexOf("/\">") + 3;
            int indexEnd = codeLine.indexOf("</a></td>");
            String stringExcerpt = codeLine.substring(indexStart, indexEnd);
            table.append("\n").append(stringExcerpt.replace(" ", "")).append(" ");
        }

        else if (codeLine.contains("<td style=\"font-weight: bold; font-size:15px; text-align:left;\"><span style=\"color:#00B5F0; font-style:italic; \">")) {
            int indexStart = codeLine.indexOf("; \">") + 4;
            int indexEnd = codeLine.indexOf("</span></td>");
            String stringExcerpt = codeLine.substring(indexStart, indexEnd);
            table.append("\n").append(stringExcerpt.replace(" ", "")).append(" ");
        }

        else if (codeLine.contains("<td style=\"font-weight: bold; text-align:right;background-color:#FFEEAA;\"")) {
            int indexStart = codeLine.indexOf("A;\">") + 4;
            int indexEnd = codeLine.indexOf("</td>");
            String stringExcerpt = codeLine.substring(indexStart, indexEnd);
            table.append(keepOnlyNumber(stringExcerpt));
        }

        else if (codeLine.contains("text-align:right;background-color:red; color:white\">")) {
            int indexStart = codeLine.indexOf("e\">") + 3;
            int indexEnd = codeLine.indexOf("</td>");
            String stringExcerpt = codeLine.substring(indexStart, indexEnd);
            table.append(keepOnlyNumber(stringExcerpt));
        }

        else if (codeLine.contains("<td style=\"font-weight: bold; text-align:right\">")) {
            int indexStart = codeLine.indexOf("t\">") + 3;
            int indexEnd = codeLine.indexOf("</td>");
            String stringExcerpt = codeLine.substring(indexStart, indexEnd);
            table.append(keepOnlyNumber(stringExcerpt));
        }

        else if (codeLine.contains("<td style=\"font-weight: bold; text-align:right;\">")) {
            int indexStart = codeLine.indexOf(";\">") + 3;
            int indexEnd = codeLine.indexOf("</td>");
            String stringExcerpt = codeLine.substring(indexStart, indexEnd);
            table.append(keepOnlyNumber(stringExcerpt));
        }

        else if (codeLine.contains("<td style=\"text-align:right;font-weight:bold;\">")) {
            int indexStart = codeLine.indexOf(";\">") + 3;
            int indexEnd = codeLine.indexOf("</td>");
            String stringExcerpt = codeLine.substring(indexStart, indexEnd);
            table.append(keepOnlyNumber(stringExcerpt));
        }

        else if (codeLine.contains("                                    text-align:right;\">")) {
            int indexStart = codeLine.indexOf(";\">") + 3;
            int indexEnd = codeLine.indexOf("</td>");
            String stringExcerpt = codeLine.substring(indexStart, indexEnd);
            table.append(keepOnlyNumber(stringExcerpt));
        }
    }

    public static String removeUnwantedColumns(String table, int n) {
        StringBuilder stringBuilder = new StringBuilder(table);
        char space = ' ';
        int numberOfColumns = 0;
        for(int i = 0; i < stringBuilder.length(); i++) {

            while(Character.isAlphabetic(stringBuilder.charAt(i)) || stringBuilder.charAt(i) == space
                    && Character.isAlphabetic(stringBuilder.charAt(i - 1)) && Character.isAlphabetic(stringBuilder.charAt(i + 1))
                    || stringBuilder.charAt(i) == space && stringBuilder.charAt(i - 1) == '.')
                i++;

            if(stringBuilder.charAt(i) == space)
                numberOfColumns++;

            if(numberOfColumns == n) {
                for(int j = i; j < stringBuilder.length(); j++) {
                    if(j == stringBuilder.length() - 1) {
                        stringBuilder.delete(i, j);
                        numberOfColumns = 0;
                        break;
                    }

                    else if(Character.isAlphabetic(stringBuilder.charAt(j + 1))) {
                        stringBuilder.delete(i, j);
                        numberOfColumns = 0;
                        break;
                    }
                }
            }
        } return stringBuilder.toString();
    }

    public String getSelectedRawTable1() throws IOException {

        URL url = new URL("https://www.worldometers.info/coronavirus/");

        URLConnection con = url.openConnection();

        con.addRequestProperty("User-Agent", "Opera");
        con.setReadTimeout(5000);
        con.setConnectTimeout(5000);

        InputStream is = con.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        String codeLine;
        StringBuilder table = new StringBuilder();

        while ((codeLine = br.readLine()) != null && !codeLine.equals("<div class=\"tab-pane \" id=\"nav-yesterday\" " +
                "role=\"tabpanel\" aria-labelledby=\"nav-yesterday-tab\">")) {

            getHtml1(codeLine, table);
        }

        return removeLastRow(removeError(table.toString()));
    }


    public String keepOnlyNumber1(String text){
        char[] chars = text.toCharArray();
        StringBuilder sb = new StringBuilder();
        if(text.equals("") || text.equals(" ") || text.equals("N/A") || text.equals("  ")) return "0 ";
        for(char c : chars){
            if(Character.isDigit(c))
                sb.append(c);
        } return sb.append(" ").toString();
    }

    public String removeError(String text){
        String [] lines = text.split("\n");

        for (int i = 1; i < lines.length; i++){
            if(lines[i].contains("UK") || lines[i].contains("Spain") || lines[i].contains("Sweden") || lines[i].contains("Netherlands")){
                lines[i] = replaceLastOccurrence(lines[i], " 0");

            } else lines[i] = replaceNewRecov(lines[i]);
        }
        
        for(int j = 0; j <lines.length; j++){
            lines[j] = lines[j].concat("\n");
        }
        return Arrays.toString(lines).replace(",", "").replace("[", "").
                replace("]", "").replaceFirst("\n", "");
    }

    public static String replaceNewRecov(String line) {

        String[] eachLine = line.split(" ");
        String output = "";

        if (eachLine.length > 13 ) {
            for (int i = 0; i < eachLine.length; i++) {
                if (i != 6) {
                    output = output.concat(eachLine[i] + " ");
                }
            }
            return replaceLastOccurrence(output, " ");
        } return line;

    }

    public static String replaceLastOccurrence(String originalString, String occurrence){

        String replacement = "";

        int start = originalString.lastIndexOf(occurrence);

        String builder = originalString.substring(0, start) +
                replacement +
                originalString.substring(start + occurrence.length());
        return builder.replace("  ", " ");
    }

    public static String removeLastRow(String table) {

        String [] lines = table.split("\n");

        for (int i = 0; i < lines.length; i++){

            String [] eachLine = lines[i].split(" ");
            eachLine[eachLine.length-1] = "";

            lines[i] = Arrays.toString(eachLine).replace(",", "").replace("[", "").
                    replace("]", "").replaceFirst("\n", "");
        }

        for(int j = 0; j <lines.length; j++){
            lines[j] = lines[j].concat("\n");
        }

        return Arrays.toString(lines).replace(",", "").replace("[", "").
                replace("]", "").replace("  ", "").trim();
    }

    private void getHtml1(String codeLine, StringBuilder table) {
        if (codeLine.contains("<td style=\"font-weight: bold; font-size:15px; text-align:left;\"><a class=\"mt_a\" href=\"country/")) {
            int indexStart = codeLine.indexOf("/\">") + 3;
            int indexEnd = codeLine.indexOf("</a></td>");
            String stringExcerpt = codeLine.substring(indexStart, indexEnd);
            table.append("\n").append(stringExcerpt.replace(" ", "")).append(" ");
        }

        else if (codeLine.contains("<td style=\"font-weight: bold; font-size:15px; text-align:left;\"><span style=\"color:#00B5F0; font-style:italic; \">")) {
            int indexStart = codeLine.indexOf("; \">") + 4;
            int indexEnd = codeLine.indexOf("</span></td>");
            String stringExcerpt = codeLine.substring(indexStart, indexEnd);
            table.append("\n").append(stringExcerpt.replace(" ", "")).append(" ");
        }

        else if (codeLine.contains("<td style=\"font-weight: bold; text-align:right;background-color:#FFEEAA;\"")) {
            int indexStart = codeLine.indexOf("A;\">") + 4;
            int indexEnd = codeLine.indexOf("</td>");
            String stringExcerpt = codeLine.substring(indexStart, indexEnd);
            table.append(keepOnlyNumber1(stringExcerpt));
        }

        else if (codeLine.contains("text-align:right;background-color:red; color:white\">")) {
            int indexStart = codeLine.indexOf("e\">") + 3;
            int indexEnd = codeLine.indexOf("</td>");
            String stringExcerpt = codeLine.substring(indexStart, indexEnd);
            table.append(keepOnlyNumber1(stringExcerpt));
        }

        else if (codeLine.contains("<td style=\"font-weight: bold; text-align:right\">")) {
            int indexStart = codeLine.indexOf("t\">") + 3;
            int indexEnd = codeLine.indexOf("</td>");
            String stringExcerpt = codeLine.substring(indexStart, indexEnd);
            table.append(keepOnlyNumber1(stringExcerpt));
        }

        else if (codeLine.contains("<td style=\"font-weight: bold; text-align:right;\">")) {
            int indexStart = codeLine.indexOf(";\">") + 3;
            int indexEnd = codeLine.indexOf("</td>");
            String stringExcerpt = codeLine.substring(indexStart, indexEnd);
            table.append(keepOnlyNumber1(stringExcerpt));
        }

        else if (codeLine.contains("<td style=\"text-align:right;font-weight:bold;\">")) {
            int indexStart = codeLine.indexOf(";\">") + 3;
            int indexEnd = codeLine.indexOf("</td>");
            String stringExcerpt = codeLine.substring(indexStart, indexEnd);
            table.append(keepOnlyNumber1(stringExcerpt));
        }

        else if (codeLine.contains("                                    text-align:right;\">")) {
            int indexStart = codeLine.indexOf(";\">") + 3;
            int indexEnd = codeLine.indexOf("</td>");
            String stringExcerpt = codeLine.substring(indexStart, indexEnd);
            table.append(keepOnlyNumber1(stringExcerpt));
        }
    }
}
package assemblers;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import static org.junit.Assert.*;

class DataAssemblerTest {

    @Test
    void getSelectedRawTable() throws IOException {

        DataAssembler rawTable = new DataAssembler();
        System.out.println(rawTable.getSelectedRawTable());
    }

    @Test
    void getSelectedRawTable1() throws IOException {

        DataAssembler rawTable = new DataAssembler();
        String output = rawTable.getSelectedRawTable1();
        System.out.println(output);
    }

    @Test
    void removeLastOccurrence(){
        String input = "UK 303110 1295 42589 128 0 0 0 379 4466 627 7714201 113651 67876328";
        String outputExpected = "UK 303110 1295 42589 128 0 0 379 4466 627 7714201 113651 67876328";

        String outputActual = DataAssembler.replaceLastOccurrence(input, " 0");

        assertEquals(outputExpected, outputActual);
    }

    @Test
    void removeLastRow(){
        String input = "UK 303110 1295 42589 128 0 0 379 4466 627 7714201 113651 67876328";
        String outputExpected = "UK 303110 1295 42589 128 0 0 379 4466 627 7714201 113651";

        String outputActual = DataAssembler.removeLastRow(input);

        assertEquals(outputExpected, outputActual);
    }

    @Test
    void removeNewRec(){
        String input = "USA 2330578 0 121980 0 972941 0 1235657 16529 7042 369 27975863 84532";
        String outputExpected = "USA 2330578 0 121980 0 972941 1235657 16529 7042 369 27975863 84532";

        String outputActual = DataAssembler.replaceNewRecov(input);

        assertEquals(outputExpected, outputActual);

    }

    @Test
    void getNewRecovered() throws IOException {
        DataAssembler assembler = new DataAssembler();

        String output = assembler.getRecovered();

        System.out.println(output);

    }

}
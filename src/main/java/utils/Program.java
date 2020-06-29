package utils;

import assemblers.GraphAssembler;
import domain.Country;
import domain.Tops;
import domain.WorldCases;

import java.io.IOException;
import java.util.Scanner;

public class Program {

    public static void runProgram() throws IOException, IllegalAccessException {
        System.out.println("\n ");
        System.out.println("Starting the program...");
        System.out.println("\nRunning...\n");
        System.out.println("Gathering data...\n");
        String option = "-1";
        String country;
        String countries;
        String top;
        String viewTop = "";
        WorldCases worldCases = new WorldCases();
        Scanner myObj = new Scanner(System.in);
        System.out.println("Hello, and welcome to Covid19 Reports. " + "\n"
                + "What do you want to find out?" + "\n");


        do {
            while (option.equals("-1")) {
                try {
                    option = selectChoice();
                } catch (IllegalArgumentException j) {
                    System.out.println(j.getMessage());
                    option = "-1";
                }
            }

            if (option.equals("1")) {
                System.out.println(worldCases.relatorio());
                while (option.equals("1")) {
                    try {
                        System.out.println("\nMain menu(1), reload data(2) or quit(3)?");
                        option = continueProgram(myObj.nextLine());
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                        option = "1";
                    }
                }
            } else if (option.equals("2")) {
                country = selectCountry();
                while (option.equals("2")) {
                    viewTop = "";
                    try {
                        System.out.println("\n" + worldCases.relatorio(country));
                    } catch (IllegalArgumentException f) {
                        System.out.println(f.getMessage());
                        country = selectCountry();
                        option = "3";
                    }
                    while (viewTop.equals("") && !option.equals("3")) {
                        try {
                            viewTop = addGraph(worldCases, country);
                        } catch (IllegalArgumentException x) {
                            System.out.println(x.getMessage());
                            option = "4";
                            viewTop = "";
                        }
                        option = "5";
                    }
                    if (!option.equals("3") && viewTop.equals("x")) {
                        System.out.println("Main menu(1), check another country(2), reload data(3) or quit(4)?");
                        option = "8";
                    } else option = "2";
                }
                while (option.equals("8")) {
                    try {
                        option = continueProgram(myObj.nextLine(), "2");
                    } catch (IllegalArgumentException g) {
                        System.out.println(g.getMessage());
                        option = "8";
                    }
                }

            } else if (option.equals("3")) {
                countries = selectCountries();
                while (option.equals("3")) {
                    try {
                        System.out.println("\n" + worldCases.compareCountries(countries));
                    } catch (IllegalArgumentException f) {
                        System.out.println(f.getMessage());
                        countries = selectCountries();
                        option = "4";
                    }
                    if (!option.equals("4")) {
                        System.out.println("Main menu(1), compare more countries(2), reload data(3) or quit(4)?");
                        option = "8";
                    } else option = "3";
                }
                while (option.equals("8")) {
                    try {
                        option = continueProgram(myObj.nextLine(), "3");
                    } catch (IllegalArgumentException g) {
                        System.out.println(g.getMessage());
                        option = "8";
                    }
                }
            } else if (option.equals("4")) {
                top = selectTops();
                while (option.equals("4")) {
                    try {
                        System.out.println("\n" + Tops.tops(top, worldCases));
                    } catch (IllegalArgumentException x) {
                        System.out.println(x.getMessage());
                        System.out.println("Please select between 1 and 6.");
                        top = selectTops();
                        option = "5";
                    }
                    if (!option.equals("5")) {
                        System.out.println("Main menu(1), see more tops(2), reload data(3) or quit(4)?");
                        option = "8";
                    } else option = "4";
                }
                while (option.equals("8")) {
                    try {
                        option = continueProgram(myObj.nextLine(), "4");
                    } catch (IllegalArgumentException g) {
                        System.out.println(g.getMessage());
                        option = "8";
                    }
                }
            } else if (option.equals("6")) {
                System.out.println(" Hey! :) Thank you for using my little program,\n" +
                        " my name is Alexandre Oliveira and I'm a 24y portuguese guy.\n I use " +
                        "www.worldometers.info's data to present it to you, this way." +
                        "\n If you want to tell me something, you can reach me at\n" +
                        " alex.dragao12@hotmail.com, bye!");
                option = getString(myObj);
            } else if (option.equals("5")) {
                System.out.println(covidTips());
                option = getString(myObj);
            } else if (option.equals("pt")){
                System.out.println("\n" + worldCases.compareCountries("portugal"));
                myObj.nextLine();
                option = "-1";
            }
            else if (option.equals("world")){
                System.out.println("\n" + worldCases.compareCountries("world"));
                myObj.nextLine();
                option = "-1";
            }
            else if (option.equals("act")){
                System.out.println("\n" + Tops.top10("20", worldCases));
                myObj.nextLine();
                option = "-1";
            }
            else if (option.equals("reload")){
                System.out.println("\nreloading...\n");
                worldCases = new WorldCases();
                option = "-1";
            }

        } while (!option.equals("7"));
        System.out.println("Program over.");
    }

    private static String getString(Scanner myObj) {
        String option;
        System.out.println("\nMain menu(1) or quit(2)?");
        option = "8";
        while (option.equals("8")) {
            try {
                option = continueProgram(myObj.nextLine());
            } catch (IllegalArgumentException g) {
                System.out.println(g.getMessage());
                option = "8";
            }
        }
        return option;
    }

    public static String selectChoice() {
        Scanner myObj = new Scanner(System.in);
        System.out.println("1. Check worldwide stats.");
        System.out.println("2. Check a country's stats.");
        System.out.println("3. Compare countries.");
        System.out.println("4. View tops.");
        System.out.println("5. Covid19 information.");
        System.out.println("6. Talk to me!");
        System.out.println("7. Shutdown.");
        System.out.println("Write 1, 2, 3, 4, 5, 6 or 7 please.");
        String choice = "";
        while (choice.equals("")) {
            choice = myObj.nextLine();
        }
        if (choice.equals("1") || choice.equals("2") || choice.equals("3") || choice.equals("4") || choice.equals("5")
                || choice.equals("6") || choice.equals("7") || choice.equals("pt") || choice.equals("world") ||
                choice.equals("act") || choice.equals("reload")) {
            return choice;
        }
        throw new IllegalArgumentException("Please choose between 1, 2, 3, 4, 5, 6 or 7.");
    }

    public static String selectCountry() {
        System.out.println("Please write the name of the country, in English.");
        Scanner myObj = new Scanner(System.in);
        return myObj.nextLine();
    }

    public static String selectCountries() {
        System.out.println("Please write the name of the countries you want to compare, in English.");
        System.out.println("Separate the names with a comma(,).");
        Scanner myObj = new Scanner(System.in);
        return myObj.nextLine();
    }

    public static String selectTops() {
        System.out.println("Choose between one of this tops:");
        System.out.println("1. Most cases.\n2. Most deaths.\n3. Most recovered.\n4. New cases.\n5. Most" +
                " in ICU.\n6. Most tests.\n7. Least cases.");
        Scanner myObj = new Scanner(System.in);
        return myObj.nextLine();
    }

    public static String continueProgram(String i) {
        if (i.equals("1")) {
            return "-1";
        } else if (i.equals("3")) {
            return "7";
        } else if (i.equals("2"))
            return "reload";
        throw new IllegalArgumentException("Please choose between 1, 2 or 3.");
    }

    public static String continueProgram(String i, String n) {
        switch (i) {
            case "1":
                return "-1";
            case "4":
                return "7";
            case "2":
                return n;
            case "3":
                return "reload";
        }
        throw new IllegalArgumentException("Please choose between 1, 2, 3 or 4.");
    }

    public static String covidTips() {
        return "\n The World Health Organization recommends you to:\n" +
                "> Wash your hands frequently;\n> Maintain social distancing (1 meter, minimum);\n" +
                "> Avoid touching eyes, nose and mouth;\n> Practice respiratory hygiene (sneezing or coughing, e.g.)" +
                ";\n> If you have fever, cough and difficulty breathing, CALL medical care early;\n" +
                "> For more information check www.who.int or your country's National Health Association;\n" +
                "> Stay inside, informed and be responsible.";
    }

    public static String addGraph(WorldCases worldCases, String country){
        System.out.println("You want to see the cases in a chart?");
        System.out.println("Just write yes or no.");
        Scanner myObj = new Scanner(System.in);

        Country country1 = (Country) worldCases.getCountriesFromString(country).get(0);

        String choice = "";
        while (choice.equals("")) {
            choice = myObj.nextLine();
        }

        if (choice.equalsIgnoreCase("Yes") ||
                choice.equalsIgnoreCase("y")){
            System.out.println("\n" + GraphAssembler.graphCountry(country1) +"\n");
            return "x";
        } else if (choice.equalsIgnoreCase("No") ||
                choice.equalsIgnoreCase("n")){
            return "x";
        }
        else throw new IllegalArgumentException("Please try again.");
    }

}

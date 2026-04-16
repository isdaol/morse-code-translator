package morse;

import java.util.Scanner;

/**
 * Interactive CLI for the Morse Code Translator.
 *
 * Usage:
 *   java -cp . morse.Main             → interactive mode
 *   java -cp . morse.Main "SOS"       → encode single argument
 *   java -cp . morse.Main "... --- ..." → decode single argument
 */
public class Main {

    private static final String BANNER = """
            
            ███╗   ███╗ ██████╗ ██████╗ ███████╗███████╗
            ████╗ ████║██╔═══██╗██╔══██╗██╔════╝██╔════╝
            ██╔████╔██║██║   ██║██████╔╝███████╗█████╗  
            ██║╚██╔╝██║██║   ██║██╔══██╗╚════██║██╔══╝  
            ██║ ╚═╝ ██║╚██████╔╝██║  ██║███████║███████╗
            ╚═╝     ╚═╝ ╚═════╝ ╚═╝  ╚═╝╚══════╝╚══════╝
             Morse Code Translator  •  github.com/you/morse
            """;

    private static final String HELP = """
            Commands:
              encode <text>   — convert text to Morse  (alias: e)
              decode <morse>  — convert Morse to text  (alias: d)
              auto   <input>  — detect and translate automatically
              help            — show this message
              exit            — quit
            
            Morse format:
              Letters separated by spaces, words by  /
              Example: ... --- ...  =  SOS
            """;

    public static void main(String[] args) {
        // Single-argument mode (great for scripts / pipes)
        if (args.length > 0) {
            String input = String.join(" ", args);
            if (MorseCode.isMorse(input)) {
                System.out.println(MorseCode.decode(input));
            } else {
                System.out.println(MorseCode.encode(input));
            }
            return;
        }

        // Interactive mode
        System.out.println(BANNER);
        System.out.println(HELP);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("morse> ");
            String line = scanner.nextLine().trim();

            if (line.isEmpty()) continue;

            String[] parts = line.split("\\s+", 2);
            String cmd  = parts[0].toLowerCase();
            String rest = parts.length > 1 ? parts[1] : "";

            switch (cmd) {
                case "encode", "e" -> {
                    if (rest.isEmpty()) { System.out.println("Usage: encode <text>"); break; }
                    String result = MorseCode.encode(rest);
                    printResult("Morse", result);
                }
                case "decode", "d" -> {
                    if (rest.isEmpty()) { System.out.println("Usage: decode <morse>"); break; }
                    String result = MorseCode.decode(rest);
                    printResult("Text", result);
                }
                case "auto", "a" -> {
                    if (rest.isEmpty()) { System.out.println("Usage: auto <input>"); break; }
                    if (MorseCode.isMorse(rest)) {
                        printResult("Text  (decoded)", MorseCode.decode(rest));
                    } else {
                        printResult("Morse (encoded)", MorseCode.encode(rest));
                    }
                }
                case "help", "h", "?" -> System.out.println(HELP);
                case "exit", "quit", "q" -> {
                    System.out.println("73 de Morse! (goodbye)");
                    scanner.close();
                    return;
                }
                default -> System.out.printf("Unknown command: '%s'. Type 'help' for usage.%n", cmd);
            }
        }
    }

    private static void printResult(String label, String value) {
        System.out.printf("  %-20s %s%n%n", label + ":", value);
    }
}

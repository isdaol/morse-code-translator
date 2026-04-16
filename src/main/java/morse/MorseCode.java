package morse;

import java.util.HashMap;
import java.util.Map;

/**
 * Core Morse Code translation engine.
 * Supports encoding text → Morse and decoding Morse → text.
 */
public class MorseCode {

    private static final Map<Character, String> TEXT_TO_MORSE = new HashMap<>();
    private static final Map<String, Character> MORSE_TO_TEXT = new HashMap<>();

    static {
        String[][] codes = {
            {"A", ".-"},   {"B", "-..."}, {"C", "-.-."},
            {"D", "-.."},  {"E", "."},    {"F", "..-."},
            {"G", "--."},  {"H", "...."}, {"I", ".."},
            {"J", ".---"}, {"K", "-.-"},  {"L", ".-.."},
            {"M", "--"},   {"N", "-."},   {"O", "---"},
            {"P", ".--."}, {"Q", "--.-"}, {"R", ".-."},
            {"S", "..."},  {"T", "-"},    {"U", "..-"},
            {"V", "...-"}, {"W", ".--"},  {"X", "-..-"},
            {"Y", "-.--"}, {"Z", "--.."},

            {"0", "-----"}, {"1", ".----"}, {"2", "..---"},
            {"3", "...--"}, {"4", "....-"}, {"5", "....."},
            {"6", "-...."}, {"7", "--..."}, {"8", "---.."},
            {"9", "----."},

            {".", ".-.-.-"}, {",", "--..--"}, {"?", "..--.."},
            {"!", "-.-.--"}, {"/", "-..-."},  {"@", ".--.-."},
            {"(", "-.--."}, {")", "-.--.-"},  {":", "---..."},
            {";", "-.-.-."}, {"=", "-...-"},  {"+", ".-.-."},
            {"-", "-....-"}, {"_", "..--.-"}, {"\"", ".-..-."},
            {"'", ".----."}, {"$", "...-..-"}
        };

        for (String[] pair : codes) {
            char letter = pair[0].charAt(0);
            String code  = pair[1];
            TEXT_TO_MORSE.put(letter, code);
            MORSE_TO_TEXT.put(code, letter);
        }
    }

    /**
     * Encodes plain text to Morse code.
     * Letters are separated by a single space, words by " / ".
     *
     * @param text input text (case-insensitive)
     * @return Morse code string, or error message for unknown characters
     */
    public static String encode(String text) {
        if (text == null || text.isBlank()) return "";

        StringBuilder result = new StringBuilder();
        String[] words = text.trim().toUpperCase().split("\\s+");

        for (int w = 0; w < words.length; w++) {
            if (w > 0) result.append(" / ");

            for (int c = 0; c < words[w].length(); c++) {
                char ch = words[w].charAt(c);
                String morse = TEXT_TO_MORSE.get(ch);

                if (morse == null) {
                    result.append("[?]");
                } else {
                    if (c > 0) result.append(" ");
                    result.append(morse);
                }
            }
        }
        return result.toString();
    }

    /**
     * Decodes Morse code back to plain text.
     * Words must be separated by " / " (space-slash-space).
     *
     * @param morse Morse code string using dots, dashes, spaces
     * @return decoded text in uppercase
     */
    public static String decode(String morse) {
        if (morse == null || morse.isBlank()) return "";

        StringBuilder result = new StringBuilder();
        String[] words = morse.trim().split("\\s*/\\s*");

        for (int w = 0; w < words.length; w++) {
            if (w > 0) result.append(" ");

            String[] letters = words[w].trim().split("\\s+");
            for (String code : letters) {
                if (code.isEmpty()) continue;
                Character ch = MORSE_TO_TEXT.get(code);
                result.append(ch != null ? ch : '?');
            }
        }
        return result.toString();
    }

    /**
     * Detects whether a string looks like Morse code.
     */
    public static boolean isMorse(String input) {
        return input != null && input.matches("[.\\-\\s/]+");
    }
}

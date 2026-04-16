package morse;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MorseCodeTest {

    // ── encode ────────────────────────────────────────────────────────────────

    @Test
    void encodeSimpleWord() {
        assertEquals("... --- ...", MorseCode.encode("SOS"));
    }

    @Test
    void encodeLowercase() {
        assertEquals("... --- ...", MorseCode.encode("sos"));
    }

    @Test
    void encodeTwoWords() {
        assertEquals(".... . .-.. .-.. --- / .-- --- .-. .-.. -..", MorseCode.encode("HELLO WORLD"));
    }

    @Test
    void encodeNumbers() {
        assertEquals(".---- ..--- ...--", MorseCode.encode("123"));
    }

    @Test
    void encodePunctuation() {
        assertEquals(".... .  .-.-.-", MorseCode.encode("HI."));
    }

    @Test
    void encodeEmptyString() {
        assertEquals("", MorseCode.encode(""));
    }

    @Test
    void encodeNull() {
        assertEquals("", MorseCode.encode(null));
    }

    // ── decode ────────────────────────────────────────────────────────────────

    @Test
    void decodeSimpleSOS() {
        assertEquals("SOS", MorseCode.decode("... --- ..."));
    }

    @Test
    void decodeTwoWords() {
        assertEquals("HELLO WORLD", MorseCode.decode(".... . .-.. .-.. --- / .-- --- .-. .-.. -.."));
    }

    @Test
    void decodeNumbers() {
        assertEquals("123", MorseCode.decode(".---- ..--- ...--"));
    }

    @Test
    void decodeEmptyString() {
        assertEquals("", MorseCode.decode(""));
    }

    @Test
    void decodeNull() {
        assertEquals("", MorseCode.decode(null));
    }

    @Test
    void decodeUnknownCodeReturnsQuestionMark() {
        assertEquals("?", MorseCode.decode(".........")); // invalid code
    }

    // ── round-trip ────────────────────────────────────────────────────────────

    @Test
    void roundTripAlphabet() {
        String original = "THE QUICK BROWN FOX";
        assertEquals(original, MorseCode.decode(MorseCode.encode(original)));
    }

    @Test
    void roundTripNumbers() {
        String original = "0123456789";
        assertEquals(original, MorseCode.decode(MorseCode.encode(original)));
    }

    // ── isMorse ───────────────────────────────────────────────────────────────

    @Test
    void detectMorseTrue() {
        assertTrue(MorseCode.isMorse("... --- ..."));
    }

    @Test
    void detectMorseFalse() {
        assertFalse(MorseCode.isMorse("HELLO"));
    }

    @Test
    void detectMorseNull() {
        assertFalse(MorseCode.isMorse(null));
    }
}

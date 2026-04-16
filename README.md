# 📡 Morse Code Translator

A clean, zero-dependency Java CLI that translates text ↔ Morse code with auto-detection.

```
... --- ...  →  SOS
HELLO WORLD  →  .... . .-.. .-.. --- / .-- --- .-. .-.. -..
```

---

## ✨ Features

- **Encode** — any text (letters, digits, punctuation) → Morse code
- **Decode** — Morse code → text
- **Auto mode** — paste anything, the tool detects direction automatically
- **Single-arg mode** — pipe-friendly, great for scripts
- **Zero dependencies** — pure Java, no frameworks
- **Unit tested** — 16 JUnit 5 tests including round-trip checks

---

## 🚀 Quick Start

### Requirements
- Java 21+
- Maven 3.8+ (only needed to build)

### Build

```bash
git clone https://github.com/you/morse-code-translator.git
cd morse-code-translator
mvn package -q
```

### Run — interactive mode

```bash
java -jar target/morse-code-translator-1.0.0-runnable.jar
```

```
morse> encode Hello World
  Morse:               .... . .-.. .-.. --- / .-- --- .-. .-.. -..

morse> decode ... --- ...
  Text:                SOS

morse> auto .... ..
  Text  (decoded):     HI

morse> auto SOS
  Morse (encoded):     ... --- ...
```

### Run — single argument (pipe mode)

```bash
# encode
java -jar morse.jar "SOS"
# → ... --- ...

# decode (auto-detected)
java -jar morse.jar "... --- ..."
# → SOS

# pipe
echo "HELLO" | xargs java -jar morse.jar
```

---

## 📐 Morse Format

| Separator | Meaning          |
|-----------|------------------|
| ` `       | Between letters  |
| ` / `     | Between words    |

Example: `.... . .-.. .-.. ---` = `HELLO`

---

## 🗂️ Project Structure

```
morse-code-translator/
├── pom.xml
└── src/
    ├── main/java/morse/
    │   ├── MorseCode.java   # translation engine
    │   └── Main.java        # CLI entry point
    └── test/java/morse/
        └── MorseCodeTest.java
```

---

## 🧪 Tests

```bash
mvn test
```

Covers encoding, decoding, round-trips, null/empty inputs, and auto-detection.

---

## 📖 Supported Characters

Letters `A–Z`, digits `0–9`, and punctuation:  
`. , ? ! / @ ( ) : ; = + - _ " ' $`

---

## 📜 License

MIT — do whatever you want.

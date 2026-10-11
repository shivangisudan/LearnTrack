# Setup Instructions

## My setup

- macOS
- IntelliJ IDEA with JDK 24
- Terminal JDK: OpenJDK 21.0.12.1
- Compile target: Java 21
- Base package: `com.airtribe.learntrack`

To check the terminal's Java version:

```sh
java -version
javac -version
```

IntelliJ and the terminal can use different JDKs. Both need JDK 21 or newer for this project.

## First run

The first program printed `Hello LearnTrack!` in IntelliJ and finished with exit code 0.

```java
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello LearnTrack!");
    }
}
```

The same check can be run in the terminal:

```sh
javac --release 21 HelloWorld.java
java HelloWorld
```

Output:

```text
Hello LearnTrack!
```

## Run LearnTrack in IntelliJ

1. Open the LearnTrack folder.
2. Select your JDK under **File > Project Structure > Project SDK**.
3. Mark `src` as **Sources Root** if needed.
4. Open `com.airtribe.learntrack.Main` and run `main` using the green arrow.

## Run from the terminal

From the project folder:

```sh
sh run.sh
```

Or compile and run manually:

```sh
mkdir -p out
find src -name '*.java' -print > out/sources.txt
javac --release 21 -d out @out/sources.txt
java -cp out com.airtribe.learntrack.Main
```

`out` holds the compiled files and is ignored by Git. There are no external libraries to install.

## Common issues

- If IntelliJ cannot find a class, check its package, imports and the Sources Root setting.
- If Java reports an unsupported class version, check both Java versions and rebuild.
- Yellow underlines are warnings or suggestions. Hover over them to see the reason; red errors need fixing before compilation.

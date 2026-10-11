# JVM Basics

## JDK, JRE and JVM

The **JDK (Java Development Kit)** is what we install to write and run Java programs. It includes the compiler, `javac`, and the runtime tools.

The **JRE (Java Runtime Environment)** contains the JVM and libraries needed to run Java programs. It doesn't include the compiler. The JDK already includes these runtime components, so a separate JRE isn't needed for this project.

The **JVM (Java Virtual Machine)** runs compiled Java code. When we run `java`, it starts the JVM and calls the program's `main` method.

## Bytecode

Compiling a `.java` file produces `.class` files containing bytecode. The JVM runs this bytecode. In LearnTrack, the compiled files go into `out`.

## Write once, run anywhere

Java bytecode can run on different operating systems as long as they have a compatible JVM. For example, the same compiled LearnTrack classes can run on a Mac or Windows computer with the right Java version.

The Java version still matters. This project compiles with `--release 21`, so it needs Java 21 or newer to run. The terminal commands may differ between operating systems, even when the compiled Java files are the same.

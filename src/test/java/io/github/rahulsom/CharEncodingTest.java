package io.github.rahulsom;

import groovy.lang.GroovyShell;
import org.apache.commons.text.WordUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

class CharEncodingTest {

    @Test
    @DisplayName("Run CharEncoding.groovy and capture output")
    void runPartOne() throws Exception {
        var shell = new GroovyShell();
        var op = new StringBuilder();
        
        // Capture System.out
        var originalOut = System.out;
        var baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));
        
        try {
            shell.evaluate(new File("src/main/groovy/CharEncoding.groovy"));
            op.append(baos.toString());
        } finally {
            System.setOut(originalOut);
        }
        
        // Write output to file
        var outputPath = Paths.get("build/CharEncoding.txt");
        Files.createDirectories(outputPath.getParent());
        Files.writeString(outputPath, op.toString());
        
        assertThat(op.toString()).isNotEmpty();
    }

    @Test
    @DisplayName("Run WrongEncoding.groovy and capture output")
    void runPartTwo() throws Exception {
        var shell = new GroovyShell();
        var op = new StringBuilder();
        
        // Capture System.out
        var originalOut = System.out;
        var baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));
        
        try {
            shell.evaluate(new File("src/main/groovy/WrongEncoding.groovy"));
            op.append(baos.toString());
        } finally {
            System.setOut(originalOut);
        }
        
        // Write output to file
        var outputPath = Paths.get("build/WrongEncoding.txt");
        Files.createDirectories(outputPath.getParent());
        Files.writeString(outputPath, op.toString());
        
        assertThat(op.toString()).isNotEmpty();
    }

    @Test
    @DisplayName("Generate list of all available charsets")
    void runPartThree() throws Exception {
        var charsetList = String.join(", ", Charset.availableCharsets().keySet());
        var wrappedText = WordUtils.wrap(charsetList, 80);
        
        var outputPath = Paths.get("build/AllCharsets.txt");
        Files.createDirectories(outputPath.getParent());
        Files.writeString(outputPath, wrappedText);
        
        assertThat(Charset.availableCharsets()).isNotEmpty();
        assertThat(wrappedText).contains("UTF-8");
    }
}
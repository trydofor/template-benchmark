package com.mitchellbosecke.benchmark;

import com.mitchellbosecke.pebble.error.PebbleException;
import freemarker.template.TemplateException;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;

import static org.junit.Assert.assertEquals;

/**
 * @author Martin Kouba
 */
public class ExpectedOutputTest {

    @BeforeClass
    public static void beforeClass() {
        Locale.setDefault(Locale.ENGLISH);
    }

    @Test
    public void testMeepoOutput() throws IOException {
        Meepo meepo = new Meepo();
        meepo.setup();
        assertOutput("meepo", meepo.benchmark());
    }

    @Test
    public void testFreemarkerOutput() throws IOException, TemplateException {
        Freemarker freemarker = new Freemarker();
        freemarker.setup();
        assertOutput("freemarker", freemarker.benchmark());
    }

    @Test
    public void testRockerOutput() throws IOException, TemplateException {
        Rocker rocker = new Rocker();
        rocker.setup();
        assertOutput("rocker", rocker.benchmark());
    }

    @Test
    public void testPebbleOutput() throws IOException, PebbleException {
        Pebble pebble = new Pebble();
        pebble.setup();
        assertOutput("pebble", pebble.benchmark());
    }

    @Test
    public void testVelocityOutput() throws IOException {
        Velocity velocity = new Velocity();
        velocity.setup();
        assertOutput("velocity", velocity.benchmark());
    }

    @Test
    public void testMustacheOutput() throws IOException {
        Mustache mustache = new Mustache();
        mustache.setup();
        assertOutput("mustache", mustache.benchmark());
    }

    @Test
    public void testThymeleafOutput() throws IOException, TemplateException {
        Thymeleaf thymeleaf = new Thymeleaf();
        thymeleaf.setup();
        assertOutput("thymeleaf", thymeleaf.benchmark());
    }

    @Test
    public void testTrimouOutput() throws IOException {
        Trimou trimou = new Trimou();
        trimou.setup();
        assertOutput("trimou", trimou.benchmark());
    }

    private void assertOutput(String type, String output) throws IOException {
        String actual = output.replaceAll("\\s", "");
        String expected = readExpectedOutputResource();
        try {
            assertEquals(expected, actual);
        } catch (Throwable e) {
            writeToFile(type, actual);
            writeToFile("expected", expected);
            throw e;
        }
    }

    private int count = 1;

    private void writeToFile(String type, String content) {
        Path path = Paths.get("./" + type + (count++) + ".txt");
        System.out.println("=========");
        System.out.println(path.toAbsolutePath());
        System.out.println("=========");
        try {
            Files.write(path, content.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String readExpectedOutputResource() throws IOException {
        StringBuilder builder = new StringBuilder();
        String name = "/expected-output.html";
        try (BufferedReader in = new BufferedReader(new InputStreamReader(ExpectedOutputTest.class.getResourceAsStream(name)))) {
            for (; ; ) {
                String line = in.readLine();
                if (line == null)
                    break;
                builder.append(line);
            }
        }
        // Remove all whitespaces
        return builder.toString().replaceAll("\\s", "");
    }

}

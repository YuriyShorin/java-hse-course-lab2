package com.example.letterscounter;

import java.io.*;
import java.util.HashMap;

/**
 * A class for counting the frequency of repetition of each letter in a file
 */
public class LettersCounter {


    /**
     * The file for which statistics are collected
     */
    private File inputFile;

    /**
     * The file for saving statistics
     */
    private File outputFile;

    /**
     * The map for calculating and storing statistics
     */
    private final HashMap<Character, Integer> lettersCounter = new HashMap<>();

    /**
     * Method that opens input file
     *
     * @param fileName The name of the input file
     * @return Does the file exist?
     */
    public boolean openInputFile(String fileName) {
        this.inputFile = new File(fileName);
        return inputFile.exists();
    }

    /**
     * Method that opens or creates output file
     *
     * @param fileName The name of output file
     * @return Was it possible to open or create a file?
     */
    public boolean openOutputFile(String fileName) {
        this.outputFile = new File(fileName);
        if (!outputFile.exists()) {
            try {
                if (!outputFile.createNewFile()) {
                    return false;
                }
            } catch (IOException e) {
                return false;
            }
        }
        return true;
    }

    /**
     * Method that calculates the frequency of letters
     *
     * @return Was it possible to calculate the frequency of letters?
     */
    public boolean countLetters() {
        try (FileReader FileReader = new FileReader(inputFile.getPath())) {
            BufferedReader reader = new BufferedReader(FileReader);
            String line = reader.readLine();
            while (line != null) {
                processLine(line);
                line = reader.readLine();
            }
        } catch (IOException exception) {
            return false;
        }
        return true;
    }

    /**
     * Method that outputs the statistics
     *
     * @return Was it possible to output the statistics
     */
    public boolean outputResults() {
        try (FileWriter fileWriter = new FileWriter(outputFile.getPath())) {
            BufferedWriter writer = new BufferedWriter(fileWriter);
            for (var entry : lettersCounter.entrySet()) {
                writer.write(entry.getKey() + " - " + entry.getValue() + '\n');
                writer.flush();
            }
            writer.close();
        } catch (IOException exception) {
            return false;
        }
        return true;
    }

    /**
     * Method that calculates the frequency of letters in one particular string
     *
     * @param line The string with letters
     */
    private void processLine(String line) {
        for (int i = 0; i < line.length(); ++i) {
            char symbol = line.charAt(i);
            if (lettersCounter.containsKey(symbol)) {
                lettersCounter.put(symbol, lettersCounter.get(symbol) + 1);
            } else {
                lettersCounter.put(symbol, 1);
            }
        }
    }
}

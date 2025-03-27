package org.griddynamics.tasks;

import java.io.*;
import java.util.*;

public class Task1 {

    public static final String RAND_STRING = "Show must go on!"; // "yellow submarine" <- Test for not throwing exception
    public static final Set<String> REMOVE_WORDS = new HashSet<>(List.of(new String[]{"yellow","submarine"}));

    //PART 1
    public static String[] lyricsToArray(String lyrics){
        StringBuilder convertLyrics = new StringBuilder(convertLyrics(lyrics));
        return convertLyrics.toString().split("\\s+");
    }
    public static String convertLyrics(String lyrics) {
        return lyrics.replaceAll("[,()\n]", " ")
                .toLowerCase(Locale.ROOT);
    }

    public static void printWordAppearance(String[] wordArray){
        Set<String> uniqueWordSet = new HashSet<>(List.of(wordArray));
        for(String word : uniqueWordSet){
            System.out.println(word+" appear "+ Arrays.stream(wordArray).
                    filter(wordInArray -> wordInArray.contains(word))
                    .count()
            + " times.");

        }
    }

    //PART 2
    public static List<String> lyricsToList(String lyrics) {
        StringBuilder convertLyrics = new StringBuilder(convertLyrics(lyrics));
        return List.of(convertLyrics
                .toString()
                .split("\\s+"));

    }

    //PART 3
    public static List<String> removeDuplicates(List<String> wordList){
        Set<String> uniqueWordSet = new HashSet<>(wordList);
        return uniqueWordSet.stream().toList();
    }

    public static void sortListByStringLength(List<String> wordList){
        wordList.sort(Comparator.comparing(String::length));
    }

    public static void removeWords(List<String> wordList){
        wordList.removeAll(REMOVE_WORDS);
    }
    //PART 4
    public static void saveToTxtFile(String lyrics) throws IOException {
        try (BufferedWriter out = new BufferedWriter(new FileWriter("./yellowSub.txt"))) {
            out.write(lyrics);
        }
    }

    public static String readFromTxtFile() throws IOException {
        StringBuilder lyrics = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader("./yellowSub.txt"))) {
            String line;
            while ((line = in.readLine()) != null)
                lyrics.append(line);
        }
        return lyrics.toString();
    }

    public static boolean isStringFoundInTheLyrics(String lyrics) {
        return lyrics.contains(RAND_STRING);
    }

    public static void main(String[] args) throws IOException {
        String lyrics = "In the town where I was born\n" +
                "Lived a man who sailed to sea\n" +
                "And he told us of his life\n" +
                "In the land of submarines\n" +
                "So we sailed onto the sun\n" +
                "Till we found a sea of green\n" +
                "And we lived beneath the waves\n" +
                "In our yellow submarine\n" +
                "We all live in a yellow submarine\n" +
                "Yellow submarine, yellow submarine\n" +
                "We all live in a yellow submarine\n" +
                "Yellow submarine, yellow submarine\n" +
                "And our friends are all aboard\n" +
                "Many more of them live next door\n" +
                "And the band begins to play\n" +
                "We all live in a yellow submarine\n" +
                "Yellow submarine, yellow submarine\n" +
                "We all live in a yellow submarine\n" +
                "Yellow submarine, yellow submarine\n" +
                "As we live a life of ease (a life of ease)\n" +
                "Everyone of us (everyone of us) has all we need (has all we need)\n" +
                "Sky of blue (sky of blue) and sea of green (sea of green)\n" +
                "In our yellow (in our yellow) submarine (submarine)\n" +
                "We all live in a yellow submarine\n" +
                "Yellow submarine, yellow submarine\n" +
                "We all live in a yellow submarine\n" +
                "A yellow submarine, yellow submarine\n" +
                "We all live in a yellow submarine\n" +
                "Yellow submarine, yellow submarine\n" +
                "We all live in a yellow submarine\n" +
                "Yellow submarine, yellow submarine\n";

        System.out.println("Part 1\n");
        String[] wordArray = lyricsToArray(lyrics);
        printWordAppearance(wordArray);

        System.out.println("\nPART 2\n");
        List<String> wordList = new ArrayList<>(removeDuplicates(lyricsToList(lyrics)));
        Task1.sortListByStringLength(wordList);
        System.out.println(wordList);

        System.out.println("\nPART 3\n");
        wordList = new ArrayList<>(lyricsToList(lyrics));
        Task1.removeWords(wordList);
        System.out.println(wordList);

        try {
            saveToTxtFile(lyrics);
            String lyricsFromTheFile = readFromTxtFile();
            if (isStringFoundInTheLyrics(lyricsFromTheFile))
                System.out.println("Random string found in lyrics");
            else
                throw new BeatlesException("NOT FOUND");

        } catch (BeatlesException e) {
            throw new RuntimeException("There is problem with random string:\n" + e.getMessage());
        }

    }
}


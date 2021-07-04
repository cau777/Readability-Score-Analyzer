package readability;

import readability.scoremethods.ReadabilityScoreCalculator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("You need to specify the file path");
            System.exit(404);
        }
        
        String filepath = args[0];
        String text = null;
        
        try {
            text = readFile(filepath);
        } catch (IOException e) {
            System.out.println("Invalid file path: " + filepath);
            System.exit(404);
        }
        
        int characterCount = WordUtils.countCharacters(text);
        int wordCount = 0;
        int sentenceCount;
        int syllablesCount = 0;
        int polysyllableWordCount = 0;
        
        if (!text.matches(".*[.!?]")) {
            text += ".";
        }
        
        String[] sentences = WordUtils.findMatches(text, "[^.!?]+[.!?]");
        sentenceCount = sentences.length;
        
        for (String sentence : sentences) {
            String[] words = WordUtils.findMatches(sentence, "\\b[,\\w]+\\b");
            for (String word : words) {
                int wordSyllables = WordUtils.countSyllables(word);
                if (WordUtils.isPolysyllable(wordSyllables))
                    polysyllableWordCount++;
                
                syllablesCount += wordSyllables;
            }
            wordCount += words.length;
        }
        
        System.out.println("The text is: \n" + text);
        System.out.println();
        System.out.println("Words: " + wordCount);
        System.out.println("Sentences: " + sentenceCount);
        System.out.println("Characters: " + characterCount);
        System.out.println("Syllables: " + syllablesCount);
        System.out.println("Polysyllables: " + polysyllableWordCount);
        
        Scanner scanner = new Scanner(System.in);
        String method;
        
        do {
            System.out.print("Enter the score you want to calculate (ARI, FK, SMOG, CL, all): ");
            method = scanner.next().toUpperCase();
            System.out.println();
        } while (!validMethodName(method));
        
        
        if ("all".equalsIgnoreCase(method)) {
            double average = 0;
            
            for (String availableMethod : ReadabilityScoreCalculator.availableMethods) {
                average += applyMethod(availableMethod, wordCount, sentenceCount, characterCount, syllablesCount, polysyllableWordCount).maxAge;
            }
            
            average /= ReadabilityScoreCalculator.availableMethods.length;
            System.out.println();
            System.out.printf("This text should be understood in average by %s-year-olds.%n", MathUtils.roundTo(average, 2));
            
        } else {
            applyMethod(method, wordCount, sentenceCount, characterCount, syllablesCount, polysyllableWordCount);
        }
    }
    
    private static boolean validMethodName(String method) {
        if ("all".equalsIgnoreCase(method)) return true;
        
        for (String availableMethod : ReadabilityScoreCalculator.availableMethods) {
            if (availableMethod.equalsIgnoreCase(method)) {
                return true;
            }
        }
        
        return false;
    }
    
    private static ReaderInfo applyMethod(String method, int words, int sentences, int characters, int syllables, int polysyllables) {
        ReadabilityScoreCalculator calculator = ReadabilityScoreCalculator.makeCalculator(method);
        double result = calculator.findReadabilityScore(words, sentences, characters, syllables, polysyllables);
        ReaderInfo info = ReaderInfo.findReader(result);
        
        System.out.printf("%s: %s (about %s-year-olds)%n", calculator.name, MathUtils.floorTo(result, 2), info.getMaxAgeString());
        return info;
    }
    
    private static String readFile(String filepath) throws IOException {
        return Files.readString(Paths.get(filepath));
    }
}

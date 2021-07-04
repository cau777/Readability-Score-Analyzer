package readability;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordUtils {
    public static int countCharacters(String str) {
        int count = 0;
        
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            
            if (c != ' ') count++;
        }
        
        return count;
    }
    
    public static int countSyllables(String word) {
        return Math.max(1, findMatches(removeSuffix(word, "e"), "[aeiouyAEIOUY]+").length);
    }
    
    public static boolean isPolysyllable(String word) {
        return countSyllables(word) > 2;
    }
    
    public static boolean isPolysyllable(int syllableCount) {
        return syllableCount > 2;
    }
    
    public static String[] findMatches(String str, String regexp) {
        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(str);
        List<String> results = new ArrayList<>();
        
        while (matcher.find()) {
            String match = matcher.group();
            if (match.length() > 0) {
                results.add(match);
            }
        }
        
        return results.toArray(new String[0]);
    }
    
    public static String removeSuffix(String word, String suffix) {
        if (word.endsWith(suffix)) {
            return word.substring(0, word.length() - suffix.length());
        }
        return word;
    }
}

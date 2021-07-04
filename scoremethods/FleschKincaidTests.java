package readability.scoremethods;

public class FleschKincaidTests extends ReadabilityScoreCalculator{
    public FleschKincaidTests() {
        super("Flesch–Kincaid readability tests");
    }
    
    @Override
    public double findReadabilityScore(int words, int sentences, int characters, int syllables, int polysyllables) {
        return 0.39 * (words / (double) sentences) + 11.8 * (syllables / (double) words) - 15.59;
    }
}

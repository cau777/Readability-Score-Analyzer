package readability.scoremethods;

public class ColemanLiauIndex extends ReadabilityScoreCalculator {
    public ColemanLiauIndex() {
        super("Coleman–Liau index");
    }
    
    @Override
    public double findReadabilityScore(int words, int sentences, int characters, int syllables, int polysyllables) {
        double l = characters * 100.0 / (double) words;
        double s = sentences * 100.0 / (double) words;
        return 0.0588 * l - 0.296 * s - 15.8;
    }
}

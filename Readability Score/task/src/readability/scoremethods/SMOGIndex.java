package readability.scoremethods;

public class SMOGIndex extends ReadabilityScoreCalculator{
    public SMOGIndex() {
        super("Simple Measure of Gobbledygook");
    }
    
    @Override
    public double findReadabilityScore(int words, int sentences, int characters, int syllables, int polysyllables) {
        return 1.043 * Math.sqrt(polysyllables * (30.0 / sentences)) + 3.1291;
    }
}

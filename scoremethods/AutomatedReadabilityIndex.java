package readability.scoremethods;

import readability.ReaderInfo;

public class AutomatedReadabilityIndex extends  ReadabilityScoreCalculator{
    public AutomatedReadabilityIndex() {
        super("Automated Readability Index");
    }
    
    @Override
    public double findReadabilityScore(int words, int sentences, int characters, int syllables, int polysyllables) {
        return 4.71 * (characters / (double) words) + 0.5 * (words / (double) sentences) - 21.43;
    }
}

package readability.scoremethods;

public abstract class ReadabilityScoreCalculator {
    public static final String[] availableMethods = {"ARI", "FK", "SMOG", "CL"};
    
    public String name;
    
    public ReadabilityScoreCalculator(String name) {
        this.name = name;
    }
    
    public abstract double findReadabilityScore(int words, int sentences, int characters, int syllables, int polysyllables);
    
    public static ReadabilityScoreCalculator makeCalculator(String method) {
        switch (method.toUpperCase()) {
            case "ARI":
                return new AutomatedReadabilityIndex();
            case "FK":
                return new FleschKincaidTests();
            case "SMOG":
                return new SMOGIndex();
            case "CL":
                return new ColemanLiauIndex();
            default:
                throw new IllegalArgumentException(method + " is not available");
        }
    }
}

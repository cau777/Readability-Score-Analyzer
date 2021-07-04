package readability;

public class ReaderInfo {
    public int minAge;
    public int maxAge;
    public String occupation;
    
    private static final ReaderInfo[] readers = new ReaderInfo[] {
            new ReaderInfo(5, 6, "Kindergarten"),
            new ReaderInfo(6, 7, "First/Second Grade"),
            new ReaderInfo(7, 9, "Third Grade"),
            new ReaderInfo(9, 10, "Fourth Grade"),
            new ReaderInfo(10, 11, "Fifth Grade"),
            new ReaderInfo(11, 12, "Sixth Grade"),
            new ReaderInfo(12, 13, "Seventh Grade"),
            new ReaderInfo(13, 14, "Eighth Grade"),
            new ReaderInfo(14, 15, "Ninth Grade"),
            new ReaderInfo(15, 16, "Tenth Grade"),
            new ReaderInfo(16, 17, "Eleventh Grade"),
            new ReaderInfo(17, 18, "Twelfth grade"),
            new ReaderInfo(18, 24, "College student"),
            new ReaderInfo(24, "Professor")
    };
    
    public ReaderInfo() {
    }
    
    public ReaderInfo(int minAge, String occupation) {
        this.minAge = minAge;
        this.maxAge = minAge;
        this.occupation = occupation;
    }
    
    public ReaderInfo(int minAge, int maxAge, String occupation) {
        this.minAge = minAge;
        this.maxAge = maxAge;
        this.occupation = occupation;
    }
    
    public String getMaxAgeString() {
        if (maxAge == minAge)
            return maxAge + "+";
        else
            return maxAge + "";
    }
    
    public static ReaderInfo findReader(double readabilityScore) {
        int roundedScore = (int) MathUtils.roundTo(readabilityScore, 0);
        return readers[MathUtils.clamp(roundedScore - 1, 0, readers.length)];
    }
}

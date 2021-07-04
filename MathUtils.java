package readability;

public class MathUtils {
    public static double ceilTo(double num, int decimalPlaces) {
        double factor = Math.pow(10, decimalPlaces);
        return Math.ceil(num * factor) / factor;
    }
    
    public static double roundTo(double num, int decimalPlaces) {
        double factor = Math.pow(10, decimalPlaces);
        return Math.round(num * factor) / factor;
    }
    
    public static double floorTo(double num, int decimalPlaces) {
        double factor = Math.pow(10, decimalPlaces);
        return Math.floor(num * factor) / factor;
    }
    
    public static int clamp(int num, int min, int max) {
        return Math.min(max, Math.max(num, min));
    }
}

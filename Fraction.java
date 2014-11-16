/**
 * Created by keith for the second coursework assignment.
 */
public class Fraction {
    private int numerator;
    private int denominator;

    public Fraction(int num, int denom) {
        if (denom == 0) {
            System.out.println("Invalid fraction with denominator 0"); 
	    // this should use exceptions
            return;
        }
        // myGcd returns the largest number that goes into both num & denom
        int gcd = myGcd(num, denom);
   //     System.out.println("num: " + num + " denom: " + denom + " gcd: " + gcd);
        // and then we divide both top and bottom by the gcd.
        // it also puts any negative on the denom
        setNumerator(num / gcd);
        setDenominator(denom / gcd);
    }

	// simple print of a fraction
    @Override
    public String toString() {
        return "" + getNumerator() + '/' + getDenominator();
    		// will need to improve for then denominator is 1
    }

	//getters & setters
    public int getNumerator() {
        return numerator;
    }

    public void setNumerator(int num) {
        numerator = num;
    }

    public int getDenominator() {
        return denominator;
    }

    public void setDenominator(int num) {
        denominator = num;
    }

	//tests if firstFraction.equals(secondFraction)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
		// CAN ACTUALLY GET HERE???... delete??
        Fraction fraction = (Fraction) o;

        if (getDenominator() != fraction.getDenominator()) return false;
        if (getNumerator() != fraction.getNumerator()) return false;

        return true;
    }

	// NO IDEA WHAT THIS IS...
	@Override
    public int hashCode() {
        int result = numerator;
        result = 31 * result + denominator;
        return result;
    }
	
	//simple return of thisFraction.TIMES(theOtherFraction).
    public Fraction multiply(Fraction other) {

        int num = this.getNumerator() * other.getNumerator();
        int denom = this.getDenominator() * other.getDenominator();
        return new Fraction(num, denom);
    }

	// myGcd returns the largest number that goes into both num & denom
	// and is negative if need to adjust signs.
    private int myGcd(int a, int b) {
        while (b != 0) {
            int t = b;
            b = a % b;
            a = t;
        }
        return a;
    }
}

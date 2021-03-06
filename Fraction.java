/**
 * Fraction Class created by keith for the second coursework assignment.
 * Extended by Mark Kingsbury lking18.
 */
 
public class Fraction {
    private int numerator;
    private int denominator;

	//Constructors
	public Fraction() {
		this.numerator = 0;
		this.denominator = 1;
	}

    public Fraction(int num, int denom) {
        if (denom == 0) {
            System.out.println("Invalid fraction with denominator 0 - ERROR"); 
			return;
        }
        // myGcd returns the largest number that goes into both num & denom
        int gcd = myGcd(num, denom);
        // and then we divide both top and bottom by the gcd.
        // it also has the effect of resolving any top & bottom negatives 
        setNumerator(num / gcd);
        setDenominator(denom / gcd);
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

	// simple print of a fraction, improved for when denominator = 1
    @Override
    public String toString() {
    	if (this.getDenominator() != 1) {
	        return "" + getNumerator() + '/' + getDenominator();
    	} else {
    		return "" + getNumerator();
    	}	
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

	//tests equality of two fractions
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

	// UNSURE IF MEANT TO TOUCH THIS
	@Override
    public int hashCode() {
        int result = numerator;
        result = 31 * result + denominator;
        return result;
    }
	
	//Multiplication
    public Fraction multiply(Fraction other) {
        int num = this.getNumerator() * other.getNumerator();
        int denom = this.getDenominator() * other.getDenominator();
        return new Fraction(num, denom);
    }

	//Division
	public Fraction divide(Fraction other) {
		int num = this.getNumerator() * other.getDenominator();
		int denom = this.getDenominator() * other.getNumerator();
		return new Fraction(num, denom);
	}	
	
	//Addition
	public Fraction add(Fraction other) {
		int num = this.getNumerator() * other.getDenominator() 
				+ other.getNumerator() * this.getDenominator();	
		int denom = this.getDenominator() * other.getDenominator();
		return new Fraction(num, denom);
	}	
	
	//Negate - changes the sign of the fraction
	public Fraction negate() {
		int num = - this.getNumerator();
		return new Fraction(num, this.getDenominator());
	}
	
	//Subtraction
	public Fraction subtract(Fraction other) {
		Fraction result = this.add(other.negate());
		return result;
	}
	
	//Absolute Value
	public Fraction absValue() {
		int num = Math.abs(this.getNumerator());
		int denom = Math.abs(this.getDenominator());
		return new Fraction(num, denom);
	}
}

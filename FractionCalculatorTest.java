public class FractionCalculatorTest {

	public static void main(String[] args) {
		FractionCalculatorTest test = new FractionCalculatorTest();
		test.launch();
	}
	
	private void launch() {

		FractionCalculator test1 = new FractionCalculator();

		//Initial test of set-up structure
		System.out.println(" <<< Testing passing a fraction and getting it back >>> "); 
		Fraction startFraction = new Fraction(3,4);
		String testString = "a string";
		Fraction result = test1.evaluate(startFraction, testString);
		System.out.println("Result is: " + result.toString());
 
	}

}
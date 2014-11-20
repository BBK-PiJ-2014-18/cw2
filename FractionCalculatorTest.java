public class FractionCalculatorTest {

	public static void main(String[] args) {
		FractionCalculatorTest test = new FractionCalculatorTest();
		test.launch();
	}
	
	private void launch() {

		FractionCalculator testCalc = new FractionCalculator();

		//Initial test of set-up structure
		
		System.out.println(" <<< Testing passing a fraction and getting it back >>> "); 
		Fraction startFraction = new Fraction(1,10);
		String testString = "a string";
//		Fraction result = testCalc.evaluate(startFraction, testString);
//		System.out.println("Result is: " + result.toString());
   
		//Test ability to load first item from front of inputString
		//store it in the right place
		//and then remove from front to create new inputString 
	
		System.out.println(" <<< Testing find String Part and store >>> ");
		System.out.println();
		System.out.println("    [1] AN OPERATION (+, -, * or /)");
		testInputStringParts(testCalc, "+ 13/456 - 7/14 * 2/4");
		System.out.println("    [2] A FRACTION (digits/digits)");
		testInputStringParts(testCalc, "524/4265 - 7/14 * 2/4");
		System.out.println("    [3] A WHOLE NUMBER (digits)");
		testInputStringParts(testCalc, "8887 - 7/14 * 2/4");
		System.out.println("    [4] AN OTHER INSTRUCTION" + 
			 "(a, A, abs, n, N, neg, c, C, clear, q, Q, or quit");
		testInputStringParts(testCalc, "clear 8887 - 7/14 * 2/4");
		System.out.println("    [5] ANYTHING ELSE");
		testInputStringParts(testCalc, "CARROT 8887 - 7/14 * 2/4");
	

	}
	//???? do i really need to pass testCalc?????
	private void testInputStringParts(FractionCalculator testCalc, String testString) {
		testCalc.resetCalculator();
		Fraction startFraction = new Fraction(0,1);
		System.out.println();	
		System.out.println("Input string: " + testString);
		testCalc.evaluate(startFraction, testString);
		System.out.println("Remembered Operation: " + testCalc.getRememberedOperation());
		System.out.println("Next Fraction: " + testCalc.getNextFraction().toString());
		System.out.println();	
	}

}
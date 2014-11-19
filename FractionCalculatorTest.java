public class FractionCalculatorTest {

	public static void main(String[] args) {
		FractionCalculatorTest test = new FractionCalculatorTest();
		test.launch();
	}
	
	private void launch() {

		FractionCalculator test1 = new FractionCalculator();
		

		//Initial test of set-up structure
		
		System.out.println(" <<< Testing passing a fraction and getting it back >>> "); 
		Fraction startFraction = new Fraction(1,10);
		String testString = "a string";
//		Fraction result = test1.evaluate(startFraction, testString);
//		System.out.println("Result is: " + result.toString());
   
		//Test ability to load first operation or faction from front of inputString
		//and then remove from front to create new inputString 
	
		System.out.println(" <<< Testing find String Part >>> ");
/*		testString = "+ 3/4 - 7/14 * 2/4";
		result = test1.evaluate(startFraction, testString);
		testString = "3/4 - 7/14 * 2/4";
		result = test1.evaluate(startFraction, testString);
*/
		System.out.println(" <<< Testing find String Part and store >>> ");
		testString = "+ 13/456 - 7/14 * 2/4";
		Fraction result = test1.evaluate(startFraction, testString);
		testString = "524/4265 - 7/14 * 2/4";
		result = test1.evaluate(startFraction, testString);

	
	}

}
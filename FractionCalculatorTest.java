public class FractionCalculatorTest {

	public static void main(String[] args) {
		FractionCalculatorTest test = new FractionCalculatorTest();
		test.launch();
	}
	
	private void launch() {

		System.out.println();				
		System.out.println("==== TESTING MARK'S FRACTION CALCULATOR ====");
		System.out.println();
		System.out.println("==== RUN TESTS THAT SHOULD NOT HAVE HAD ERRORS ====");

		//1. test of: boolean isFraction(String nextItem)
		testStringIsFraction("1/2", true, "error 1.00");
		testStringIsFraction("-1/2", true, "error 1.01");
		testStringIsFraction("1/-2", true, "error 1.02");
		testStringIsFraction("1/-2", true, "error 1.03");
		testStringIsFraction("-1/-2", true, "error 1.04");

		testStringIsFraction("12/2", true, "error 1.05");
		testStringIsFraction("123/2", true, "error 1.06");
		testStringIsFraction("1/12", true, "error 1.07");
		testStringIsFraction("1/123", true, "error 1.08");

		testStringIsFraction("1/", false, "error 1.09");
		testStringIsFraction("/1", false, "error 1.10");
		
		testStringIsFraction("0/1", true, "error 1.11");
		testStringIsFraction("-0/1", true, "error 1.12");
		
		testStringIsFraction("1/0", false, "error 1.13");
		testStringIsFraction("1/-", false, "error 1.14");
		testStringIsFraction("1/-0", false, "error 1.15");
		testStringIsFraction("1/-01", false, "error 1.16");

		testStringIsFraction("x/1", false, "error 1.17");
		testStringIsFraction("1/x", false, "error 1.18");
		testStringIsFraction("123x45/1", false, "error 1.19");
		testStringIsFraction("1/123x45", false, "error 1.20");

		testStringIsFraction("10//11", false, "error 1.21");
		testStringIsFraction("123", false, "error 1.22");
		
		//2. test of: int getNumPart(String nextItem)
		
		testGetNumPart("1/2", 1, "error 2.01");
		testGetNumPart("123/2", 123, "error 2.02");
		testGetNumPart("-1/2", -1, "error 2.03");
		testGetNumPart("-123/2", -123, "error 2.04");
		
		//3. test of: int getDenomPart(String nextItem)
		
		testGetDenomPart("1/1", 1, "error 3.01");
		testGetDenomPart("1/123", 123, "error 3.02");
		testGetDenomPart("1/-1", -1, "error 3.03");
		testGetDenomPart("1/-123", -123, "error 3.04");
		
		//4. test of passing a valid fraction string to boolean checkNextItem(string)
				
		testCheckNextItemWhenFraction("1/2", true, new Fraction (1,2), "error 4.01");
		testCheckNextItemWhenFraction("0/2", true, new Fraction (0,2), "error 4.02");
		testCheckNextItemWhenFraction("-1/2", true, new Fraction (-1,2), "error 4.03");
		testCheckNextItemWhenFraction("1/123", true, new Fraction (1,123), "error 4.04");

		//5. test of: boolean isWholeNumber(String nextItem)
		
		testCheckIsWholeNumber("1", true, "error 5.01");
		testCheckIsWholeNumber("-1", true, "error 5.02");
		testCheckIsWholeNumber("123", true, "error 5.03");
		testCheckIsWholeNumber("-123", true, "error 5.04");
		testCheckIsWholeNumber("x", false, "error 5.05");
		testCheckIsWholeNumber("x123", false, "error 5.06");
		testCheckIsWholeNumber("123x", false, "error 5.07");
		
		//6. test of: boolean isOtherInstruction(String nextItem)
		
		checkIsOtherInstruction("n", true, "error 6.01");
		checkIsOtherInstruction("N", true, "error 6.02");
		checkIsOtherInstruction("neg", true, "error 6.03");
		checkIsOtherInstruction("a", true, "error 6.04");
		checkIsOtherInstruction("A", true, "error 6.05");
		checkIsOtherInstruction("abs", true, "error 6.06");
		checkIsOtherInstruction("c", true, "error 6.07");
		checkIsOtherInstruction("C", true, "error 6.08");
		checkIsOtherInstruction("clear", true, "error 6.09");
		checkIsOtherInstruction("q", true, "error 6.10");
		checkIsOtherInstruction("Q", true, "error 6.11");
		checkIsOtherInstruction("quit", true, "error 6.12");
		checkIsOtherInstruction("x", false, "error 6.13");
		
		//7. test of: char makeShortOtherInstruction(String nextItem) 

		checkMakeShortOI("Quit", 'q', "error 7.01");
		checkMakeShortOI("Q", 'q', "error 7.02");
		
		//8. test String getStringPart(String)
	
		checkGetStringPart("1/2 + 1/4 + 3/4", "+ 1/4 + 3/4", "error 8.01"); 

		//9. test full strings on evaluate method only with +
		testFullString("1/19 + 2/19 + 3/19", new Fraction(6,19),"error 9.01");	
		testFullString("1/2 + 2 + 1/2", new Fraction(3,1),"error 9.02");	
		testFullString("3 + 1/2 + 1/2", new Fraction(4,1),"error 9.03");
		testFullString("-1/2 + 1/2 + 4", new Fraction(4,1),"error 9.04");			
		testFullString("1/2 + 1/2 + -4", new Fraction(-3,1),"error 9.05");			
		testFullString("1/2 + 1/-2 + 4", new Fraction(4,1),"error 9.06");			
		testFullString("1/2 + -1/2 + 4", new Fraction(4,1),"error 9.07");			
		testFullString("-1/2 + 1/2 + 4", new Fraction(4,1),"error 9.08");

		testFullString("-0/2 + 2/2 + 4", new Fraction(5,1),"error 9.09");
		testFullString("-1/2 + 1/2 + 0", new Fraction(0,1),"error 9.10");
		testFullString("0/2 + 1/2 + 4", new Fraction(9,2),"error 9.11");
		
		//10. test full strings on evaluate method only one fraction/numer entered
		testFullString("1/2", new Fraction(1,2),"error 10.01");		
		testFullString("1", new Fraction(1,1),"error 10.02");		
		testFullString("-1/2", new Fraction(-1,2),"error 10.03");		
		testFullString("-1", new Fraction(-1,1),"error 10.04");		
		testFullString("0", new Fraction(0,1),"error 10.05");
		testFullString("-0/2", new Fraction(0,1),"error 10.06");

		//11. test full strings on evaluate method when one fraction/numer entered
		// 		straight after another
		testFullString("1/2 8", new Fraction(8,1),"error 11.01");		
		testFullString("1 -8", new Fraction(-8,1),"error 11.02");		
		testFullString("1/2 1/4", new Fraction(1,4),"error 11.03");		
		testFullString("1/2 -1/4", new Fraction(-1,4),"error 11.04");		
		testFullString("0 1/2", new Fraction(1,2),"error 11.05");

		//12. test full strings including neg and abs actions
		testFullString("1/2 n + 1/2", new Fraction(0,1),"error 12.01");		
		testFullString("N 1/2 + 1/2", new Fraction(1,1),"error 12.02");		
		testFullString("1/2 + 1/2 neg", new Fraction(-1,1),"error 12.03");		
		testFullString("-1/2 + -1/2 a ", new Fraction(1,1),"error 12.04");		
		testFullString("a -1/2 + -1/2", new Fraction(-1,1),"error 12.05");
		testFullString("-1/2 A + 1/2", new Fraction(1,1),"error 12.06");
		testFullString("-1/2 + abs 1/2", new Fraction(1,1),"error 12.07");		
		
		//13. test full strings on evaluate method with all operations
		testFullString("1/2 - 1/2 + 3 * 1/2", new Fraction(3,2),"error 13.01");		
		testFullString("2/1 / 1/2 neg", new Fraction(-4,1),"error 13.02");		

		//14. replicating extended examples - first line
		testFullString("", new Fraction(0,1),"error 14.01");
		testFullString("1/2", new Fraction(1,2),"error 14.02");
		testFullString("1/2 -", new Fraction(1,2),"error 14.03");
		testFullString("1/2 - 3/4", new Fraction(-1,4),"error 14.04");
		testFullString("1/2 - 3/4 *", new Fraction(-1,4),"error 14.05");
		testFullString("1/2 - 3/4 * abs", new Fraction(1,4),"error 14.06");
				
		//15. replicating extended examples - first line
		testFullString("1/4 8", new Fraction(8,1),"error 15.01");
		testFullString("1/4 8 7/8", new Fraction(7,8),"error 15.02");
		testFullString("1/4 8 7/8 neg", new Fraction(-7,8),"error 15.03");
		testFullString("1/4 8 7/8 neg +", new Fraction(-7,8),"error 15.04");

		System.out.println();				
		System.out.println("==== END OF TESTS THAT SHOULD NOT HAVE HAD ERRORS ====");
		System.out.println();
	
		System.out.println("==== TESTING WE GET ERRORS ON STRING INPUT ====");
			
		//16. multiple spaces in input, or space at front should cause error
		System.out.println();
		System.out.println(" [1] MULTIPLE SPACES (want two errors)");
		testFullString("1/4  + 1/2", new Fraction(1,1),"error 16.01");
		testFullString(" 1/4 + 1/2", new Fraction(1,1),"error 16.02");
	
		//17. multiple operations should cause error
		System.out.println();
		System.out.println(" [2] MULTIPLE OPERATIONS (want four errors)");
		testFullString("1/4 + + 1/2", new Fraction(1,1),"error 17.01");
		testFullString("+ + 1/4 + 1/2", new Fraction(1,1),"error 17.02");
		testFullString("1/4 + * 1/2", new Fraction(1,1),"error 17.03");
		testFullString("+ * 1/4 + 1/2", new Fraction(1,1),"error 17.04");
	
		//18. any other invalid input should cause error
		System.out.println();
		System.out.println(" [4] INVALID INPUT (want three errors)");
		testFullString("MONKEY + 1/4 + 1/2", new Fraction(1,1),"error 18.01");
		testFullString("1/4 + monkey + 1/2", new Fraction(1,1),"error 18.02");
		testFullString("1/4 + 1/2 + monkey", new Fraction(1,1),"error 18.03");

		//19. denom as zero should cause errors
		System.out.println();
		System.out.println(" [4] DENOM AS ZERO (want one error)");		
		testFullString("1/4 + 2/0 + 1/2", new Fraction(1,1),"error 19.01");
	
		System.out.println();
		System.out.println("==== END OF TESTS  ====");
		System.out.println();
	}
	
	private void testStringIsFraction(String testString, boolean answer, String msg) {
		FractionCalculator testCalc = new FractionCalculator();
		boolean result = testCalc.isFraction(testString);
		if (result != answer) {
			System.out.println("ERROR: " + msg);
		}	
	}
	
	private void testGetNumPart(String testString, int answer, String msg) {
		FractionCalculator testCalc = new FractionCalculator();
		int result = testCalc.getNumPart(testString);
		if (result != answer) {
			System.out.println("ERROR: " + msg);
		}
	}
	
	private void testGetDenomPart(String testString, int answer, String msg) {
		FractionCalculator testCalc = new FractionCalculator();
		int result = testCalc.getDenomPart(testString);
		if (result != answer) {
			System.out.println("ERROR: " + msg);
		}
	}
	
	private void testCheckNextItemWhenFraction(String testString, boolean answer, 
		Fraction fractionStored, String msg) {
		FractionCalculator testCalc = new FractionCalculator();
		boolean result = testCalc.checkNextItem(testString);
		if (result != answer) {
			System.out.println("ERROR: " + msg);
		}
		if (!testCalc.getNextFraction().equals(fractionStored)) {
			System.out.println("ERROR: " + msg);
		}		
	}
	
	private void testCheckIsWholeNumber(String testString, boolean answer, String msg) {
		FractionCalculator testCalc = new FractionCalculator();
		boolean result = testCalc.isWholeNumber(testString);
		if (result != answer) {
			System.out.println("ERROR: " + msg);
		}
	}
	
	private void checkIsOtherInstruction(String testString, boolean answer, String msg) {
		FractionCalculator testCalc = new FractionCalculator();
		boolean result = testCalc.isOtherInstruction(testString);
		if (result != answer) {
			System.out.println("ERROR: " + msg);
		}
	}
	
	private void checkMakeShortOI(String testString, char answer, String msg) {
		FractionCalculator testCalc = new FractionCalculator();
		char result = testCalc.makeShortOtherInstruction(testString);
		if (result != answer) {
			System.out.println("ERROR: " + msg);
		}
	}
	
	private void checkGetStringPart(String testString, String answer, String msg) {
		FractionCalculator testCalc = new FractionCalculator();
		String result = testCalc.getStringPart(testString);
		if (!result.equals(answer)) {
			System.out.println("ERROR: " + msg);
		}
	}

	private void testFullString(String testString, Fraction answer, String msg) {
		FractionCalculator testCalc = new FractionCalculator();	
		Fraction startFraction = new Fraction(0,1);
		Fraction result = testCalc.evaluate(startFraction, testString);
		if (!result.equals(answer)) {
			System.out.println("ERROR: " + msg);
		}		
	}
}
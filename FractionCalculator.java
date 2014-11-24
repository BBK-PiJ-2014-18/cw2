import java.util.Scanner;

public class FractionCalculator {

	private Fraction calcValue;
	private Fraction nextFraction;
	private char rememberedOperation;
	private boolean quit;

	//constructor
	public FractionCalculator() {
		calcValue = new Fraction(0,1);
		nextFraction = null;
		rememberedOperation = ' ';
		quit = false;
	}
	
	//getters
	public Fraction getCalcValue() {
		return this.calcValue;
	}
	public Fraction getNextFraction() {
		return this.nextFraction;
	}
	public char getRememberedOperation() {
		return this.rememberedOperation;
	}
	public boolean getQuit() {
		return this.quit;
	}	
	
	//reset Calculator 
	public void resetCalculator() {
		this.calcValue = new Fraction(0,1);
		this.nextFraction = null;
		this.rememberedOperation = ' ';
		this.quit = false;
	}	

	public static void main (String[] args) {
		FractionCalculator fc = new FractionCalculator();
		fc.launch();
	}
	
	private void launch() {
		resetCalculator();
		System.out.println("Hello Mark.  Enter fractions to calculate.");
		System.out.print("> ");
		Fraction fraction = new Fraction(0,1);
		String inputString = "";
		Scanner sc = new Scanner(System.in);
		while (!quit) {
			if(sc.hasNextLine()) {
				inputString = sc.nextLine();
			} else {	
				quit = true;	
			}			
			fraction = evaluate(fraction, inputString);
			if(quit) {
				quitNow();
			} else {
				rememberedOperation = ' ';
				System.out.println("= " + fraction.toString());
				System.out.print("> ");
			}	
		}
	}	
	
	private void quitNow() {
		System.out.println("Goodbye");	
	}
	
	public Fraction evaluate(Fraction fraction, String inputString) {
		if(!inputString.equals("")) {
			calcValue = fraction;
			inputString = getStringPart(inputString);
			if(!inputString.equals("ERROR")) {
				processState();
				evaluate(calcValue, inputString);
			} else {
				System.out.println("ERROR");
				resetCalculator();
				inputString = "";
			}	
		}
		return calcValue;
	}

	private void processState() {
		//if no operation remembered, set calcValue to nextFraction
		if(rememberedOperation == ' ' && nextFraction != null) {
			calcValue = nextFraction;
			nextFraction = null;
			return;
		}		
		//if operation and fraction present, do operation
		if(rememberedOperation != ' ' && nextFraction != null) {
			doOperation();
			nextFraction = null;
			rememberedOperation = ' ';						
		}		
	}

	private void doOperation() {
		if(rememberedOperation == '+') {
			calcValue = calcValue.add(nextFraction);
		}
		if(rememberedOperation == '-') {
			calcValue = calcValue.subtract(nextFraction);
		}
		if(rememberedOperation == '*') {
			calcValue = calcValue.multiply(nextFraction);
		}
		if(rememberedOperation == '/') {
			calcValue = calcValue.divide(nextFraction);
		}	
	}

	/**
	*	load the next operation or fraction from the string
	*	into rememberedOperation or nextFraction then
	*	remove that item from the front of the string and 
	*	return the remainder of the string
	*/
	
	public String getStringPart(String inputString) {
		String result = "";
		int i = 0;
		while(i < inputString.length() && inputString.charAt(i) != ' ') {
			i++;
		}	
		//nextItem is the next operation or fraction in the string
		String nextItem = inputString.substring(0,i);
		//result is input string with nextItem & space removed from front
		if(i < inputString.length()-1) {
			result = inputString.substring(i+1, inputString.length()); 
		} else {
			result = "";
		}
		//check what next item is and store it in nextFraction or rememberedOperation
		Boolean nextItemOK = checkNextItem(nextItem);
		if (!nextItemOK) {
			result = "ERROR";
		}		
		//return the inputString minus the nextItem
		return result;
	}

	//check and process
	public boolean checkNextItem(String nextItem) {

		//if nextItem blank (due to multiple spaces in input) return false
		if(nextItem.equals("")) {
			System.out.print("Multiple spaces present - ");
			return false;
		}
		if(nextItem.length() == 1) {
			char c = nextItem.charAt(0);
			if(c == '+' || c == '-'	|| c == '*' || c == '/') {
				if(rememberedOperation == ' ') {
					rememberedOperation = (char) nextItem.charAt(0);
					return true;
				} else {
					//already have an operation remembered
					System.out.print("Multiple operations - ");
					return false;
				}			
			}
		}	
		// if nextItem contains "/" store it as nextFraction & return true
		if(isFraction(nextItem)) {
			int num = getNumPart(nextItem);
			int denom = getDenomPart(nextItem);
			nextFraction = new Fraction(num, denom);
			return true;			
		}
		// if nextItem is a whole number store it as nextFraction & return true
		if(isWholeNumber(nextItem)) {
			int num = Integer.parseInt(nextItem);
			int denom = 1;
			nextFraction = new Fraction(num, denom);
			return true;			
		}	
		if(isOtherInstruction(nextItem)) {
			char shortOtherInstruction = makeShortOtherInstruction(nextItem);
			processShortOtherInstruction(shortOtherInstruction);
			return true;
		}	
				// if none of above check out ok, return false - we have invalid input
		System.out.print("Input not allowed - ");
		return false;	
	}

	private void processShortOtherInstruction(char instruction) {
		if(instruction == 'n') {
			calcValue = calcValue.negate();
		}
		if(instruction == 'a') {
			calcValue = calcValue.absValue();
		}
		if(instruction == 'c') {
			resetCalculator();
		}		
		if(instruction == 'q') {
			quit = true;
		}
	}

	//tested
	public boolean isFraction(String nextItem) {
		int countDivideSigns = 0;
		int positionOfDivideSign = 0;
		//check only and only one divide sign		
		for (int i = 0 ; i < nextItem.length() ; i++ ) {
			if(nextItem.charAt(i) == '/') {
				positionOfDivideSign = i;
				countDivideSigns ++;
			}	
		}
		if(countDivideSigns != 1) {
			return false;
		}
		//check numerator part is digits
		char c = nextItem.charAt(0);
		if(!Character.isDigit(c) && c != '-') {
			return false;
		}	 
		for (int i = 1 ; i < positionOfDivideSign ; i++) {
			c = (char) nextItem.charAt(i);
			if(!Character.isDigit(c)) {
				return false;
			}
		}
		//check denominator is not blank, does not start with zero & is digits 
		//can be +ve or -ve
		if(positionOfDivideSign + 1 >= nextItem.length()) {
			return false;
		}	
		c = nextItem.charAt(positionOfDivideSign + 1);
		char d;
		if(positionOfDivideSign + 2 < nextItem.length()) {
			d = nextItem.charAt(positionOfDivideSign + 2);
		} else {
			d = ' ';
		}	
		if(c == '0') {
			return false;
		}
		if(c == '-' && d == '0') {
			return false;
		}
		if(c == '-' && d == ' ') {
			return false;
		}
		if(!Character.isDigit(c) && c != '-') {
			return false;
		}
		for (int i = positionOfDivideSign + 2; i < nextItem.length() ; i++) {
			c = (char) nextItem.charAt(i);
			if(!Character.isDigit(c)) {
				return false;
			}
		}			
		return true;
	}

	//tested
	public int getNumPart(String nextItem) {		
		int result;
		int i = 0;
		while(nextItem.charAt(i) != '/') {
			i++;
		}
		String numPart = nextItem.substring(0,i);
		result = Integer.parseInt(numPart);
		return result;
	}

	//tested
	public int getDenomPart(String nextItem) {
		int result;
		int i = 0;
		while(nextItem.charAt(i) != '/') {
			i++;
		}
		String denomPart = nextItem.substring(i+1,nextItem.length());
		result = Integer.parseInt(denomPart);
		return result;
	}
	//tested
	public boolean isWholeNumber(String nextItem) {
		boolean result = true;
		char c = (char) nextItem.charAt(0);
		if(c != '-' && !Character.isDigit(c)) {
			result = false;
		}	
		for (int i = 1; i < nextItem.length() ; i ++) {
			c = (char) nextItem.charAt(i);
			if(!Character.isDigit(c)) {
				result = false;
			}
		} 
		return result;
	}
	//tested
	public boolean isOtherInstruction(String nextItem) {
		if(nextItem.equals("a") || nextItem.equals("A") || nextItem.equals("abs") 
			|| nextItem.equals("n") || nextItem.equals("N") || nextItem.equals("neg")
			|| nextItem.equals("c") || nextItem.equals("C") || nextItem.equals("clear")
			|| nextItem.equals("q") || nextItem.equals("Q") || nextItem.equals("quit")) {
			return true;
		} else {
			return false;
		}		
	}

	//tested
	//make other instruction lowercase first letter	
	public char makeShortOtherInstruction(String nextItem) {
		nextItem = nextItem.toLowerCase();
		return nextItem.charAt(0);
	}
}
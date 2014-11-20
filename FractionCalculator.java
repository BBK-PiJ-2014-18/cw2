public class FractionCalculator {

	private Fraction calcValue;
	private Fraction nextFraction;
	private String rememberedOperation;

	//constructor
	public FractionCalculator() {
		calcValue = null;
		nextFraction = null;
		rememberedOperation = null;
	}
		
	//the evaluate method
	public Fraction evaluate(Fraction fraction, String inputString) {
		
		calcValue = fraction;
		System.out.println();
		System.out.println("Input String starts as: " + inputString);
		inputString = getStringPart(inputString);
		System.out.println("Input String ends as: " + inputString);	
		System.out.println("Remembered Operation is: " + rememberedOperation);
		if(nextFraction != null) {
			System.out.println("Next Fraction is: " + nextFraction.toString());
		} else {
			System.out.println("Next Fraction is empty");
		}
		System.out.println();
		return calcValue;

	}

	/**
	*	load the next operation or fraction from the string
	*	into rememberedOperation or nextFraction then
	*	remove that item from the front of the string and 
	*	return the remainder of the string
	*/
	
	private String getStringPart(String inputString) {
	
		String result = "";
		int i = 0;
		while(inputString.charAt(i) != ' ' && i < inputString.length()) {
			i++;
		}	
		//nextItem is the next operation or fraction in the string
		String nextItem = inputString.substring(0,i);
		//result is input string with nextItem & space removed from front
		result = inputString.substring(i+1, inputString.length()); 
		//check what next item is and store it in nextFraction or rememberedOperation
		Boolean nextItemCheck = checkNextItem(nextItem);
		
		//return the inputString minus the nextItem
		return result;
	}

	//THIS IS MESSY... WHY NEED TO WORK WITH CHAR RATHER THAN STR?? str.EQUALS !! DUH.
	private boolean checkNextItem(String nextItem) {
	
		if(nextItem.length() == 1) {
			//if nextItem is an operation store it as that & return true
			if(nextItem.charAt(0) == '+' || nextItem.charAt(0) == '-' 
					|| nextItem.charAt(0) == '*' || nextItem.charAt(0) == '/') {
				rememberedOperation = nextItem;
				return true;
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
			
		// add more.....
		
		
		// if none of above check out ok, return false - we have invalid input
		return false;	
	}

	private boolean isFraction(String nextItem) {
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
		boolean result = true;
		for (int i = 0 ; i < positionOfDivideSign ; i++) {
			char c = (char) nextItem.charAt(i);
			if(!Character.isDigit(c)) {
				result = false;
			}
		}
		//check denominator part is digits
		for (int i = positionOfDivideSign + 1; i < nextItem.length() ; i++) {
		
			char c = (char) nextItem.charAt(i);
			if(!Character.isDigit(c)) {
				result = false;
			}
		}			
		return result;
	}

	private int getNumPart(String nextItem) {		
		int result;
		int i = 0;
		while(nextItem.charAt(i) != '/') {
			i++;
		}
		String numPart = nextItem.substring(0,i);
		result = Integer.parseInt(numPart);
		return result;
	}

	private int getDenomPart(String nextItem) {
		int result;
		int i = 0;
		while(nextItem.charAt(i) != '/') {
			i++;
		}
		String numPart = nextItem.substring(i+1,nextItem.length());
		result = Integer.parseInt(numPart);
		return result;
	}
	
	private boolean isWholeNumber(String nextItem) {
		boolean result = true;
		for (int i = 0 ; i < nextItem.length() ; i ++) {
			char c = (char) nextItem.charAt(i);
			if(!Character.isDigit(c)) {
				result = false;
			}
		} 
		return result;
	}
	

}
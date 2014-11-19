/**
 * Test File for Fraction. Created by keith for the second coursework assignment.
 * Extended by Mark Kingsbury lking18.
 */
 
public class FractionTest {
    public static void main(String[] args) {

        // test divide by zero - should print an error (??and exit)
        new Fraction(1, 0);
       
        // test equals - only error test 5 should fail
		test(new Fraction(1, 2),new Fraction(1, 2),"error test 1");
		test(new Fraction(1, 2),new Fraction(3, 6),"error test 2");
		test(new Fraction(-1, 2),new Fraction(1, -2),"error test 3");
		test(new Fraction(-1, -2),new Fraction(1, 2),"error test 4");
		test(new Fraction(4, -8),new Fraction(1, 2),"error test 5");
		
		// test multiply - this should pass (simple)
		Fraction f = new Fraction(3,10);
		Fraction g = new Fraction(1,2);
		Fraction h = new Fraction(3,5);
		if (!f.equals(g.multiply(h))) System.out.println("Multiply failed (NOT OK)");        
        // test multiply - this should fail (simple)
        f= new Fraction(4,10);
        g = new Fraction(1,2);
        h = new Fraction (3,5);
        if (!f.equals(g.multiply(h))) System.out.println("Multiply failed (OK!)");
        // test multiply - this should pass (test gcd & negs done in Fraction work))
        f= new Fraction(30,-100);
        g = new Fraction(-1,2);
        h = new Fraction (-6,-10);
        if (!f.equals(g.multiply(h))) System.out.println("Multiply failed (NOT OK)");
 
        //exploring Fraction (incl myGcd/negatives) & to.String output
        System.out.println (">>>> Exploring Fraction <<<<");
		System.out.println ("3/10 =\t" + new Fraction(3, 10).toString());
	   	System.out.println ("5/10 =\t" + new Fraction(5, 10).toString());
    	System.out.println ("13/10 =\t" + new Fraction(13, 10).toString());
	   	System.out.println ("3/1 =\t" + new Fraction(3, 1).toString());
        System.out.println ("6/24 =\t" + new Fraction(6, 24).toString());
        System.out.println ("6/12 =\t" + new Fraction(6, 12).toString());
    	System.out.println ("9/12 =\t" + new Fraction(9, 12).toString());
		System.out.println ("4/2 =\t" + new Fraction(4, 2).toString());
    	System.out.println ("-1/2 =\t" + new Fraction(-1, 2).toString());
    	System.out.println ("1/-2 =\t" + new Fraction(1,-2).toString());
    	System.out.println ("-1/-2 =\t" + new Fraction(-1,-2).toString());

		// test divide - this should pass (simple)
		System.out.println (">>>> Testing Divide <<<<");
		f = new Fraction(3,4);
		g = new Fraction(3,10);
		h = new Fraction(2,5);
		System.out.println(g.toString() + " / " + h.toString() + " = " 
				+ g.divide(h).toString());
		if (!f.equals(g.divide(h))) System.out.println("Divide failed (NOT OK)");
		//test divide - this should fail (simple)
		f = new Fraction(1,7);
		g = new Fraction(3,10);
		h = new Fraction(2,5);
		System.out.println(g.toString() + " / " + h.toString() + " = " 
				+ g.divide(h).toString());
		if (!f.equals(g.divide(h))) System.out.println("Divide failed (OK!)");
        // test divide - this should pass (test gcd & negs done in Fraction work))
        f= new Fraction(3,-4);
        g = new Fraction(-30,100);
        h = new Fraction (-4,-10);
 		System.out.println(g.toString() + " / " + h.toString() + " = " 
				+ g.divide(h).toString());
 		if (!f.equals(g.divide(h))) System.out.println("Divide failed (NOT OK)");
 		
		
		
		
		

	}

    static void test(Fraction f1, Fraction f2, String msg){
    	if (! f1.equals(f2))
		System.out.println(msg);
    }
}

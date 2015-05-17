"We were expecting you to use a unit testing framework for the tests.
Use javadoc for all methods.
Avoid code such as
public boolean isOtherInstruction(String nextItem) { if(nextItem.equals(""a"") || nextItem.equals(""A"") || nextItem.equals(""abs"")  || nextItem.equals(""n"") || nextItem.equals(""N"") || nextItem.equals(""neg"") || nextItem.equals(""c"") || nextItem.equals(""C"") || nextItem.equals(""clear"") || nextItem.equals(""q"") || nextItem.equals(""Q"") || nextItem.equals(""quit"")) { return true; } else { return false; }
use sets instead and always try and avoid the pattern
if (expr) return true; else return false;
almost always you can just return expr;
 "

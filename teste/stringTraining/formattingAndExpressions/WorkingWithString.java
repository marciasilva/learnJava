package formattingAndExpressions;

import java.util.StringJoiner;

public class WorkingWithString {

	/*
	 * Specify string to separate values optionally specify start/end strings add
	 * values and retrieve result string
	 */
	public static void creatingAStringComposing() {
		StringJoiner sj1 = new StringJoiner(", ");
		addValues(sj1);
		print(sj1);

		sj1.add("new").add("value");
		print(sj1);

		StringJoiner sj2 = new StringJoiner(", ", "{", "}");
		addValues(sj2);
		print(sj2);

		// [alpha], [theta], ... [value]
		StringJoiner sj3 = new StringJoiner("], [", "[", "]");
		addValues(sj3);
		print(sj3);
	}
	
	public static void handleEmptyString() {
		
		StringJoiner sj1  = new StringJoiner(", ");
		sj1.setEmptyValue("EMPTY"); //Empty means add was never called
		print(sj1);
		
		StringJoiner sj2 = new StringJoiner(", ", "{", "}");
		sj2.add("");
		print(sj2);
	}
	
	/*% - format specifier start
	 * .1 precision : decimal places to display
	 * f conversion
	 * 
	 * %[argument index][flags][width][precision]conversion
	 * 
	 * d  - Decimal : 32 -32
	 * o  - Octal : 32 - 40
	 * xX - Hex : 32 -20
	 * f  - Floating Point : 123.0 - 123.000000
	 * eE - Scientific Notation : 123.0 - 1.230000e+02
	 * s  - String : "Hello" - Hello
	 * */
	public static void handleStringWithFormat() {
		int david = 13;
		int dawson = 11;
		int dillon = 4;
		int gordon = 2;
		
		String sf = String.format("My nephews are %d, %d, %d and %d years old.", david, dawson, dillon, gordon);
		System.out.println(sf);
		
		double avgDiff = ((david - dawson) + (dawson - dillon) + (dillon -gordon)) / 3.0d;
		
		String avgInfo = String.format("The average age between each is %.1f years.", avgDiff);
		System.out.println(avgInfo);
		
	}
	/* format flags
	 * #      Include radix
	 * 0      Zero-padding
	 * -      Left justify
	 * ,      Include grouping separator
	 * space  Leave space for positive numbers
	 * +      Always shown sign
	 * (      Enclose negative values in parenthesis
	 * */
	
	public static void handleFormatFlag() {
		String s1 = String.format("%d", 32);
		String s2 = String.format("%o", 32);
		String s3 = String.format("%x", 32);
		String s4 = String.format("%#o", 32);
		System.out.println(s1);
		System.out.println(s2);
		System.out.println(s3);
		System.out.println(s4);
		
		s1 = String.format("W:%4d X:%d", 5, 235);
		s2 = String.format("W:%04d X:%04d", 5, 235);
		s3 = String.format("W:%-4d X:%-4d", 5, 235);
		s4 = String.format("W:%-4d X:%-4d", 487, 25);
		System.out.println(s1);
		System.out.println(s2);
		System.out.println(s3);
		System.out.println(s4);
		
		s1 = String.format("%,d", 1234567);
		s2 = String.format("%,.2f", 1234567.0);
		System.out.println(s1);
		System.out.println(s2);	
		
		s3 = String.format("% d", 123);
		s4 = String.format("% d", -456);
		System.out.println(s3);
		System.out.println(s4);
		
		s3 = String.format("%+d", 123);
		s4 = String.format("%+d", -456);
		System.out.println(s3);
		System.out.println(s4);
		
		s1 = String.format("% (d", 123);		
		s2 = String.format("%(d", -987);
		System.out.println(s1);
		System.out.println(s2);
	}

	private static void addValues(StringJoiner sj) {
		sj.add("alpha");
		sj.add("theta");
		sj.add("gamma");
	}

	private static void print(StringJoiner sj) {
		String theResult = sj.toString();
		System.out.println(theResult);
	}

}

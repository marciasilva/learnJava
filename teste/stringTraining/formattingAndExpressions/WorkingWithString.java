package formattingAndExpressions;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Formatter;

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

		StringJoiner sj1 = new StringJoiner(", ");
		sj1.setEmptyValue("EMPTY"); // Empty means add was never called
		print(sj1);

		StringJoiner sj2 = new StringJoiner(", ", "{", "}");
		sj2.add("");
		print(sj2);
	}

	/*
	 * % - format specifier start .1 precision : decimal places to display f
	 * conversion
	 * 
	 * %[argument index][flags][width][precision]conversion
	 * 
	 * d - Decimal : 32 -32 o - Octal : 32 - 40 xX - Hex : 32 -20 f - Floating Point
	 * : 123.0 - 123.000000 eE - Scientific Notation : 123.0 - 1.230000e+02 s -
	 * String : "Hello" - Hello
	 */
	public static void handleStringWithFormat() throws IOException {
		int david = 13;
		int dawson = 11;
		int dillon = 4;
		int gordon = 2;

		String sf = String.format("My nephews are %d, %d, %d and %d years old.", david, dawson, dillon, gordon);
		System.out.println(sf);

		double avgDiff = ((david - dawson) + (dawson - dillon) + (dillon - gordon)) / 3.0d;

		String avgInfo = String.format("The average age between each is %.1f years.", avgDiff);
		System.out.println(avgInfo);

		doWrite(david, dawson, dillon, gordon, avgDiff);
	}
	/*
	 * format flags # Include radix 0 Zero-padding - Left justify , Include grouping
	 * separator space Leave space for positive numbers + Always shown sign (
	 * Enclose negative values in parenthesis
	 */

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

	private static void doWrite(int david, int dawson, int dillon, int gordon, double avgDiff) throws IOException {
		BufferedWriter writer = Files.newBufferedWriter(Paths.get("myFile.txt"));

		try (Formatter f = new Formatter(writer)) {
			f.format("My nephews are %d, %d, %d, and %d years old.", david, dawson, dillon, gordon);
			f.format("The average age between each is %.1f years.", avgDiff);
		}
	}

	public static void usingRegExp() {
		String s1 = "apple, apple and orange please";
		String s2 = s1.replace("ple", "ricot");
		System.out.println(s2);

		String s3 = s1.replaceAll("ple\\b", "ricot");
		System.out.println(s3);

		String[] parts = s1.split("\\b");
		for (String thePart : parts) {
			if (thePart.matches("\\w+")) {
				System.out.println(thePart);
			}
		}

		Pattern pattern = Pattern.compile("\\w+");
		Matcher matcher = pattern.matcher(s1);

		while (matcher.find()) {
			System.out.println(matcher.group());
		}
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

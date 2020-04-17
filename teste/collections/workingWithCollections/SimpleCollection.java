package workingWithCollections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.TreeSet;

public class SimpleCollection {
	/*
	 * ArrayList: A List backed by a resizable array. Efficient random access but
	 * inefficient random inserts
	 * 
	 * LinkedList: A list and queue backed by doubly-linked list. Efficient random
	 * insert but inefficient random access
	 * 
	 * HashSet: A set implemented as a hash table. Efficient general purpose usage
	 * at any size
	 * 
	 * TreeSet: A sortedSet implemented as a balanced binary tree. Members
	 * accessible in order but less efficient to modify and search than a hashSet
	 * 
	 * Map: basic map operations SortedMap: map whose keys are sorted HashMap:
	 * efficient general purpose Map implementation TreeMap: sortedMap implemented
	 * as a self-balancing tree. supports comparable and comparator sorting
	 */

	public static void usingArrayList() {
		ArrayList<String> list = new ArrayList<>();
		list.add("Foo");
		list.add("Bar");

		System.out.println("Elements: " + list.size());

		for (String o : list) {
			System.out.println(o);
		}

		LinkedList<String> list2 = new LinkedList<>();
		list2.add("Baz");
		list2.add("Boo");

		list.addAll(list2);

		for (String s : list) {
			System.out.println(s);
		}
	}

	public static void removingAMember() {
		ArrayList<MyClass> list = new ArrayList<>();
		MyClass v1 = new MyClass("v1", "abc");
		MyClass v2 = new MyClass("v2", "abc");
		MyClass v3 = new MyClass("v3", "abc");

		list.add(v1);
		list.add(v2);
		list.add(v3);

		/*
		 * Remove is based on equals method, as MyClass override it it will remove the
		 * first value that matches
		 */
		list.remove(v3);

		for (MyClass m : list) {
			System.out.println(m.getLabel());
		}
	}

	/*
	 * forEach - perform code for each member removeIf - remove element if test is
	 * true
	 * 
	 */

	public static void collectionsWithLambdas() {
		ArrayList<MyClass> list = new ArrayList<>();
		MyClass v1 = new MyClass("v1", "abc");
		MyClass v2 = new MyClass("v2", "xyz");
		MyClass v3 = new MyClass("v3", "abc");
		list.add(v1);
		list.add(v2);
		list.add(v3);
		/* parameter -> */
		list.forEach(m -> System.out.println(m.getLabel()));

		list.removeIf(m -> m.getValue().equals("abc"));
		list.forEach(m -> System.out.println(m.getLabel()));
	}

	public static void converting() {
		ArrayList<MyClass> list = new ArrayList<>();
		list.add(new MyClass("v1", "abc"));
		list.add(new MyClass("v2", "xyz"));
		list.add(new MyClass("v3", "abc"));

		Object[] objArray = list.toArray();
		System.out.println(objArray);
		MyClass[] a1 = list.toArray(new MyClass[0]);
		System.out.println(a1);
		MyClass[] a2 = new MyClass[3];
		MyClass[] a3 = list.toArray(a2);

		if (a2 == a3) {
			System.out.println("a2 & a3 reference the same array");
		}

		MyClass[] myArray = { new MyClass("v1", "abc"), new MyClass("v2", "xyz"), new MyClass("v3", "abc") };

		Collection<MyClass> colList = Arrays.asList(myArray);
		colList.forEach(c -> System.out.println(c.getLabel()));
	}

	public static void sorting() {
		TreeSet<MyClass> tree = new TreeSet<>();

		tree.add(new MyClass("2222", "ghi"));
		tree.add(new MyClass("3333", "abc"));
		tree.add(new MyClass("1111", "def"));
		// TreeSet is sorted
		// As MyClass compareTo method is based on value we have 3333, 1111 and 2222
		tree.forEach(m -> System.out.printf("%s | %s \n", m.getLabel(), m.getValue()));

		// Using MyComparator class where compare is based on label
		TreeSet<MyClass> tree2 = new TreeSet<>(new MyComparator());
		tree2.add(new MyClass("2222", "ghi"));
		tree2.add(new MyClass("3333", "abc"));
		tree2.add(new MyClass("1111", "def"));

		tree2.forEach(m -> System.out.printf("{%s | %s} ", m.getLabel(), m.getValue()));
	}

	public static void usingMap() {
		Map<String, String> map = new HashMap<>();
		map.put("2222", "ghi");
		map.put("3333", "abc");
		map.put("1111", "def");

		String s1 = map.get("3333");
		System.out.println(s1);
		String s2 = map.get("9999");
		System.out.println(s2);
		String s3 = map.getOrDefault("9999", "xyz");
		System.out.println(s3);

		map.forEach((k, v) -> System.out.println(k + " | " + v));
		
		map.replaceAll((k,v) -> v.toUpperCase());
		map.forEach((k, v) -> System.out.println(k + " | " + v));
		
		System.out.println("Sorted Map:");
		SortedMap <String, String> map2 = new TreeMap<>();
		map2.put("2222", "ghi");
		map2.put("3333", "abc");
		map2.put("1111", "def");
		map2.put("6666", "xyz");
		map2.put("4444", "mno");
		map2.put("5555", "pqr");
		
		map2.forEach((k,v) -> System.out.println(k + " | " + v));
		
		SortedMap<String, String> hMap = map2.headMap("3333");
		
		System.out.println("Head Map:"); //exclude key passed
		hMap.forEach((k,v) -> System.out.println(k + " | " + v));
		
		SortedMap<String, String> tMap = map2.tailMap("3333");
		System.out.println("Tail Map:"); //include key passed
		tMap.forEach((k,v) -> System.out.println(k + " | " + v));
	}
	
	
}

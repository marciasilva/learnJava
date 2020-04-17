package workingWithCollections;

import java.util.Comparator;

public class MyComparator implements Comparator<MyClass> {

	@Override
	public int compare(MyClass arg0, MyClass arg1) {
		return arg0.getLabel().compareToIgnoreCase(arg1.getLabel());
	}

}

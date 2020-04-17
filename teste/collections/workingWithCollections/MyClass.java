package workingWithCollections;

public class MyClass implements Comparable<MyClass> {
	
	private String label, value;

	public MyClass(String label, String value) {
		super();
		this.label = label;
		this.value = value;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	/*override equals. to look only into value */
	public boolean equals(Object o) {
		MyClass other = (MyClass) o;
		return value.equalsIgnoreCase(other.value);
	}

	@Override
	public int compareTo(MyClass other) {
		return value.compareToIgnoreCase(other.value);		
	}	
	

}

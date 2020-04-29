package structural.flyweight;


//Instances of Item will be the Flyweights
//everything is immutable
public class Item {
	
	private final String name;
	
	public Item(String name) {
		this.name = name;
	}
	
	public String toString() {
		return name;
	}

}

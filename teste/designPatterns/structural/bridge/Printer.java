package structural.bridge;

import java.util.List;

public abstract class Printer {
	
	//Printer does not nothing about formatter just the contract of the interface
	public String print(Formatter formatter) {
		return formatter.format(getHeader(), getDetails());		
	}
	
	abstract protected List<Detail> getDetails();
	abstract protected String getHeader();

}

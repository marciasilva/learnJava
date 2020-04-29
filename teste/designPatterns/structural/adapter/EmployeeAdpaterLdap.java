package structural.adapter;

import structural.adapter.model.Employee;
import structural.adapter.model.EmployeeLdap;

public class EmployeeAdpaterLdap implements Employee {

	private EmployeeLdap instance;

	public EmployeeAdpaterLdap(EmployeeLdap instance) {
		this.instance = instance;
	}

	@Override
	public String getId() {
		return instance.getCn();
	}

	@Override
	public String getFirstName() {
		return instance.getGivenName();
	}

	@Override
	public String getLastName() {
		return instance.getSurname();
	}

	@Override
	public String getEmail() {
		return instance.getMail();
	}
	
	//use toString to return the object on the same way as the other object
	public String toString() {
		return "ID: " + instance.getCn();
	}

}

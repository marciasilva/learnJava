package structural.adapter;

import structural.adapter.model.Employee;
import structural.adapter.model.EmployeeCSV;

public class EmployeeAdpaterCSV implements Employee {
	
	private EmployeeCSV instance;
	
	public EmployeeAdpaterCSV(EmployeeCSV instance) {
		this.instance = instance;
	}

	@Override
	public String getId() {
		return instance.getId() + "";
	}

	@Override
	public String getFirstName() {
		return instance.getFirstname();
	}

	@Override
	public String getLastName() {
		return instance.getLastname();
	}

	@Override
	public String getEmail() {
		return instance.getEmailAddress();
	}

}

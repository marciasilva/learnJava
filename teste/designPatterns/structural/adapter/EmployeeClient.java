package structural.adapter;

import java.util.ArrayList;
import java.util.List;

import structural.adapter.model.Employee;
import structural.adapter.model.EmployeeCSV;
import structural.adapter.model.EmployeeDB;
import structural.adapter.model.EmployeeLdap;

public class EmployeeClient {

	public List<Employee> getEmployeeList() {
		List<Employee> employees = new ArrayList<>();

		Employee employeeFromDB = new EmployeeDB("1234", "John", "Doe", "joe.doe@email.com");

		employees.add(employeeFromDB);

		// Will not work! This is where the adapter comes into play!
		// Employee employeeFromLdap = new EmployeeLdap("1234", "Doe", "Jane",
		// "jane.doe@email.com");

		EmployeeLdap employeeFromLdap = new EmployeeLdap("1234", "Doe", "Jane", "jane.doe@email.com");
		employees.add(new EmployeeAdpaterLdap(employeeFromLdap));

		EmployeeCSV employeeFromCSV = new EmployeeCSV("121,Sherlock,Holmes,sherlock@detective.com");
		employees.add(new EmployeeAdpaterCSV(employeeFromCSV));

		return employees;
	}

}

package structural.adapter;

import java.util.List;

import structural.adapter.model.Employee;

public class AdapterDemo {

	public static void main(String[] args) {
		EmployeeClient client = new EmployeeClient();
		
		List<Employee> employees = client.getEmployeeList();
		
		System.out.println(employees);
		System.out.println(employees.get(0).getFirstName());

	}

}

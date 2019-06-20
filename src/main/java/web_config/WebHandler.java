package web_config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;

import bom.Department;
import bom.Employee;
import services.DepartmentService;
import services.EmployeeService;


@SuppressWarnings("deprecation")
@ManagedBean
@ViewScoped
public class WebHandler {

	private Department department = new Department();
	private Employee employee = new Employee();
	
	@Inject
	private EmployeeService empService;
	
	@Inject 
	private DepartmentService depService;
	
	private List<Employee> employeeList = new ArrayList<>();
	
	private List<Department> departmentList = new ArrayList<>();
	
	//i dun understand well
	@PostConstruct
	public void init() {
		try {
			employeeList = empService.toBoms(empService.showAll());
			departmentList = depService.toBoms(depService.showAll());
			if (departmentList.size() > 0) {
				department = departmentList.get(0);
			}
		} catch (Throwable e) {
			System.out.println("Cause: " + e.getCause());
			System.out.println("Message: " + e.getMessage());
			System.out.println("Class: " + e.getClass());
			System.out.println("StackTrace: " + e.getStackTrace());
			throw e;
		}
	}

	public String addNewEmployee() {
		employee.setDepartment(depService.toEntity(department));
		empService.save(empService.toEntity(employee));
		employeeList = empService.toBoms(empService.showAll());
		return "index.xhtml?faces-redirect=true&includeViewParams=true";
	}
	
	public String updateEmployee() {
		employee.setDepartment(depService.toEntity(department));
		empService.update(empService.toEntity(employee));
		employeeList = empService.toBoms(empService.showAll());
		return "index.xhtml?faces-redirect=true&includeViewParams=true";
	}
	
	public String deleteEmployee(Employee employeeBOM) {
		empService.deleteEmployee(empService.toEntity(employeeBOM));
		employeeList = empService.toBoms(empService.showAll());
		return "index.xhtml?faces-redirect=true&includeViewParams=true";
	}
	
	public void editEmployee(Employee employeeBOM) {
		employee = employeeBOM;
	}
	
	public void changeDepartment(ValueChangeEvent dept) {
		department = depService
				.toBom(depService.findDepartmentById(Integer.parseInt(dept.getNewValue().toString())));
	}
	
	public void reverseEmployeeList() {
		Collections.reverse(employeeList);
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public List<Employee> getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(List<Employee> employeeList) {
		this.employeeList = employeeList;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public List<Department> getDepartmentList() {
		return departmentList;
	}

	public void setDepartmentList(List<Department> departmentList) {
		this.departmentList = departmentList;
	}
	
	
}

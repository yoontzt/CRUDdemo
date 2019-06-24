package web_config;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;

import bom.Department;
import bom.Employee;
import lombok.Getter;
import lombok.Setter;
import services.DepartmentService;
import services.EmployeeService;

@SuppressWarnings("deprecation")
@ManagedBean
@SessionScoped
public class WebHandler implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8495411679904641370L;
	private @Getter @Setter Department department = new Department();
	private @Getter @Setter Employee employee = new Employee();
	private @Getter @Setter Employee editEmployee = new Employee();
	private @Getter @Setter int id;

	@Inject
	private EmployeeService empService;

	@Inject
	private DepartmentService depService;

	private @Getter @Setter List<Employee> employeeList = new ArrayList<>();

	private @Getter @Setter List<Department> departmentList = new ArrayList<>();


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
		empService.addEmployee(employee);
		employeeList = empService.toBoms(empService.showAll());
		return "index.xhtml?faces-redirect=true&includeViewParams=true";
	}

	public String updateEmployee() {
		employee.setDepartment(depService.toEntity(department));
		empService.updateEmployee(employee);
		employeeList = empService.toBoms(empService.showAll());
		return "index.xhtml?faces-redirect=true&includeViewParams=true";
	}

	public String deleteEmployee(Employee employeeBOM) {
		empService.deleteEmployee(empService.toEntity(employeeBOM));
		employeeList = empService.toBoms(empService.showAll());
		return "index.xhtml?faces-redirect=true&includeViewParams=true";
	}

	public String viewEmployee(Employee emp) {
		setEmployee(emp);
		 setId(employee.getDepartment().getId());
			return "update.xhtml?faces-redirect=true&id="+id;
	}

	public void changeDepartment(ValueChangeEvent dept) {
		department = depService.toBom(depService.findDepartmentById(Integer.parseInt(dept.getNewValue().toString())));
	}
}

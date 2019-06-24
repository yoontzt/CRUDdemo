import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.modules.junit4.PowerMockRunner;

import bom.Department;
import bom.Employee;
import entites.DepartmentEntity;
import services.DepartmentService;
import services.EmployeeService;
import web_config.WebHandler;

@RunWith(PowerMockRunner.class)
public class WebHandlerTest {

	@InjectMocks
	WebHandler webHandler;

	@Mock
	DepartmentService depService;

	@Mock
	EmployeeService empService;

	@Test
	public void testAddNewEmployee_ShouldReturnPage_WhenWeAddSuccessful() {

		Department department = createDepartment();
		Employee employee = createEmployee();
		webHandler.setDepartment(department);
		webHandler.setEmployee(employee);

		Mockito.when(depService.toEntity(department)).thenReturn(createDepartmentEntity());

		String actual = webHandler.addNewEmployee();

		Mockito.verify(empService).addEmployee(employee);
		assertEquals("index.xhtml?faces-redirect=true&includeViewParams=true", actual);

	}

	@Test
	public void testUpdateEmployee_ShouldReturnPage_WhenUpdatedIsSuccessful() {

		Department department = createDepartment();
		Employee employee = createEmployee();
		webHandler.setDepartment(department);
		webHandler.setEmployee(employee);

		Mockito.when(depService.toEntity(department)).thenReturn(createDepartmentEntity());

		String actual = webHandler.updateEmployee();

		Mockito.verify(empService).updateEmployee(employee);
		assertEquals("index.xhtml?faces-redirect=true&includeViewParams=true", actual);

	}

	@Test
	public void testDeleteEmployee_ShouldReturnPage_WhenDeletedIsSuccessful() {
		Employee employee = createEmployee();
		webHandler.setEmployee(employee);
		String actual = webHandler.deleteEmployee(employee);

		assertEquals("index.xhtml?faces-redirect=true&includeViewParams=true", actual);

	}
	
	@Test
	public void testViewEmployee_ShouldReturnUpdatePage_WhenViewEmployeeisSuccessful() {
		Department department = createDepartment();
		Employee employee = createEmployee();
		webHandler.setDepartment(department);
		webHandler.setEmployee(employee);

		Mockito.when(depService.toEntity(department)).thenReturn(createDepartmentEntity());
		
		String actual = webHandler.viewEmployee(employee);
		int id = employee.getDepartment().getId();
		assertEquals("update.xhtml?faces-redirect=true&id="+id, actual);

	}
	

	private Employee createEmployee() {
		DepartmentEntity department = createDepartmentEntity();
		return new Employee(1, "Yoon", 20, "yoon@gmail.com", department);
	}

	private Department createDepartment() {

		return new Department(1, "ICT");
	}

	private DepartmentEntity createDepartmentEntity() {
		return new DepartmentEntity(1, "Yoon");
	}

}

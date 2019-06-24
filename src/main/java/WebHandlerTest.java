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
	public void testAddNewEmployee_ShouldReturnInNewPage_WhenWeAddSuccessful() {

		Department department = createDepartment();
		Employee employee = createEmployee();
		webHandler.setDepartment(department);
		webHandler.setEmployee(employee);

		Mockito.when(depService.toEntity(department)).thenReturn(createDepartmentEntity());

		String actual = webHandler.addNewEmployee();

		Mockito.verify(empService).addEmployee(employee);
		assertEquals("index.xhtml?faces-redirect=true&includeViewParams=true", actual);

	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddNewEmployee_ShouldThrowExveption_WhenFailedoAdd() {

		Department department = createDepartment();
		Employee employee = createEmployee();
		webHandler.setDepartment(department);
		webHandler.setEmployee(employee);

		Mockito.when(depService.toEntity(department)).thenThrow(new IllegalArgumentException());

		String actual = webHandler.addNewEmployee();

		Mockito.verify(empService).addEmployee(employee);
		assertEquals("index.xhtml?faces-redirect=true&includeViewParams=true", actual);

	}

	private Employee createEmployee() {

		return new Employee(1, "Yoon", 20, "yoon@gmail.com", null);
	}

	private Department createDepartment() {

		return new Department(1, "ICT");
	}

	private DepartmentEntity createDepartmentEntity() {
		return new DepartmentEntity(1, "Yoon");
	}

}

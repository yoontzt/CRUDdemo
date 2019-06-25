import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import javax.faces.event.ValueChangeEvent;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.modules.junit4.PowerMockRunner;

import bom.Department;
import bom.Employee;
import entites.DepartmentEntity;
import entites.EmployeeEntity;
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

	@Mock
	ValueChangeEvent valueChangeEvent;

	@Test
	public void testInit() {
		Department department = createDepartment();
		List<EmployeeEntity> employeeEntities = Arrays.asList(createEmployeeEntity());
		List<DepartmentEntity> departmentEntities = Arrays.asList(createDepartmentEntity());

		Mockito.when(empService.showAll()).thenReturn(employeeEntities);
		Mockito.when(empService.toBoms(employeeEntities)).thenReturn(Arrays.asList(createEmployee()));

		Mockito.when(depService.showAll()).thenReturn(departmentEntities);
		Mockito.when(depService.toBoms(departmentEntities)).thenReturn(Arrays.asList(createDepartment()));

		webHandler.init();

		assertEquals(department, webHandler.getDepartment());

	}

	@Test
	public void testInit_() {
		List<EmployeeEntity> employeeEntities = Arrays.asList(createEmployeeEntity());

		Mockito.when(empService.showAll()).thenReturn(employeeEntities);
		Mockito.when(empService.toBoms(employeeEntities)).thenReturn(Arrays.asList(createEmployee()));

		webHandler.init();

		assertEquals(new Department(), webHandler.getDepartment());

	}

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

		String actual = webHandler.updateEmployeeFromPage();

		Mockito.verify(empService).updateEmployee(employee);
		assertEquals("index.xhtml?faces-redirect=true&includeViewParams=true", actual);

	}

	@Test
	public void testDeleteEmployee_ShouldReturnPage_WhenDeletedIsSuccessful() {
		Employee employee = createEmployee();
		
		
		EmployeeEntity empEntity = createEmployeeEntity();
	
		
		Mockito.when(empService.toEntity(employee)).thenReturn(empEntity);
		
		
		
		String actual = webHandler.deleteEmployeeFromPage(employee);
		
		Mockito.verify(empService).deleteEmployee(empEntity);
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
		assertEquals("update.xhtml?faces-redirect=true&id=" + id, actual);

	}

	@Test
	public void testChangeDepartment() {
		
		// Initial Variables
		Department expected = createDepartment();
		DepartmentEntity departmentEntity = createDepartmentEntity();

		// Mock
		Mockito.when(valueChangeEvent.getNewValue()).thenReturn(1);
		Mockito.when(depService.findDepartmentById(1)).thenReturn(departmentEntity);
		Mockito.when(depService.toBom(departmentEntity)).thenReturn(createDepartment());


		// Call Function
		webHandler.changeDepartment(valueChangeEvent);
		// AssertEquals
		Department department = webHandler.getDepartment();
		assertEquals(expected, department);

		// Verify
	}
//create functions
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

	private EmployeeEntity createEmployeeEntity() {
		DepartmentEntity department = createDepartmentEntity();
		return new EmployeeEntity(1, "Yoon", 20, "yoon@gmail.com", department);
	}

}

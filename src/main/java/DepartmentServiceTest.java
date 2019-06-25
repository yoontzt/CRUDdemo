import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.powermock.modules.junit4.PowerMockRunner;

import bom.Department;
import entites.DepartmentEntity;
import services.DepartmentService;

@RunWith(PowerMockRunner.class)
public class DepartmentServiceTest {

	@InjectMocks
	DepartmentService departmentService;

	@Test
	public void testToEntity_ShouldReturnEntity_WhenBomIsGiven() {
		DepartmentEntity entity = createDepartmentEntity();
		Department bom = createDepartment();
		DepartmentEntity actual = departmentService.toEntity(bom);
		
		assertEquals(entity, actual);
	}
	@Test
	public void testToBom_ShouldReturnBom_WhenEntityIsGiven() {
		DepartmentEntity entity = createDepartmentEntity();
		Department bom = createDepartment();
		Department actual = departmentService.toBom(entity);
		
		assertEquals(bom, actual);
	}
	@Test
	public void testToEntity_ShouldReturnNull_WhenBomIsNull() {
		Department bom = null;
		DepartmentEntity actual = departmentService.toEntity(bom);
		assertEquals(null, actual);
	}

	private Department createDepartment() {
		// TODO Auto-generated method stub
		return new Department(1, "Yoon");
	}

	private DepartmentEntity createDepartmentEntity() {
		return new DepartmentEntity(1, "Yoon");
	}
	
	
}
package bom;

import entites.DepartmentEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Employee {

	private int id;
	private String name;
	private Integer age;
	private String email;
	private DepartmentEntity department;
}

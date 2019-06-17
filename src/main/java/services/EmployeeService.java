package services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import entites.EmployeeEntity;

@Stateless
public class EmployeeService extends GenericService<EmployeeEntity> {
	
	public List<EmployeeEntity> showAll(){
		TypedQuery<EmployeeEntity> q = em.createQuery("select e from EmployeeEntity e", EmployeeEntity.class);
		return q.getResultList();
	}
	
	public EmployeeEntity findById(int id) {
		EmployeeEntity newemp = new EmployeeEntity();
		newemp = em.find(EmployeeEntity.class, id);
		return newemp;
		
	}
	
	
	public void addEmployee(EmployeeEntity e) {
		EmployeeEntity newEntity = new EmployeeEntity();
		newEntity.setName(e.getName());
		newEntity.setAge(e.getAge());
		newEntity.setEmail(e.getEmail());
		this.save(newEntity);
	}

	public void updateEmployee(EmployeeEntity e) {
		EmployeeEntity newEntity = findById(e.getId());
		newEntity.setName(e.getName());
		newEntity.setAge(e.getAge());
		newEntity.setEmail(e.getEmail());
		this.update(newEntity);
		
	}
	
	public void deleteEmployee(EmployeeEntity e) {
		EmployeeEntity newEntity = findById(e.getId());
		this.remove(newEntity);
		
	}

	public void deleteEmployeebyId(int id) {
		EmployeeEntity newemp = findById(id);
		this.remove(newemp);
		
	}
	

	
	
}

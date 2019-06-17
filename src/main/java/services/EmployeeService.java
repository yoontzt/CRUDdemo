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

}

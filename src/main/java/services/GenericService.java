package services;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class GenericService<E> {
	
	@PersistenceContext(name="demo")
	EntityManager em;
	
	public void setEm(EntityManager em) {
		this.em=em;
	}
	
	public EntityManager getEm() {
		return em;
	}

}

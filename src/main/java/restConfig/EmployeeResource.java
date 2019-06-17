package restConfig;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import entites.EmployeeEntity;
import services.EmployeeService;

@Stateless
@Path("employee")
public class EmployeeResource {
	
	@EJB
	EmployeeService empService;
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public List<EmployeeEntity> showAll(){
		return empService.showAll();
	}
	
	@GET
	@Path("{EmployeeId}")
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	public EmployeeEntity read(@PathParam("EmployeeId") int id) {
		return empService.findById(id);
		
	}

}

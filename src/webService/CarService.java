package webService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dto.Car;

@Path("/")
public class CarService {
	
	@GET
	@Path("/car")
	@Produces(MediaType.APPLICATION_JSON)
	public Car getCar(){
		Car car = new Car();
		car.setBrand("Audi");
		car.setRegId(1662);
		return car;
	}

}

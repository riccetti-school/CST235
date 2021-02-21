package business;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import beans.Order;

@RequestScoped
@Path("/orders")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class OrdersRestService {

	@Inject
	private  OrdersBusinessInterface s;	
	
	@GET
	@Path("/getjson")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Order> getOrdersAsJson(){
		return s.getOrders();
	}
	
	@GET
	@Path("/getOrderById")
	@Produces(MediaType.APPLICATION_JSON)	
	public Order getOrderById(@Context UriInfo info) {
		String id = info.getQueryParameters().getFirst("id");
		
		if(id != null) {
			int idValue = Integer.valueOf(id);
			List<Order> orders = s.getOrders();
			
			for(Order o: orders) {
				if(o.getId()==idValue) {
					return o;
				}
			}
		}
		
		return new Order() {};
	}
	
	@GET
	@Path("/getOrderByName")
	@Produces(MediaType.APPLICATION_JSON)	
	public Order getOrderByName(@Context UriInfo info) {
		String name = info.getQueryParameters().getFirst("name");
		
		if(name != null) {
			
			List<Order> orders = s.getOrders();
			
			for(Order o: orders) {
				if(o.getProductName().equals(name)) {
					return o;
				}
			}
		}
		
		return new Order() {};
	}	
	
	@GET
	@Path("/getxml")
	@Produces(MediaType.APPLICATION_XML)
	public Order[] getOrdersAsXml() {
		List<Order> o = s.getOrders();
		Order[] orders = new Order[o.size()];
		orders = o.toArray(orders);
		return orders;
	}
	
	
}

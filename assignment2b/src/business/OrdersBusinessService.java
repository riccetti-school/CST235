package business;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;

import beans.Order;
import beans.Orders;

/**
 * Session Bean implementation class OrdersBusinessService
 */
@Stateless
@LocalBean
@Alternative
public class OrdersBusinessService implements OrdersBusinessInterface {

	private List<Order> orders;
	
    /**
     * Default constructor. 
     */
    public OrdersBusinessService() {
        // TODO Auto-generated constructor stub
    	orders = new Orders().getOrders();
    }

	@Override
	public void test() {
		// TODO Auto-generated method stub
		System.out.println("Hello from the OrdersBusinessService");
	}

	@Override
	public List<Order> getOrders() {
		// TODO Auto-generated method stub
		return orders;
	}

	@Override
	public void setOrders(List<Order> orders) {
		// TODO Auto-generated method stub
		orders = orders;
	}

}

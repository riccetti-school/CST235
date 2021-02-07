package business;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;

import beans.Order;
import beans.Orders;

/**
 * Session Bean implementation class AnotherOrdersBusinessService
 */
@Stateless
@LocalBean
@Alternative
public class AnotherOrdersBusinessService implements OrdersBusinessInterface {

	private List<Order> orders;
	
    /**
     * Default constructor. 
     */
    public AnotherOrdersBusinessService() {
        // TODO Auto-generated constructor stub
    	orders = new ArrayList<Order>() {
			{
				add(new Order("1A", "Another Product 1a", 1.0f, 5));
				add(new Order("2A", "Another Product 2a", 2.0f, 15));
				add(new Order("3A", "Another Product 3a", 3.0f, 51));
				add(new Order("4A", "Another Product 4a", 4.0f, 52));
				add(new Order("5A", "Another Product 5a", 13.0f, 52));
				add(new Order("6A", "Another Product 6a", 12.0f, 51));
				add(new Order("7A", "Another Product 7a", 11.0f, 55));
				add(new Order("8A", "Another Product 8a", 2.0f, 56));
				add(new Order("9A", "Another Product 9a", 1.0f, 57));
			}
		};
    }

	@Override
	public void test() {
		// TODO Auto-generated method stub
		System.out.println("Hello from the AnotherOrdersBusinessService");
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

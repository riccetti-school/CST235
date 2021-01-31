package beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class Orders {

	private List<Order> orders;

	public Orders() {
		orders = new ArrayList<Order>() {
			{
				add(new Order("1A", "Product 1a", 1.0f, 5));
				add(new Order("2A", "Product 2a", 2.0f, 15));
				add(new Order("3A", "Product 3a", 3.0f, 51));
				add(new Order("4A", "Product 4a", 4.0f, 52));
				add(new Order("5A", "Product 5a", 13.0f, 52));
				add(new Order("6A", "Product 6a", 12.0f, 51));
				add(new Order("7A", "Product 7a", 11.0f, 55));
				add(new Order("8A", "Product 8a", 2.0f, 56));
				add(new Order("9A", "Product 9a", 1.0f, 57));
			}
		};
	}
	
	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	
	
	
}

package business;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;
import javax.jms.*;

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
	
	
	@Resource(mappedName="java:/ConnectionFactory")
	private ConnectionFactory connectionFactory;
	
	@Resource(mappedName="java:/jms/queue/Order")
	private Queue queue;
	
	
    /**
     * Default constructor. 
     */
    public OrdersBusinessService() {
        // TODO Auto-generated constructor stub
    	orders = new ArrayList<Order>();
    }

	@Override
	public void test() {
		// TODO Auto-generated method stub
		System.out.println("Hello from the OrdersBusinessService");
	}

	@Override
	public List<Order> getOrders() {
		return new Orders().getOrders();
	}

	@Override
	public void setOrders(List<Order> orders) {
		// TODO Auto-generated method stub
		orders = orders;
	}
	
	@Override
	public void insertOrder(Order order) {
		new Orders().insertOrder(order);
	}
	
	@Override
	public void sendOrder(Order order) {
		
		
		// Send a Message for an Order
		try 
		{
			Connection connection = connectionFactory.createConnection();
			Session  session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			MessageProducer messageProducer = session.createProducer(queue);
			TextMessage message1 = session.createTextMessage();
			message1.setText("This is test message");
			messageProducer.send(message1);
			connection.close();
		} 
		catch (JMSException e) 
		{
			e.printStackTrace();
		}
				
	}

}

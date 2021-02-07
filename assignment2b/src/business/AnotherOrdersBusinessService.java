package business;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;

/**
 * Session Bean implementation class AnotherOrdersBusinessService
 */
@Stateless
@LocalBean
@Alternative
public class AnotherOrdersBusinessService implements OrdersBusinessInterface {

    /**
     * Default constructor. 
     */
    public AnotherOrdersBusinessService() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void test() {
		// TODO Auto-generated method stub
		System.out.println("Hello from the AnotherOrdersBusinessService");
	}

}

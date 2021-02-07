package business;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import beans.User;
import services.DataService;

/**
 * Session Bean implementation class RegistrationService
 */
@Stateless
@LocalBean
public class RegistrationService {

    /**
     * Default constructor. 
     */
    public RegistrationService() {
        // TODO Auto-generated constructor stub
    }

    public void save(User user) {
		// register this user
		DataService ds = new DataService();
		ds.save(user);    	
    }
    
}

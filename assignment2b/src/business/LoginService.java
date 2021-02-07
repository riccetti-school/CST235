package business;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import beans.User;
import services.DataService;

/**
 * Session Bean implementation class LoginService
 */
@Stateless
@LocalBean
public class LoginService {

    /**
     * Default constructor. 
     */
    public LoginService() {
        // TODO Auto-generated constructor stub
    }

    public boolean validate(User user) {
		DataService ds = new DataService();
		return ds.validate(user);    	
    }
    
}

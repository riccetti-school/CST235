package beans;

import java.io.Serializable;

import javax.annotation.ManagedBean;
import javax.faces.bean.ViewScoped;

@SuppressWarnings("deprecation")
@ManagedBean
@ViewScoped
public class User implements Serializable  {

	public String test = "HI";
	
	
	public String getTest() {
		return test;
	}


	public void setTest(String test) {
		this.test = test;
	}
	private String firstName;
	private String lastName;
	

	public User() {

		this.setFirstName("firstName");
		this.setLastName("lastName");
	}
	
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}
